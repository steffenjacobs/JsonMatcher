package me.steffenjacobs.jsonmatcher.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.map.LRUMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.detectlanguage.DetectLanguage;
import com.detectlanguage.Result;
import com.detectlanguage.errors.APIError;

/** @author Steffen Jacobs */
public class LanguageDetector {

	private static final Logger LOG = LoggerFactory.getLogger(LanguageDetector.class);

	private static final String DEFAULT_LANGUAGE = "en";
	private static final String API_KEY = "587287859eb8ff3de71ff2db6247416a";

	private final LRUMap cacheNonDetectableWords = new LRUMap(1000);
	private final LRUMap cacheDetectableWords = new LRUMap(1000);

	public String detectLanguage(String word) {
		DetectLanguage.apiKey = API_KEY;

		List<Result> results = new ArrayList<>();
		if (this.cacheDetectableWords.containsKey(word)) {
			// cache hit in detectable words
			return (String) this.cacheDetectableWords.get(word);
		}
		if (this.cacheNonDetectableWords.containsKey(word)) {
			// cache hit in non-detectable words
			return DEFAULT_LANGUAGE;
		}

		try {
			results = DetectLanguage.detect(word);
		} catch (APIError e) {
			LOG.error(e.getMessage(), e);
		}

		if (!results.isEmpty()) {
			// language detected
			Result result = results.get(0);
			LOG.info("Detected language for '{}' is '{}'.", word, result.language);
			cacheDetectableWords.put(word, result.language);
			return result.language;
		} else {
			// no language detectable
			cacheNonDetectableWords.put(word, null);
			LOG.info("Could not detect language for word '{}'.", word);
			return DEFAULT_LANGUAGE;
		}
	}

}
