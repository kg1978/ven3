package ven3.request;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MockUserLoginRequest {
   @NotBlank
   private String username;

   @NotBlank
   private String password;
}
