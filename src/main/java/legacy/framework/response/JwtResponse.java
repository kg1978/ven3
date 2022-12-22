package legacy.framework.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JwtResponse {
   private String token;

   public JwtResponse(String accessToken) {
      this.token = accessToken;
   }

   @Override
   public String toString() {
      return "JwtResponse [token=" + token + "]";
   }
}
