package me.steffenjacobs.syntacticjsonmatcher.service;

import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

/** @author Steffen Jacobs */
public class TestSynonymService {

	@Test
	public void testSynonymService() {
		Map<String, Integer> result = new SynonymService().findSynonyms("humidity");
		Assert.assertFalse(result.isEmpty());
		Assert.assertTrue(result.containsKey("humidness"));
		Assert.assertTrue(result.get("humidness") > 0);
	}

	@Test
	public void testSynonymService2() {
		Map<String, Integer> result = new SynonymService().findSynonyms("air pressure");
		Assert.assertTrue(result.isEmpty());
	}

}
