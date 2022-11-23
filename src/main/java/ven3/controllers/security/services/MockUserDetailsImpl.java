package ven3.controllers.security.services;

import java.util.Collection;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ven3.models.MockUser;

public class MockUserDetailsImpl implements UserDetails {
   private static final long serialVersionUID = 1L;

   private String username;

   @JsonIgnore
   private String password;

   public MockUserDetailsImpl(String username, String password) {
      this.username = username;
      this.password = password;
   }

   public static MockUserDetailsImpl build(MockUser user) {
      return new MockUserDetailsImpl(user.getUsername(), user.getPassword());
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
      MockUserDetailsImpl user = (MockUserDetailsImpl) o;
      return Objects.equals(username, user.username);
   }
}
