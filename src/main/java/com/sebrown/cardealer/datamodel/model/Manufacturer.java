/**
 * 
 */
package com.sebrown.cardealer.datamodel.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Steve Brown
 * 
 * Bean for the 'Manufacturer' table.
 *
 */
@Entity
@Getter @Setter 
@NoArgsConstructor
public class Manufacturer {
	
	@Id	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "manufacturer_id")
	private Long id;
	
	@Column(nullable = false, name = "manufacturer_name")
	private String name;
	
	@OneToMany(mappedBy = "manufacturer")
	private List<Model> models = new ArrayList<>();
	
	public Manufacturer(Long manId, String manName) {
		this.id = manId;
		this.name = manName;
	}	
}
