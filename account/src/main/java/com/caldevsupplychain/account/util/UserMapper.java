package com.caldevsupplychain.account.util;

import org.mapstruct.Mapper;

import com.caldevsupplychain.account.model.User;
import com.caldevsupplychain.account.vo.UserBean;
import com.caldevsupplychain.common.ws.account.UserWS;

@Mapper(config = MapperBaseConfig.class, uses = {RoleMapper.class})
public interface UserMapper {

	UserBean userWSToBean(UserWS userWS);
	UserWS userBeanToWS(UserBean userBean);
	User userBeanToUser(UserBean userBean);
	UserBean userToBean(User user);
}
