package com.caldevsupplychain.order.model;

import com.caldevsupplychain.common.entity.BaseEntity;
import com.caldevsupplychain.order.vo.Currency;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "orders")
public class Order extends BaseEntity {

	@Column(name = "uuid", nullable = false, unique = true, updatable = false)
	private String uuid;

	@Column(name = "owner_id", nullable = false, updatable = false)
	private Long ownerId;

	@Column(name = "user_id", nullable = false, updatable = false)
	private Long userId;

	@Column(name = "agent_id", nullable = false, updatable = false)
	private Long agentId;

	@Column(name = "SKU", nullable = false)
	private String SKU;

	@OneToMany(mappedBy = "order")
	@BatchSize(size = 100)
	private List<Item> items = new ArrayList<>();

	@Column(name = "currency")
	@Enumerated(EnumType.STRING)
	private Currency currency;

	@Column(name = "total_price", precision = 22, scale = 10, nullable = false)
	private BigDecimal totalPrice;

	@Column(name = "shippping_instruction", nullable = false)
	private String shippingInstruction;

	@Column(name = "order_note")
	private String orderNote;

	@PrePersist
	@Override
	protected void onCreate() {
		super.onCreate();
		uuid = UUID.randomUUID().toString();
	}
}
