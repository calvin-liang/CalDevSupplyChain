package com.caldevsupplychain.order.model;

import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.caldevsupplychain.common.entity.BaseEntity;
import com.caldevsupplychain.order.util.QuantityConverterJson;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "items")
@ToString(exclude = "order")
public class Item extends BaseEntity {

	@Column(name = "uuid", nullable = false, unique = true, updatable = false)
	private String uuid;

	@Column(name = "display_id", nullable = false, unique = true, updatable = false)
	private String displayId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id")
	private Order order;

	@Column(name = "color", nullable = false)
	private String color;

	@Column(name = "description", nullable = false)
	private String description;

	@Column(name = "fabric", nullable = false)
	private String fabric;

	@Convert(converter = QuantityConverterJson.class)
	@Column(name = "quantity", nullable = false)
	private Quantity quantity;

	@Column(name = "price", precision = 22, scale = 10, nullable = false)
	private BigDecimal price;

	@Column(name = "note")
	private String note;

	@PrePersist
	@Override
	protected void onCreate() {
		super.onCreate();
		uuid = UUID.randomUUID().toString();
		String[] arr = uuid.split("-");
		displayId = arr[arr.length - 1].toUpperCase();
	}

}