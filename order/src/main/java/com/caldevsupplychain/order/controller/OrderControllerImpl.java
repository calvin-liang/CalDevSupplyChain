package com.caldevsupplychain.order.controller;

import java.util.List;
import java.util.Optional;

import javax.annotation.Nullable;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.caldevsupplychain.account.service.AccountService;
import com.caldevsupplychain.account.util.ContextUtil;
import com.caldevsupplychain.account.vo.UserBean;
import com.caldevsupplychain.common.exception.ApiErrorsExceptionHandler;
import com.caldevsupplychain.common.type.ErrorCode;
import com.caldevsupplychain.common.ws.ApiErrorsWS;
import com.caldevsupplychain.order.service.ItemService;
import com.caldevsupplychain.order.service.OrderService;
import com.caldevsupplychain.order.util.CycleAvoidingMappingContext;
import com.caldevsupplychain.order.util.OrderMapper;
import com.caldevsupplychain.order.validator.OrderValidator;
import com.caldevsupplychain.order.vo.ItemWS;
import com.caldevsupplychain.order.vo.OrderBean;
import com.caldevsupplychain.order.vo.OrderStatus;
import com.caldevsupplychain.order.vo.OrderWS;
import io.swagger.annotations.Api;

@Slf4j
@RestController
@AllArgsConstructor
@Api(value = "/api/v1/orders", description = "Order API")
@RequestMapping("/api/v1/orders")
public class OrderControllerImpl implements OrderController {

	private ContextUtil contextUtil;

	private OrderService orderService;
	private ItemService itemService;
	private AccountService accountService;

	private OrderValidator orderValidator;

	private OrderMapper orderMapper;

	private ApiErrorsExceptionHandler apiErrorsExceptionHandler;

