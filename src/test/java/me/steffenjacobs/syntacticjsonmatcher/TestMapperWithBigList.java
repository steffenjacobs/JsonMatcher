package me.steffenjacobs.syntacticjsonmatcher;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.steffenjacobs.syntacticjsonmatcher.domain.MappingDTO;
import me.steffenjacobs.syntacticjsonmatcher.service.PrintingService;

/** @author Steffen Jacobs */
public class TestMapperWithBigList {

	private static final Logger LOG = LoggerFactory.getLogger(TestMapperWithBigList.class);

	private static final boolean SHOW_JSON = false;
	private static final boolean PRINT = false;
	private static final boolean PRINT_ERRORS = true;
	/** More settings: see {@link DebugSettings} */

	private static final Map<String, List<String>> allowedMappings = new HashMap<>();
	private static final PrintingService printingService = new PrintingService();

	private static final double MIN_CONFIDENCE = .9999;

	private static final double MS_PER_PERMUTATION = 29400d / 448900d;

	@BeforeClass
	public static void initAllowedMappings() {
		List<String> allowedTemperatures = Arrays.asList("t", "tp", "temp", "temperature", "temperatura", "temperatur", "Temperatur");
		List<String> allowedHumidities = Arrays.asList("h", "hm", "hum", "humidity", "Luftfeuchtigkeit", "Luftfeuchte", "luftfeuchte", "humedad", "humidness");
		List<String> allowedPressures = Arrays.asList("pressure", "pressione", "druck", "Luftdruck", "pr", "pre", "p", "pres", "air pressure");
		List<String> allowedCo2 = Arrays.asList("co2", "CO2", "co2 value", "carbon dioxide");
		List<String> allowedLights = Arrays.asList("light", "light level", "brightness", "Helligkeit", "luminance");

		for (String temp : allowedTemperatures) {
			allowedMappings.put(temp, allowedTemperatures);
		}

		for (String hum : allowedHumidities) {
			allowedMappings.put(hum, allowedHumidities);
		}

		for (String pres : allowedPressures) {
			allowedMappings.put(pres, allowedPressures);
		}
		for (String co2 : allowedCo2) {
			allowedMappings.put(co2, allowedCo2);
		}
		for (String light : allowedLights) {
			allowedMappings.put(light, allowedLights);
		}
	}

	@Test
	public void testMapperWithBigList() throws IOException, URISyntaxException {
		File f = new File("all.list");
		new TestFileGenerator().generateFull("all.lst");
		testForList("all.lst");

		f.delete();
	}

	private boolean isMappingAllowed(MappingDTO<Object, Object> mapping) {
		return allowedMappings.get(mapping.getKeySource()).contains(mapping.getKeyTarget());
	}

	public void testForList(String listFile) throws IOException, URISyntaxException {

		long countTotalMappings = 0;
		long countErrors = 0;

		List<String> lines = Files.readAllLines(new File(listFile).toPath());
		lines = lines.stream().filter(l -> !"".equals(l)).collect(Collectors.toList());
		List<String> lines2 = new ArrayList<>(lines);
		LOG.info("Checking mapper for {} permutations...", lines.size() * lines2.size());
		LOG.info("Estimated time: {}ms", lines.size() * lines2.size() * MS_PER_PERMUTATION);

		Mapper mapper = new Mapper();

		for (String line : lines) {
			println("----\n" + line + ": ");
			for (String line2 : lines2) {
				Collection<MappingDTO<Object, Object>> mappings = mapper.map(line, line2);
				for (MappingDTO<Object, Object> mapping : mappings) {
					countTotalMappings++;
					println(printingService.mappingToString(line, line2, mapping, SHOW_JSON));
					try {
						assertTrue(isMappingAllowed(mapping));
					} catch (AssertionError error) {
						countErrors++;
						if (PRINT_ERRORS) {
							System.out.println("Bad mapping: " + mapping.getKeySource() + " -> " + mapping.getKeyTarget() + " (" + listFile + ")");
							for (MappingDTO<Object, Object> m : mappings) {
								System.out.println(printingService.mappingToString(line, line2, m, SHOW_JSON));
							}
							// throw error;
						}
					}
				}
				println("--");
			}
			println("----");
			println();
		}

		double confidence = 1 - countErrors / (double) countTotalMappings;
		System.out.println("Done.\nConfidence: " + confidence);
		assertTrue(MIN_CONFIDENCE <= confidence);
	}

	private void println() {
		println("");
	}

	private void println(String string) {
		if (PRINT) {
			System.out.println(string);
		}
	}

}
