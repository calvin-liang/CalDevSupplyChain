package com.caldevsupplychain.order.util;

import com.caldevsupplychain.common.util.MapperBaseConfig;
import com.caldevsupplychain.order.model.Order;
import com.caldevsupplychain.order.vo.OrderBean;
import com.caldevsupplychain.order.vo.OrderWS;
import org.mapstruct.Context;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(config = MapperBaseConfig.class, uses = {ItemMapper.class})
public interface OrderMapper {

	@Named("orderWSToBean")
	OrderBean toBean(OrderWS orderWS,  @Context CycleAvoidingMappingContext context);
	Order toOrder(OrderBean orderBean, @Context CycleAvoidingMappingContext context);
	@Named("orderToBean")
	OrderBean toBean(Order order,  @Context CycleAvoidingMappingContext context);
	OrderWS toWS(OrderBean orderBean,  @Context CycleAvoidingMappingContext context);

	@IterableMapping(qualifiedByName = "orderToBean")
	List<OrderBean> ordersToBeans(List<Order> order, @Context CycleAvoidingMappingContext context);

	@IterableMapping(qualifiedByName = "toWS")
	List<OrderWS> toWSs(List<OrderBean> orderBean, @Context CycleAvoidingMappingContext context);

}
