CREATE TABLE answers (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    message TEXT NOT NULL,
    topic_id BIGINT NOT NULL,
    creation_date DATETIME NOT NULL,
    user_id BIGINT NOT NULL,
    solution BOOLEAN NOT NULL,
    CONSTRAINT fk_answers_topic FOREIGN KEY (topic_id) REFERENCES topics (id) ON DELETE CASCADE,
    CONSTRAINT fk_answers_user FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);