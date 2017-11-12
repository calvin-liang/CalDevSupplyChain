package com.caldevsupplychain.account.model;

import java.util.*;

import javax.persistence.*;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.caldevsupplychain.common.entity.BaseEntity;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "users")
public class User extends BaseEntity {

	@Column(name = "uuid", nullable = false, unique = true, updatable = false)
	private String uuid;

	@Column(name = "username", nullable = false)
	private String username;

	@Column(name = "email_address", nullable = false, updatable = false)
	private String emailAddress;

	@Column(name = "token")
	private String token;

	@Column(name = "password", nullable = false)
	private String password;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "company_id", referencedColumnName = "id")
	private Company company;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_2_role",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id"))
	private List<Role> roles = new ArrayList<>();

	@PrePersist
	@Override
	protected void onCreate() {
		super.onCreate();
		uuid = UUID.randomUUID().toString();
	}
}
