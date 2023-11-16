package com.rentalCar.managementBackend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@SpringBootTest
@AutoConfigureMockMvc
public class VehicleControllerTests {
    @Autowired
    MockMvc mockMvc;

    @Test
    void getAllVehicles() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/vehicles"))
            .andExpect(MockMvcResultMatchers.status().isOk());
    }

}
