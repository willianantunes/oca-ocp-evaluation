package br.com.willianantunes.examocp;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class MergeExampleMaps {
	public static void main(String[] args) {
		BiFunction<String, String, String> mapper = (v1, v2) -> { 
			return v1.length() >= v2.length()? v1 : v2;
		};
		
		Map<String, String> favorites = new HashMap<>();
		favorites.put("Jenny", "Bus tour");
		favorites.put("Tom", "Tram");
		
		System.out.println(favorites);
		String jenny = favorites.merge("Jenny", "Skyrideee", mapper);
		System.out.println(favorites);
		String tom = favorites.merge("Tom", "Skyride", mapper);
		System.out.println(favorites);
	}
}
