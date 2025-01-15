package joels.peoplehub.domain.answer;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import joels.peoplehub.domain.topic.DataNewTopic;
import joels.peoplehub.domain.topic.Topic;
import joels.peoplehub.domain.topic.TopicRepository;
import joels.peoplehub.domain.user.UserRepository;
import joels.peoplehub.infra.error.ValidaException;

@Service
public class ValidationAnswer {

	 	@Autowired
	    private AnswerRepository answerRepository;

	    @Autowired
	    private UserRepository userRepository;

	    @Autowired
	    private TopicRepository topicRepository;
	    
	    public DataNewAnswer postAnswer(DataNewAnswer dataNewAnswer) {
	    	// Verificar que el usuario exista
	        if (!userRepository.existsById(dataNewAnswer.userId())) {
	            throw new ValidaException("The user id doesn't exist");
	        }

	        // Verificar que el tópico exista
	        if (!topicRepository.existsById(dataNewAnswer.topicId())) {
	            throw new ValidaException("The topic id doesn't exist");
	        }

	        // Obtener el usuario y el tópico
	        var user = userRepository.findById(dataNewAnswer.userId())
	                .orElseThrow(() -> new ValidaException("User not found"));

	        var topic = topicRepository.findById(dataNewAnswer.topicId())
	                .orElseThrow(() -> new ValidaException("Topic not found"));

	        // Crear la respuesta con el objeto Topic
	        var answer = new Answer(topic,
	                dataNewAnswer.message(),
	                LocalDateTime.now(),
	                true,
	                user);

	        // Guardar la respuesta
	        answerRepository.save(answer);

	        // Retornar el DTO correspondiente
	        return new DataNewAnswer(answer);

		}
}
