package ven3.repository;

import java.util.Optional;

import ven3.models.MockUser;

public class UserFileDataRepository {

   Optional<MockUser> findByUsername(String username) {
      return Optional.empty();
   }

   Optional<MockUser> findByUsernameAndPw(String username, String pw) {
      return Optional.empty();
   }
}
