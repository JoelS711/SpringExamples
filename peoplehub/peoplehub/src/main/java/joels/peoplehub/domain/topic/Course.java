package joels.peoplehub.domain.topic;

public enum Course {

	WEB_DEVELOPMENT,
    DATA_SCIENCE,
    CLOUD_COMPUTING,
    ARTIFICIAL_INTELLIGENCE,
    CYBER_SECURITY,
    MOBILE_DEVELOPMENT,
    GAME_DEVELOPMENT,
    SOFTWARE_TESTING,
    DIGITAL_MARKETING,
    PROJECT_MANAGEMENT;
	
	public static Course fromString(String input) {
		if(input == null || input.isBlank()) {
			throw new IllegalArgumentException("Input cannot be null or empty");
		}
		
		String normalizedInput = input.trim().toUpperCase().replace(" ", "_");
		
		try {
			return Course.valueOf(normalizedInput);
		}catch(IllegalArgumentException e) {
			throw new IllegalArgumentException("Invalid course type: " + input);
		}
	}
}
