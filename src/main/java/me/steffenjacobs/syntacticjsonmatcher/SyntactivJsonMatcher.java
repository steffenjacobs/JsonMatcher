package me.steffenjacobs.syntacticjsonmatcher;

import java.util.Collection;
import java.util.Scanner;

import me.steffenjacobs.syntacticjsonmatcher.domain.MappingDTO;
import me.steffenjacobs.syntacticjsonmatcher.service.PrintingService;

/** @author Steffen Jacobs */
public class SyntactivJsonMatcher {

	public static void main(String[] args) {

		System.out.println("* * * * * * * * * * * * * * * * * * * * *");
		System.out.println("* * Welcome to Syntactic JSON Matcher * *");
		System.out.println("* * * * by Steffen Jacobs, 2018 * * * * *");
		System.out.println("* * * * * * * * * * * * * * * * * * * * *");
		System.out.println();
		Scanner scanner = new Scanner(System.in);

		Mapper mapper = new Mapper();
		PrintingService printingService = new PrintingService();
		boolean running = true;
		while (running) {
			System.out.println("Please enter the first JSON String to match.");
			System.out.print(">");
			String json1 = scanner.nextLine();
			System.out.println("Please enter the second JSON String to match.");
			System.out.print(">");
			String json2 = scanner.nextLine();
			System.out.println("Matching...");
			Collection<MappingDTO<Object, Object>> mappings = mapper.map(json1, json2);
			System.out.println("Done! Results:");
			for (MappingDTO<Object, Object> mapping : mappings) {
				System.out.println(printingService.mappingToString(json1, json2, mapping, false));
			}
		}

		scanner.close();
	}

}
