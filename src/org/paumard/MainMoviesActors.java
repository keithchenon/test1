
package org.paumard;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.Comparator;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.paumard.model.Actor;
import org.paumard.model.Movie;

/**
 *
 * @author José Paumard
 */
public class MainMoviesActors {

    public static void main(String... args) throws IOException {

        Set<Movie> movies = new HashSet<>();

        Stream<String> lines
                = Files.lines(
                        Paths.get("files", "movies-mpaa.txt")
                );

        lines.forEach(
                (String line) -> {
                    String[] elements = line.split("/");
                    String title = 
                            elements[0].substring(0, elements[0].lastIndexOf("(")).trim();
                    String releaseYear = 
                            elements[0].substring(elements[0].lastIndexOf("(") + 1, elements[0].lastIndexOf(")"));

                    if (releaseYear.contains(",")) {
                        // with skip movies with a coma in their title
                        return;
                    }

                    Movie movie = new Movie(title, Integer.valueOf(releaseYear));

                    for (int i = 1; i < elements.length; i++) {
                        String[] name = elements[i].split(", ");
                        String lastName = name[0].trim();
                        String firstName = "";
                        if (name.length > 1) {
                            firstName = name[1].trim();
                        }

                        Actor actor = new Actor(lastName, firstName);
                        movie.addActor(actor);
                    }

                    movies.add(movie);
                }
        );
        
        System.out.println("# movies = " + movies.size()) ;

        String output=movies.stream().collect(StringBuilder::new, (sb, movie)-> sb.append(movie.title()).append(" "+movie.releaseYear()).append("\n"), StringBuilder::append).toString();
        System.out.print(output.length());
        // # of actors
        long numberOfActors = movies.stream()
                .flatMap(movie -> movie.actors().stream()) // Stream<Actors>
                .distinct()
                .count();
        
        System.out.println("# of actors : " + numberOfActors);
        
        // # of actors that played in the greatest # of movies
        // Map<Actor, Long> collect =
        Entry<Actor, Long> mostViewedActor =
        movies.stream()
                .flatMap(movie -> movie.actors().stream()) // Stream<Actors>
                .collect(
                        Collectors.groupingBy(
                                Function.identity(), 
                                Collectors.counting()
                        )
                )
                .entrySet().stream() // Stream<Map.Entry<Actor, Long>>
                .max(
                        // Comparator.comparing(entry -> entry.getValue())
                        Entry.comparingByValue()
                )
                .get();
        System.out.println("Most viewed actor : " + mostViewedActor);
        
        // actor that played in the greatest # of movies during a year
        // Map<release years, Map<Actor, # of movies during that year>>
        // Map<Integer, HashMap<Actor, AtomicLong>> collect =
       Entry<Integer, Entry<Actor, AtomicLong>> get =
 //       Map<Integer, Map.Entry<Actor, AtomicLong>> get =

                movies.stream()
                .collect(
                        Collectors.groupingBy(
                                movie -> movie.releaseYear(), 
                                Collector.of(
                                        () -> new HashMap<Actor, AtomicLong>(),// supplier
                                        (map, movie) -> {
                                            movie.actors().forEach(
                                                    actor -> map.computeIfAbsent(actor, a -> new AtomicLong()).incrementAndGet()
                                            );
                                        }, // accumulator
                                        (map1, map2) -> {
                                            map2.entrySet().forEach(
                                                    entry2 -> map1.merge(
                                                            entry2.getKey(), entry2.getValue(),
                                                            (al1, al2) -> {
                                                                al1.addAndGet(al2.get());
                                                                return al1;
                                                            }
                                                    )
                                            );
                                            return map1;
                                        }, // combiner
                                        Collector.Characteristics.IDENTITY_FINISH
                                )
                        )
                ) // Map<Integer, HashMap<Actor, AtomicLong>>
                .entrySet().stream()
                .collect(
                        Collectors.toMap(
                                entry -> entry.getKey(),
                                entry -> entry.getValue().entrySet().stream()
                                        .max(
                                                Entry.comparingByValue(Comparator.comparing(l -> l.get()))
                                        )
                                        .get()

                        )
                ) // Map<Integer, Map.Entry<Actor, AtomicLong>>
                .entrySet().stream()
                .max(
                        Entry.comparingByValue(
                                Comparator.comparing(
                                        entry -> entry.getValue().get()
                                )
                        )
                )
                .get();
        // Map.Entry<Integer, Map.Entry<Actor, AtomicLong>>
        System.out.println(get);

        Map<Actor, Set<String>> result1 = movies.stream()
                                                .collect(HashMap::new, (map, movie)-> {
                                                    movie.actors().forEach(
                                                            actor->
                                                                map.computeIfAbsent(actor, (unused)-> new TreeSet<>()).add(movie.title()+ movie.releaseYear())
                                                            );}
                                                ,(map1, map2) -> {
                                                            map2.entrySet().forEach(
                                                                    entry2 -> map1.merge(
                                                                            entry2.getKey(), entry2.getValue(),
                                                                            (al1, al2) -> {
                                                                                al1.addAll(al2);
                                                                                return al1;
                                                                            }
                                                                    )
                                                            );
                                                           // return map1;
                                                        });
        System.out.println(result1.size());

        Entry<Actor, Set<String>> result2Entry = result1.entrySet().stream()
                .max((e1,e2)->e1.getValue().size()-e2.getValue().size())
                .get();
        System.out.println(result2Entry.getKey().toString()+ result2Entry.getValue().size());
        String result2=                result2Entry.getValue().stream().collect(
                        StringBuilder::new, (sb, s)-> sb.append(s+"\n"), StringBuilder::append
                ).toString();
        System.out.println(result2);
        System.out.println("actor played less *********************");
        String result3 = result1.entrySet().stream()
                .min((e1,e2)->e1.getValue().size()-e2.getValue().size()).get().getValue().stream().collect(
                        StringBuilder::new, (sb, s)-> sb.append(s+"\n"), StringBuilder::append
                ).toString();
        System.out.println(result3);

        long numberOfActors1 = movies.stream().flatMap(movie -> movie.actors().stream()).distinct().count();
        System.out.println(numberOfActors1);

        Pattern namePattern = Pattern.compile("^W(\\S*)r$");
        String actorList = result1.entrySet().stream()
                .filter(entry-> "Frank".equalsIgnoreCase(entry.getKey().firstName) && namePattern.matcher(entry.getKey().lastName()).matches())
                .map(entry->entry.getKey().firstName+" "+ entry.getKey().lastName)
                .collect(StringBuilder::new, (sb,s)->sb.append(s+","), StringBuilder::append).toString();
        System.out.println("Frank:"+actorList);

    }
}
