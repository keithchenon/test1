
package org.paumard.collections;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.paumard.collections.model.Actor;
import org.paumard.collections.model.Movie;
import org.paumard.collections.model.MovieReader;

public class ConcurrentHashMapParallelPatterrns {

	public static void main(String[] args) {

		ConcurrentHashMap<Actor, Set<Movie>> map = new ConcurrentHashMap<>();

		MovieReader reader = new MovieReader();
		reader.addActorsToMap(map);
		
		System.out.println("# Actors = " + map.size());
		
		int maxMoviesForOneActor = map.reduce(10, (actor, movies) -> movies.size(), Integer::max);
		System.out.println("Max movies for one actor = " + maxMoviesForOneActor);
		
		Actor mostSeenActor = 
				map.search(10, (actor, movies) -> movies.size() == maxMoviesForOneActor ? actor : null);
		System.out.println("Most seen actor = " + mostSeenActor);
		
		int numberOfMoviesReferences = map.reduce(10, (actor, movies) -> movies.size(), Integer::sum);
		
		System.out.println("Average movies per actor = " + numberOfMoviesReferences/map.size());

		ConcurrentSkipListSet<String> words = new ConcurrentSkipListSet<>();
		words = map.keySet().stream().map(actor -> actor.lastName())
				.collect(Collectors.toCollection(ConcurrentSkipListSet::new));
		System.out.println("words size: "+words.size());
		Set<Character> characters = new HashSet<>();
		words.forEach( w-> {for (char c: w.toCharArray()) {
			characters.add(c);
		}});
		System.out.println("characters: "+characters);
		System.out.println("characters size: "+characters.size());
		ConcurrentHashMap<Character,Long> charFrequencesMap = words.stream().collect(ConcurrentHashMap::new,
				(m,s)-> {wordConvertor(s).entrySet().stream().forEach(
						entry-> m.merge(entry.getKey(),entry.getValue(),(v1,v2)->Math.max(v1,v2))
				);},(map1,map2)->{map2.entrySet().stream().forEach(
						entry1-> map1.merge(entry1.getKey(), entry1.getValue(), (vl1,vl2)->Math.max(vl1,vl2))
				);});
		System.out.println(charFrequencesMap);
		System.out.println("map size:"+charFrequencesMap.size());
	}

	public static Map<Character,Long> wordConvertor(String str){
		return str.chars().mapToObj(i->Character.valueOf((char)i)).collect(
				Collectors.groupingBy(Function.identity(),Collectors.counting()));
	}
}
