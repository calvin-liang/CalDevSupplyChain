package com.caldevsupplychain.order.util;

import java.util.List;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import com.caldevsupplychain.common.util.MapperBaseConfig;
import com.caldevsupplychain.order.model.Item;
import com.caldevsupplychain.order.vo.ItemBean;
import com.caldevsupplychain.order.vo.ItemWS;

@Component
@Mapper(config = MapperBaseConfig.class, uses = {OrderMapper.class})
public interface ItemMapper {

	@Named("wsToBean")
	ItemBean toBean(ItemWS itemWS, @Context CycleAvoidingMappingContext context);

	Item toItem(ItemBean itemBean, @Context CycleAvoidingMappingContext context);

	@Named("itemToBean")
	ItemBean toBean(Item item, @Context CycleAvoidingMappingContext context);

	ItemWS toWS(ItemBean itemBean, @Context CycleAvoidingMappingContext context);

	List<Item> toItemList(List<ItemBean> itemBeans, @Context CycleAvoidingMappingContext context);

	List<ItemBean> toItemBeanList(List<Item> items, @Context CycleAvoidingMappingContext context);

}
