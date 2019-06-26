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

import lombok.Data;

/**
 * @author Steve Brown
 *
 * Bean for Stock Files. 
 */
@Entity
@Table(name="STOCK_FILE")
@Data
public class StockFile implements Serializable{

	private static final long serialVersionUID = 7587976947050115582L;

	@Id @Column(name = "update_id") 
	private int fileId;
	
	@Column(name = "date_of_last_update", nullable = false)
	private Calendar lastUpdate;
	
	@Column(name = "file_name", nullable = false)
	private String fileName;
	
	@ManyToOne @JoinColumn(name = "fk_stock_list_id")
	StockList stockList;
}
