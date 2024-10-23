package com.joels.screenmatchv2;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import model.DataSerie;
import service.ConvertData;
import service.ServiceAPI;

@SpringBootApplication
public class Screenmatchv2Application implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(Screenmatchv2Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var serviceApi = new ServiceAPI();
		var json = serviceApi.getData("http://www.omdbapi.com/?t=game+of+thrones&apikey=9f0dabfa");
		System.out.println(json);
		ConvertData converter = new ConvertData();
		var data = converter.getData(json, DataSerie.class);
		System.out.println(data);
	}

}
