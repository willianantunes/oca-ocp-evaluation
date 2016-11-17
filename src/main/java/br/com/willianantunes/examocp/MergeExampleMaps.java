package br.com.willianantunes.examocp;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class MergeExampleMaps {
	public static void main(String[] args) {
		BiFunction<String, String, String> mapper = (v1, v2) -> { 
			boolean result = v1.length() >= v2.length();
			System.out.println(String.format("v1: %s / v2: %s ==> Result: %s", v1, v2, result));
			// Like "Do I must keep the original one?
			return result? v1 : v2;
		};
		
		Map<String, String> favorites = new HashMap<>();
		favorites.put("Jenny", "Bus tour");
		favorites.put("Tom", "Tram");
		
		System.out.println(favorites); // {Tom=Tram, Jenny=Bus tour}
		String jenny = favorites.merge("Jenny", "Skyrideee", mapper);
		System.out.println(favorites); // {Tom=Tram, Jenny=Skyrideee}
		String tom = favorites.merge("Tom", "Skyride", mapper);
		System.out.println(favorites); // {Tom=Skyride, Jenny=Skyrideee}
		String antunes = favorites.merge("Antunes", "HWorld", mapper);
		System.out.println(favorites); // {Tom=Skyride, Jenny=Skyrideee, Antunes=HWorld}
		String antunes2 = favorites.merge("Antunes", "HWor", mapper);
		System.out.println(favorites); // {Tom=Skyride, Jenny=Skyrideee, Antunes=HWorld}
		
		/*
		{Tom=Tram, Jenny=Bus tour}
		v1: Bus tour / v2: Skyrideee ==> Result: false
		{Tom=Tram, Jenny=Skyrideee}
		v1: Tram / v2: Skyride ==> Result: false
		{Tom=Skyride, Jenny=Skyrideee}
		{Tom=Skyride, Jenny=Skyrideee, Antunes=HWorld}
		v1: HWorld / v2: HWor ==> Result: true
		{Tom=Skyride, Jenny=Skyrideee, Antunes=HWorld}
		*/
	}
}
