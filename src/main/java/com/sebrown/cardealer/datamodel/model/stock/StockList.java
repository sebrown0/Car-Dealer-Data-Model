/**
 * 
 */
package com.sebrown.cardealer.datamodel.model.stock;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Steve Brown
 *
 * Bean represents the cars that are in stock. 
 */
@Entity
@Table(name="STOCK_LIST")
@Getter @Setter
@NoArgsConstructor
public class StockList implements Serializable {

	private static final long serialVersionUID = 3266639570568288623L;

	@Id @Column(name = "stock_list_id") 
	private int stockListId;
		
	@ManyToOne @JoinColumn(name = "fk_stock_status_id")
	StockStatus stockStatus;
	
	@Column(name = "date_updated", nullable = false)
	private Calendar dateUpdated; 
}
