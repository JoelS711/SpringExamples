package joels.peoplehub.domain.topic;

import org.springframework.data.jpa.repository.JpaRepository;

import jakarta.validation.constraints.NotBlank;

public interface TopicRepository extends JpaRepository<Topic, Long>{

	boolean existsByTitle(@NotBlank String title);

}
