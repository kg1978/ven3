package legacy.framework.models;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Role {
   public static final String JOGKOD_PUBLIKUS = "-1";

   private String username;
   private List<String> jogkod;

   @Override
   public String toString() {
      return "Role [username=" + username + ", jogkod=" + jogkod + "]";
   }
}
