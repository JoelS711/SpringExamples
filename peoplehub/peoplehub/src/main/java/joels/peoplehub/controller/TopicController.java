package joels.peoplehub.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.data.domain.Sort;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import joels.peoplehub.domain.answer.AnswerRepository;
import joels.peoplehub.domain.topic.DataNewTopic;
import joels.peoplehub.domain.topic.DataUpdateTopic;
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
	private TopicRepository topicRepository;

	@Autowired
	private AnswerRepository answerRespository;

	@Autowired
	private ValidationTopic validation;

	@PostMapping
	public ResponseEntity<DataNewTopic> newTopic(@RequestBody @Valid DataNewTopic data,
			UriComponentsBuilder uriComponentsBuilder) {
		DataNewTopic dataNewTopic = validation.postTopic(data);
		URI url = uriComponentsBuilder.path("/topics/{id}").buildAndExpand(dataNewTopic.userId()).toUri();
		return ResponseEntity.created(url).body(dataNewTopic);
	}

	@GetMapping
	public ResponseEntity<Page<DataNewTopic>> listTopics(
			@PageableDefault @SortDefault(sort = "status", direction = Sort.Direction.ASC) @SortDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {

		return ResponseEntity.ok(topicRepository.findAll(pageable).map(DataNewTopic::new));
	}
	
	@PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DataUpdateTopic> updateTopic(@PathVariable("id") Long id, @RequestBody @Valid DataUpdateTopic dataUpdate){
		DataNewTopic updatedData = validation.updateTopic(id, dataUpdate);
		return ResponseEntity.ok(dataUpdate);
	}
}
