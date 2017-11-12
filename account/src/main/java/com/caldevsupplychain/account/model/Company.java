package com.caldevsupplychain.account.model;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "companies")
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "company_name", nullable = false)
	String name;

	@OneToOne(mappedBy = "company", cascade = CascadeType.ALL)
	private User user;

	public Company(){

	}

	public Company(String companyName) {
		this.name = companyName;
	}
}
