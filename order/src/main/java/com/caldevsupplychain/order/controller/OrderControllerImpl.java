package com.caldevsupplychain.order.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import javax.annotation.Nullable;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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

import com.caldevsupplychain.account.annotation.RequiresJwtAuthentication;
import com.caldevsupplychain.account.service.AccountService;
import com.caldevsupplychain.account.util.ContextUtil;
import com.caldevsupplychain.account.vo.UserBean;
import com.caldevsupplychain.common.exception.ApiErrorsExceptionHandler;
import com.caldevsupplychain.common.type.ErrorCode;
import com.caldevsupplychain.common.ws.ApiErrorsWS;
import com.caldevsupplychain.common.ws.ErrorWS;
import com.caldevsupplychain.order.service.OrderService;
import com.caldevsupplychain.order.util.CycleAvoidingMappingContext;
import com.caldevsupplychain.order.util.OrderMapper;
import com.caldevsupplychain.order.validator.OrderValidator;
import com.caldevsupplychain.order.vo.OrderBean;
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
	private AccountService accountService;

	private OrderValidator orderValidator;

	private OrderMapper orderMapper;

	private ApiErrorsExceptionHandler apiErrorsExceptionHandler;

	@PostMapping
	@RequiresJwtAuthentication
	@RequiresPermissions("order:create")
	public ResponseEntity<?> createOrder(@Validated @RequestBody OrderWS orderWS) {

		Optional<UserBean> currentUserBean = contextUtil.currentUser();

		if (!currentUserBean.isPresent()) {
			log.error("Current user not found");
			return new ResponseEntity<>(new ApiErrorsWS(ErrorCode.USER_NOT_FOUND.name(), "Cannot find current user"), HttpStatus.NOT_FOUND);
		}

		ResponseEntity<?> validateUserAgentUuidResult = validateUserAndAgent(orderWS);

		if (validateUserAgentUuidResult != null) {
			return validateUserAgentUuidResult;
		}

		BindException errors = new BindException(orderWS, "OrderWS");

		UserBean user = currentUserBean.get();

		if (!user.isAgent()) {

			BigDecimal orderTotalPrice = orderWS.getTotalPrice();

			if (orderTotalPrice != null && orderTotalPrice.compareTo(BigDecimal.ZERO) != 0) {
				log.error("Error in user create order. End user cannot set order total price. user uuid={}", user.getUuid());
				errors.rejectValue("totalPrice", ErrorCode.ORDER_TOTAL_PRICE_NOT_ZERO.name(), "User cannot set order total price.");
			}
		}

		orderValidator.validate(orderWS, errors);

		if (errors.hasErrors()) {
			log.error("Error in create order. Fail in order validation fields. orderWS={}", orderWS.toString());
			List<ErrorWS> errorWSList = apiErrorsExceptionHandler.generateErrorWSList(errors);
			return new ResponseEntity<>(new ApiErrorsWS(errorWSList), HttpStatus.UNPROCESSABLE_ENTITY);
		}

		OrderBean orderBean = orderMapper.toBean(orderWS, new CycleAvoidingMappingContext());

		OrderBean order = orderService.createOrder(orderBean);

		return new ResponseEntity<>(orderMapper.toWS(order, new CycleAvoidingMappingContext()), HttpStatus.OK);
	}

	@PutMapping("/order/{uuid}")
	@RequiresJwtAuthentication
	@RequiresPermissions("order:update")
	public ResponseEntity<?> updateOrder(@PathVariable("uuid") String orderUuid, @Validated @RequestBody OrderWS orderWS) {

		if (!orderService.orderExists(orderUuid)) {
			log.error("Error in update order. Fail in finding order uuid={}", orderUuid);
			return new ResponseEntity<>(new ApiErrorsWS(ErrorCode.ORDER_NOT_FOUND.name(), "Cannot find order by uuid"), HttpStatus.NOT_FOUND);
		}

		ResponseEntity<?> validateUserAgentUuidResult = validateUserAndAgent(orderWS);

		if (validateUserAgentUuidResult != null) {
			return validateUserAgentUuidResult;
		}

		BindException errors = new BindException(orderWS, "OrderWS");

		orderValidator.validate(orderWS, errors);

		if (errors.hasErrors()) {
			log.error("Error in update order. Fail in update order validation fields. orderWS={}", orderWS.toString());
			List<ErrorWS> errorWSList = apiErrorsExceptionHandler.generateErrorWSList(errors);
			return new ResponseEntity<>(new ApiErrorsWS(errorWSList), HttpStatus.UNPROCESSABLE_ENTITY);
		}

		OrderBean orderBean = orderMapper.toBean(orderWS, new CycleAvoidingMappingContext());

		OrderBean order = orderService.updateOrder(orderUuid, orderBean);

		return new ResponseEntity<>(orderMapper.toWS(order, new CycleAvoidingMappingContext()), HttpStatus.OK);
	}

	@DeleteMapping("/order/{uuid}")
	@RequiresJwtAuthentication
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

	@GetMapping("/order/{uuid}")
	@RequiresJwtAuthentication
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
	@RequiresJwtAuthentication
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
		Optional<UserBean> agentBean = accountService.findByUuid(orderWS.getAgentUuid());

		// check user uuid
		if (!userBean.isPresent()) {
			log.error("Error in create order. User uuid={} not found", orderWS.getUserUuid());
			return new ResponseEntity<>(new ApiErrorsWS(ErrorCode.USER_NOT_FOUND.name(), "In create order user uuid={" + orderWS.getUserUuid() + "} not found"), HttpStatus.NOT_FOUND);
		}

		// check agent uuid
		if (!agentBean.isPresent()) {
			log.error("Error in create order. Agent uuid={} not found", orderWS.getAgentUuid());
			return new ResponseEntity<>(new ApiErrorsWS(ErrorCode.AGENT_NOT_FOUND.name(), "In create order agent uuid={" + orderWS.getAgentUuid() + "} not found"), HttpStatus.NOT_FOUND);
		}

		// check agent role
		if (!agentBean.get().isAgent()) {
			log.error("Error in create order. Subject uuid={} found but role is not Agent", agentBean.get().getUuid());
			return new ResponseEntity<>(new ApiErrorsWS(ErrorCode.ROLE_NOT_MATCH.name(), "In create order agent uuid found but role not match."), HttpStatus.NOT_FOUND);
		}
		return null;
	}
}
