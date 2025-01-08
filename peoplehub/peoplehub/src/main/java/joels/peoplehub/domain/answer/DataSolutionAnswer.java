package joels.peoplehub.domain.answer;

import jakarta.validation.constraints.NotNull;

public record DataSolutionAnswer(
		@NotNull
		Long userId,
		@NotNull
        Long topicId,
        @NotNull
        Long answerId,
        Boolean solution
		) {

}
