package me.steffenjacobs.syntacticjsonmatcher.service.matcher;

import java.util.Map;

import org.apache.commons.text.similarity.LevenshteinDistance;

import me.steffenjacobs.syntacticjsonmatcher.DebugSettings;
import me.steffenjacobs.syntacticjsonmatcher.service.LanguageDetector;
import me.steffenjacobs.syntacticjsonmatcher.service.PrintingService;
import me.steffenjacobs.syntacticjsonmatcher.service.SynonymService;
import me.steffenjacobs.syntacticjsonmatcher.service.TranslationService;
import me.steffenjacobs.syntacticjsonmatcher.util.MathUtil;
import me.steffenjacobs.syntacticjsonmatcher.util.Pair;

/** @author Steffen Jacobs */
public class KeyMatcher {

	private final LanguageDetector languageDetector;
	private final TranslationService translationService;
	private final PrintingService printingService = new PrintingService();
	private final SynonymService synonymService = new SynonymService();

	// penalty as multiplicator for result
	private static final double PENALTY_TRANSLATION = .9;
	private static final double PENALTY_SYNONYM = .89;
	private static final double PENALTY_LEVENSHTEIN = .5;
	private static final double PENALTY_EQUALS = 1;
	private static final double PENALTY_NULL = 0;

	// penalty value when weighting with equal weights
	private static final double PENALTY_WEIGHT_CONTAINS = .93;
	private static final double PENALTY_WEIGHT_STARTS_WITH = 1;
	private static final double PENALTY_WEIGHT_LEVENSHTEIN = .3;

	public KeyMatcher() {

		languageDetector = new LanguageDetector();
		translationService = new TranslationService();
	}

	public double matchKeys(Pair<String, String> keys) {
		return 1 - matchKeys(keys.getA(), keys.getB());
	}

	public double matchKeys(String source, String target) {
		if (source == target) {
			return PENALTY_EQUALS;
		}

		if (source == null || target == null) {
			return PENALTY_NULL;
		}

		source = source.toLowerCase();
		target = target.toLowerCase();
		
		// both keys are equal -> 100% match
		if (source.equals(target)) {
			return PENALTY_EQUALS;
		}

		// translate source and target to English if necessary
		Pair<Boolean, String> sourceTr = translateToEnglishIfNecessary(source);
		Pair<Boolean, String> targetTr = translateToEnglishIfNecessary(target);

		String sourceT = sourceTr.getB();
		String targetT = targetTr.getB();

		// now they might match
		if (sourceT.equals(targetT)) {
			final double score = penalizeTranslation(1, sourceTr, targetTr);
			print(source, target, score, "TRANSLATION");
			return score;
		}

		double score = 0;

		// synonyms match
		final double synonymMatch = synonymMatch(sourceT, targetT);
		if (synonymMatch > 0 && synonymMatch > score) {
			score = synonymMatch;
			print(source, target, score, "SYNONYM");
		}

		double levenshteinSimilarity = getLevenshteinSimilarity(sourceT, targetT);

		// starts with
		if (startsWithAnyDirection(sourceT, targetT)) {
			// penalize similar to containsAnyDirection
			final double equallyWeightedLevenshtein = MathUtil.calculateWeightedResult(new Pair<>(levenshteinSimilarity, PENALTY_WEIGHT_LEVENSHTEIN),
					new Pair<>(PENALTY_WEIGHT_STARTS_WITH, 1d));
			if (equallyWeightedLevenshtein > score) {
				score = equallyWeightedLevenshtein;
				print(source, target, equallyWeightedLevenshtein, "LEVENSHTEIN STARTS WITH");
			}
		}

		// contains
		if (containsAnyDirection(sourceT, targetT)) {
			// penalize containsAnyDirection by including levenshtein distance,
			// since it is less probable that e.g. 't' stands just for
			// 'temperature'
			final double equallyWeightedLevenshtein = MathUtil.calculateWeightedResultWithEqualWeights(levenshteinSimilarity, PENALTY_WEIGHT_CONTAINS);
			if (equallyWeightedLevenshtein > score) {
				score = equallyWeightedLevenshtein;
				print(source, target, equallyWeightedLevenshtein, "LEVENSHTEIN CONTAINS");
			}
		}

		double penalizedLevenshteinScore = levenshteinSimilarity * PENALTY_LEVENSHTEIN;

		if (score == 0 && penalizedLevenshteinScore > score) {
			score = penalizedLevenshteinScore;
			print(source, target, penalizedLevenshteinScore, "LEVENSHTEIN");
		}
		return penalizeTranslation(score, sourceTr, targetTr);
	}

	private double penalizeTranslation(double score, Pair<Boolean, String> firstTranslation, Pair<Boolean, String> secondTranslation) {
		return score * (firstTranslation.getA() ? PENALTY_TRANSLATION : 1) * (secondTranslation.getA() ? PENALTY_TRANSLATION : 1);
	}

	/**
	 * @return a tuple with:<br/>
	 *         A: true, if translation to English was necessary</br>
	 *         B: translation
	 */
	private Pair<Boolean, String> translateToEnglishIfNecessary(String string) {
		String lang = languageDetector.detectLanguage(string);
		if (!"en".equals(lang)) {
			return new Pair<>(true, translationService.translateToEnglish(string, lang));
		}
		return new Pair<>(false, string);
	}

	private double synonymMatch(String source, String target) {
		double result = penalizePossibleSynonym(target, source);
		return result;
	}

	private double penalizePossibleSynonym(final String source, final String target) {
		final Map<String, Integer> synonymsSource = synonymService.findSynonyms(source);
		if (synonymsSource.containsKey(target)) {
			int max = Integer.MIN_VALUE;
			for (Map.Entry<String, Integer> entr : synonymsSource.entrySet()) {
				if (entr.getValue() > max) {
					max = entr.getValue();
				}
			}
			double penaltyFromDissimilarity = synonymsSource.get(target) / (double) max;
			
			//fix for strange outliers in datamuseAPI
			penaltyFromDissimilarity = penaltyFromDissimilarity < .1 ? 1: penaltyFromDissimilarity;
			return PENALTY_SYNONYM * penaltyFromDissimilarity;
		}
		return 0;
	}

	private boolean containsAnyDirection(String source, String target) {
		return source.contains(target) || target.contains(source);
	}

	private boolean startsWithAnyDirection(String source, String target) {
		return source.startsWith(target) || target.startsWith(source);
	}

	private double getLevenshteinSimilarity(String source, String target) {
		int levenshtein = LevenshteinDistance.getDefaultInstance().apply(source, target);
		return 1 - levenshtein / MathUtil.max(source.length(), target.length());
	}

	private void print(String source, String target, double value, String reason) {
		if (DebugSettings.SHOW_DECISIONS) {
			System.out.println(printingService.formatDecisionString(source, target, value, reason));
		}
	}
}
