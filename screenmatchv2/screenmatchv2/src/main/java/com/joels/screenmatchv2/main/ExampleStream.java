package com.joels.screenmatchv2.main;

import java.util.Arrays;
import java.util.List;

public class ExampleStream {

	public void showExample() {
		List<String> names = Arrays.asList("Camilo", "Juan", "Mauricio", "Duvan","Fabian", "Diego");
		names.stream().sorted().limit(4).filter(t -> t.startsWith("D")).map(n -> n.toUpperCase()).forEach(System.out::println);
	}
}
