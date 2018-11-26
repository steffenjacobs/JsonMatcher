package me.steffenjacobs.jsonmatcher.domain;

/** @author Steffen Jacobs */
public class MatchResult {
	private final double matchRate;

	private final String key1;
	private final String key2;

	public MatchResult(double matchRate, String key1, String key2) {
		super();
		this.matchRate = matchRate;
		this.key1 = key1;
		this.key2 = key2;
	}

	public double getMatchRate() {
		return matchRate;
	}

	public String getKey1() {
		return key1;
	}

	public String getKey2() {
		return key2;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((key1 == null) ? 0 : key1.hashCode()) + ((key2 == null) ? 0 : key2.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MatchResult other = (MatchResult) obj;
		if (key1 == null) {
			if (other.key1 != null && other.key2 != null)
				return false;
		} else if (!key1.equals(other.key1) && !key1.equals(other.key2))
			return false;
		if (key2 == null) {
			if (other.key2 != null && other.key1 != null)
				return false;
		} else if (!key2.equals(other.key2) && !key2.equals(other.key1))
			return false;
		return true;
	}

}
