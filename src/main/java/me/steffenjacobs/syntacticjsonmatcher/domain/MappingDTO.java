package me.steffenjacobs.syntacticjsonmatcher.domain;

import java.util.function.Function;

/** @author Steffen Jacobs */
public class MappingDTO<S, T> {
	private final String keySource;
	private final String keyTarget;
	private final double matchRate;

	private final Function<S, T> valueTransformation;

	public MappingDTO(String keySource, String keyTarget, Function<S, T> valueTransformation, double matchRate) {
		super();
		this.keySource = keySource;
		this.keyTarget = keyTarget;
		this.valueTransformation = valueTransformation;
		this.matchRate = matchRate;
	}

	public String getKeySource() {
		return keySource;
	}

	public String getKeyTarget() {
		return keyTarget;
	}

	public double getMatchRate() {
		return matchRate;
	}

	public Function<S, T> getValueTransformation() {
		return valueTransformation;
	}
}
