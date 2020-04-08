package com.jy.medicine;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.jy.medicine.dao")
@ComponentScan("com.jy.medicine.*")
public class MedicineApplication {
	public static void main(String[] args) {
		SpringApplication.run(MedicineApplication.class, args);
	}

}
