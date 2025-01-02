package joels.peoplehub.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import joels.peoplehub.domain.answer.AnswerRepository;
import joels.peoplehub.domain.topic.DataNewTopic;
import joels.peoplehub.domain.topic.TopicRepository;
import joels.peoplehub.domain.topic.ValidationTopic;
import joels.peoplehub.domain.user.UserRepository;

@RestController
@RequestMapping("/topics/{topicId}")
@SecurityRequirement(name = "bearer-key")
public class TopicController {

	@Autowired
	private UserRepository userRespository;
	
	@Autowired
	private TopicRepository topicRespository;
	
	@Autowired
	private AnswerRepository answerRespository;
	
	@Autowired
	private ValidationTopic validation;
	
	@PostMapping
	public ResponseEntity<DataNewTopic> newTopic(@RequestBody @Valid DataNewTopic data, UriComponentsBuilder uriComponentsBuilder){
		DataNewTopic dataNewTopic = validation.postTopic(data);
		URI url = uriComponentsBuilder.path("/topics/{id}").buildAndExpand(dataNewTopic.userId()).toUri();
		return ResponseEntity.created(url).body(dataNewTopic);
	}
}
