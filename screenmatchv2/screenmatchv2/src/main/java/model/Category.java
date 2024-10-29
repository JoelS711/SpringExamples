package model;

public enum Category {

	ACTION("Action"),
	ROMANCE("Romance"),
	COMEDY("Comedy"),
	CRIME("Crime"),
	DRAMA("Drama");
	
	private String categoryOmdb;
	
	Category (String categoryOmdb){
		this.categoryOmdb = categoryOmdb;
	}
	
	public static Category fromString(String text) {
		for(Category category : Category.values()) {
			if(category.categoryOmdb.equalsIgnoreCase(text)) {
				return category;
			}
		}
		throw new IllegalArgumentException("no category found" +text);
	}
}