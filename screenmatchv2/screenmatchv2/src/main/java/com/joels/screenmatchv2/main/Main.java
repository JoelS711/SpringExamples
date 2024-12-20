package com.joels.screenmatchv2.main;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.joels.screenmatchv2.model.DataEpisode;
import com.joels.screenmatchv2.model.DataSeason;
import com.joels.screenmatchv2.model.DataSerie;
import com.joels.screenmatchv2.model.Episode;
import com.joels.screenmatchv2.service.ConvertData;
import com.joels.screenmatchv2.service.ServiceAPI;

public class Main {

	private Scanner keyboard = new Scanner(System.in);
	private ServiceAPI serviceApi = new ServiceAPI();
	private final String URL_BASE = "http://www.omdbapi.com/?t=";
	private final String API_KEY = "&apikey=9f0dabfa";
	private ConvertData converter = new ConvertData();
	private List<DataSerie> dataSerie = new ArrayList<>();

	public void showMenu() {
		System.out.println("Enter de series name: ");
		// search for general data of the series
		var serieName = keyboard.nextLine();
		var json = serviceApi.getData(URL_BASE + serieName.replace(" ", "+") + API_KEY);
		var data = converter.getData(json, DataSerie.class);
		// System.out.println(data);
		// Search data by season
	/*	List<DataSeason> seasons = new ArrayList<>();
		for (int i = 1; i <= data.totalSeasons(); i++) {
			json = serviceApi.getData(URL_BASE + serieName.replace(" ", "+") + "&Season=" + i + API_KEY);
			var dataSeason = converter.getData(json, DataSeason.class);
			seasons.add(dataSeason);
		}*/

		// seasons.forEach(t -> t.episodes().forEach(e ->
		// System.out.println(e.title())));
	//	List<DataEpisode> dataEpisode = seasons.stream().flatMap(t -> t.episodes().stream())
	//			.collect(Collectors.toList());

		// Top 5 Episodes
//		System.out.println("Top 5 Episodes");
//		dataEpisode.stream().filter(e -> !e.imdbRating().equalsIgnoreCase("N/A"))
//				.peek(e -> System.out.println("First filter (N/A) " + e))
//				.sorted(Comparator.comparing(DataEpisode::imdbRating).reversed())
//				.peek(e -> System.out.println("Second filter (M>m) " + e))
//				.map(e -> e.title().toUpperCase())
//				.peek(e -> System.out.println("Third filter (m->M) " + e))
//				.limit(5).forEach(System.out::println);

		// Converting data of type "Episode"
	//	List<Episode> episodes = seasons.stream()
	//			.flatMap(t -> t.episodes().stream().map(d -> new Episode(t.number(), d))).collect(Collectors.toList());

		//episodes.forEach(System.out::println);

		// Episode search from x year

//		System.out.println("Enter the year of the episodes to search");
//		var year = keyboard.nextInt();
//		keyboard.nextLine();

//		LocalDate searchDate = LocalDate.of(year, 1, 1);

	//	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

//		episodes.stream().filter(e -> e.getRelaseDate() != null && e.getRelaseDate().isAfter(searchDate))
//				.forEach(e -> System.out.println("Season " + e.getSeason() + "Episode " + e.getTitle() + "Realase Date "
//						+ e.getRelaseDate().format(dtf)));

		
		//Search episodes by title
//		System.out.println("Enter the title of the episode you want to watch");
//		var searchTitle = keyboard.nextLine();
//		Optional <Episode> searchedEpisode = episodes.stream().filter(e -> e.getTitle().toUpperCase().contains(searchTitle.toUpperCase())).findFirst();
//		
//		if(searchedEpisode.isPresent()) {
//			System.out.println("Searched Episode");
//			System.out.println("The episode found is: "+searchedEpisode.get());
//		}
		
		/*Map<Integer, Double> seasonRatings = episodes.stream()
				.filter(e -> e.getRating() > 0.0)
				.collect(Collectors.groupingBy(Episode::getSeason, Collectors.averagingDouble(Episode::getRating)));
		
		System.out.println(seasonRatings);
		
		DoubleSummaryStatistics std = episodes.stream().filter(e -> e.getRating() > 0.0).collect(Collectors.summarizingDouble(Episode::getRating));
		System.out.println("average rating: " + std.getAverage());
		System.out.println("best rated episode: " + std.getMax());
		System.out.println("worst rated episode: "+std.getMin());
		*/
	}
	
	
	

}
