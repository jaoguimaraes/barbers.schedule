package com.barbers.schedule;

import com.barbers.schedule.domain.model.*;
import com.barbers.schedule.repository.AddressRepository;
import com.barbers.schedule.repository.CustomerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;

@Slf4j
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	private Address buildAddress() {
		Address address = new Address();
		address.setCountry("Brasil");
		address.setCity("São Paulo");
		address.setState("São Paulo");
		address.setNumber("100");
		address.setStreet("Rua Waldemir de Oliveira");
		address.setZipCode("05386-360");
		return address;
	}

	private Customer buildCustomer(final Address address) {
		Customer customer = new Customer();
		customer.setAddress(address);
		customer.setName("Customer Name");
		return customer;
	}

	private Employee buildEmployee() {
		Employee employee = new Employee();
		employee.setName("Lucas");
		employee.setActive(true);
		employee.setServices(Set.of(buildServices()));
		return employee;
	}

	private Services buildServices() {
		Services services = new Services();
		services.setName("Corte de Cabelo");
		services.setCost(BigDecimal.TEN);
		services.setDescription("Corte de cabelo masculino");
		return new Services();
	}

	private Schedules buildSchedules() {
		Schedules schedules = new Schedules();
		schedules.setName("Corte Masculino");
		schedules.setClientName("Wallace");
		return schedules;
	}
}
