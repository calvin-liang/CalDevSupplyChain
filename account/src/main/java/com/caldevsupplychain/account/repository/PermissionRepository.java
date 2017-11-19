package com.caldevsupplychain.account.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.caldevsupplychain.account.model.Permission;
import com.caldevsupplychain.account.vo.PermissionName;

public interface PermissionRepository extends PagingAndSortingRepository<Permission, Long> {
	Permission findByName(PermissionName name);
}
