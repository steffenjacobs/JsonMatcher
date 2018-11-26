package me.steffenjacobs.jsonmatcher.util;

import java.util.function.Function;

/** @author Steffen Jacobs */
public final class MatrixUtils {

	private MatrixUtils() {
	}

	public static double[][] deepCopy(double[][] array) {
		double[][] copy = new double[array.length][];
		for (int i = 0; i < array.length; i++) {
			copy[i] = new double[array[i].length];
			for (int j = 0; j < array[i].length; j++) {
				copy[i][j] = array[i][j];
			}
		}
		return copy;
	}

	public static double[][] buildCostMatrix(String[] sources, String[] targets, Function<Pair<String, String>, Double> costFunction) {
		double[][] result = new double[sources.length][];
		for (int i = 0; i < sources.length; i++) {
			result[i] = new double[targets.length];
			for (int j = 0; j < targets.length; j++) {
				result[i][j] = costFunction.apply(new Pair<>(sources[i], targets[j]));
			}
		}
		return result;
	}
}
