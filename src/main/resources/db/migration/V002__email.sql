CREATE TABLE IF NOT EXISTS email_templates (
  id BIGINT AUTO_INCREMENT NOT NULL,
  created_on TIMESTAMP,
  last_modified TIMESTAMP,
  locale VARCHAR(100) NOT NULL,
  subject VARCHAR(255) NOT NULL,
  to_email VARCHAR(255),
  from_email VARCHAR(255),
  content TEXT NOT NULL,
  type VARCHAR(100) UNIQUE,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO email_templates (created_on, last_modified, locale, subject, to_email, from_email, content, type)
VALUES (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'en_US', 'Registration Activation Link', NULL, NULL, '<a href={rootURL}/activating/{token}>Activate Account Confirmation Link', 'ACTIVATION');
INSERT INTO email_templates (created_on, last_modified, locale, subject, to_email, from_email, content, type)
VALUES (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'en_US', 'Forgot Password Link', NULL, NULL, '<p>To verify your identity, please use the following token:</p><br><h2>{token}</h2><br><br>CalDevSupplyChain takes your account security very seriously. CalDevSupplyChain will never email you and ask you to disclose or verify your Amazon password, credit card, or banking account number. If you receive a suspicious email with a link to update your account information, do not click on the link—instead, report the email to CalDevSupplChain for investigation. We hope to see you again soon.', 'FORGOT_PASSWORD');


