package joels.peoplehub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import joels.peoplehub.domain.answer.AnswerRepository;
import joels.peoplehub.domain.topic.TopicRepository;
import joels.peoplehub.domain.user.UserRepository;

@RestController
@RequestMapping("/topics/{topicId}")
@SecurityRequirement(name = "bearer-key")
public class AnswerController {

	@Autowired
	private UserRepository userRespository;
	
	@Autowired
	private TopicRepository topicRespository;
	
	@Autowired
	private AnswerRepository answerRespository;
	

}
