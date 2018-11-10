package me.steffenjacobs.syntacticjsonmatcher.service.matcher;

import org.apache.commons.text.similarity.LevenshteinDistance;

import me.steffenjacobs.syntacticjsonmatcher.service.LanguageDetector;
import me.steffenjacobs.syntacticjsonmatcher.service.TranslationService;
import me.steffenjacobs.syntacticjsonmatcher.util.MathUtil;

/** @author Steffen Jacobs */
public class KeyMatcher {

	private final LanguageDetector languageDetector;
	private final TranslationService translationService;

	// penalty as multiplicator for result
	private static final double PENALTY_TRANSLATION = .9;
	private static final double PENALTY_SYNONYM = .9;

	// penalty value when weighting with equal weights
	private static final double PENALTY_WEIGHT_CONTAINS = 1;

	public KeyMatcher() {

		languageDetector = new LanguageDetector();
		translationService = new TranslationService();
	}

	public double matchKeys(String source, String target) {
		if (source == target) {
			return 1;
		}

		if (source == null || target == null) {
			return 0;
		}

		// both keys are equal -> 100% match
		if (source.equals(target)) {
			return 1;
		}

		// translate source and target to English if necessary
		source = translateToEnglishIfNecessary(source);
		target = translateToEnglishIfNecessary(target);

		// now they match
		if (source.equals(target)) {
			return PENALTY_TRANSLATION;
		}

		// synonyms match -> 100% match
		if (synonymMatch(source, target)) {
			return PENALTY_SYNONYM;
		}

		double levenstheinPercent = getLevenshteinPercent(source, target);

		if (containsAnyDirection(source, target)) {
			// penalize containsAnyDirection by including levenshtein distance,
			// since it is less probable that e.g. 't' stands just for
			// 'temperature'
			return MathUtil.calculateWeightedResultWithEqualWeights(levenstheinPercent, PENALTY_WEIGHT_CONTAINS);
		}

		return levenstheinPercent;
	}

	private String translateToEnglishIfNecessary(String string) {
		String lang = languageDetector.detectLanguage(string);
		if (!"en".equals(lang)) {
			return translationService.translateToEnglish(string, lang);
		}
		return string;
	}

	private boolean synonymMatch(String source, String target) {
		// TODO: check synonyms and answer with percent
		return false;
	}

	private boolean containsAnyDirection(String source, String target) {
		return source.contains(target) || target.contains(source);
	}

	private double getLevenshteinPercent(String source, String target) {
		int levenshtein = LevenshteinDistance.getDefaultInstance().apply(source, target);
		return levenshtein / (MathUtil.max(source.length(), target.length()));
	}

}
