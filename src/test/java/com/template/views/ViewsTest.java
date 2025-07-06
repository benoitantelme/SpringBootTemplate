package com.template.views;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ViewsTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getLogin() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/login").accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("<label> Password: <input type=\"password\" name=\"password\"/> </label>")));
    }

    @WithMockUser(roles = "USER", username = "user")
    @Test
    public void getHome() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/home").accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("here</a> to see a greeting.</p>")));
    }

}
