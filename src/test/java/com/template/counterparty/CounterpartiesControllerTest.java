package com.template.counterparty;

import com.template.counterparty.db.CounterpartyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CounterpartiesControllerTest {

    @Autowired
    CounterpartyRepository repo;

    @Autowired
    private MockMvc mvc;

    @WithMockUser(roles = "USER", username = "user")
    @Test
    public void getTrades() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/counterparties").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(3)));
    }

    @WithMockUser(roles = "USER", username = "user")
    @Test
    public void getTrade() throws Exception {
        String CPTY = "BNP";
        mvc.perform(MockMvcRequestBuilders.get("/counterparty/" + CPTY).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(CPTY)));
    }

}
