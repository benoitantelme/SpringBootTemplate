package com.template.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest()
@AutoConfigureMockMvc
public class SecurityUserTest {

    @Autowired
    private MockMvc mvc;


    @WithMockUser(roles = "USER", username = "user")
    @Test
    void testHomeRightPw() throws Exception {
        mvc.perform(get("/"))
                .andExpect(status().isOk());
    }

    @Test
    void testHomeWrongPw() throws Exception {
        mvc.perform(get("/").with(httpBasic("wrong_user", "1234567")))
                .andExpect(status().isUnauthorized());
    }


}
