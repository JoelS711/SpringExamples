package joels.peoplehub.domain.topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import joels.peoplehub.domain.answer.AnswerRepository;
import joels.peoplehub.domain.user.UserRepository;

@Service
public class ValidationTopic {

	@Autowired
	private UserRepository userRespository;
	
	@Autowired
	private TopicRepository topicRespository;
	
	@Autowired
	private AnswerRepository answerRespository;
}
