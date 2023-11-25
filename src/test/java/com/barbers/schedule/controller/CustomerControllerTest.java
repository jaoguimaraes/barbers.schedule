package com.barbers.schedule.controller;

import com.barbers.schedule.dto.AddressDTO;
import com.barbers.schedule.dto.CustomerDTO;
import com.barbers.schedule.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTest {

    private static final Long CUSTOMER_ID = 1L;
    private static final String CUSTOMER_NAME = "Barber1";
    private static final String ADDRESS_COUNTRY = "Brazil";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void itShouldGetCustomerById() throws Exception {
        when(customerService.findCustomerById(CUSTOMER_ID)).thenReturn(buildCustomerDTO());
        mockMvc.perform(MockMvcRequestBuilders.get("/customer/1")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(CUSTOMER_ID))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(CUSTOMER_NAME))
                .andExpect(MockMvcResultMatchers.jsonPath("$.address").exists());
    }

    @Test
    void itShouldCreateCustomer() throws Exception {
        when(customerService.createCustomer(any())).thenReturn(buildCustomerDTO());
        mockMvc.perform(MockMvcRequestBuilders.post("/customer/create")
                        .content(objectMapper.writeValueAsString(buildCustomerDTO()))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(CUSTOMER_ID))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(CUSTOMER_NAME))
                .andExpect(MockMvcResultMatchers.jsonPath("$.address").exists());
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
