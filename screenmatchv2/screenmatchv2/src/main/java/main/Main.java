package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


import model.DataSeason;
import model.DataSerie;
import service.ConvertData;
import service.ServiceAPI;

public class Main {

	private Scanner keyboard = new Scanner(System.in);
	private ServiceAPI serviceApi = new ServiceAPI();
	private final String URL_BASE = "http://www.omdbapi.com/?t=";
	private final String API_KEY="&apikey=9f0dabfa";
	private ConvertData converter = new ConvertData();
	
	
	public void showMenu() {
		System.out.println("Enter de series name: ");
		//search for general data of the series
		var serieName = keyboard.nextLine();
		var json = serviceApi.getData(URL_BASE+ serieName.replace(" ", "+") + API_KEY);
		var data = converter.getData(json, DataSerie.class);
		System.out.println(data);
		//Search data by season
		List<DataSeason> seasons = new ArrayList<>();
		for(int i = 1;i <= data.totalSeasons(); i++) {
			json = serviceApi.getData(URL_BASE+ serieName.replace(" ", "+") + "&Season="+ i + API_KEY);
			var dataSeason = converter.getData(json, DataSeason.class);
			seasons.add(dataSeason);
		}
		
		seasons.forEach(t -> t.episodes().forEach(e -> System.out.println(e.title())));
		
	}
}
