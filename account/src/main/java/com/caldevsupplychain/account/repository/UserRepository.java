package com.caldevsupplychain.account.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.caldevsupplychain.account.model.User;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {

	User findByUuid(String uuid);

	User findByEmailAddress(String emailAddress);

	User findByToken(String token);

	@Query(value = "select * from users u " +
		"left outer join user_2_role u2r on u2r.user_id = u.id " +
		"left outer join roles r on u2r.role_id = r.id " +
		"where r.name = ?1", nativeQuery = true)
	List<User> findUsersByRole(String role);
}
