package me.steffenjacobs.jsonmatcher.service;

import java.io.StringReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.collections.map.LRUMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.steffenjacobs.jsonmatcher.domain.wolframapi.Queryresult;
import me.steffenjacobs.jsonmatcher.domain.wolframapi.Queryresult.Pod;
import me.steffenjacobs.jsonmatcher.domain.wolframapi.Queryresult.Pod.Subpod;
import me.steffenjacobs.jsonmatcher.util.HTTPRequester;

/** @author Steffen Jacobs */
public class UnitConversionService {

	private static final Logger LOG = LoggerFactory.getLogger(UnitConversionService.class);
	private static final String APP_ID = "J6XHLV-7TXAV24RY7";

	private static final Pattern CLEAN_PATTERN = Pattern.compile("([^\\(\\)]*)\\(.*\\)");

	private final HTTPRequester httpRequester = new HTTPRequester();

	private final CachePersistingService cachePersistingService;

	private final LRUMap conversionCache;

	public UnitConversionService() {
		conversionCache = new LRUMap(1000);
		cachePersistingService = new CachePersistingService();
		cachePersistingService.load(conversionCache, "conversion.cache", StringWithUnit::new, s -> s);
	}

	public String convert(String source, String unit) {
		if (source.contains(unit)) {
			return source;
		}
		StringWithUnit key = new StringWithUnit(source, unit);
		if (conversionCache.containsKey(key)) {
			return (String) conversionCache.get(key);
		} else {
			String request = buildRequest(source, unit);
			String oldresult = httpRequester.sendGet(request);
			String result = "";
			try {
				result = extractResult(oldresult, source, unit);
				if (result == null) {
					conversionCache.put(key, source);
					return source;
				}
				result = cleanResult(result);
			} catch (JAXBException e) {
				LOG.error(e.getMessage());
			}

			conversionCache.put(key, result);
			LOG.info("Transformation for '{}' to '{}' is '{}'.", source, unit, result);
			cachePersistingService.persistCache(conversionCache, "conversion.cache");
			return result;
		}
	}

	public String cleanResult(String extractedResult) {
		Matcher matcher = CLEAN_PATTERN.matcher(extractedResult);
		if (matcher.find()) {
			return matcher.group(1).trim();
		}
		return extractedResult;
	}

	public String extractResult(String input, String source, String unit) throws JAXBException {

		JAXBContext jc = JAXBContext.newInstance(Queryresult.class);

		Unmarshaller unmarshaller = jc.createUnmarshaller();
		Queryresult result = (Queryresult) unmarshaller.unmarshal(new StringReader(input));

		if (result.isSuccess()) {
			for (Pod pod : result.getPod()) {
				if ("Result".equals(pod.getId())) {
					for (Subpod subpod : pod.getSubpod()) {
						if (subpod != null && "".equals(subpod.getTitle())) {
							return subpod.getPlaintext();
						}
					}
					LOG.error("Result Subpod not found.");
				} else if ("Unit conversions".equals(pod.getTitle())) {
					for (Subpod subpod : pod.getSubpod()) {
						if (subpod.getPlaintext().contains(unit)) {
							return subpod.getPlaintext();
						}
					}
					System.out.println(input);
					LOG.error("No result with correct converted unit in subpods found.");
				}
			}
			LOG.error("No result found for conversion {} -> {}.", source, unit);
		} else {
			LOG.error("Query not successful.");
		}
		return null;

	}

	private String buildRequest(String source, String unit) {
		StringBuilder sb = new StringBuilder();
		sb.append("http://api.wolframalpha.com/v2/query?appid=");
		sb.append(APP_ID);
		sb.append("&input=");
		sb.append(source);
		sb.append(" to ");
		sb.append(unit);
		return sb.toString();
	}

	private static class StringWithUnit {
		private static final String SPLIT_CHAR = ":";

		private final String source;
		private final String unit;

		public StringWithUnit(String loaded) {
			String[] split = loaded.split(SPLIT_CHAR);
			source = split[0];
			unit = split[1];
		}

		public StringWithUnit(String source, String unit) {
			super();
			this.source = source;
			this.unit = unit;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((source == null) ? 0 : source.hashCode());
			result = prime * result + ((unit == null) ? 0 : unit.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			StringWithUnit other = (StringWithUnit) obj;
			if (source == null) {
				if (other.source != null)
					return false;
			} else if (!source.equals(other.source))
				return false;
			if (unit == null) {
				if (other.unit != null)
					return false;
			} else if (!unit.equals(other.unit))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return source + SPLIT_CHAR + unit;
		}

	}

}
