package ven3.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class User {
   private String username;
   private String password;

   @Override
   public String toString() {
      return "User [username=" + username + ", password=" + password + "]";
   }
}
