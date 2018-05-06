package com.iot.warehouse.warehousebot;

import com.iot.warehouse.warehousebot.repository.BotRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WarehousebotApplication {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private BotRepository botRepository;

	public static void main(String[] args) {
		SpringApplication.run(WarehousebotApplication.class, args);
	}
}
