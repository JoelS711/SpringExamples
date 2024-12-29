package joels.peoplehub.domain.topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import joels.peoplehub.domain.answer.AnswerRepository;
import joels.peoplehub.domain.user.UserRepository;
import joels.peoplehub.infra.error.ValidaException;

@Service
public class ValidationTopic {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TopicRepository topicRepository;
	
	@Autowired
	private AnswerRepository answerRepository;
	
	public void postTopic(DataNewTopic dataNewTopic) {
		if(!userRepository.existsById(dataNewTopic.userId())) {
			throw new ValidaException("The user id doesn't exist");
		}
		
		if(!topicRepository.existsByTitle(dataNewTopic.title())) {
			throw new ValidaException("The user id doesn't exist");
		}
		
		var topic = new Topic(dataNewTopic, userRepository);
		
		topicRepository.save(topic);
	}
	
	
	
}
