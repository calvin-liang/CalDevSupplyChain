package com.caldevsupplychain.account.util;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;

import com.caldevsupplychain.account.model.User;
import com.caldevsupplychain.account.vo.UserBean;
import com.caldevsupplychain.account.vo.UserWS;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(config = MapperBaseConfig.class, uses = {RoleMapper.class})
public interface UserMapper {

	@Named("wsToBean")
	UserBean toBean(UserWS userWS);
	UserWS toWS(UserBean userBean);
	User toUser(UserBean userBean);
	@Named("userToBean")
	UserBean toBean(User user);

	@IterableMapping(qualifiedByName = "wsToBean")
	List<UserBean> wssToBeans(List<UserWS> userWS);
	@IterableMapping(qualifiedByName = "toWS")
	List<UserWS> toWss(List<UserBean> userBean);
	@IterableMapping(qualifiedByName = "toUser")
	List<User> toUsers(List<UserBean> userBean);
	@IterableMapping(qualifiedByName = "userToBean")
	List<UserBean> usersToBeans(List<User> user);
}
