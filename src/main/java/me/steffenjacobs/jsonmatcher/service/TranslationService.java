package me.steffenjacobs.jsonmatcher.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.collections.map.LRUMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.steffenjacobs.jsonmatcher.util.HTTPRequester;

/** @author Steffen Jacobs */
public class TranslationService {

	private static final Logger LOG = LoggerFactory.getLogger(TranslationService.class);
	private static final Pattern TRANSLATION_RESULT_PATTERN = Pattern.compile("\\[\\[\\[\"(.*)\",\".*\\]\\]\\]");
	private final HTTPRequester httpRequester = new HTTPRequester();
	private final CachePersistingService cachePersistingService;

	private final LRUMap translationCache;

	public TranslationService() {
		translationCache = new LRUMap(1000);
		cachePersistingService = new CachePersistingService();
		cachePersistingService.load(translationCache, "translations.cache", WordInLanguage::new, s -> s);
	}

	public String translateToEnglish(String word, String detectedLanguage) {
		final WordInLanguage key = new WordInLanguage(word, detectedLanguage);
		if (translationCache.containsKey(key)) {
			return (String) translationCache.get(key);
		}
		String request = buildRequest(word, detectedLanguage);
		String result = httpRequester.sendGet(request);
		result = extractTranslation(result);

		translationCache.put(key, result);
		LOG.info("Translation for '{}' in '{}' is '{}'.", word, detectedLanguage, result);
		cachePersistingService.persistCache(translationCache, "translations.cache");
		return result;

	}

	private String extractTranslation(String input) {
		Matcher matcher = TRANSLATION_RESULT_PATTERN.matcher(input);
		if (matcher.find()) {
			return matcher.group(1);

		}
		return null;
	}

	private String buildRequest(String word, String detectedLanguage) {
		StringBuilder sb = new StringBuilder();
		sb.append("https://translate.googleapis.com/translate_a/single?client=gtx&sl=auto&tl=en&hl=");
		sb.append(detectedLanguage);
		sb.append("&dt=t&q=");
		sb.append(word);
		return sb.toString();
	}

	private class WordInLanguage {
		private static final String SPLIT_CHAR = ":";
		private final String word;
		private final String language;

		public WordInLanguage(String wordInLanguage) {
			String[] split = wordInLanguage.split(SPLIT_CHAR);
			word = split[0];
			language = split[1];
		}

		public WordInLanguage(String word, String language) {
			super();
			this.word = word;
			this.language = language;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + ((language == null) ? 0 : language.hashCode());
			result = prime * result + ((word == null) ? 0 : word.hashCode());
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
			WordInLanguage other = (WordInLanguage) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (language == null) {
				if (other.language != null)
					return false;
			} else if (!language.equals(other.language))
				return false;
			if (word == null) {
				if (other.word != null)
					return false;
			} else if (!word.equals(other.word))
				return false;
			return true;
		}

		private TranslationService getOuterType() {
			return TranslationService.this;
		}

		@Override
		public String toString() {
			return word + SPLIT_CHAR + language;
		}

	}

}
