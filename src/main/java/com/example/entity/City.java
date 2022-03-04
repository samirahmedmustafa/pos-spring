package com.example.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.sun.istack.NotNull;

@Entity
@Table(name = "cities")
public class City implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(unique = true)
	@NotNull
	private String name;
	private String nameAr;
	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	private Country country;
	@JsonIgnore
	@OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
	private List<Neighbourhood> neighbourhoods;
	@JsonIgnore
	@OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
	private List<Supplier> suppliers;
	@JsonIgnore
	@OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
	private List<User> users;

	public List<User> getUsers() {
		return users;
	}

	public void setEmployees(List<User> users) {
		this.users = users;
	}

	public List<Supplier> getSuppliers() {
		return suppliers;
	}

	public void setSuppliers(List<Supplier> suppliers) {
		this.suppliers = suppliers;
	}

	public List<Neighbourhood> getNeighbourhoods() {
		return neighbourhoods;
	}

	public void setNeighbourhoods(List<Neighbourhood> neighbourhoods) {
		this.neighbourhoods = neighbourhoods;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public String getNameAr() {
		return nameAr;
	}

	public void setNameAr(String nameAr) {
		this.nameAr = nameAr;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public City() {
		super();
		// TODO Auto-generated constructor stub
	}

}
