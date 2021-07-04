
package org.paumard.stream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.paumard.MergingMaps;
import org.paumard.stream.model.Person;

/**
 *
 * @author José
 */
public class CollectorsExample {

    public static void main(String... args)  {
        
        List<Person> persons = new ArrayList<>();
        
        try (

                BufferedReader reader =
                new BufferedReader(
                    new InputStreamReader(
                            MergingMaps.class.getResourceAsStream("people.txt")));

                Stream<String> stream = reader.lines();
        ) {

            stream.map(line -> {
                String[] s = line.split(" ");
                Person p = new Person(s[0].trim(), Integer.parseInt(s[1]));
                persons.add(p);
                return p;
                    })
                    .forEach(System.out::println);
                
            
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
        
        Optional<Person> opt = 
        persons.stream().filter(p -> p.getAge() >= 20)
                .min(Comparator.comparing(Person::getAge));
        System.out.println(opt);
        
        Optional<Person> opt2 = 
        persons.stream().max(Comparator.comparing(Person::getAge));
        System.out.println(opt2);
        
        Map<Integer, String> map = 
        persons.stream()
                .collect(
                        Collectors.groupingBy(
                                Person::getAge, 
                                Collectors.mapping(
                                        Person::getName, 
                                        Collectors.joining(", ")
                                )
                        )
                );
        System.out.println(map);

        Map<String, Set<String>> map1 =
                persons.stream()
                        .collect(
                                Collectors.groupingBy(
                                        p->p.getName().substring(0,1),
                                        TreeMap::new,
                                        Collectors.mapping(p->p.getName(), Collectors.toSet())

                                        )
                                )
                        ;
        System.out.println(map1);

        Map<String, List<String>> map2 =
                persons.stream()
                        .collect(
                                Collectors.groupingBy(
                                        p->p.getName().substring(0,1),
                                        TreeMap::new,
                                        Collectors.mapping(p->p.getName(), Collectors.toList())

                                )
                        )
                ;
        System.out.println(map2);

        Map<String, Map<String,Integer>> map3 =
                persons.stream()
                        .collect(
                                Collectors.groupingBy(
                                        p->p.getName().substring(0,1),
                                        Collector.of(HashMap::new,
                                                (m,p)-> {
                                                            m.put(p.getName(),p.getAge());},
                                                (m1,m2)->{
                                                        m2.entrySet().forEach(
                                                        entry-> m1.put(entry.getKey(),entry.getValue()));
                                                        return m1;
                                                              }
                                                ,
                                                Collector.Characteristics.IDENTITY_FINISH
                                        )
                                )
                        )


                ;
        System.out.println(map3);
        EnumMap enumMap = null;
    }
}
