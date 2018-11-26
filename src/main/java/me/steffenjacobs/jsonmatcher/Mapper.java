package me.steffenjacobs.jsonmatcher;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

import org.json.JSONObject;

import me.steffenjacobs.jsonmatcher.domain.MappingDTO;
import me.steffenjacobs.jsonmatcher.domain.MatchResult;
import me.steffenjacobs.jsonmatcher.service.HungarianAlgorithm;
import me.steffenjacobs.jsonmatcher.service.matcher.KeyMatcher;
import me.steffenjacobs.jsonmatcher.service.matcher.ValueMatcher;
import me.steffenjacobs.jsonmatcher.util.MatrixUtils;

/** @author Steffen Jacobs */
public class Mapper {

	private final ValueMatcher valueMatcher = new ValueMatcher();
	private final KeyMatcher keyMatcher = new KeyMatcher();

	public Collection<MappingDTO<Object, Object>> map(String source, String target) {
		JSONObject jsonSource = new JSONObject(source);
		JSONObject jsonTarget = new JSONObject(target);

		String[] sourceKeys = new String[jsonSource.keySet().size()];
		jsonSource.keySet().toArray(sourceKeys);

		String[] targetKeys = new String[jsonTarget.keySet().size()];
		jsonTarget.keySet().toArray(targetKeys);
		
		//match keys
		double[][] costMatrix = MatrixUtils.buildCostMatrix(sourceKeys, targetKeys, keyMatcher::matchKeys);
		double[][] costMatrixBackup = MatrixUtils.deepCopy(costMatrix);
		
		int[][] assignment = new HungarianAlgorithm(costMatrix).findOptimalAssignment();
		
		Set<MatchResult> results = new HashSet<>();
		for (int i = 0; i < assignment.length; i++) {
			results.add(new MatchResult(costMatrixBackup[assignment[i][1]][assignment[i][0]], sourceKeys[assignment[i][1]], targetKeys[assignment[i][0]]));
		}

		// match values / transform values
		Collection<MappingDTO<Object, Object>> mappings = new HashSet<>();
		for (MatchResult mr : results) {
			Function<Object, Object> function = valueMatcher.matchValues(jsonSource.get(mr.getKey1()), jsonTarget.get(mr.getKey2()));

			mappings.add(new MappingDTO<>(mr.getKey1(), mr.getKey2(), function, mr.getMatchRate()));
		}
		return mappings;
	}
}
