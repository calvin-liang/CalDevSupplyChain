package com.caldevsupplychain.account.service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.caldevsupplychain.account.repository.PermissionRepository;

@Service
public class PermissionServiceImpl implements PermissionService {
	@Autowired
	private PermissionRepository permissionRepository;

	@Override
	@Transactional(readOnly = true)
	public Set<String> getPermissions(Collection<String> roles) {
		return permissionRepository.findByRoles(roles)
									.stream()
									.map(p -> p.getName().toString())
									.collect(Collectors.toSet());
	}
}
