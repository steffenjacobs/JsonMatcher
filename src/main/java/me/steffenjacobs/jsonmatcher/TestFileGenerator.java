package me.steffenjacobs.jsonmatcher;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.LinkedList;

/** @author Steffen Jacobs */
public class TestFileGenerator {
	private static final String[] KEYS_TEMPERATURE = new String[] { "temperature", "temp", "t", "temperatura", "Temperatur", "temperatur" };
	private static final String[] VALUES_TEMPERATURE = new String[] { "23", "\"23\"", "23.1", "\"23.1\"", "23°F", "23°C", "23.1°F" };

	private static final String[] KEYS_HUMIDITY = new String[] { "humidity", "hum", "h", "humedad", "humidness", "Luftfeuchte", "Luftfeuchtigkeit" };
	private static final String[] VALUES_HUMIDITY = new String[] { "44", "\"44\"", "\"44%\"", "44.4", "\"44.4%\"" };

	private static final String[] KEYS_PRESSURE = new String[] { "pressure", "p", "pres", "air pressure", "druck", "Luftdruck", "pressione" };
	private static final String[] VALUES_PRESSURE = new String[] { "905", "\"905\"", "905.5", "\"905psi\"", "\"905.5psi\"", "\"905.5bar\"" };

	private static final String[] KEYS_CO2 = new String[] { "co2", "CO2", "co2 value" };
	private static final String[] VALUES_CO2 = new String[] { "505", "505.5", "\"505ppm\"" };

	private static final String[] KEYS_LIGHT = new String[] { "light", "brightness", "Helligkeit", "luminance" };
	private static final String[] VALUES_LIGHT = new String[] { "1505", "1505.5", "\"1505lx\"", "\"1505phots\"" };

	private static final double RANDOM_SAMPLING_RATE = .00008;

	public static void main(String[] args) throws FileNotFoundException {
		new TestFileGenerator().generateFull("all.lst");
	}

	public void generateFull(String filename) throws FileNotFoundException {
		Collection<String> temperature = getAllPermutations(KEYS_TEMPERATURE, VALUES_TEMPERATURE);
		Collection<String> humidity = getAllPermutations(KEYS_HUMIDITY, VALUES_HUMIDITY);
		Collection<String> pressure = getAllPermutations(KEYS_PRESSURE, VALUES_PRESSURE);
		Collection<String> co2 = getAllPermutations(KEYS_CO2, VALUES_CO2);
		Collection<String> light = getAllPermutations(KEYS_LIGHT, VALUES_LIGHT);

		final PrintWriter printWriter = new PrintWriter(new File(filename));

		for (String temp : temperature) {
			StringBuilder sbT = new StringBuilder("{");
			sbT.append(temp);
			sbT.append(", ");
			for (String hum : humidity) {
				StringBuilder sbH = new StringBuilder(sbT);
				sbH.append(hum);
				sbH.append(", ");
				for (String pres : pressure) {
					StringBuilder sbP = new StringBuilder(sbH);
					sbP.append(pres);
					sbP.append(", ");
					for (String c : co2) {
						StringBuilder sbC = new StringBuilder(sbP);
						sbC.append(c);
						sbC.append(", ");
						for (String l : light) {
							if (Math.random() < RANDOM_SAMPLING_RATE) {
								StringBuilder sb = new StringBuilder(sbC);
								sb.append(l);
								sb.append("}");
								printWriter.println(sb.toString());
							}
						}
					}
				}
			}
		}

		printWriter.close();
	}

	private Collection<String> getAllPermutations(String[] keys, String[] values) {
		Collection<String> result = new LinkedList<>();
		for (String k : keys) {
			for (String v : values) {
				result.add(buildString(k, v));
			}
		}
		return result;
	}

	private String buildString(String key, String value) {
		StringBuilder sb = new StringBuilder();
		sb.append("\"");
		sb.append(key);
		sb.append("\": ");
		sb.append(value);
		return sb.toString();
	}

}
