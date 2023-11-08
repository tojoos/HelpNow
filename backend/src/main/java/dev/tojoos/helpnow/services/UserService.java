package dev.tojoos.helpnow.services;

import dev.tojoos.helpnow.model.Role;
import dev.tojoos.helpnow.model.User;

public interface UserService extends CrudService<User> {
  Role addRole(Role role);

  void addRoleToUser(String username, String roleName);

  User getByUsername(String username);
}
