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
	
	
	public void updateTopic(Long id, DataUpdateTopic dataUpdateTopic) {
		if(!topicRepository.existsById(id)) {
			throw new ValidaException("The Id topic doesn't exist");
		}
		
		if(!userRepository.existsById(dataUpdateTopic.userId())) {
			throw new ValidaException("The Id user doesn't exist");
		}
		
		if(!topicRepository.getReferenceById(id).getUser().getId().equals(dataUpdateTopic.userId())) {
			throw new ValidaException("Your user does not have authorization to modify this topic");
		}
		
		if(topicRepository.existsByTitle(dataUpdateTopic.title())) {
			if(topicRepository.getReferenceById(id).getTitle().equals(dataUpdateTopic.title())) {
				throw new ValidaException("The updated title is the same as the original title.");
			} else {
				throw new ValidaException("Topic with the entered title already exists");
			}
		}
		
		if(topicRepository.existsByMessage(dataUpdateTopic.message())) {
			if(topicRepository.getReferenceById(id).getMessage().equals(dataUpdateTopic.message())) {
				throw new ValidaException("The updated message is the same as the original message.");
			} else {
				throw new ValidaException("Topic with the entered message already exists");
			}
		}
		
		Topic topic = topicRepository.getReferenceById(id);
		topic.update(dataUpdateTopic);
	}
	
	
	
}
