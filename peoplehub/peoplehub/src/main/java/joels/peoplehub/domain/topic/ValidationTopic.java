package joels.peoplehub.domain.topic;

import java.time.LocalDateTime;

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
	
	public DataNewTopic postTopic(DataNewTopic dataNewTopic) {
		if(!userRepository.existsById(dataNewTopic.userId())) {
			throw new ValidaException("The user id doesn't exist");
		}
		
		if(!topicRepository.existsByTitle(dataNewTopic.title())) {
			throw new ValidaException("The user id doesn't exist");
		}
		
		var user = userRepository.findById(dataNewTopic.userId()).orElseThrow(() -> new ValidaException("User not found"));
		
		var topic = new Topic(dataNewTopic.title(),
                dataNewTopic.message(),
                LocalDateTime.now(),
                true,
                user,
                dataNewTopic.course());
		
		topicRepository.save(topic);
	    return new DataNewTopic(topic);

	}
	
	
	public DataNewTopic updateTopic(Long id, DataUpdateTopic dataUpdateTopic) {
	    Topic topic = topicRepository.findById(id)
	            .orElseThrow(() -> new ValidaException("The Id topic doesn't exist"));


	    if (!userRepository.existsById(dataUpdateTopic.userId())) {
	        throw new ValidaException("The Id user doesn't exist");
	    }
	    if (!topic.getUser().getId().equals(dataUpdateTopic.userId())) {
	        throw new ValidaException("Your user does not have authorization to modify this topic");
	    }


	    if (topicRepository.existsByTitle(dataUpdateTopic.title()) &&
	        !topic.getTitle().equals(dataUpdateTopic.title())) {
	        throw new ValidaException("Topic with the entered title already exists");
	    }


	    if (topicRepository.existsByMessage(dataUpdateTopic.message()) &&
	        !topic.getMessage().equals(dataUpdateTopic.message())) {
	        throw new ValidaException("Topic with the entered message already exists");
	    }


	    topic.update(dataUpdateTopic);


	    return new DataNewTopic(topic);
	}
	
	public void deleteTopic(Long id) {
		if(!topicRepository.existsById(id)) {
			throw new ValidaException("The Id topic doesn't exist");
		}
		Topic topic = topicRepository.getReferenceById(id);
		topicRepository.deleteById(id);
	}
	
	
	
}
