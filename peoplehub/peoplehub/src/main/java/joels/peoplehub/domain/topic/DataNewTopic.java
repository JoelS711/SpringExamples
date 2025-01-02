package joels.peoplehub.domain.topic;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DataNewTopic(@NotNull Long userId,@NotBlank String title,@NotBlank String message,@NotNull  Course course) {

	public DataNewTopic(Topic topic) {
		this(topic.getId(), topic.getTitle(), topic.getMessage(), topic.getCourse());
	}

}
