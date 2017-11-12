package com.caldevsupplychain.order.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.caldevsupplychain.account.annotation.RequiresJwtAuthentication;
import com.caldevsupplychain.account.service.AccountService;
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
import com.caldevsupplychain.order.vo.OrderStatus;
import com.caldevsupplychain.order.vo.OrderWS;
import io.swagger.annotations.Api;

@Slf4j
@RestController
@AllArgsConstructor
@Api(value="/api/v1/orders", description = "Order API")
@RequestMapping("/api/v1/orders")
public class OrderControllerImpl implements OrderController {

	private OrderService orderService;
	private AccountService accountService;

	private OrderValidator orderValidator;

	private OrderMapper orderMapper;

	private ApiErrorsExceptionHandler apiErrorsExceptionHandler;

	@PostMapping
	@RequiresJwtAuthentication
	@RequiresPermissions("order:create")
	public ResponseEntity<?> createOrder(@Validated @RequestBody OrderWS orderWS) {

		ResponseEntity<?> validateUserAgentUuidResult = validateUserAndAgentUuid(orderWS);


		if(validateUserAgentUuidResult != null) {
			return validateUserAgentUuidResult;
		}

		String currentUserUuid = (String) SecurityUtils.getSubject().getPrincipal();

		Optional<UserBean> currentUserBean =  accountService.findByUuid(currentUserUuid);

		BindException errors = new BindException(orderWS, "OrderWS");

		UserBean user = currentUserBean.get();

		if(!user.isAgent()){

			BigDecimal orderTotalPrice = orderWS.getTotalPrice();

			if(orderTotalPrice != null && orderTotalPrice.compareTo(BigDecimal.ZERO) != 0){
				log.error("Error in user create order. End user cannot set order total price. user uuid={}", currentUserUuid);
				errors.rejectValue("totalPrice", ErrorCode.ORDER_TOTAL_PRICE_NOT_ZERO.name(),"User cannot set order total price.");
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

		Optional<OrderBean> tmpOrderBean = orderService.findByUuid(orderUuid);

		if(!tmpOrderBean.isPresent()) {
			log.error("Error in update order. Fail in finding order uuid={}", orderUuid);
			return new ResponseEntity<>(new ApiErrorsWS(ErrorCode.ORDER_UUID_NOT_FOUND.name(), "Cannot find order by uuid"), HttpStatus.NOT_FOUND);
		}

		ResponseEntity<?> validateUserAgentUuidResult = validateUserAndAgentUuid(orderWS);

		if(validateUserAgentUuidResult != null) {
			return validateUserAgentUuidResult;
		}

		BindException errors = new BindException(orderWS, "OrderWS");

		orderValidator.validate(orderWS, errors);

		if (errors.hasErrors()) {
			log.error("Error in update order. Fail in update order validation fields. orderWS={}", orderWS.toString());
			List<ErrorWS> errorWSList = apiErrorsExceptionHandler.generateErrorWSList(errors);
			return new ResponseEntity<>(new ApiErrorsWS(errorWSList), HttpStatus.UNPROCESSABLE_ENTITY);
		}

		OrderBean order = tmpOrderBean.get();

		OrderBean orderBean = orderMapper.toBean(orderWS, new CycleAvoidingMappingContext());

		orderBean.setId(order.getId());

		order = orderService.updateOrder(orderBean);

		return new ResponseEntity<>(orderMapper.toWS(order, new CycleAvoidingMappingContext()), HttpStatus.OK);
	}

	@DeleteMapping("/order/{uuid}")
	@RequiresJwtAuthentication
	@RequiresPermissions("order:update")
	public ResponseEntity<?> deleteOrder(@PathVariable("uuid") String orderUuid) {

		Optional<OrderBean> order = orderService.findByUuid(orderUuid);

		if(!order.isPresent()){
			log.error("Error in delete order. Fail in finding order uuid={}", orderUuid);
			return new ResponseEntity<>(new ApiErrorsWS(ErrorCode.ORDER_UUID_NOT_FOUND.name(), "Cannot find order by uuid that is not found"), HttpStatus.NOT_FOUND);
		}

		orderService.deleteOrder(order.get());

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/order/{uuid}")
	@RequiresJwtAuthentication
	@RequiresPermissions("order:read")
	public ResponseEntity<?> readOrder(@PathVariable("uuid") String orderUuid) {

		Optional<OrderBean> order = orderService.findByUuid(orderUuid);

		if(!order.isPresent()){
			log.error("Error in read order. Fail in finding order uuid={}", orderUuid);
			return new ResponseEntity<>(new ApiErrorsWS(ErrorCode.ORDER_DISPLAY_ID_NOT_FOUND.name(), "Cannot read order by order uuid that is not found"), HttpStatus.NOT_FOUND);
		}

		if(order.get().getOrderStatus().equals(OrderStatus.DELETED)){
			log.error("User and Agent cannot read deleted order uuid ={}", orderUuid);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(orderMapper.toWS(order.get(), new CycleAvoidingMappingContext()), HttpStatus.OK);
	}

	@GetMapping(params = "userUuid")
	@RequiresJwtAuthentication
	@RequiresPermissions("order:read")
	public ResponseEntity<?> readOrdersByUserUuid(@RequestParam(value = "userUuid") String userUuid) {

		Optional<List<OrderBean>> tmpOrderBeans = orderService.findByUserUuid(userUuid);

		if(!tmpOrderBeans.isPresent()){
			log.error("Error in read orders. Fail in finding user uuid={}", userUuid);
			return new ResponseEntity<>(new ApiErrorsWS(ErrorCode.USER_UUID_NOT_FOUND.name(), "Cannot read orders by user uuid that is not found"), HttpStatus.NOT_FOUND);
		}

		List<OrderBean> orderBeans = tmpOrderBeans.get();

		for(OrderBean orderBean : orderBeans) {
			if(orderBean.getOrderStatus().equals(OrderStatus.DELETED)){
				log.error("User cannot read deleted orders");
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}

		return new ResponseEntity<>(orderMapper.toWSs(orderBeans, new CycleAvoidingMappingContext()), HttpStatus.OK);
	}

	@GetMapping(params = "agentUuid")
	@RequiresJwtAuthentication
	@RequiresPermissions("order:read")
	public ResponseEntity<?> readOrdersByAgentUuid(@RequestParam(value = "agentUuid") String agentUuid) {

		Optional<List<OrderBean>> tmpOrderBeans = orderService.findByAgentUuid(agentUuid);

		if(!tmpOrderBeans.isPresent()){
			log.error("Error in read orders. Fail in finding user uuid={}", agentUuid);
			return new ResponseEntity<>(new ApiErrorsWS(ErrorCode.USER_UUID_NOT_FOUND.name(), "Cannot read orders by agent uuid that is not found"), HttpStatus.NOT_FOUND);
		}

		List<OrderBean> orderBeans = tmpOrderBeans.get();

		for(OrderBean orderBean : orderBeans) {
			if(orderBean.getOrderStatus().equals(OrderStatus.DELETED)){
				log.error("Agent cannot read deleted orders");
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}

		return new ResponseEntity<>(orderMapper.toWSs(orderBeans, new CycleAvoidingMappingContext()), HttpStatus.OK);
	}

	/******************     NON API     *******************/
	public ResponseEntity<?> validateUserAndAgentUuid(OrderWS orderWS) {

		Optional<UserBean> userBean = accountService.findByUuid(orderWS.getUserUuid());
		Optional<UserBean> agentBean = accountService.findByUuid(orderWS.getAgentUuid());

		// check user uuid
		if(!userBean.isPresent()){
			log.error("Error in create order. User uuid={} not found", orderWS.getUserUuid());
			return new ResponseEntity<>(new ApiErrorsWS(ErrorCode.USER_UUID_NOT_FOUND.name(), "In create order user uuid={" + orderWS.getUserUuid() + "} not found"), HttpStatus.NOT_FOUND);
		}

		// check agent uuid
		if(!agentBean.isPresent()){
			log.error("Error in create order. Agent uuid={} not found", orderWS.getAgentUuid());
			return new ResponseEntity<>(new ApiErrorsWS(ErrorCode.AGENT_UUID_NOT_FOUND.name(), "In create order agent uuid={" + orderWS.getAgentUuid() + "} not found"), HttpStatus.NOT_FOUND);
		}

		// check user role
		if(!userBean.get().isUser()) {
			log.error("Error in create order. Subject uuid={} found but role is not User", userBean.get().getUuid());
			return new ResponseEntity<>(new ApiErrorsWS(ErrorCode.ROLE_NOT_MATCH.name(), "In create order user uuid found but role not match."), HttpStatus.NOT_FOUND);
		}

		// check agent role
		if(!agentBean.get().isAgent()) {
			log.error("Error in create order. Subject uuid={} found but role is not Agent", agentBean.get().getUuid());
			return new ResponseEntity<>(new ApiErrorsWS(ErrorCode.ROLE_NOT_MATCH.name(), "In create order agent uuid found but role not match."), HttpStatus.NOT_FOUND);
		}
		return null;
	}
}
