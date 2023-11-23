package com.barbers.schedule;

import com.barbers.schedule.domain.model.Address;
import com.barbers.schedule.domain.model.Customer;
import com.barbers.schedule.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
