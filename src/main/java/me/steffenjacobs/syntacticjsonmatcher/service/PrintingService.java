package me.steffenjacobs.syntacticjsonmatcher.service;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Collection;

import org.json.JSONObject;

import me.steffenjacobs.syntacticjsonmatcher.domain.MappingDTO;

/** @author Steffen Jacobs */
public class PrintingService {

	private final NumberFormat nf = new DecimalFormat("0.##");

	public void printAlternatives(Collection<Collection<MappingDTO<Object, Object>>> alternatives) {
		int count = 0;
		for (Collection<MappingDTO<Object, Object>> alternative : alternatives) {
			System.out.println("Permutation " + count + " (" + calculateMatchRateForAlternative(alternative) + ")");
			count++;
			for (MappingDTO<Object, Object> mapping : alternative) {
				System.out.println(mapping.getKeySource() + " -> " + mapping.getKeyTarget());
				if (!mapping.getKeySource().equals(mapping.getKeyTarget())) {
					System.out.println(mapping.getKeyTarget() + " -> " + mapping.getKeySource());
				}
			}
			System.out.println();
		}
	}

	public String formatDecisionString(String source, String target, double value, String reason) {
		StringBuilder sb = new StringBuilder();
		sb.append(value);
		sb.append(": (");
		sb.append(reason);
		sb.append(") ");
		sb.append(source);
		sb.append(" -> ");
		sb.append(target);
		return sb.toString();
	}

	/** @return Rhe average match rate for {@link #alternative}. */
	private double calculateMatchRateForAlternative(Collection<MappingDTO<Object, Object>> alternative) {
		double sumMatchRate = 0d;
		for (MappingDTO<Object, Object> mapping : alternative) {
			sumMatchRate += mapping.getMatchRate();
		}
		return sumMatchRate / alternative.size();
	}

	public String mappingToString(String source, String target, MappingDTO<Object, Object> mapping, boolean showJson) {
		JSONObject jsonSource = new JSONObject(source);
		JSONObject jsonTarget = new JSONObject(target);
		StringBuilder sb = new StringBuilder();
		if (showJson) {
			sb.append(source);
			sb.append(" -> ");
			sb.append(target);
			sb.append(": ");
		}
		sb.append("Confidence: ");
		sb.append(nf.format(1 - mapping.getMatchRate()));
		sb.append(" ");
		sb.append(mapping.getKeySource());
		sb.append(" -> ");
		sb.append(mapping.getKeyTarget());
		sb.append(": ");

		final Object sourceValue = jsonSource.get(mapping.getKeySource());
		sb.append("(");
		sb.append(className(sourceValue));
		sb.append(")");
		sb.append(sourceValue);
		sb.append(" -> ");

		final Object transformationResult = mapping.getValueTransformation().apply(sourceValue);
		sb.append("(");
		sb.append(className(transformationResult));
		sb.append(")");
		sb.append(transformationResult);

		sb.append(" Target: ");
		final Object expectedTargetValue = jsonTarget.get(mapping.getKeyTarget());
		sb.append("(");
		sb.append(className(expectedTargetValue));
		sb.append(")");
		sb.append(expectedTargetValue);

		return sb.toString();
	}

	private String className(Object obj) {
		return obj.getClass().getSimpleName();
	}

}
