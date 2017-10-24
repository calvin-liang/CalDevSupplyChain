package com.caldevsupplychain.account.util;
import com.caldevsupplychain.account.vo.UserBean;
import com.caldevsupplychain.common.jwt.vo.JwtBean;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(config = MapperBaseConfig.class)
@Component
public interface JwtMapper {

	@Mapping(source = "emailAddress", target = "sub")
	JwtBean toBean(UserBean userBean);

}
