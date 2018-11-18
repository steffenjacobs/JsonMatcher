package me.steffenjacobs.syntacticjsonmatcher.util;

import java.util.Arrays;
import java.util.stream.Collectors;

/** @author Steffen Jacobs */
public final class MathUtil {

	private MathUtil() {
	}

	public static double max(double d1, double d2, double... doubles) {
		double max = d1 > d2 ? d1 : d2;
		if (doubles.length > 0) {
			for (double d : doubles) {
				max = max > d ? max : d;
			}
		}
		return max;
	}

	public static double calculateWeightedResultWithEqualWeights(double... values) {
		return calculateWeightedResult(Arrays.stream(values).mapToObj(value -> new Pair<Double, Double>(value, 1d)).collect(Collectors.toList()));
	}

	@SafeVarargs
	public static double calculateWeightedResult(Pair<Double, Double>... weightedValues) {
		return calculateWeightedResult(Arrays.asList(weightedValues));
	}

	public static double calculateWeightedResult(Iterable<Pair<Double, Double>> weightedValues) {
		double weightedSum = 0;
		double weightsSum = 0;
		for (Pair<Double, Double> pair : weightedValues) {
			weightedSum += pair.getA() * pair.getB();
			weightsSum += pair.getB();
		}
		return weightedSum > 0 ? weightedSum / weightsSum : 1;
	}

}
