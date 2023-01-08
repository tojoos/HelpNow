package dev.tojoos.helpnow.controllers;

import dev.tojoos.helpnow.model.User;
import dev.tojoos.helpnow.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class UserControllerTest {

    @Mock
    UserService userService;

    UserController userController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userController = new UserController(userService);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    void testGetUserList() throws Exception {
        User user1 = User.builder().id(1L).username("username1").build();
        User user2 = User.builder().id(2L).username("username2").build();
        User user3 = User.builder().id(3L).username("username3").build();

        //when
        when(userService.getAll()).thenReturn(new ArrayList<>(List.of(user1, user2, user3)));

        mockMvc.perform(get("/user/list"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE));

        verify(userService, times(1)).getAll();
    }
}