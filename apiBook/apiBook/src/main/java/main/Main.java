package main;

import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

import model.Data;
import model.DataBook;
import service.ConvertData;
import service.ServiceAPI;

public class Main {

	private static final String URL_BASE = "https://gutendex.com/books/";
	private ServiceAPI serviceApi = new ServiceAPI();
	private ConvertData converter = new ConvertData();
	private Scanner keyboard = new Scanner(System.in);

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
		
		//search for books by name
		System.out.println("enter the name of the book to search");
		var bookTitle = keyboard.nextLine();
		json = serviceApi.getData(URL_BASE+"?search="+bookTitle.replace(" ","+"));
		var dataSearch = converter.getData(json, Data.class);
		Optional<DataBook> searchedBook = dataSearch.books().stream().filter(l -> l.title().toUpperCase().contains(bookTitle.toUpperCase())).findFirst();
		System.out.println("enter the name of the book to search");
		if(searchedBook.isPresent()) {
			System.out.println("** found book **");
			System.out.println(searchedBook.get());
		}else {
			System.out.println("book not found");
		}
		
		//Statistics
		
		DoubleSummaryStatistics sta = data.books().stream().filter(d -> d.downloadsCount() > 0).collect(Collectors.summarizingDouble(DataBook::downloadsCount));
		System.out.println("Average number of downloads: "+ sta.getAverage());
		System.out.println("Maximum number of downloads: "+sta.getMax());
		System.out.println("Minimum number of downloads: "+sta.getMin());
		System.out.println("Number of records evaluated to calculate statistics: " + sta.getCount());
		
		
	}

}
