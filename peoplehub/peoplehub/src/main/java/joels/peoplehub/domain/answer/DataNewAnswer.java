package joels.peoplehub.domain.answer;

public record DataNewAnswer(Long userId, Long topicId, String message) {

	public DataNewAnswer(Answer answer) {
		this(answer.getUser().getId(),
		answer.getTopic().getId(),
		answer.getMessage());
	}
}
