package me.steffenjacobs.syntacticjsonmatcher;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.steffenjacobs.syntacticjsonmatcher.domain.MappingDTO;
import me.steffenjacobs.syntacticjsonmatcher.service.PrintingService;

/** @author Steffen Jacobs */
public class TestMapper {

	private static final Logger LOG = LoggerFactory.getLogger(TestMapper.class);

	private static final boolean SHOW_JSON = false;
	private static final boolean PRINT = false;
	private static final boolean PRINT_ERRORS = true;
	/** More settings: see {@link DebugSettings} */

	private static final Map<String, List<String>> allowedMappings = new HashMap<>();
	private static final PrintingService printingService = new PrintingService();

	@BeforeClass
	public static void initAllowedMappings() {
		List<String> allowedTemperatures = Arrays.asList("t", "tp", "temp", "temperature", "temperatura", "temperatur", "Temperatur");
		List<String> allowedHumidities = Arrays.asList("h", "hm", "hum", "humidity", "Luftfeuchtigkeit", "Luftfeuchte", "luftfeuchte", "humedad");
		List<String> allowedPressures = Arrays.asList("pressure", "pressione", "druck", "Luftdruck", "pr", "pre", "p");

		for (String temp : allowedTemperatures) {
			allowedMappings.put(temp, allowedTemperatures);
		}

		for (String hum : allowedHumidities) {
			allowedMappings.put(hum, allowedHumidities);
		}

		for (String pres : allowedPressures) {
			allowedMappings.put(pres, allowedPressures);
		}
	}

	@Test
	public void testMappingReflexive() {
		Mapper mapper = new Mapper();
		final String source = "{\"temperature\": 25, \"humidity\": 55}";
		final String target = source;
		Collection<MappingDTO<Object, Object>> mappings = mapper.map(source, source);
		for (MappingDTO<Object, Object> mapping : mappings) {
			println(printingService.mappingToString(source, target, mapping, SHOW_JSON));
			assertTrue(isMappingAllowed(mapping));
		}
	}

	@Test
	public void testMappingValuesIntToFloat() {
		Mapper mapper = new Mapper();
		final String source = "{\"temperature\": 25, \"humidity\": 55}";
		final String target = "{\"temperature\": 25, \"humidity\": 55.5}";
		Collection<MappingDTO<Object, Object>> mappings = mapper.map(source, source);
		for (MappingDTO<Object, Object> mapping : mappings) {
			println(printingService.mappingToString(source, target, mapping, SHOW_JSON));
			assertTrue(isMappingAllowed(mapping));
		}
	}

	@Test
	public void testMappingValuesStringToNumber() {
		Mapper mapper = new Mapper();
		final String source = "{\"temperature\": \"25\", \"humidity\": \"55\"}";
		final String target = "{\"temperature\": 25, \"humidity\": 55.5}";
		Collection<MappingDTO<Object, Object>> mappings = mapper.map(source, source);
		for (MappingDTO<Object, Object> mapping : mappings) {
			println(printingService.mappingToString(source, target, mapping, SHOW_JSON));
			assertTrue(isMappingAllowed(mapping));
		}
	}

	@Test
	public void testMappingValuesNumberToString() {
		Mapper mapper = new Mapper();
		final String source = "{\"temperature\": 25, \"humidity\": 55}";
		final String target = "{\"temperature\": \"25\", \"humidity\": \"55.5\"}";
		Collection<MappingDTO<Object, Object>> mappings = mapper.map(source, source);
		for (MappingDTO<Object, Object> mapping : mappings) {
			println(printingService.mappingToString(source, target, mapping, SHOW_JSON));
			assertTrue(isMappingAllowed(mapping));
		}
	}

	@Test
	public void testTemperatureAndHumidtyMapping() throws IOException, URISyntaxException {
		testForList("temperatureHumidity.lst");
	}

	@Test
	public void testTemperatureAndHumidtyMapping2() throws IOException, URISyntaxException {
		testForList("temperatureHumidity2.lst");
	}

	@Test
	public void testTemperatureAndHumidtyMapping3() throws IOException, URISyntaxException {
		testForList("temperatureHumidity3.lst");
	}

	@Test
	public void testTemperatureMapping() throws IOException, URISyntaxException {
		testForList("temperature.lst");
	}

	@Test
	public void testTemperatureHumidityPressureMapping() throws IOException, URISyntaxException {
		testForList("TemperatureHumidityPressure.lst");
	}

	@Ignore
	@Test
	public void testTemperatureHumidityPressureMapping2() throws IOException, URISyntaxException {
		testForList("TemperatureHumidityPressure2.lst");
	}

	private boolean isMappingAllowed(MappingDTO<Object, Object> mapping) {
		return allowedMappings.get(mapping.getKeySource()).contains(mapping.getKeyTarget());
	}

	public void testForList(String listFile) throws IOException, URISyntaxException {
		List<String> lines = Files.readAllLines(Paths.get(TestMapper.class.getClassLoader().getResource(listFile).toURI()));
		lines = lines.stream().filter(l -> !"".equals(l)).collect(Collectors.toList());
		List<String> lines2 = new ArrayList<>(lines);
		LOG.info("Checking mapper for {} permutations...", lines.size() * lines2.size());

		Mapper mapper = new Mapper();

		for (String line : lines) {
			println("----\n" + line + ": ");
			for (String line2 : lines2) {
				Collection<MappingDTO<Object, Object>> mappings = mapper.map(line, line2);
				for (MappingDTO<Object, Object> mapping : mappings) {
					println(printingService.mappingToString(line, line2, mapping, SHOW_JSON));
					try {
						assertTrue(isMappingAllowed(mapping));
					} catch (AssertionError error) {
						if (PRINT_ERRORS) {
							System.out.println("Bad mapping: " + mapping.getKeySource() + " -> " + mapping.getKeyTarget() + " (" + listFile + ")");
							for (MappingDTO<Object, Object> m : mappings) {
								System.out.println(printingService.mappingToString(line, line2, m, SHOW_JSON));
							}
							throw error;
						}
					}
				}
				println("--");
			}
			println("----");
			println();
		}
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
