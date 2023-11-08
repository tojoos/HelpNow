package dev.tojoos.helpnow.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserTest {

    User user;

    @BeforeEach
    void setUp() {
        user = new User();
    }

    @Test
    void testBasicGettersAndSetters() {
        Long id = 2L;
        String username = "example";
        user.setId(id);
        user.setUsername(username);

        assertEquals(id, user.getId());
        assertEquals(username, user.getUsername());
    }
}
