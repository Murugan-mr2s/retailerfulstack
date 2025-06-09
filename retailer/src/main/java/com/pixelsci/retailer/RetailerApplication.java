package com.pixelsci.retailer;

import com.pixelsci.retailer.model.Retailer;
import com.pixelsci.retailer.model.Shop;
import com.pixelsci.retailer.repository.RetailerRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class RetailerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RetailerApplication.class, args);
	}


	@Bean
	public ApplicationRunner applicationRunner(RetailerRepository retailerRepository) {


		return args -> {
				System.out.println("hello world");



			/*
			List<Shop> shops=  List.of (new Shop("murugan store","solai alagupram, madurai"));
			Retailer retailer = new Retailer("murugan", "madurai-11","muru@gmail.com","9159340375",shops);
			shops.forEach( shop -> shop.setRetailer(retailer) );
			retailerRepository.save(retailer);
			*/



		} ;


	}
}
