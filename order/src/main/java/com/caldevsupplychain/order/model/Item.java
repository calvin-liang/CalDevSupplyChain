package com.caldevsupplychain.order.model;

import com.caldevsupplychain.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "items")
public class Item extends BaseEntity {

	@Column(name = "uuid", nullable = false, unique = true, updatable = false)
	private String uuid;

	@Column(name = "color", nullable = false)
	private String color;

	@Column(name = "description", nullable = false)
	private String description;

	@Column(name = "fabric", nullable = false)
	private String fabric;

	@Column(name = "quantity", nullable = false)
	private Quantity quantity;

	@Column(name = "price", nullable = false)
	private BigDecimal price;

	@Column(name = "price_note")
	private String priceNote;

	@PrePersist
	@Override
	protected void onCreate() {
		super.onCreate();
		uuid = UUID.randomUUID().toString();
	}
}
