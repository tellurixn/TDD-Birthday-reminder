package com.example.tdd.controllers;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(HomeController.class)
public class HomeControllerTests {

    @Autowired
    private MockMvc mockMvc;

    /*Тест, выполняющий HTTP-запрос GET с корневым путем / и затем убеждающийся, что
    контроллер выбрал имя представления home, а сама страница содержит фразу «Друзья».*/
    @Test
    public void testHomePage() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"))
                .andExpect(content().string(
                        containsString("Друзья")));
    }

    @Test
    void testPostForm() throws Exception {
        mockMvc.perform(post("/submitForm")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("friendName", "Sergey")
                        .param("friendLastName", "Sergeev")
                        .param("friendBirthday", "06-06-2006")
                )
                .andExpect(status().isOk());
    }
}