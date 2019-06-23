/**
 * 
 */
package com.sebrown.cardealer.datamodel.model.stock;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Steve Brown
 *
 * Bean represents the stock status of a car. 
 */
@Entity
@Table(name="STOCK_STATUS")
@Getter @Setter
@NoArgsConstructor
public class StockStatus {

	@Id @Column(name = "status_id") 
	private int statusId;
		
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "stockStatus", cascade = CascadeType.ALL)
	private List<Car> cars;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "stockStatus")
	private List<StockList> stockLists;
	
	@Column(name = "status", nullable = false)
	private String status;
}
