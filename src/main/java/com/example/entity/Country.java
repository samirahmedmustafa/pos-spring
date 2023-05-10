package com.example.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "countries")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Country implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(unique=true)
	@NotNull
	private String name;
	private String nameAr;
	@Column(unique=true)
	private String code;
	@JsonIgnore
	@OneToMany(mappedBy = "country", cascade = CascadeType.ALL)
	private List<Address> addresses;
	@JsonIgnore
	@OneToMany(mappedBy = "country", cascade = CascadeType.ALL)
	private List<City> cities;
	@JsonIgnore
	@OneToMany(mappedBy = "country", cascade = CascadeType.ALL)
	private List<Supplier> suppliers;
	@JsonIgnore
	@OneToMany(mappedBy = "country", cascade = CascadeType.ALL)
	private List<Employee> employees;
}
