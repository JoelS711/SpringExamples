package joels.peoplehub.domain.answer;

import jakarta.validation.constraints.NotNull;

public record DataUpdateAnswer(@NotNull Long topicId,

		@NotNull Long userId,

		@NotNull Long answerId,

		String message) {

}
