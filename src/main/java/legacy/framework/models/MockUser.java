package legacy.framework.models;

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

   @NotBlank
   @Size(max = 50)
   private String sid;

   @Override
   public String toString() {
      return "MockUser [username=" + username + ", password=" + password + ", sid=" + sid + "]";
   }
}
