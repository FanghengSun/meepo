package com.fangheng.meepo;

import com.fangheng.meepo.controller.UserController;
import com.fangheng.meepo.service.UserServiceImpl;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @author Fangheng Sun on 2020/10/29
 */

@RunWith(SpringRunner.class)
@WebMvcTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @MockBean
    private UserServiceImpl userService;

    @Autowired
    UserController userController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void whenPostRequestToUsersAndValidUser_thenCorrectResponse() throws Exception {
        MediaType textPlainUtf8 = new MediaType(MediaType.TEXT_PLAIN, Charset.forName("UTF-8"));
        String user = "{\"userName\" : \"bob\", \"password\": \"12345678\",\"email\" : \"bob@domain.com\", " +
                "\"phone\" : \"15888888888\", \"fullName\" : \"bob southwick\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/user/save")
                .content(user)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(textPlainUtf8));
    }

    @Test
    public void whenPostRequestToUsersAndInValidUser_thenCorrectResponse() throws Exception {
        String user = "{\"userName\" : \"bob\", \"password\": \"12345\", \"email\" : \"bob@domain.com\", " +
                "\"phone\" : \"15888888888\", \"fullName\" : \"bob southwick\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/user/save")
                .content(user)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.password", Is.is("Password length should be between 8 and 30")))
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON));
    }
}
