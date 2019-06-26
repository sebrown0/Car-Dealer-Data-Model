/**
 * 
 */
package com.sebrown.cardealer.datamodel.model.stock;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author Steve Brown
 *
 * Bean represents the stock status of a car. 
 */
@Entity
@Table(name="STOCK_STATUS")
@Data
public class StockStatus implements Serializable {

	private static final long serialVersionUID = 7813126143879053820L;

	@Id @Column(name = "status_id") 
	private int statusId;
		
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "stockStatus", cascade = CascadeType.ALL)
	private List<Car> cars;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "stockStatus")
	private List<StockList> stockLists;
	
	@Column(name = "status", nullable = false)
	private String status;
}
