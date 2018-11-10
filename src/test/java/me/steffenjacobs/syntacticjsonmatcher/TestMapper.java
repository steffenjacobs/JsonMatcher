package me.steffenjacobs.syntacticjsonmatcher;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.steffenjacobs.syntacticjsonmatcher.Mapper;
import me.steffenjacobs.syntacticjsonmatcher.domain.MappingDTO;
import me.steffenjacobs.syntacticjsonmatcher.service.PrintingService;

/** @author Steffen Jacobs */
public class TestMapper {

	private final Logger LOG = LoggerFactory.getLogger(TestMapper.class);
	private final PrintingService printingService = new PrintingService();

	private static final boolean SHOW_JSON = false;
	private static final boolean PRINT = true;

	@Ignore
	@Test
	public void testMapping() {
		Mapper mapper = new Mapper();
		final String source = "{\"temperature\": 25, \"humidity\": 55}";
		final String target = source;
		Collection<MappingDTO<Object, Object>> mappings = mapper.map(source, source);
		for (MappingDTO<Object, Object> mapping : mappings) {
			printingService.mappingToString(source, target, mapping, SHOW_JSON);
		}
	}

	@Ignore
	@Test
	public void testMapping2() {
		Mapper mapper = new Mapper();
		final String source = "{\"temperature\": 25, \"humidity\": 55}";
		final String target = "{\"temperature\": 25, \"humidity\": 55.5}";
		Collection<MappingDTO<Object, Object>> mappings = mapper.map(source, source);
		for (MappingDTO<Object, Object> mapping : mappings) {
			printingService.mappingToString(source, target, mapping, SHOW_JSON);
		}
	}

	@Ignore
	@Test
	public void testTemperatureAndHumidtyMapping() throws IOException, URISyntaxException {
		testForList("temperatureHumidity.lst");
	}

	// @Ignore
	@Test
	public void testTemperatureAndHumidtyMapping2() throws IOException, URISyntaxException {
		testForList("temperatureHumidity2.lst");
	}

	@Ignore
	@Test
	public void testTemperatureAndHumidtyMapping3() throws IOException, URISyntaxException {
		testForList("temperatureHumidity3.lst");
	}

	@Ignore
	@Test
	public void testTemperatureMapping() throws IOException, URISyntaxException {
		testForList("temperature.lst");
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
					printingService.mappingToString(line, line2, mapping, SHOW_JSON);
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
