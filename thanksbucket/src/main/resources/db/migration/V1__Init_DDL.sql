CREATE TABLE bucket_template
(
    id                 BIGINT AUTO_INCREMENT NOT NULL,
    created_date       datetime              NOT NULL,
    last_modified_date datetime              NOT NULL,
    bucket_name        VARCHAR(255)          NOT NULL,
    bucket_todo_names  VARCHAR(255)          NULL,
    CONSTRAINT pk_bucket_template PRIMARY KEY (id)
);

CREATE TABLE bucket_template_topic
(
    id                 BIGINT AUTO_INCREMENT NOT NULL,
    created_date       datetime              NOT NULL,
    last_modified_date datetime              NOT NULL,
    bucket_template_id BIGINT                NOT NULL,
    topic_id           BIGINT                NOT NULL,
    CONSTRAINT pk_bucket_template_topic PRIMARY KEY (id)
);

CREATE TABLE bucket_todo
(
    id                 BIGINT AUTO_INCREMENT NOT NULL,
    created_date       datetime              NOT NULL,
    last_modified_date datetime              NOT NULL,
    content            VARCHAR(255)          NOT NULL,
    is_done            BIT(1)                NOT NULL,
    bucket_id          BIGINT                NOT NULL,
    CONSTRAINT pk_buckettodo PRIMARY KEY (id)
);

CREATE TABLE bucket_topics
(
    id                 BIGINT AUTO_INCREMENT NOT NULL,
    created_date       datetime              NOT NULL,
    last_modified_date datetime              NOT NULL,
    bucket_id          BIGINT                NOT NULL,
    topic_id           BIGINT                NOT NULL,
    CONSTRAINT pk_bucket_topics PRIMARY KEY (id)
);

CREATE TABLE `buckets`
(
    id                 BIGINT AUTO_INCREMENT NOT NULL,
    created_date       datetime              NOT NULL,
    last_modified_date datetime              NOT NULL,
    title              VARCHAR(255)          NULL,
    goal_date          date                  NULL,
    is_done            BIT(1)                NOT NULL,
    member_id          BIGINT                NULL,
    CONSTRAINT pk_buckets PRIMARY KEY (id)
);

CREATE TABLE members
(
    id                 BIGINT AUTO_INCREMENT NOT NULL,
    created_date       datetime              NOT NULL,
    last_modified_date datetime              NOT NULL,
    email              VARCHAR(255)          NOT NULL,
    password           VARCHAR(255)          NULL,
    nickname           VARCHAR(255)          NULL,
    image_url          VARCHAR(255)          NULL,
    birthday           date                  NULL,
    member_role        VARCHAR(255)          NOT NULL,
    social_type        VARCHAR(255)          NULL,
    social_id          VARCHAR(255)          NULL,
    discovery_path     VARCHAR(255)          NULL,
    refresh_token      VARCHAR(255)          NULL,
    occupation_id      BIGINT                NULL,
    CONSTRAINT pk_members PRIMARY KEY (id)
);

CREATE TABLE occupations
(
    id                 BIGINT AUTO_INCREMENT NOT NULL,
    created_date       datetime              NOT NULL,
    last_modified_date datetime              NOT NULL,
    name               VARCHAR(255)          NOT NULL,
    CONSTRAINT pk_occupations PRIMARY KEY (id)
);

CREATE TABLE topics
(
    id                 BIGINT AUTO_INCREMENT NOT NULL,
    created_date       datetime              NOT NULL,
    last_modified_date datetime              NOT NULL,
    content            VARCHAR(255)          NOT NULL,
    CONSTRAINT pk_topics PRIMARY KEY (id)
);

ALTER TABLE members
    ADD CONSTRAINT uc_members_nickname UNIQUE (nickname);

ALTER TABLE occupations
    ADD CONSTRAINT uc_occupations_name UNIQUE (name);

ALTER TABLE topics
    ADD CONSTRAINT uc_topics_content UNIQUE (content);

ALTER TABLE `buckets`
    ADD CONSTRAINT FK_BUCKETS_ON_MEMBER FOREIGN KEY (member_id) REFERENCES members (id);

ALTER TABLE bucket_todo
    ADD CONSTRAINT FK_BUCKETTODO_ON_BUCKET FOREIGN KEY (bucket_id) REFERENCES `buckets` (id);

ALTER TABLE bucket_template_topic
    ADD CONSTRAINT FK_BUCKET_TEMPLATE_TOPIC_ON_BUCKET_TEMPLATE FOREIGN KEY (bucket_template_id) REFERENCES bucket_template (id);

ALTER TABLE bucket_template_topic
    ADD CONSTRAINT FK_BUCKET_TEMPLATE_TOPIC_ON_TOPIC FOREIGN KEY (topic_id) REFERENCES topics (id);

ALTER TABLE bucket_topics
    ADD CONSTRAINT FK_BUCKET_TOPICS_ON_BUCKET FOREIGN KEY (bucket_id) REFERENCES `buckets` (id);

ALTER TABLE bucket_topics
    ADD CONSTRAINT FK_BUCKET_TOPICS_ON_TOPIC FOREIGN KEY (topic_id) REFERENCES topics (id);

ALTER TABLE members
    ADD CONSTRAINT FK_MEMBERS_ON_OCCUPATION FOREIGN KEY (occupation_id) REFERENCES occupations (id);