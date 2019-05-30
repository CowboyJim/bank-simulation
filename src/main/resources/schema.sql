DROP TABLE IF EXISTS webhooks;

CREATE TABLE webhooks (
  id             INT          AUTO_INCREMENT PRIMARY KEY,
  url            VARCHAR(250) NOT NULL,
  account_number VARCHAR(250) NOT NULL,
  account_name    VARCHAR(250) NOT NULL
);
