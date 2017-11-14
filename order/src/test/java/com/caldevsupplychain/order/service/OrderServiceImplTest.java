package com.caldevsupplychain.order.service;

import com.caldevsupplychain.order.model.Order;
import com.caldevsupplychain.order.model.Quantity;
import com.caldevsupplychain.order.repository.OrderRepository;
import com.caldevsupplychain.order.util.CycleAvoidingMappingContext;
import com.caldevsupplychain.order.util.ItemMapper;
import com.caldevsupplychain.order.util.OrderMapper;
import com.caldevsupplychain.order.vo.*;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.AssertJUnit.assertNotNull;

@Slf4j
public class OrderServiceImplTest {

	@Mock
	private OrderMapper orderMapper = Mappers.getMapper(OrderMapper.class);

	@Mock
	private ItemMapper itemMapper = Mappers.getMapper(ItemMapper.class);;

	@Mock
	private OrderRepository orderRepository;

	@InjectMocks
	private OrderService orderService = new OrderServiceImpl(orderRepository, orderMapper, itemMapper);

	@BeforeMethod(alwaysRun = true)
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void test(){


		//		TODO: delete later after testing
//		List<String> greeting = Lists.newArrayList("hello", "bye");
//		Optional.ofNullable(greeting).ifPresent(temps -> {
//			temps.stream().forEach(g -> {
//				System.out.println(g);
//			});
//		});
//		System.out.println(tmp);

//		OrderWS orderWS = new OrderWS();
//		orderWS.setOrderId(123L);
//		orderWS.setUserId(1L);
//		orderWS.setAgentId(2L);
//		orderWS.setSKU("SKU123");
//
//		ItemWS itemWS = new ItemWS();
//		itemWS.setColor("red");
//		itemWS.setDescription("item description");
//		itemWS.setFabric("denim");
//
//		Quantity quantity = new Quantity();
//		quantity.setXS(9);
//		quantity.setS(1);
//		quantity.setM(5);
//		quantity.setL(7);
//
//		itemWS.setQuantity(quantity);
//		itemWS.setPrice(new BigDecimal(324.54));
//		itemWS.setNote("It is so expensive!");
//
//		orderWS.setCurrency(Currency.USD);
//		orderWS.setTotalPrice(itemWS.getPrice());
//		orderWS.setShippingInstruction("Ship to Atlantis");
//		orderWS.setOrderNote("Sample order note");
//
//		orderWS.setItems(Lists.newArrayList(itemWS));
//		itemWS.setOrder(orderWS);
//
//		CycleAvoidingMappingContext context = new CycleAvoidingMappingContext();
//
//		OrderBean orderBean = orderMapper.toBean(orderWS, context);
//		assertThat(orderBean.getItems().get(0).equals(itemWS));
//		assertThat(orderBean.getCurrency().equals("USD"));

//		log.warn("check orderBean item not null={}", orderBean.getItems() != null);

//		ItemBean itemBean = itemMapper.toBean(itemWS, context);
//		log.warn("check single ItemBean's order currency = {}", itemBean.getOrder().getCurrency());

		/* TODO: DEBUG - this part got error but not sure why, it seems orderMapper + itemMapper not initialize in Mapstruct
		*  Tried but really can't figure out. :(
		* */
//		Item item = itemMapper.toItem(itemBean, context);
//		log.warn("check single Item's order currency = {}", item.getOrder().getCurrency());

//		Order order = orderMapper.toOrder(orderBean, context);
//		assertNotNull(order);
//		log.info("check order currency = {}", order.getCurrency());
//		log.info("check order currency = {}", order.getItems().get(0).getOrder().getCurrency());

	}


}
