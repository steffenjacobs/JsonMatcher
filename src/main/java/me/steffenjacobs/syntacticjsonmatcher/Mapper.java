package me.steffenjacobs.syntacticjsonmatcher;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

import org.json.JSONObject;

import me.steffenjacobs.syntacticjsonmatcher.domain.MappingDTO;
import me.steffenjacobs.syntacticjsonmatcher.domain.MatchResult;
import me.steffenjacobs.syntacticjsonmatcher.service.PrintingService;
import me.steffenjacobs.syntacticjsonmatcher.service.matcher.KeyMatcher;
import me.steffenjacobs.syntacticjsonmatcher.service.matcher.ValueMatcher;

/** @author Steffen Jacobs */
public class Mapper {

	private final ValueMatcher valueMatcher = new ValueMatcher();
	private final KeyMatcher keyMatcher = new KeyMatcher();
	private final PrintingService printingService = new PrintingService();

	public Collection<MappingDTO<Object, Object>> map(String source, String target) {
		JSONObject jsonSource = new JSONObject(source);
		JSONObject jsonTarget = new JSONObject(target);

		Set<MatchResult> results = new HashSet<>();

		// match keys
		for (String key : jsonSource.keySet()) {
			for (String key2 : jsonTarget.keySet()) {
				if (!results.contains(new MatchResult(0, key, key2))) {
					// only calculate new permutations since transform(key,
					// key2)
					// <=> transform(key2, key)
					results.add(new MatchResult(keyMatcher.matchKeys(key, key2), key, key2));
				}
			}
		}

		// match values / transform values
		Collection<MappingDTO<Object, Object>> mappings = new HashSet<>();
		for (MatchResult mr : results) {
			Function<Object, Object> function = valueMatcher.matchValues(jsonSource.get(mr.getKey1()), jsonTarget.get(mr.getKey2()));

			mappings.add(new MappingDTO<>(mr.getKey1(), mr.getKey2(), function, mr.getMatchRate()));
		}

		Collection<Collection<MappingDTO<Object, Object>>> alternatives = computeAlternatives(mappings);

		printingService.printAlternatives(alternatives);

		return findBestMappings(alternatives);
	}

	/**
	 * Computes all alternative mappings without overlap between source key and
	 * target key.
	 */
	private Collection<Collection<MappingDTO<Object, Object>>> computeAlternatives(Collection<MappingDTO<Object, Object>> mappings) {
		Collection<Collection<MappingDTO<Object, Object>>> alternatives = new HashSet<>();
		for (MappingDTO<Object, Object> mapping : mappings) {
			Collection<MappingDTO<Object, Object>> mappingsInCurrentAlternative = new HashSet<>();
			mappingsInCurrentAlternative.add(mapping);
			for (MappingDTO<Object, Object> mapping2 : mappings) {
				if (!hasOverlap(mapping, mapping2)) {
					mappingsInCurrentAlternative.add(mapping2);
				}
			}
			alternatives.add(mappingsInCurrentAlternative);
		}
		return alternatives;

	}

	/**
	 * @return true, if the source key or the target key of {@link #mapping1} is
	 *         equal to the source key or the target key of {@link #mapping2}.
	 */
	private boolean hasOverlap(MappingDTO<Object, Object> mapping1, MappingDTO<Object, Object> mapping2) {
		return mapping1.getKeySource().equals(mapping2.getKeySource()) || mapping1.getKeySource().equals(mapping2.getKeyTarget())
				|| mapping1.getKeyTarget().equals(mapping2.getKeySource()) || mapping1.getKeyTarget().equals(mapping2.getKeyTarget());
	}

	/** @return Rhe average match rate for {@link #alternative}. */
	private double calculateMatchRateForAlternative(Collection<MappingDTO<Object, Object>> alternative) {
		double sumMatchRate = 0d;
		for (MappingDTO<Object, Object> mapping : alternative) {
			sumMatchRate += mapping.getMatchRate();
		}
		return sumMatchRate / alternative.size();
	}

	/**
	 * Finds the best mapping from all {@link #alternatives} regarding
	 * aggregated match rate over alternative.
	 */
	private Collection<MappingDTO<Object, Object>> findBestMappings(Collection<Collection<MappingDTO<Object, Object>>> alternatives) {
		Collection<MappingDTO<Object, Object>> bestAlternative = null;
		double bestMatchRate = 0d;

		for (Collection<MappingDTO<Object, Object>> alternative : alternatives) {
			double avgMatchRate = calculateMatchRateForAlternative(alternative);
			if (avgMatchRate > bestMatchRate) {
				bestAlternative = alternative;
				bestMatchRate = avgMatchRate;
			}
		}
		return bestAlternative;
	}

}
