package me.steffenjacobs.jsonmatcher.service;

import org.junit.Assert;
import org.junit.Test;

import me.steffenjacobs.jsonmatcher.service.HungarianAlgorithm;
import me.steffenjacobs.jsonmatcher.util.MatrixUtils;

/** @author Steffen Jacobs */
public class TestHungrarianAlgorithm {

	@Test
	public void testHungarianAlgorithm2x2() {
		final double[][] initialCostMatrix = new double[][] { new double[] { 1, .5 }, new double[] { .5, 1 } };
		final double[][] initialCostMatrix2 = MatrixUtils.deepCopy(initialCostMatrix);

		int[][] assignment = new HungarianAlgorithm(initialCostMatrix).findOptimalAssignment();

		double sum = 0;
		for (int i = 0; i < assignment.length; i++) {
			final double value = initialCostMatrix2[assignment[i][1]][assignment[i][0]];
			sum += value;
		}

		Assert.assertEquals(1, sum, .01);
	}

	@Test
	public void testHungarianAlgorithm3x3() {
		final double[][] initialCostMatrix = new double[][] { new double[] { 250, 400, 350 }, new double[] { 400, 600, 350 }, new double[] { 200, 400, 250 } };
		final double[][] initialCostMatrix2 = MatrixUtils.deepCopy(initialCostMatrix);

		int[][] assignment = new HungarianAlgorithm(initialCostMatrix).findOptimalAssignment();

		int sum = 0;
		for (int i = 0; i < assignment.length; i++) {
			final double value = initialCostMatrix2[assignment[i][1]][assignment[i][0]];
			sum += value;
		}

		Assert.assertEquals(950, sum);
	}

	@Test
	public void testHungarianAlgorithm4x4() {
		final double[][] initialCostMatrix = new double[][] { new double[] { 90, 75, 75, 80 }, new double[] { 435, 85, 55, 65 }, new double[] { 125, 95, 90, 105 },
				new double[] { 45, 110, 95, 115 } };
		final double[][] initialCostMatrix2 = MatrixUtils.deepCopy(initialCostMatrix);

		int[][] assignment = new HungarianAlgorithm(initialCostMatrix).findOptimalAssignment();

		int sum = 0;
		for (int i = 0; i < assignment.length; i++) {
			final double value = initialCostMatrix2[assignment[i][1]][assignment[i][0]];
			sum += value;
		}

		Assert.assertEquals(275, sum);
	}

}
