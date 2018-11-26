package me.steffenjacobs.jsonmatcher.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.map.LRUMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.steffenjacobs.jsonmatcher.util.HTTPRequester;

/** @author Steffen Jacobs */
public class SynonymService {

	private static final Logger LOG = LoggerFactory.getLogger(SynonymService.class);

	private final HTTPRequester httpRequester = new HTTPRequester();

	private final LRUMap synonymCache;

	public SynonymService() {
		synonymCache = new LRUMap(1000);
	}

	private String buildRequest(String word) {
		StringBuilder sb = new StringBuilder();
		sb.append("https://api.datamuse.com/words?rel_syn=");
		try {
			sb.append(URLEncoder.encode(word, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			LOG.error(e.getMessage());
		}
		return sb.toString();
	}

	/**
	 * @return a {@Link Set} of synonymous words with their corresponding score
	 *         as assigned by datamuse.
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Integer> findSynonyms(String word) {

		if (synonymCache.containsKey(word)) {
			return (Map<String, Integer>) synonymCache.get(word);
		}

		Map<String, Integer> map = new HashMap<>();
		String request = buildRequest(word);
		String result = httpRequester.sendGet(request);
		try {
			if (result.isEmpty()) {
				synonymCache.put(word, map);
				return map;
			}
			JSONArray json = new JSONArray(result);
			if (json.isEmpty()) {
				synonymCache.put(word, map);
				return map;
			}

			for (Object o : json) {
				if (o instanceof JSONObject) {
					JSONObject jo = (JSONObject) o;
					map.put(jo.getString("word"), jo.getInt("score"));
				}
			}
		} catch (JSONException e) {
			LOG.info("Could not parse JSON: {}", e.getMessage());
		}
		synonymCache.put(word, map);
		return map;
	}
}
