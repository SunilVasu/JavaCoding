package com.java.streams;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.StringJoiner;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Streams {

	public static void main(String[] args) {
		System.out.println("Hello World! Java 8");

		List<Integer> list = new ArrayList<>();
		list.add(10);
		list.add(20);
		list.add(30);
		list.add(40);
		list.add(50);

		streams(list);
		strings(list);
		optionals();
	}

	public static void streams(List<Integer> list) {
		long cnt = list.stream().filter(x -> x > 30).count();
		System.out.println("\n#cnt (>30) = " + cnt);
		System.out.println("#Iterate = ");
		Stream.iterate(1, c -> c + 1).filter(x -> x % 13 == 0).limit(3).forEach(System.out::println);

		List<Integer> collection = Stream.iterate(1, c -> c + 1).filter(x -> x % 13 == 0).limit(3)
				.collect(Collectors.toList());
		System.out.println("#collection as List = " + collection);

		Set<Integer> set = Stream.iterate(1, c -> c + 1).filter(x -> x % 13 == 0).limit(3).collect(Collectors.toSet());
		System.out.println("#collection as Set = " + set);

		Double avg = Stream.iterate(1, c -> c + 1).filter(x -> x % 13 == 0).limit(3)
				.collect(Collectors.averagingInt(x -> x));
		System.out.println("#collection avg = " + avg);

		Map<Integer, Long> grp = Stream.iterate(1, c -> c + 1).filter(x -> x % 13 == 0).limit(3)
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		System.out.println("#collection grp = " + grp);
	}

	public static void strings(List<Integer> list) {
		StringJoiner sj = new StringJoiner("-");
		sj.add("Hello").add("World").add("Welcome");
		System.out.println("\n#StringJoiner = " + sj);

		sj = new StringJoiner("-", "(", ")");
		sj.add("Hello").add("World").add("Welcome");
		System.out.println("#StringJoiner2 = " + sj);

		String str = Stream.iterate(1, c -> c * 2).filter(x -> x % 2 == 0).limit(5).map(x -> x.toString())
				.collect(Collectors.joining(",", "(", ")"));
		System.out.println("#Stream str = " + str);
	}

	public static void optionals() {
		String[] str = new String[10];

		Optional<String> isNull = Optional.ofNullable(str[2]);
		if (isNull.isPresent()) {
			System.out.println("#substr=" + str[2].substring(2));
		} else {
			System.out.println("#substr is empty");
		}
		str[2] = "Hello";
		Optional<String> isNull2 = Optional.ofNullable(str[2]);
		if (isNull2.isPresent()) {
			System.out.println("#substr=" + str[2].substring(1));
		} else {
			System.out.println("#substr is empty");
		}

		Optional<String> msg = Optional.of("Learning Java Optional");
		Optional<String> nothing = Optional.empty();
		if (msg.isPresent()) {
			System.out.println("#msg => " + msg);
		} else {
			System.out.println("#String is empty");
		}
		msg.ifPresent(m -> System.out.println("#m => " + m));
		nothing.ifPresent(s -> System.out.println("This is nothing, will not be printed"));
	}

}
