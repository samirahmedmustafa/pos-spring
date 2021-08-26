package com.example.entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "neighbourhoods")
public class Neighbourhood {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String name;
	private String nameAr;
	@JsonIgnore
	@OneToMany(mappedBy = "neighbourhood", cascade = CascadeType.MERGE)
	private List<Address> addresses;
	@JsonIgnore
	@OneToMany(mappedBy = "neighbourhood", cascade = CascadeType.MERGE)
	private List<Neighbourhood> cities;

	public String getNameAr() {
		return nameAr;
	}

	public void setNameAr(String nameAr) {
		this.nameAr = nameAr;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public List<Neighbourhood> getCities() {
		return cities;
	}

	public void setCities(List<Neighbourhood> cities) {
		this.cities = cities;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Neighbourhood() {
		super();
		// TODO Auto-generated constructor stub
	}

}