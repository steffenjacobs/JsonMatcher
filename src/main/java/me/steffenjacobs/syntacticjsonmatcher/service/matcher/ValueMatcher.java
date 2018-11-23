package me.steffenjacobs.syntacticjsonmatcher.service.matcher;

import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import me.steffenjacobs.syntacticjsonmatcher.service.UnitConversionService;
import me.steffenjacobs.syntacticjsonmatcher.util.Pair;

/** @author Steffen Jacobs */
public class ValueMatcher {

	private static final Pattern PATTERN_NUMBER_WITHOUT_UNIT = Pattern.compile("(\\d*\\.?\\d+)");
	private final UnitConversionService unitConversionService = new UnitConversionService();

	public Function<Object, Object> matchValues(Object source, Object target) {

		// TODO handle unit in string and do conversion

		// same types
		if (source.getClass() == target.getClass()) {

			// both strings
			if (source instanceof String) {
				// unit conversion possible
				final Pair<Double, String> numberWithUnit = findNumberWithUnit((String) target);
				if (numberWithUnit.getB() != null && !"".equals(numberWithUnit.getB())) {
					return t -> {
						final Pair<Double, String> numberWithUnitSrc = findNumberWithUnit((String) t);
						if (!"".equals(numberWithUnitSrc.getB())) {
							return unitConversionService.convert((String) t, numberWithUnit.getB());
						}
						return t;
					};
				}
				return t -> t;
			}
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
						return findNumberWithUnit((String) t).getA();
					}
				};
			} else {
				return t -> {
					try {
						return (long) Double.parseDouble((String) t);
					} catch (NumberFormatException e) {
						// probably a unit in the string -> find number without
						// unit
						final double numberWithoutUnit = findNumberWithUnit((String) t).getA();
						return numberWithoutUnit != Double.NaN ? (long) numberWithoutUnit : -1l;
					}
				};
			}
		} else {
			return t -> null;
		}
	}

	public Pair<Double, String> findNumberWithUnit(String numberWithUnit) {
		Matcher matcher = PATTERN_NUMBER_WITHOUT_UNIT.matcher(numberWithUnit);
		if (!matcher.find()) {
			return new Pair<>(Double.NaN, numberWithUnit);
		}
		String result = matcher.group(1);
		try {
			return new Pair<>(Double.parseDouble(result), matcher.replaceFirst(""));
		} catch (NumberFormatException e) {
			return new Pair<>(Double.NaN, numberWithUnit);
		}
	}

	private boolean isFloatingPoint(Number obj) {
		return (obj.doubleValue() != Math.ceil(obj.doubleValue()));
	}
}
