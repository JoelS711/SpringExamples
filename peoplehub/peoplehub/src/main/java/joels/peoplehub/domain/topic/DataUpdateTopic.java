package joels.peoplehub.domain.topic;

import jakarta.validation.constraints.NotNull;

public record DataUpdateTopic(
		 @NotNull
	     Long topicId,

	     @NotNull
	     Long userId,

	     String title,

	     String message
		) {

	public DataUpdateTopic(Long topicId, Long userId, String title, String message) {
		this.topicId = topicId;
		this.userId = userId;
		this.title = title;
		this.message = message;
	}
}
