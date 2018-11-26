package me.steffenjacobs.jsonmatcher.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

import me.steffenjacobs.jsonmatcher.domain.MatchResult;

/** @author Steffen Jacobs */
public class TestMatchResult {

	@Test
	public void testHashcodeEqualsSame() {
		MatchResult matchResult = new MatchResult(1, "test1", "test2");
		MatchResult matchResult2 = new MatchResult(3, "test1", "test2");

		assertEquals(matchResult.hashCode(), matchResult2.hashCode());
		assertEquals(matchResult, matchResult2);
	}

	@Test
	public void testHashcodeEqualsInverse() {
		MatchResult matchResult = new MatchResult(1, "test1", "test2");
		MatchResult matchResult2 = new MatchResult(3, "test2", "test1");

		assertEquals(matchResult.hashCode(), matchResult2.hashCode());
		assertEquals(matchResult, matchResult2);
	}

	@Test
	public void testHashcodeEqualsFalse() {
		MatchResult matchResult = new MatchResult(1, "test1", "test2");
		MatchResult matchResult2 = new MatchResult(1, "test1", "test1");

		assertFalse(matchResult.hashCode() == matchResult2.hashCode());
		assertFalse(matchResult.equals(matchResult2));
	}

}
