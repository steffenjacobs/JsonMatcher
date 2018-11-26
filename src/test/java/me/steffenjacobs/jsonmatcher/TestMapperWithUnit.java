package me.steffenjacobs.jsonmatcher;

import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;

import me.steffenjacobs.jsonmatcher.Mapper;
import me.steffenjacobs.jsonmatcher.domain.MappingDTO;
import me.steffenjacobs.jsonmatcher.service.PrintingService;

/** @author Steffen Jacobs */
public class TestMapperWithUnit {
	
	@Test
	public void testMappingWithUnits(){
		Mapper mapper = new Mapper();
		final PrintingService printingService = new PrintingService();
		
		
		final String source = "{\"temperature\": \"23.1°C\"}";
		final String target = "{\"temperature\": \"73.58°F\"}";
		
		Collection<MappingDTO<Object,Object>> map = mapper.map(source, target);
		for(MappingDTO<Object, Object> mapping : map){
			Assert.assertEquals("73.58 °F", mapping.getValueTransformation().apply("23.1°C"));
			System.out.println(printingService.mappingToString(source, target, mapping, false));
			
		}
	}
}
