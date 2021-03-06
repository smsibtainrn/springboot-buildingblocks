package com.smsrn.restservices.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.context.annotation.Import;
import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;

@SuppressWarnings("rawtypes")
@ApiModel(description = "This model is to create a user")
@Entity
@Table(name = "user")
//@JsonIgnoreProperties({"firstName","lastName"}) -- Part of Static Filtering @JsonIgnore
//@JsonFilter("userFilter") - Used for MappingJacksonValue Filtering Section
public class User extends RepresentationModel {
	@ApiModelProperty(notes = "Auto generate unique id", required = true, position = 1)
	@Id
	@GeneratedValue
	@JsonView(Views.External.class)
	private Long id;

	@ApiModelProperty(notes = "Username should be in format flname", example = "smsrn", required = true, position = 2)
	@Size(min = 2, max = 50)
	@NotEmpty(message = "Username is Mendatory field. Please provided username")
	@Column(name = "USER_NAME", length = 50, nullable = false, unique = true)
	@JsonView(Views.External.class)
	private String userName;

	@Size(min = 2, max = 50, message = "FirstName should have atleast 2 characters")
	@Column(name = "FIRST_NAME", length = 50, nullable = false)
	@JsonView(Views.External.class)
	private String firstName;

	@Column(name = "LAST_NAME", length = 50, nullable = false)
	@JsonView(Views.External.class)
	private String lastName;

	@Column(name = "EMAIL_ADDRESS", length = 50, nullable = false)
	@JsonView(Views.External.class)
	private String email;

	@Column(name = "ROLE", length = 50, nullable = false)
	@JsonView(Views.Internal.class)
	private String role;

	@Column(name = "SSN", length = 50, nullable = false, unique = true)
//	@JsonIgnore -- Part of Static Filtering @JsonIgnore
	@JsonView(Views.Internal.class)
	private String ssn;

	@OneToMany(mappedBy = "user")
	@JsonView(Views.Internal.class)
	private List<Order> orders;

	@Column(name = "ADDRESS")
	private String address;

	public User() {

	}

	public User(Long id, @NotEmpty(message = "Username is Mendatory field. Please provided username") String userName,
			@Size(min = 2, message = "FirstName should have atleast 2 characters") String firstName, String lastName,
			String email, String role, String ssn, List<Order> orders, String address) {
		super();
		this.id = id;
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.role = role;
		this.ssn = ssn;
		this.orders = orders;
		this.address = address;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + ", role=" + role + ", ssn=" + ssn + ", orders=" + orders + ", address=" + address
				+ "]";
	}
}
