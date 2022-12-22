package legacy.framework.security.services;

import java.util.Collection;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

public class UserDetailsImpl implements UserDetails {
   private static final long serialVersionUID = 1L;

   private String username;

   @Setter
   @Getter
   private String sid;

   @JsonIgnore
   private String password;

   public UserDetailsImpl(String username, String password) {
      this.username = username;
      this.password = password;
      this.sid = "";
   }

   public UserDetailsImpl(String username, String password, String sid) {
      this.username = username;
      this.password = password;
      this.sid = sid;
   }

   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
      return null;
   }

   @Override
   public String getPassword() {
      return password;
   }

   @Override
   public String getUsername() {
      return username;
   }

   @Override
   public boolean isAccountNonExpired() {
      return true;
   }

   @Override
   public boolean isAccountNonLocked() {
      return true;
   }

   @Override
   public boolean isCredentialsNonExpired() {
      return true;
   }

   @Override
   public boolean isEnabled() {
      return true;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o)
         return true;
      if (o == null || getClass() != o.getClass())
         return false;
      UserDetailsImpl user = (UserDetailsImpl) o;
      return Objects.equals(username, user.username);
   }

   @Override
   public String toString() {
      return "UserDetailsImpl [username=" + username + ", sid=" + sid + ", password=" + password + "]";
   }
}
