package ven3.models;

import java.util.List;

public class Role {
   public static final String JOGKOD_PUBLIKUS = "-1";

   private String username;
   private List<String> jogkod;

   public String getUsername() {
      return username;
   }

   public void setUsername(String username) {
      this.username = username;
   }

   public List<String> getJogkod() {
      return jogkod;
   }

   public void setJogkod(List<String> jogkod) {
      this.jogkod = jogkod;
   }

   @Override
   public String toString() {
      return "Role [username=" + username + ", jogkod=" + jogkod + "]";
   }
}
