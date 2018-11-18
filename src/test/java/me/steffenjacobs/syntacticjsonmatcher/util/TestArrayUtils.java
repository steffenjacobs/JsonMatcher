package me.steffenjacobs.syntacticjsonmatcher.util;

import java.util.Arrays;
import java.util.function.Function;

import org.junit.Assert;
import org.junit.Test;

import me.steffenjacobs.syntacticjsonmatcher.util.MatrixUtils;
import me.steffenjacobs.syntacticjsonmatcher.util.Pair;

/** @author Steffen Jacobs */
public class TestArrayUtils {

	@Test
	public void testDeepCopy() {
		double[][] arr = new double[][] { new double[] { 1, 2, 3 }, new double[] { 4, 5, 6 }, new double[] { 7, 8, 9 } };
		double[][] arr2 = new double[][] { new double[] { 1, 2, 3 }, new double[] { 4, 5, 6 }, new double[] { 7, 8, 9 } };

		double[][] copy = MatrixUtils.deepCopy(arr);
		assertMatrixEquals(arr2, copy);
	}
	

	@Test
	public void testBuildCostMatrix() {
		String[] sources = new String[] { "temperature", "temp", "pressure" };
		String[] targets = new String[] { "temperature", "temp", "pressure" };

		Function<Pair<String, String>, Double> costFunction = new Function<Pair<String, String>, Double>() {

			@Override
			public Double apply(Pair<String, String> t) {
				return (double) (t.getA().equals(t.getB()) ? 1 : t.getA().startsWith(t.getB()) | t.getB().startsWith(t.getA()) ? .5 : 0);
			}
		};

		final double[][] expectedCostMatrix = new double[][] { new double[] { 1, .5, 0 }, new double[] { .5, 1, 0 }, new double[] { 0, 0, 1 } };

		//build cost matrix
		double[][] costMatrix = MatrixUtils.buildCostMatrix(sources, targets, costFunction);

		assertMatrixEquals(expectedCostMatrix, costMatrix);

		for (int i = 0; i < costMatrix.length; i++) {
			System.out.println(String.join(" ", Arrays.toString(costMatrix[i])));
		}
		
	}

	private void assertMatrixEquals(double[][] matrixA, double[][] matrixB) {
		Assert.assertEquals(matrixA.length, matrixB.length);
		for (int i = 0; i < matrixA.length; i++) {
			Assert.assertArrayEquals(matrixA[i], matrixB[i], .01);
		}
	}

}
