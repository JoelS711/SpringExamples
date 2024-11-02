package com.joels.screenmatchv2.model;

public enum Category {

	ACTION("Action","Acción"),
	ROMANCE("Romance","Romance"),
	COMEDY("Comedy","Comedia"),
	CRIME("Crime","Crimen"),
	DRAMA("Drama", "Drama");
	
	private String categoryOmdb;
	private String categorySpanish;
	
	Category (String categoryOmdb, String categorySpanish){
		this.categoryOmdb = categoryOmdb;
		this.categorySpanish = categorySpanish;
	}
	
	public static Category fromString(String text) {
		for(Category category : Category.values()) {
			if(category.categoryOmdb.equalsIgnoreCase(text)) {
				return category;
			}
		}
		throw new IllegalArgumentException("no category found" +text);
	}
	
	public static Category fromSpanish(String text) {
		for(Category category : Category.values()) {
			if(category.categorySpanish.equalsIgnoreCase(text)) {
				return category;
			}
		}
		throw new IllegalArgumentException("no category found" +text);
	}
}
