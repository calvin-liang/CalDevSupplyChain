package com.caldevsupplychain.order.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.hibernate.annotations.BatchSize;

import com.caldevsupplychain.common.entity.BaseEntity;
import com.caldevsupplychain.order.vo.Currency;
import com.caldevsupplychain.order.vo.OrderStatus;
import com.caldevsupplychain.order.vo.OrderType;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "orders")
public class Order extends BaseEntity {

	@Column(name = "uuid", nullable = false, unique = true, updatable = false)
	private String uuid;

	@Column(name = "display_id", nullable = false, unique = true, updatable = false)
	private String displayId;

	@Column(name = "user_uuid", nullable = false, updatable = false)
	private String userUuid;

	@Column(name = "agent_uuid", nullable = false, updatable = false)
	private String agentUuid;

	@Column(name = "SKU", nullable = false)
	private String sku;

	@Column(name = "order_type")
	@Enumerated(EnumType.STRING)
	private OrderType orderType;

	@Column(name = "order_status")
	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;

	@OneToMany(mappedBy = "order")
	@BatchSize(size = 100)
	private List<Item> items = new ArrayList<>();

	@Column(name = "currency")
	@Enumerated(EnumType.STRING)
	private Currency currency;

	@Column(name = "total_price", precision = 22, scale = 10, nullable = false)
	private BigDecimal totalPrice;

	@Column(name = "shipping_instruction", nullable = false)
	private String shippingInstruction;

	@Column(name = "order_note")
	private String orderNote;

	@PrePersist
	@Override
	protected void onCreate() {
		super.onCreate();
		uuid = UUID.randomUUID().toString();
		String[] arr = uuid.split("-");
		displayId = arr[arr.length - 1].toUpperCase();
	}
}