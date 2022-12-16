package legacy.framework.request;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserLoginRequest {
   @NotBlank
   private String username;

   @NotBlank
   private String password;
}
