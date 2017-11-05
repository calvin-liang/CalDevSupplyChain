package com.caldevsupplychain.order.util;

import com.caldevsupplychain.common.util.MapperBaseConfig;
import com.caldevsupplychain.order.model.Order;
import com.caldevsupplychain.order.vo.OrderBean;
import com.caldevsupplychain.order.vo.OrderWS;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
@Mapper(config = MapperBaseConfig.class, uses = {ItemMapper.class})
public interface OrderMapper {

	@Named("orderWSToBean")
	OrderBean toBean(OrderWS orderWS,  @Context CycleAvoidingMappingContext context);

	Order toOrder(OrderBean orderBean, @Context CycleAvoidingMappingContext context);

	@Named("orderToBean")
	OrderBean toBean(Order order,  @Context CycleAvoidingMappingContext context);

	OrderWS toWS(OrderBean orderBean,  @Context CycleAvoidingMappingContext context);

}
