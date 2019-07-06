package com.sebrown.cardealer.datamodel.service;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan
@Configuration
public class ServiceConfiguration {
	/*
	 * Path to stock files.
	 */
	public static final String CAR_STOCK_PATH = ("src/main/resources/data/");

}
