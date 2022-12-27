package dev.tojoos.helpnow.services;

import dev.tojoos.helpnow.model.Role;
import dev.tojoos.helpnow.model.User;
import dev.tojoos.helpnow.repositories.RoleRepository;
import dev.tojoos.helpnow.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            log.error("User with username={} not found.", username);
            throw new UsernameNotFoundException("User not found.");
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
        log.info("User with username={} found.", username);

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    @Override
    public User add(User user) {
        log.info("Adding new user {}", user.getName());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User getById(Long id) {
        log.info("Getting user with id={}", id);
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User with id=" + id + " not found."));
    }

    @Override
    public List<User> getAll() {
        log.info("Getting all users");
        return userRepository.findAll();
    }

    @Override
    public User update(User user) {
        log.info("Updating user {}", user.getName());
        userRepository.findById(user.getId()).orElseThrow(() -> new EntityNotFoundException("User with id=" + user.getId() + " not found."));
        return userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        log.info("Deleting user with id={}", id);
        userRepository.deleteById(id);
    }

    @Override
    public Role addRole(Role role) {
        log.info("Adding new role {}", role.getName());
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("Adding role {} to user {}", roleName, username);
        User foundUser = this.getByUsername(username);
        Role foundRole = roleRepository.findByName(roleName);
        foundUser.getRoles().add(foundRole);
    }

    @Override
    public User getByUsername(String username) {
        log.info("Getting user {} from DB", username);
        return userRepository.findByUsername(username);
    }
}
