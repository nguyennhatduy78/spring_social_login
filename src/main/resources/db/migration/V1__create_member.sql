CREATE TABLE users(
  id INT NOT NULL AUTO_INCREMENT,
  user_uuid VARCHAR(100) NOT NULL,
  user_name VARCHAR(200) NOT NULL,
  user_email VARCHAR(100) NOT NULL,
  user_password VARCHAR(100) NOT NULL,
  user_login_type VARCHAR(3) NOT NULL,
  created_datetime DATETIME NOT NULL,
  updated_datetime DATETIME NOT NULL,
  PRIMARY KEY (id),
  UNIQUE (user_name, user_email, user_uuid)
)