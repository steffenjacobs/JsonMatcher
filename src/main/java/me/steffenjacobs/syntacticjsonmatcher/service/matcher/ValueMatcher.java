package me.steffenjacobs.syntacticjsonmatcher.service.matcher;

import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** @author Steffen Jacobs */
public class ValueMatcher {

	private static final Pattern PATTERN_NUMBER_WITHOUT_UNIT = Pattern.compile("(\\d*\\.?\\d+)");

	public Function<Object, Object> matchValues(Object source, Object target) {

		// TODO handle unit in string and do conversion

		// same types
		if (source.getClass() == target.getClass()) {
			return t -> t;
		}

		// both numbers -> use doubleValue
		else if (source instanceof Number && target instanceof Number) {
			if (isFloatingPoint((Number) source)) {
				return t -> ((Number) t).doubleValue();
			} else {
				return t -> ((Number) t).longValue();
			}
		}

		// source is number and target is string -> convert to string
		else if (source instanceof Number && target instanceof String) {
			return Object::toString;
		}
		// source is string and target is number -> convert to number
		else if (source instanceof String && target instanceof Number) {
			if (isFloatingPoint((Number) target)) {
				return t -> {
					try {
						return Double.parseDouble((String) t);
					} catch (NumberFormatException e) {
						// probably a unit in the string -> find number without
						// unit
						return findNumberWithoutUnit((String) t);
					}
				};
			} else {
				return t -> {
					try {
						return (long) Double.parseDouble((String) t);
					} catch (NumberFormatException e) {
						// probably a unit in the string -> find number without
						// unit
						final double numberWithoutUnit = findNumberWithoutUnit((String) t);
						return numberWithoutUnit != Double.NaN ? (long) numberWithoutUnit : -1l;
					}
				};
			}
		} else {
			return t -> null;
		}
	}

	private double findNumberWithoutUnit(String numberWithUnit) {
		Matcher matcher = PATTERN_NUMBER_WITHOUT_UNIT.matcher(numberWithUnit);
		if (!matcher.find()) {
			return Double.NaN;
		}
		String result = matcher.group(1);
		try {
			return Double.parseDouble(result);
		} catch (NumberFormatException e) {
			return Double.NaN;
		}
	}

	private boolean isFloatingPoint(Number obj) {
		return (obj.doubleValue() != Math.ceil(obj.doubleValue()));
	}
}
