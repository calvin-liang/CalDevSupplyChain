package com.caldevsupplychain.account.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "companies")
public class Company {

	@Column(name = "company_name", nullable = false)
	String name;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToOne(mappedBy = "company", cascade = CascadeType.ALL)
	private User user;

	public Company() {

	}

	public Company(String companyName) {
		this.name = companyName;
	}
}
