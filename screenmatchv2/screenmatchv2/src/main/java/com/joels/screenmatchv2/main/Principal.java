package com.joels.screenmatchv2.main;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.joels.screenmatchv2.model.Category;
import com.joels.screenmatchv2.model.DataSeason;
import com.joels.screenmatchv2.model.DataSerie;
import com.joels.screenmatchv2.model.Episode;
import com.joels.screenmatchv2.model.Serie;
import com.joels.screenmatchv2.repository.SerieRepository;
import com.joels.screenmatchv2.service.ConvertData;
import com.joels.screenmatchv2.service.ServiceAPI;

public class Principal {

	private Scanner keyboard = new Scanner(System.in);
	private ServiceAPI serviceApi = new ServiceAPI();
	private final String URL_BASE = "http://www.omdbapi.com/?t=";
	private final String API_KEY = "&apikey=9f0dabfa";
	private ConvertData converter = new ConvertData();
	private SerieRepository repository;
	private List<Serie> series;
	private Optional<Serie> searchedSerie;

	public Principal(SerieRepository repository) {
		this.repository = repository;
	}

	public void showMenu() {
		var option = -1;
		while (option != 0) {
			var menu = """
					1. Search serie
					2. Search episode
					3. Show searched series
					4. Search series by title
					5. Top 5 best series
					6. Search series by type
					7. Search serie by episode and rating
					8. Search episode x name
					9. Top 5 Episodes

					0. Exit
					""";
			System.out.println(menu);
			option = keyboard.nextInt();
			keyboard.nextLine();

			switch (option) {
			case 1:
				searchSerieWeb();
				break;
			case 2:
				searchEpisodeXSerie();
				break;
			case 3:
				showSearchedSeries();
				break;
			case 4:
				searchSerieXTitle();
				break;
			case 5:
				searchTop5Series();
				break;
			case 6:
				searchSeriesByType();
				break;
			case 7:
				searchSeriesBySeasonAndRating();
				break;
			case 8:
				searchEpisodeXName();
				break;
			case 9:
				top5Episodes();
				break;
			case 0:
				System.out.println("Exiting...");
				break;
			default:
				System.out.println("Invalid Option");
			}
		}
	}

	private DataSerie getDataSerie() {
		System.out.println("name of the series to search: ");
		var serieName = keyboard.nextLine();
		var json = serviceApi.getData(URL_BASE + serieName.replace(" ", "+") + API_KEY);
		DataSerie data = converter.getData(json, DataSerie.class);
		return data;
	}

	private void searchEpisodeXSerie() {
		showSearchedSeries();
		System.out.println("Enter the name of the series you want to watch the episodes of: ");
		var nameSerie = keyboard.nextLine();
		Optional<Serie> serie = series.stream()
				.filter(s -> s.getTitle().toLowerCase().contains(nameSerie.toLowerCase())).findFirst();

		if (serie.isPresent()) {
			var foundSerie = serie.get();
			List<DataSeason> seasons = new ArrayList<>();

			for (int i = 1; i <= foundSerie.getTotalSeasons(); i++) {
				var json = serviceApi
						.getData(URL_BASE + foundSerie.getTitle().replace(" ", "+") + "&season=" + i + API_KEY);
				DataSeason dataSeason = converter.getData(json, DataSeason.class);
				seasons.add(dataSeason);
			}
			seasons.forEach(System.out::println);
			List<Episode> episodes = seasons.stream()
					.flatMap(d -> d.episodes().stream().map(e -> new Episode(d.number(), e)))
					.collect(Collectors.toList());
			foundSerie.setEpisodes(episodes);
			repository.save(foundSerie);
		}

	}

	private void searchSerieWeb() {

		DataSerie data = getDataSerie();
		Serie serie = new Serie(data);
		repository.save(serie);
		// dataSerie.add(data);
		System.out.println(data);
	}

	private void showSearchedSeries() {
		series = repository.findAll();
		series.stream().sorted(Comparator.comparing(Serie::getGenre)).forEach(System.out::println);

	}

	private void searchSerieXTitle() {
		System.out.println("Enter the name of the series you want to watch: ");
		var nameSerie = keyboard.nextLine();

		searchedSerie = repository.findByTitleContainsIgnoreCase(nameSerie);
		if (searchedSerie.isPresent()) {
			System.out.println("The searched series is: " + searchedSerie.get());
		} else {
			System.out.println("Serie not found");
		}
	}

	private void searchTop5Series() {
		List<Serie> topSeries = repository.findTop5ByOrderByRatingDesc();
		topSeries.forEach(s -> System.out.println("Serie: " + s.getTitle() + " Rating: " + s.getRating()));
	}

	private void searchSeriesByType() {
		System.out.println("Enter the type of movie to search: ");
		var genre = keyboard.nextLine();
		var category = Category.fromSpanish(genre);
		List<Serie> serieByKind = repository.findByGenre(category);
		System.out.println("The kind of serie is: ");
		serieByKind.forEach(System.out::println);
	}

	private void searchSeriesBySeasonAndRating() {
		System.out.println("How many seasons does the series have?: ");
		var totalSeason = keyboard.nextInt();
		keyboard.nextLine();
		System.out.println("Rating from what value?: ");
		var rating = keyboard.nextDouble();
		keyboard.nextLine();
		List<Serie> filterSerie = repository.serieBySeasonAndRating(totalSeason, rating);
		System.out.println("***Leaked series***");
		filterSerie.forEach(s -> System.out.println(s.getTitle() + " -rating: " + s.getRating()));
	}

	private void searchEpisodeXName() {
		System.out.println("Enter the name of episode to search: ");
		var nameEpisode = keyboard.nextLine();
		List<Episode> episodesFound = repository.episodeXName(nameEpisode);
		episodesFound.forEach(e -> System.out.printf("Serie: %s Season %s Episode %s Rating%s\n", e.getSerie(),
				e.getSeason(), e.getEpisodeNumber(), e.getRating()));
	}

	private void top5Episodes() {
		searchSerieXTitle();
		if (searchedSerie.isPresent()) {
			Serie serie = searchedSerie.get();
			List<Episode> topEpisodes = repository.top5Episodes(serie);
			topEpisodes.forEach(e -> System.out.printf("Serie: %s Season %s Episode %s Rating %s\n", e.getSerie(),
					e.getSeason(), e.getTitle(), e.getRating()));
		}
	}
}
