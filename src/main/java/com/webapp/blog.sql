CREATE TABLE IF NOT EXISTS users
(
  id        SERIAL       NOT NULL PRIMARY KEY,
  user_name VARCHAR(255) NOT NULL,
  password  VARCHAR(255) NOT NULL,
  status    BOOLEAN DEFAULT TRUE
);

CREATE TABLE IF NOT EXISTS activity (
  id      SERIAL       NOT NULL PRIMARY KEY,
  status  VARCHAR(255) NOT NULL,
  date    DATE         NOT NULL,
  user_id INTEGER      NOT NULL,
  CONSTRAINT user_id FOREIGN KEY (user_id) REFERENCES "users" (id)
);

CREATE TABLE IF NOT EXISTS category (
  id      SERIAL       NOT NULL PRIMARY KEY,
  name    VARCHAR(255) NOT NULL,
  user_id INTEGER      NOT NULL,
  status BOOLEAN DEFAULT TRUE,
  CONSTRAINT user_id FOREIGN KEY (user_id) REFERENCES "users" (id)
);
CREATE TABLE IF NOT EXISTS post (
  id          SERIAL       NOT NULL PRIMARY KEY,
  title       VARCHAR(255) NOT NULL,
  description TEXT         NOT NULL,
  category_id INTEGER      NOT NULL,
  user_id     INTEGER      NOT NULL,
  CONSTRAINT category_id FOREIGN KEY (category_id) REFERENCES "category" (id),
  CONSTRAINT user_id FOREIGN KEY (user_id) REFERENCES "users" (id)
);
CREATE TABLE IF NOT EXISTS tag (
  id   SERIAL       NOT NULL PRIMARY KEY,
  name VARCHAR(255) NOT NULL
);
CREATE TABLE IF NOT EXISTS posts_tags (
  id      SERIAL PRIMARY KEY NOT NULL,
  tags_id INTEGER            NOT NULL,
  post_id INTEGER            NOT NULL,
  CONSTRAINT tags_id FOREIGN KEY (tags_id) REFERENCES "tag" (id),
  CONSTRAINT post_id FOREIGN KEY (post_id) REFERENCES "post" (id)
);

INSERT INTO users (user_name, password) VALUES ('Yury', '342');

ALTER TABLE users
  ADD COLUMN status BOOLEAN;
ALTER TABLE category
  ADD COLUMN status BOOLEAN;
ALTER TABLE tags RENAME TO tag;

