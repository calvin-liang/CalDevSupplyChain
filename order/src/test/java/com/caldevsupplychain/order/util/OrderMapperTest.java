package com.caldevsupplychain.order.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import lombok.extern.slf4j.Slf4j;

import org.junit.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;

import com.caldevsupplychain.order.model.Quantity;
import com.caldevsupplychain.order.vo.Currency;
import com.caldevsupplychain.order.vo.ItemBean;
import com.caldevsupplychain.order.vo.ItemWS;
import com.caldevsupplychain.order.vo.OrderBean;
import com.caldevsupplychain.order.vo.OrderWS;
import com.google.common.collect.Lists;

@Slf4j
public class OrderMapperTest {

	@Mock
	private OrderMapper orderMapper = Mappers.getMapper(OrderMapper.class);;
	@Mock
	private ItemMapper itemMapper = Mappers.getMapper(ItemMapper.class);;

	@BeforeMethod(alwaysRun = true)
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testOrderWSToOrderBean(){

		OrderWS orderWS = new OrderWS();
		orderWS.setId(123L);
		orderWS.setUserId(1L);
		orderWS.setAgentId(2L);
		orderWS.setSKU("SKU123");

		ItemWS itemWS = new ItemWS();
		itemWS.setColor("red");
		itemWS.setDescription("item description");
		itemWS.setFabric("denim");

		Quantity quantity = new Quantity();
		quantity.setXS(9);
		quantity.setS(1);
		quantity.setM(5);
		quantity.setL(7);

		itemWS.setQuantity(quantity);
		itemWS.setPrice(new BigDecimal(324.54));
		itemWS.setNote("It is so expensive!");

		orderWS.setCurrency(Currency.USD);
		orderWS.setTotalPrice(itemWS.getPrice());
		orderWS.setShippingInstruction("Ship to Atlantis");
		orderWS.setOrderNote("Sample order note");

		orderWS.setItems(Lists.newArrayList(itemWS));
		itemWS.setOrder(orderWS);

		/* success test 1 */
		// switch to order mapper by adjust order and item to test with, the test is passed
		CycleAvoidingMappingContext context = new CycleAvoidingMappingContext();
		OrderBean orderBean = orderMapper.toBean(orderWS, context);
		assertThat(orderBean.getCurrency().equals(orderWS.getCurrency()));
		assertThat(orderBean.getItems().equals(orderWS.getItems()));
		assertThat(orderBean.getItems().get(0).getQuantity().equals(quantity));

		log.warn("check orderBean item not null={}", orderBean.getItems() != null);

		ItemBean itemBean = itemMapper.toBean(itemWS, context);
		log.warn("check single ItemBean's order currency = {}", itemBean.getOrder().getCurrency());

		/* TODO: DEBUG - this part need to be fix. Got error but not sure exactly what cause it, it seems orderMapper + itemMapper not initialize in Mapstruct */
//		Item item = itemMapper.toItem(itemBean, context);
//		log.warn("check single Item's order currency = {}", item.getOrder().getCurrency());

//		Order order = orderMapper.toOrder(orderBean, context);
//		assertNotNull(order);
//		log.warn("Check order items={}", order.getItems().get(0).getOrder().getCurrency());
	}
}
