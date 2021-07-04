import org.paumard.paumard.model.Person;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.ToIntFunction;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class TestA {

    public static void main ( String[] args ){
/*        List<String> strs= Arrays.asList("eat","tea","tan","ate","nat","bat");
        Map<Integer, List<String>> map =
                strs.stream().collect(
                        Collectors.groupingBy(
                                // Person::getAge
                                str-> str.chars().sum()
                        )
                );

        map.forEach((age, list) -> System.out.println(age + " -> " + list));


        System.out.println("hello");*/
        String[] str = {"eat","tea","tan","ate","nat","bat","eta","tae"};
        List<String> list = Arrays.asList(str);
        List<List<String>> out = new ArrayList<>();
        Map<Integer, List<String>> map = list.stream()
                .sorted(Comparator.comparingInt(s->((String)s).charAt(0))
                        .thenComparing(s->((String)s).charAt(1))
                        .thenComparing(s->((String)s).charAt(2)))
                .peek(s->System.out.println(s))
                .collect(
                Collectors.groupingBy(s-> s.chars().sum())
        );
        map.entrySet().stream().forEach(entry-> out.add(entry.getValue()));

        System.out.println(out);


/*       int[] num1 = {1,2,3,4,1,2,1,4,4,5,4,6};
       Map<Integer, Long> out1= Arrays.stream(num1)
                                    .mapToObj(Integer::new)
                                    .collect(Collectors.groupingBy(Function.identity(),Collectors
                                            .counting()))
                                    .entrySet()
                                    .stream()
                                   .peek(m->System.out.println(m))
                                   .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                                    .limit(4)
                                    .collect(Collectors.toMap(entry->entry.getKey(), Map.Entry::getValue, (m1,m2)->m1, LinkedHashMap::new));
       System.out.println(out1);*/

        //water pool calc
/*        int[] waterPool = {0,2,4,0,2,1,2,6,1};
        int[] border = Arrays.stream(waterPool)
                                .mapToObj(Integer::new)
                                .sorted(Comparator.reverseOrder())
                                .limit(2)
                                .collect(Collectors.toList())
                                .stream()
                                .mapToInt(Integer::intValue)
                                .toArray();
        List<Integer> waterPoolList = Arrays.stream(waterPool).boxed().collect(Collectors.toList());
        int head = waterPoolList.indexOf(border[1]);
        int tail = waterPoolList.lastIndexOf(border[0]);
        System.out.println(Arrays.toString(border));
        int[] waterPoolWithOnesideBorder= Arrays.stream(waterPool)
                                .skip(head)
                                .toArray();
        System.out.println(Arrays.toString(waterPoolWithOnesideBorder));
        int[] waterPoolWithBorder = Arrays.copyOfRange(waterPool,head+1,tail);
        System.out.println(Arrays.toString(waterPoolWithBorder));

        int[] waterPool1 = waterPool.clone();
        Integer[] border1 =  Arrays.stream(waterPool1)
                            .boxed()
                            .sorted(Comparator.reverseOrder())
                            .toArray(Integer[]::new);
        System.out.println(Arrays.toString(border1));
        Arrays.sort(border1, (a, b)-> a.compareTo(b));
        System.out.println(Arrays.toString(border1));
        Arrays.sort(border1, Comparator.reverseOrder());
        System.out.println(Arrays.toString(border1));
        int waterVolume = Arrays.stream(waterPoolWithBorder)
                            .map(a-> border[1]-a)
                            .sum();
        System.out.println(waterVolume);*/

        /*        int primeNumber= 4;
        boolean notPrimeNumberFlag =false;
        for (int i=1;i<primeNumber;i++){
            if (primeNumber% i ==0 && i!=1){
                notPrimeNumberFlag = true;
            }
        }

        if (notPrimeNumberFlag) {
            System.out.println("this is not prime number");
        } else {
            System.out.println("this is prime number");
        }

    }*/
        int number = 12321;
/*        int result = number / 10;
        int modNumber = number % 10;
        while (result > 0 && modNumber!=0) {
            int firstbit = (modNumber *10 ) / modNumber;
            if (modNumber != firstbit) {
                System.out.println("this is invalid number");
                return;
            }
            modNumber = result % 10;
            result = result / 10;
        }
        System.out.println("this is valid number");*/
/*    12321
    1221
    12345*/
/*        long result = LongStream.rangeClosed(2,100000000)
                .mapToObj(BigInteger::valueOf)
                .filter(i->i.isProbablePrime(50))
                .count();
        System.out.println("prime number count: "+ result);
        boolean t = (new BigInteger("12321")).isProbablePrime(1);
        System.out.println("check: "+t);*/

   // boolean numberCheck = String.valueOf(number).chars()
        int mid = String.valueOf(number).length() >>1;
        int left = Integer.parseInt(String.valueOf(number).substring(0,mid));
        int[] right = String.valueOf(number).substring(mid+1).chars().toArray();
        StringBuffer output1 = new StringBuffer();
        String right1 = "";
                        Arrays.stream(right)
                        .mapToObj(i->String.valueOf(Character.toChars(i)))
                        .peek(System.out::println)
                        .collect(Collectors.toCollection(LinkedList::new))
                        .descendingIterator()
                        .forEachRemaining(s->output1.append(s));
/*                         .peek(System.out::println)
                        .collect(Collectors.joining());*/
        System.out.println("left:"+ left);
        System.out.println("right::"+ output1.toString());
        System.out.println("result:"+ (Integer.parseInt(output1.toString())==left));



/*        int[] a1 = {1, 3,  8, -5,2};
        BigDecimal sum2= Arrays.stream(a1)
                .mapToObj(BigDecimal::new)
                .reduce(BigDecimal.ONE, BigDecimal::multiply);
        System.out.println("multiply result:"+ sum2);*/

        // Find the maximum repeating character
/*        String str1 = "sdfjsa;dkfj;akdfj;kajjjdf;";
        Map<String, Long> get =str1.chars()
                .mapToObj(i-> String.valueOf(Character.toChars(i)))
                .collect(
                        Collectors.groupingBy(Function.identity(),Collectors.counting())

                );
        String max = get.entrySet().stream()
                    .max((e1,e2)-> e1.getValue().compareTo(e2.getValue()))
                    .get()
                    .getKey();
        System.out.println(get);
        System.out.println(max);*/

        // Find the maximum repeating word
        String[] words = {"hello","world","morning","abc","hello","abc","hello"};
        Map<String,Long> counts = Arrays.asList(words)
                                    .stream()
                                    .collect(
                                            Collectors.groupingBy(
                                                    Function.identity(),
                                                    Collectors.counting()
                                            )
                                    );
        String maxWord = counts.entrySet()
                            .stream()
                            .max((e1,e2)-> e1.getValue().compareTo(e2.getValue()))
                            .get()
                            .getKey();
        System.out.println(maxWord);

        Stream<String> s1= Arrays.asList("a","b","c").stream();
        Stream<String> s2= Arrays.asList("a","b","c").stream();
        int[] a1= {1,2,3,4,5};
        int[] a2={4,3,7,8};
        List<Integer> comp1 = Arrays.stream(a2)
                                .boxed()
                                .collect(Collectors.toList());
        List<Integer> common1= new ArrayList<>();
                Arrays.stream(a1)
                            .boxed()
                            .iterator()
                            .forEachRemaining(
                                    i-> {
                                        if (comp1.contains(i))
                                        common1.add(i);}
                            );
                System.out.println(common1);

        List<Integer> list1 = Arrays.asList(1,2,3,4,5);
        List<Integer> list2 = Arrays.asList(4,3,7,8);

        Set<Integer> setCommon = new HashSet<>(list1);
/*        setCommon.retainAll(list2);
        System.out.println(setCommon);*/

        setCommon = list1.stream()
                .distinct()
                .filter(i -> list2.contains(i))
                .collect(Collectors.toSet());
        System.out.println(setCommon);

        Stream<Integer> stream1 = Stream.of(1,2,3,4);
        Stream<Integer> stream2 = Stream.of(5,6);
        Stream<Integer> stream3 = Stream.of(7,8);
        Stream<Integer> stream4 = Stream.of(stream1,stream2,stream3)
                                        .reduce(Stream::concat)
                                        .get();
        System.out.println(stream4.collect(Collectors.toList()));

        StringJoiner sj = new StringJoiner("");
        sj.add("abc");
        sj.add("def");
        sj.add("gh");
        System.out.println(sj.toString());

/*
        Write a program to remove the Duplicate numbers from an input Array Example
        input -> {1,2,3,4,4,5,6,7,7,7,8} outpout -> {1,2,3,5,6,8}
*/
        int[] output =Arrays.stream(new int[] {1,2,3,4,4,5,6,7,7,7,8})
                .distinct()
                .toArray();
        System.out.println(Arrays.toString(output));

/*        Write a program to replace all vowels in an input string with the corresponding numbers. Ex ( a,e,I,o,u) should replace with 1,2,3,4,5).
        Example : helloworld -> h2ll4w4rld*/

        String vowels = "aeiou";
        String vowelsReplaced = "12345";
        String input2 = "helloworld";
        StringBuilder output2 = new StringBuilder();
       input2.chars()
                                .map(c->{
                                    int index = vowels.indexOf(c);
                                    if (index>=0)
                                        return vowelsReplaced.charAt(index);
                                    else
                                        return c;})
                                .forEach(c-> {output2.append(Character.toChars(c));});
        System.out.println(output2.toString());

        //How to count vowels in Java through functional programming?
/*        List<String> l = Arrays.asList("hello", "world", "test");
        int vowelsSum = l.stream().map(s -> s.replaceAll("[^aeiou]", "").length())
                .reduce(0, Integer::sum);
        System.out.println(vowelsSum);*/
        System.out.println((new TestA()).replaceVowels(input2));

        List<String> l = new ArrayList<>(Arrays.asList("abc", "cde", "eft"));
        try {
            l.removeIf(str1->str1.startsWith("a"));
        } catch ( Exception e) {
        throw new UnsupportedOperationException(); };

        l.forEach(System.out::println);

        Consumer c1 = s-> System.out.println("consumer1:"+s);
        Consumer c2 = s-> System.out.println("consumer2:"+s);
        Consumer c3 = c1.andThen(c2);
        c3.accept("Hello");

        List<String> names= Arrays.asList("abc","peter","jothson","amy","keith");
        ToIntFunction<String> toLength = s->s.length();
        Comparator cmp = Comparator.comparingInt(toLength);
        names.sort(cmp);
        System.out.println(names);


        int n = 10;

        Function<Integer, Integer> g = x -> x; // Or Function.identity()
        Function<Integer, Integer> f = x -> x + 1;

        for (int i = 0; i < n; i++) {
            g = g.compose(f);
        };

/*        g = Collections.nCopies(n, f)
                .stream()
                .reduce(g, Function::compose);*/

        System.out.println(g.apply(0));

        CurriedFunction3 f1 =
                s -> i -> l1 -> s + i + l1;

        System.out.println(f1.apply((short)1).apply(2).apply(3L));

        Function<Double, Function<Double, Double>> calculateTax = rate -> amount -> amount * rate;

        Function<Double, Double> calculateNationalTax = calculateTax.apply(0.2);
        Function<Double, Double> calculateInternationalTax = calculateTax.apply(0.3);

        System.out.println( calculateNationalTax.apply(100.0) );
        System.out.println( calculateInternationalTax.apply(100.0) );

/*        Scanner in = new Scanner(System.in);
        System.out.print("Input a number: ");
        int nn = in.nextInt();
        int sum = 0, r;
        int temp = nn;
        while(nn>0)
        {
            r = nn % 10;
            sum = (sum*10)+r;
            nn = nn/10;
        }
        if(temp==sum)
            System.out.println("It is a Palindrome number.");
        else
            System.out.println("Not a palindrome");*/
        String str1 = "abc";
        String str2 = "bca";
        Function<String,Integer> calc1 = w-> w.chars().sum();
        Comparator<String> cmp1 = (ww1,ww2) -> calc1.apply(ww1) - calc1.apply(ww2);
        BiPredicate<String,String> equalCheck = (str33,str44) -> calc1.apply(str33).equals(calc1.apply(str44));
        System.out.println(equalCheck.test(str1,str2));

        String str111 = "asfdasdf asdfasfd sdkkk";
        String[] out111 = str111.split("\\W+");
        System.out.println(Arrays.toString(out111));


        String ss1 = "developper";
        String ss2 = "programmer";

        String commons = CommonCharsFinder.findCommonChars(ss1, ss2);
        System.out.println(commons);


}

    public String replaceVowels(String inputStr) {

        return inputStr.replaceAll("[aA]","1").
                replaceAll("[eE]","2").
                replaceAll("[iI]","3").
                replaceAll("[oO]","4").
                replaceAll("[uU]","5");

    }

/*    Your code will duplicate common characters for example if you compare "developper" to "programmer" your result string will contain three time the e character

    If you don't want that behaviour I suggest that you also use a Set like this:*/



}


interface CurriedFunction3 extends
        Function<Short, Function<Integer, Function<Long, Long>>> {}


 class CommonCharsFinder {

    static String findCommonChars(String a, String b) {
        StringBuilder resultBuilder = new StringBuilder();
        Set<Character> charsMap = new HashSet<Character>();
        for (int i = 0; i < a.length(); i++) {
            char ch = a.charAt(i); //a and b are the two words given by the user
            if (b.indexOf(ch) != -1) {
                charsMap.add(Character.valueOf(ch));
            }
        }

        Iterator<Character> charsIterator = charsMap.iterator();
        while (charsIterator.hasNext()) {
            resultBuilder.append(charsIterator.next().charValue());
        }
        return resultBuilder.toString();
    }
}

