package com.caldevsupplychain.account.service;

import com.caldevsupplychain.account.model.Role;
import com.caldevsupplychain.account.model.User;
import com.caldevsupplychain.account.repository.RoleRepository;
import com.caldevsupplychain.account.repository.UserRepository;
import com.caldevsupplychain.account.util.RoleMapper;
import com.caldevsupplychain.account.util.UserMapper;
import com.caldevsupplychain.account.vo.UserBean;
import com.caldevsupplychain.common.type.ErrorCode;
import com.google.common.base.Preconditions;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.credential.PasswordService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private PasswordService passwordService;
	private UserMapper userMapper;
	private RoleMapper roleMapper;

	@Override
	public boolean userExist(String emailAddress) {
		return userRepository.findByEmailAddress(emailAddress) != null;
	}

	@Override
	@Transactional
	public UserBean createUser(UserBean userBean) {
		Preconditions.checkState(!userBean.getRoles().isEmpty(), "Must assign at least one role when creating a user.");

		List<Role> roleList = roleRepository.findByName(userBean.getRoles());

		if (roleList == null) {
			return null;
		}

		userBean.setToken(UUID.randomUUID().toString());

		userBean.setPassword(passwordService.encryptPassword(userBean.getPassword()));

		User user = userMapper.toUser(userBean);

		userRepository.save(user);

		return userMapper.toBean(user);
	}

	@Override
	@Transactional
	public UserBean updateUser(UserBean userBean) {
		User user = userRepository.findByEmailAddress(userBean.getEmailAddress());

		Preconditions.checkState(user != null, ErrorCode.USER_NOT_FOUND.toString());

		// make it Optional.ofNullable because it give flexibility to different fields that going to be updated
		Optional.ofNullable(userBean.getUsername()).ifPresent(username -> user.setUsername(username));

		Optional.ofNullable(userBean.getPassword()).ifPresent(password -> user.setPassword(passwordService.encryptPassword(password)));

		Optional.ofNullable(userBean.getToken()).ifPresent(token -> user.setToken(token));

		Optional.ofNullable(userBean.getRoles()).ifPresent(roleNames -> user.setRoles(roleMapper.toRoleList(roleNames)));

		return userMapper.toBean(user);
	}

	@Override
	@Transactional
	public void activateUser(long id) {
		User user = userRepository.findOne(id);
		Preconditions.checkState(user != null, "[activateUser Error]: User with id %s not found.", id);
		user.setToken(null);
	}

	@Override
	public Optional<UserBean> findByUuid(String uuid) {
		User user = userRepository.findByUuid(uuid);
		if (user != null) {
			return Optional.of(userMapper.toBean(user));
		}
		return Optional.empty();
	}

	@Override
	public Optional<UserBean> findByEmailAddress(String emailAddress) {
		User user = userRepository.findByEmailAddress(emailAddress);
		if (user != null) {
			return Optional.of(userMapper.toBean(user));
		}
		return Optional.empty();
	}

	@Override
	public Optional<UserBean> findByToken(String token) {
		User user = userRepository.findByToken(token);
		if (user != null) {
			return Optional.of(userMapper.toBean(user));
		}
		return Optional.empty();
	}

	@Override
	public Optional<UserBean> findById(Long id){
		User user = userRepository.findOne(id);
		if(user != null){
			return Optional.of(userMapper.toBean(user));
		}
		return Optional.empty();
	}

	@Override
	public List<UserBean> getAllUsers() {
		Page<User> users = userRepository.findAll(new PageRequest(0, Integer.MAX_VALUE));
		return userMapper.usersToBeans(users.getContent());
	}
}
