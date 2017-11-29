package com.caldevsupplychain.account.service;

import java.util.Collection;
import java.util.Set;

public interface PermissionService {
	Set<String> getPermissions(Collection<String> roles);
}
