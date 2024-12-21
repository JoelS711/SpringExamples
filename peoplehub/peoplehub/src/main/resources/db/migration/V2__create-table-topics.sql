CREATE TABLE topics (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    message TEXT NOT NULL,
    creation_date DATETIME NOT NULL,
    status BOOLEAN NOT NULL,
    user_id BIGINT NOT NULL,
    course VARCHAR(255) NOT NULL,
    answer_id BIGINT,
    CONSTRAINT fk_topics_user FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_topics_answer FOREIGN KEY (answer_id) REFERENCES answers(id)
);