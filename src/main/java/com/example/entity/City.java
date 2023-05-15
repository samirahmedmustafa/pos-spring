package com.example.entity;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cities")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class City implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(unique = true)
	private String name;
	private String nameAr;
	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	private Country country;
//	@JsonIgnore
//	@OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
//	private List<Neighbourhood> neighbourhoods;
	@JsonIgnore
	@OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
	private List<Supplier> suppliers;
	@JsonIgnore
	@OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
	private List<Employee> employees;
}
