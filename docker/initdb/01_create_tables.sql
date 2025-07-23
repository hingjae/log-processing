use logprocessing;

CREATE TABLE user_event_log (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  user_id VARCHAR(20) NOT NULL,
  action VARCHAR(20),
  resource VARCHAR(255),
  client_ip VARCHAR(45),
  user_agent TEXT,
  timestamp DATETIME,
  created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);