package com.caldevsupplychain.notification.mail.model;

import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.caldevsupplychain.common.entity.BaseEntity;
import com.caldevsupplychain.notification.mail.type.EmailType;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "email_templates")
public class EmailTemplate extends BaseEntity {
	@Column(name = "locale", nullable = false)
	private Locale locale;

	@Column(name = "subject")
	private String subject;

	@Column(name = "to_email")
	private String toEmail;

	@Column(name = "from_email")
	private String fromEmail;

	@Column(name = "content")
	private String content;

	@Column(name = "type", nullable = false)
	@Enumerated(EnumType.STRING)
	private EmailType type;
}
