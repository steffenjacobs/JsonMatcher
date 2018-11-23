package me.steffenjacobs.syntacticjsonmatcher.service.matcher;

import org.junit.Assert;
import org.junit.Test;

import me.steffenjacobs.syntacticjsonmatcher.util.Pair;

/** @author Steffen Jacobs */
public class TestValueMatcher {

	@Test
	public void testFindNumberWithUnitInt() {
		final ValueMatcher valueMatcher = new ValueMatcher();
		Pair<Double, String> findNumberWithUnit = valueMatcher.findNumberWithUnit("25 °C");
		Assert.assertEquals((Double) 25d, findNumberWithUnit.getA());
		Assert.assertEquals(" °C", findNumberWithUnit.getB());
	}

	@Test
	public void testFindNumberWithUnitFloat() {
		final ValueMatcher valueMatcher = new ValueMatcher();
		Pair<Double, String> findNumberWithUnit = valueMatcher.findNumberWithUnit("23.1 °C");
		Assert.assertEquals((Double) 23.1d, findNumberWithUnit.getA());
		Assert.assertEquals(" °C", findNumberWithUnit.getB());
	}

}
