package com.caldevsupplychain.account.repository;

import java.util.Collection;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.caldevsupplychain.account.model.Permission;
import com.caldevsupplychain.account.vo.PermissionName;

public interface PermissionRepository extends PagingAndSortingRepository<Permission, Long> {
	Permission findByName(PermissionName name);

	@Query(value = "select p.* from permissions p " +
		"left outer join role_2_permission r2p on (p.id = r2p.permission_id) " +
		"left outer join roles r on (r.id = r2p.role_id) " +
		"where r.name in ?1", nativeQuery = true)
	Set<Permission> findByRoles(Collection<String> names);
}
