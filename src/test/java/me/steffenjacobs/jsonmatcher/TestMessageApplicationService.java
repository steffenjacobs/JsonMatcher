package me.steffenjacobs.jsonmatcher;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

import org.json.JSONObject;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import me.steffenjacobs.jsonmatcher.domain.MappingDTO;
import me.steffenjacobs.jsonmatcher.service.MessageApplicationService;
import me.steffenjacobs.jsonmatcher.service.matcher.ValueMatcher;

/** @author Steffen Jacobs */
public class TestMessageApplicationService {

	private static MessageApplicationService messageApplicationService;
	private static ValueMatcher valueMatcher;

	@BeforeClass
	public static void setup() {
		messageApplicationService = new MessageApplicationService();
		valueMatcher = new ValueMatcher();
	}

	@Test
	public void testApplyModelToJson() {

		final String exemplaryJsonSource = "{\"t\": \"23.3°C\", \"hum\": 22}";
		// expected: "{\"temperature\": \"73.94°F\", \"humidity\": \"22%\"}";

		// celsius to fahrenheit conversion formula
		final Function<Double, Double> convertCelsiusToFahrenheit = t -> t * 1.8 + 32;

		// create mappings
		final MappingDTO<String, String> mapTemperature = new MappingDTO<>("t", "temperature",
				t -> convertCelsiusToFahrenheit.apply(valueMatcher.findNumberWithUnit(t).getA()) + "°F", 1);
		final MappingDTO<Integer, String> mapHumidity = new MappingDTO<>("hum", "humidity", h -> h + "%", 1);

		final Set<MappingDTO<? extends Object, ? extends Object>> mappings = new HashSet<>();
		mappings.add(mapTemperature);
		mappings.add(mapHumidity);

		// execute mapping
		String result = messageApplicationService.applyModelToJson(exemplaryJsonSource, mappings);

		// check if mapping is correct
		JSONObject jsonResult = new JSONObject(result);
		Assert.assertEquals(2, jsonResult.length());
		Assert.assertEquals("73.94°F", jsonResult.get("temperature"));
		Assert.assertEquals("22%", jsonResult.get("humidity"));
	}
}
