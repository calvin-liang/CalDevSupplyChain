package com.caldevsupplychain.account.util;

import java.util.List;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.caldevsupplychain.account.model.Role;
import com.caldevsupplychain.account.repository.RoleRepository;
import com.caldevsupplychain.account.vo.RoleName;
import com.caldevsupplychain.common.util.MapperBaseConfig;

@Component
@Mapper(config = MapperBaseConfig.class)
public abstract class RoleMapper {

	@Autowired
	private RoleRepository roleRepository;

	public RoleName toRoleName(Role role) {
		return role != null ? role.getName() : null;
	}

	public Role toRole(RoleName roleName) {
		return roleName != null ? roleRepository.findByName(roleName) : null;
	}

	public abstract List<RoleName> toRoleNameList(List<Role> roles);

	public abstract List<Role> toRoleList(List<RoleName> roleNames);
}