	@PostMapping
	@RequiresAuthentication
	@RequiresPermissions("order:create")
	public ResponseEntity<?> createOrder(@Validated @RequestBody OrderWS orderWS) {
		BindException errors = new BindException(orderWS, "OrderWS");
		orderValidator.validateCreateOrder(orderWS, errors);
		if (errors.hasErrors()) {
			return new ResponseEntity<>(new ApiErrorsWS(errors), HttpStatus.UNPROCESSABLE_ENTITY);
		}
		if (!accountService.findByUuid(orderWS.getUserUuid()).isPresent()) {
			return new ResponseEntity<>(new ApiErrorsWS(ErrorCode.USER_NOT_FOUND, String.format("Cannot find user with userUuid=%s", orderWS.getUserUuid())), HttpStatus.NOT_FOUND);
		}
		Optional<UserBean> agent = accountService.findDefaultAgent();
		if (!agent.isPresent()) {
			return new ResponseEntity<>(new ApiErrorsWS(ErrorCode.AGENT_NOT_FOUND, "Cannot find an agent."), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		OrderBean orderBean = orderMapper.toBean(orderWS, new CycleAvoidingMappingContext());
		orderBean.setAgentUuid(agent.get().getUuid());
		if (contextUtil.currentUser().get().isAgent()) {
			orderBean.setOrderStatus(OrderStatus.PENDING_APPROVAL);
		} else {
			// Order created by end user.
			orderBean.setOrderStatus(OrderStatus.PENDING);
		}

		OrderBean order = orderService.createOrder(orderBean);

		return new ResponseEntity<>(orderMapper.toWS(order, new CycleAvoidingMappingContext()), HttpStatus.OK);
	}

	@PutMapping("/{uuid}")
	@RequiresAuthentication
	@RequiresPermissions("order:update")
	public ResponseEntity<?> updateOrder(@PathVariable("uuid") String orderUuid, @Validated @RequestBody OrderWS orderWS) {
		BindException errors = new BindException(orderWS, "OrderWS");
		orderValidator.validateUpdateOrder(orderWS, errors);
		if (!orderUuid.equals(orderWS.getUuid())) {
			errors.rejectValue("uuid", ErrorCode.INVALID_PAYLOAD.name(), "Order uuid does not match.");
		}
		if (errors.hasErrors()) {
			return new ResponseEntity<>(new ApiErrorsWS(errors), HttpStatus.UNPROCESSABLE_ENTITY);
		}
		if (!orderService.orderExists(orderUuid)) {
			return new ResponseEntity<>(new ApiErrorsWS(ErrorCode.ORDER_NOT_FOUND.name(), String.format("Cannot find order with uuid=%s.", orderUuid)), HttpStatus.NOT_FOUND);
		}
		for (ItemWS item: orderWS.getItems()) {
			if (item.getUuid() != null && !itemService.orderItemExists(orderUuid, item.getUuid())) {
				return new ResponseEntity<>(new ApiErrorsWS(ErrorCode.ITEM_NOT_FOUND.name(), String.format("Cannot find item with uuid=%s.", item.getUuid())), HttpStatus.NOT_FOUND);
			}
		}
		OrderBean orderBean = orderMapper.toBean(orderWS, new CycleAvoidingMappingContext());

		OrderBean order = orderService.updateOrder(orderUuid, orderBean);

		return new ResponseEntity<>(orderMapper.toWS(order, new CycleAvoidingMappingContext()), HttpStatus.OK);
	}

	@DeleteMapping("/{uuid}")
	@RequiresAuthentication
	@RequiresPermissions("order:update")
	public ResponseEntity<?> deleteOrder(@PathVariable("uuid") String orderUuid) {

		Optional<OrderBean> order = orderService.findByUuid(orderUuid);

		if (!order.isPresent()) {
			log.error("Error in delete order. Fail in finding order uuid={}", orderUuid);
			return new ResponseEntity<>(new ApiErrorsWS(ErrorCode.ORDER_NOT_FOUND.name(), "Cannot find order by uuid that is not found"), HttpStatus.NOT_FOUND);
		}

		orderService.deleteOrder(order.get().getId());

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/{uuid}")
	@RequiresAuthentication
	@RequiresPermissions("order:read")
	public ResponseEntity<?> readOrder(@PathVariable("uuid") String orderUuid) {

		Optional<OrderBean> order = orderService.getOrder(orderUuid);

		if (!order.isPresent()) {
			log.error("Error in read order. Order not found");
			return new ResponseEntity<>(new ApiErrorsWS(ErrorCode.ORDER_NOT_FOUND.name(), "Order not found"), HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(orderMapper.toWS(order.get(), new CycleAvoidingMappingContext()), HttpStatus.OK);
	}

	@GetMapping
	@RequiresAuthentication
	@RequiresPermissions("order:read")
	public ResponseEntity<?> readOrders(@Nullable String userUuid, @Nullable String agentUuid) {
		List<OrderBean> orderBeans = null;

		if (userUuid != null && agentUuid != null) {
			log.error("Error in read orders. Can only read orders by either user or agent uuid and not both");
			return new ResponseEntity<>(new ApiErrorsWS(ErrorCode.READ_ORDERS_ERROR.name(), "Error in read orders. Can only read orders by either user or agent and not both"), HttpStatus.BAD_REQUEST);

		}
		if (userUuid == null && agentUuid == null) {
			log.error("Error in read orders. Read orders must have either user or agent uuid");
			return new ResponseEntity<>(new ApiErrorsWS(ErrorCode.READ_ORDERS_ERROR.name(), "Error in read orders. Read orders must have either user or agent uuid"), HttpStatus.BAD_REQUEST);

		} else if (Optional.ofNullable(userUuid).isPresent()) {
			orderBeans = orderService.findByUserUuid(userUuid);
		} else if (Optional.ofNullable(agentUuid).isPresent()) {
			orderBeans = orderService.findByAgentUuid(agentUuid);
		}

		return new ResponseEntity<>(orderMapper.toWSs(orderBeans, new CycleAvoidingMappingContext()), HttpStatus.OK);
	}

	/******************     NON API     *******************/
	public ResponseEntity<?> validateUserAndAgent(OrderWS orderWS) {

		Optional<UserBean> userBean = accountService.findByUuid(orderWS.getUserUuid());

		// check user uuid
		if (!userBean.isPresent()) {
			log.error("Error in create order. User uuid={} not found", orderWS.getUserUuid());
			return new ResponseEntity<>(new ApiErrorsWS(ErrorCode.USER_NOT_FOUND.name(), "In create order user uuid={" + orderWS.getUserUuid() + "} not found"), HttpStatus.NOT_FOUND);
		}
		return null;
	}
}
