package ven3.framework.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MockUser {
   @NotBlank
   @Size(max = 20)
   private String username;

   @NotBlank
   @Size(max = 20)
   private String password;

   @Override
   public String toString() {
      return "User [username=" + username + ", password=" + password + "]";
   }
}
