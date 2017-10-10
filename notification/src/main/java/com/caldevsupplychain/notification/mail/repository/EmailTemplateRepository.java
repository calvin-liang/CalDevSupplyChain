package com.caldevsupplychain.notification.mail.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.caldevsupplychain.notification.mail.model.EmailTemplate;
import com.caldevsupplychain.notification.mail.type.EmailType;

public interface EmailTemplateRepository extends PagingAndSortingRepository<EmailTemplate, Long> {
	EmailTemplate findByType(EmailType type);
}
