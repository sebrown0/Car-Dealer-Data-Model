/**
 * 
 */
package com.sebrown.cardealer.datamodel.model.stock;

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
import lombok.ToString;

/**
 * @author Steve Brown
 *
 * Bean for Stock Files. 
 */
@Entity
@Table(name="STOCK_FILE")
@Getter @Setter
@NoArgsConstructor
@ToString
public class StockFile {

	@Id @Column(name = "update_id") 
	private int fileId;
	
	@Column(name = "date_of_last_update", nullable = false)
	private Calendar lastUpdate;
	
	@Column(name = "file_name", nullable = false)
	private String fileName;
	
	@ManyToOne @JoinColumn(name = "fk_stock_list_id")
	StockList stockList;
}
