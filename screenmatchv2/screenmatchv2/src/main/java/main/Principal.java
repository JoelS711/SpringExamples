package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.DataSeason;
import model.DataSerie;
import service.ConvertData;
import service.ServiceAPI;

public class Principal {

	private Scanner keyboard = new Scanner(System.in);
	private ServiceAPI serviceApi = new ServiceAPI();
	private final String URL_BASE = "http://www.omdbapi.com/?t=";
	private final String API_KEY = "&apikey=9f0dabfa";
	private ConvertData converter = new ConvertData();
	private List<DataSerie> dataSerie = new ArrayList<>();

	public void showMenu() {
		var option = -1;
		while (option != 0) {
			var menu = """
					1. Search serie
					2. Search episode
					3. Show searched series

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
		System.out.println(json);
		DataSerie data = converter.getData(json, DataSerie.class);
		return data;
	}

	private void searchEpisodeXSerie() {
		DataSerie dataSerie = getDataSerie();
		List<DataSeason> seasons = new ArrayList<>();

		for (int i = 1; i <= dataSerie.totalSeasons(); i++) {
			var json = serviceApi.getData(URL_BASE + dataSerie.title().replace(" ", "+") + API_KEY);
			DataSeason dataSeason = converter.getData(json, DataSeason.class);
			seasons.add(dataSeason);
		}
		seasons.forEach(System.out::println);

	}

	private void searchSerieWeb() {

		DataSerie data =  getDataSerie();
		dataSerie.add(data);
		System.out.println(data);
	}
	
	private void showSearchedSeries() {
		dataSerie.forEach(System.out::println);
		
	}
}
