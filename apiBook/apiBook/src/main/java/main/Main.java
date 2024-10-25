package main;



import model.Data;
import service.ConvertData;
import service.ServiceAPI;

public class Main {

	private static final String URL_BASE = "https://gutendex.com/books/"; 
	private ServiceAPI serviceApi = new ServiceAPI();
	private ConvertData converter = new ConvertData();
	public void showMenu(){
		var json = serviceApi.getData(URL_BASE);
		System.out.println(json);
		var data = converter.getData(json, Data.class);
		System.out.println(data);
	}
	

}
