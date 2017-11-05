package com.caldevsupplychain.order.model;

import com.caldevsupplychain.common.entity.BaseEntity;
import com.caldevsupplychain.common.util.JpaConverterJson;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "items")
public class Item extends BaseEntity {

	@Column(name = "uuid", nullable = false, unique = true, updatable = false)
	private String uuid;

	@ManyToOne(fetch = FetchType.LAZY)
	@BatchSize(size = 100)
	@JoinColumn(name = "order_id")
	private Order order;

	@Column(name = "color", nullable = false)
	private String color;

	@Column(name = "description", nullable = false)
	private String description;

	@Column(name = "fabric", nullable = false)
	private String fabric;

	@Convert(converter = JpaConverterJson.class)
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
	}
}
