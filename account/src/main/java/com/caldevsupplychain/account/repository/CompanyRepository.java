package com.caldevsupplychain.account.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.caldevsupplychain.account.model.Company;
import com.caldevsupplychain.account.model.User;

public interface CompanyRepository extends PagingAndSortingRepository<Company, Long> {
	Company findByUser(User user);
}
