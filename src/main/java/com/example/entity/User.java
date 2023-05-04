package com.example.entity;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String userId;
	private String username;
	private String firstName;
	private String lastName;
	private String password;
	private String email;
	private String img;
	private Date lastLoginDate;
	private Date joinDate;
	private Boolean isActive;
	private Boolean isNotLocked;	
	@ManyToMany
	private Collection<Role> roles;
	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	private City city;
	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	private Country country;
	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	private Neighbourhood neighbourhood;
	private String notes;
	@JsonIgnore
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Order> orders;
}
