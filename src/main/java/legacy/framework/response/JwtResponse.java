package legacy.framework.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JwtResponse {
   private String token;
   private String type = "Bearer";
   private String username;
   private String sid;

   public JwtResponse(String accessToken, String username) {
      this.token = accessToken;
      this.username = username;
      this.sid = "";
   }

   public JwtResponse(String accessToken, String username, String sid) {
      this.token = accessToken;
      this.username = username;
      this.sid = sid;
   }

   @Override
   public String toString() {
      return "JwtResponse [token=" + token + ", type=" + type + ", username=" + username + ", sid=" + sid + "]";
   }
}
