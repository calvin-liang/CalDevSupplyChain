package com.caldevsupplychain.account.util;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;

import com.caldevsupplychain.account.model.User;
import com.caldevsupplychain.account.vo.UserBean;
import com.caldevsupplychain.common.ws.account.UserWS;

import java.util.List;

@Mapper(config = MapperBaseConfig.class, uses = {RoleMapper.class})
public interface UserMapper {

	UserBean userWSToBean(UserWS userWS);
	UserWS userBeanToWS(UserBean userBean);
	User userBeanToUser(UserBean userBean);
	UserBean userToBean(User user);

	@IterableMapping(qualifiedByName = "userWSToBean")
	List<UserBean> userWSsToUserBeans(List<UserWS> userWS);
	@IterableMapping(qualifiedByName = "userBeanToWS")
	List<UserWS> userBeansToUserWSs(List<UserBean> userBean);
	@IterableMapping(qualifiedByName = "userBeanToUser")
	List<User> userBeansToUsers(List<UserBean> userBean);
	@IterableMapping(qualifiedByName = "userToBean")
	List<UserBean> usersToUserBeans(List<User> user);
}
