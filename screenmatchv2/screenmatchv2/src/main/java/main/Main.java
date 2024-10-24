package main;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import model.DataEpisode;
import model.DataSeason;
import model.DataSerie;
import model.Episode;
import service.ConvertData;
import service.ServiceAPI;

public class Main {

	private Scanner keyboard = new Scanner(System.in);
	private ServiceAPI serviceApi = new ServiceAPI();
	private final String URL_BASE = "http://www.omdbapi.com/?t=";
	private final String API_KEY = "&apikey=9f0dabfa";
	private ConvertData converter = new ConvertData();

	public void showMenu() {
		System.out.println("Enter de series name: ");
		// search for general data of the series
		var serieName = keyboard.nextLine();
		var json = serviceApi.getData(URL_BASE + serieName.replace(" ", "+") + API_KEY);
		var data = converter.getData(json, DataSerie.class);
		// System.out.println(data);
		// Search data by season
		List<DataSeason> seasons = new ArrayList<>();
		for (int i = 1; i <= data.totalSeasons(); i++) {
			json = serviceApi.getData(URL_BASE + serieName.replace(" ", "+") + "&Season=" + i + API_KEY);
			var dataSeason = converter.getData(json, DataSeason.class);
			seasons.add(dataSeason);
		}

		// seasons.forEach(t -> t.episodes().forEach(e ->
		// System.out.println(e.title())));
		List<DataEpisode> dataEpisode = seasons.stream().flatMap(t -> t.episodes().stream())
				.collect(Collectors.toList());

		// Top 5 Episodes
		System.out.println("Top 5 Episodes");
		dataEpisode.stream().filter(e -> !e.imdbRating().equalsIgnoreCase("N/A"))
				.peek(e -> System.out.println("First filter (N/A) " + e))
				.sorted(Comparator.comparing(DataEpisode::imdbRating).reversed())
				.peek(e -> System.out.println("Second filter (M>m) " + e))
				.map(e -> e.title().toUpperCase())
				.peek(e -> System.out.println("Third filter (m->M) " + e))
				.limit(5).forEach(System.out::println);

		// Converting data of type "Episode"
		List<Episode> episodes = seasons.stream()
				.flatMap(t -> t.episodes().stream().map(d -> new Episode(t.number(), d))).collect(Collectors.toList());

		episodes.forEach(System.out::println);

		// Episode search from x year

		System.out.println("Enter the year of the episodes to search");
		var year = keyboard.nextInt();
		keyboard.nextLine();

		LocalDate searchDate = LocalDate.of(year, 1, 1);

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		episodes.stream().filter(e -> e.getRelaseDate() != null && e.getRelaseDate().isAfter(searchDate))
				.forEach(e -> System.out.println("Season " + e.getSeason() + "Episode " + e.getTitle() + "Realase Date "
						+ e.getRelaseDate().format(dtf)));

	}
}
