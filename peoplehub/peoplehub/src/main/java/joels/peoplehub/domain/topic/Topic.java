package joels.peoplehub.domain.topic;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import joels.peoplehub.domain.answer.Answer;
import joels.peoplehub.domain.user.User;
import joels.peoplehub.domain.user.UserRepository;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "topics")
@Entity(name = "Topic")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topic {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;

	private String message;

	private LocalDateTime creationDate;

	private Boolean status;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@Enumerated(EnumType.STRING)
	private Course course;

	@OneToMany(mappedBy = "topic", cascade = CascadeType.ALL,orphanRemoval = true)
	private List<Answer> answers = new ArrayList<>();
	
	public Topic(DataNewTopic data, UserRepository userRepository) {
        this.title = data.title();
        this.message = data.message();
        this.creationDate = LocalDateTime.now();
        this.status = true;
        this.user = userRepository.findById(data.userId())
                .orElseThrow(() -> new IllegalArgumentException("User not fount with Id: " + data.userId()));

        this.course = data.course();
    }

	public void update(@Valid DataUpdateTopic dataUpdateTopic) {
		if(dataUpdateTopic.title() != null) {
			this.title = dataUpdateTopic.title();
		}
		if(dataUpdateTopic.message() != null) {
			this.message = dataUpdateTopic.message();
		}
		
	}

}
