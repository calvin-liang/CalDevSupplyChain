package com.caldevsupplychain.order.model;

import com.caldevsupplychain.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

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

	@Id
	@Column(nullable = false, updatable = false)
	private Long user_id;

	@Id
	@Column(nullable = false, updatable = false)
	private Long agent_id;

	@Column(name = "SKU", nullable = false)
	private String SKU;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "order_2_item", joinColumns = @JoinColumn(name = "order_id"),
			inverseJoinColumns = @JoinColumn(name = "item_id"))
	private List<Item> items = new ArrayList<>();

	@Column(name = "currency", nullable = false)
	private String currency;

	@Column(name = "total_price", nullable = false)
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
