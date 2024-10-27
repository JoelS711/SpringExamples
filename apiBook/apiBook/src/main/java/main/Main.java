package main;

import java.util.Comparator;

import model.Data;
import model.DataBook;
import service.ConvertData;
import service.ServiceAPI;

public class Main {

	private static final String URL_BASE = "https://gutendex.com/books/";
	private ServiceAPI serviceApi = new ServiceAPI();
	private ConvertData converter = new ConvertData();

	public void showMenu() {
		var json = serviceApi.getData(URL_BASE);
		System.out.println(json);
		var data = converter.getData(json, Data.class);
		System.out.println(data);

		// top 10 most downloaded books

		System.out.println("top 10 most downloaded books");
		data.books().stream().sorted(Comparator.comparing(DataBook::downloadsCount).reversed()).limit(10)
		.map(l -> l.title().toUpperCase())		
		.forEach(System.out::println);
	}

}
