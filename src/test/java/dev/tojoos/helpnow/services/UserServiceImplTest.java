package dev.tojoos.helpnow.services;

import dev.tojoos.helpnow.model.User;
import dev.tojoos.helpnow.repositories.RoleRepository;
import dev.tojoos.helpnow.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.EntityNotFoundException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @Mock
    UserRepository userRepository;

    @Mock
    RoleRepository roleRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = new UserServiceImpl(userRepository, roleRepository, passwordEncoder);
    }

    @Test
    void testSave() {
        //given
        User user1 = User.builder().id(10L).build();

        //when
        when(userRepository.save(any(User.class))).thenReturn(user1);

        User savedUser = userService.add(user1);

        //then
        assertEquals(10L, savedUser.getId());
        verify(userRepository,times(1)).save(any());
    }

    @Test
    void testSaveNull() {
        //when
        when(userRepository.save(any(User.class))).thenReturn(null);

        assertThrows(NullPointerException.class, () -> userService.add(null), "Expected to throw, but didn't.");


        //then
        verify(userRepository,times(0)).save(any());
    }

    @Test
    void findAll() {
        //given
        List<User> users = new ArrayList<>();
        users.add(User.builder().id(1L).build());
        users.add(User.builder().id(2L).build());
        users.add(User.builder().id(3L).build());

        //when
        when(userRepository.findAll()).thenReturn(users);
        List<User> usersFound = userService.getAll();

        //then
        assertEquals(3, usersFound.size());
        verify(userRepository,times(1)).findAll();
    }

    @Test
    void findById() {
        //given
        User user1 = User.builder().id(20L).build();
        Optional<User> userOpt = Optional.of(user1);

        //when
        when(userRepository.findById(anyLong())).thenReturn(userOpt);
        User foundUser = userService.getById(1L);

        //then
        assertEquals(20L, foundUser.getId());
        verify(userRepository,times(1)).findById(any());
    }

    @Test
    void findByIdNotExisting() {
        //when
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> userService.getById(2L), "Expected to throw, but didn't.");

        verify(userRepository,times(1)).findById(any());
    }

    @Test
    void deleteById() {
        List<User> users = new ArrayList<>();
        users.add(User.builder().id(1L).build());
        users.add(User.builder().id(2L).build());
        users.add(User.builder().id(3L).build());

        //when
        when(userRepository.findAll()).thenReturn(users);

        //when
        userService.deleteById(1L);

        verify(userRepository,times(1)).deleteById(any());
    }
}