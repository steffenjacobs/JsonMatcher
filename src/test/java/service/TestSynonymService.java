package service;

import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import me.steffenjacobs.syntacticjsonmatcher.service.SynonymService;

/** @author Steffen Jacobs */
public class TestSynonymService {

	@Test
	public void testSynonymService() {
		Map<String, Integer> result = new SynonymService().findSynonyms("humidity");
		Assert.assertFalse(result.isEmpty());
		Assert.assertTrue(result.containsKey("humidness"));
		Assert.assertTrue(result.get("humidness") > 0);
	}

}
