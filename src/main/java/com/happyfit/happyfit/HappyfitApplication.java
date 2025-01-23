package com.happyfit.happyfit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
//import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

@SpringBootApplication
@EnableScheduling
//@EnableEncryptableProperties
public class HappyfitApplication {

	public static void main(String[] args) {
		SpringApplication.run(HappyfitApplication.class, args);
	}
}