package com.barbers.schedule.service;

import com.barbers.schedule.domain.model.Customer;
import com.barbers.schedule.dto.AddressDTO;
import com.barbers.schedule.dto.CustomerDTO;
import com.barbers.schedule.repository.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class CustomerServiceTest {

    private static final long CUSTOMER_ID = 1L;
    private static final String CUSTOMER_NAME = "Barber1";
    private static final String ADDRESS_COUNTRY = "Brazil";

    @Mock
    private CustomerRepository customerRepository;

    private CustomerService service;

    @BeforeEach
    public void beforeEach() {
        MockitoAnnotations.openMocks(this);
        this.service = new CustomerService(customerRepository);
    }

    @Test
    void itShouldFindCustomerById() {
        when(customerRepository.findById(CUSTOMER_ID)).thenReturn(Optional.of(buildCustomer()));
        CustomerDTO result = service.findCustomerById(CUSTOMER_ID);
        Assertions.assertEquals(result.getId(), CUSTOMER_ID);
        Assertions.assertEquals(result.getName(), CUSTOMER_NAME);
    }

    @Test
    void itShouldCreateCustomer() {
        when(customerRepository.save(any())).thenReturn(buildCustomer());
        CustomerDTO result = service.createCustomer(buildCustomerDTO());
        Assertions.assertEquals(result.getId(), CUSTOMER_ID);
        Assertions.assertEquals(result.getName(), CUSTOMER_NAME);
    }

    private Customer buildCustomer() {
        Customer customer = new Customer();
        customer.setId(CUSTOMER_ID);
        customer.setName(CUSTOMER_NAME);
        return customer;
    }

    private CustomerDTO buildCustomerDTO() {
        return CustomerDTO.builder()
                .id(CUSTOMER_ID)
                .name(CUSTOMER_NAME)
                .address(buildAddressDTO())
                .build();
    }

    private AddressDTO buildAddressDTO() {
        return AddressDTO.builder()
                .country(ADDRESS_COUNTRY)
                .build();
    }

}
