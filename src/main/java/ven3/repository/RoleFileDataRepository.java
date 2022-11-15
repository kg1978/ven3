package ven3.repository;

import java.util.Optional;

import ven3.models.User;

public class RoleFileDataRepository {

   Optional<User> findByUsername(String username) {
      return Optional.empty();
   }

   Optional<User> findByUsernameAndPw(String username, String pw) {
      return Optional.empty();
   }
}
