package dev.tojoos.helpnow.repositories;

import dev.tojoos.helpnow.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.DirtiesContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository.save(User.builder().id(1L).username("username1").build());
        userRepository.save(User.builder().id(2L).username("username2").build());
        userRepository.save(User.builder().id(3L).username("username3").build());
        userRepository.save(User.builder().id(4L).username("username4").build());

    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    void testFindAll() {
        //then
        List<User> foundUsers = new ArrayList<>(userRepository.findAll());
        assertEquals(4, foundUsers.size());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    void testDeleteById() {
        //then
        userRepository.deleteById(1L);
        assertEquals(3, userRepository.findAll().spliterator().estimateSize());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    void testDeleteByIdNotFound() {
        //then
        assertThrows(EmptyResultDataAccessException.class, () -> userRepository.deleteById(99L));
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    void testSave() {
        //then
        User UserToSave = User.builder().id(9L).username("username9").build();
        userRepository.save(UserToSave);
        assertEquals(5, userRepository.findAll().spliterator().estimateSize());
    }
}
