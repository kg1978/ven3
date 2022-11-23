package ven3.controllers.security.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import ven3.Ven3Application;
import ven3.models.MockUser;

public class MockUserDetailsServicesImpl implements UserDetailsService {

   @Override
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      for (MockUser u : Ven3Application.listMockUsers) {
         if (u.getUsername().equals(username)) {
            return new MockUserDetailsImpl(u.getUsername(), u.getPassword());
         }
      }
      throw new UsernameNotFoundException("username not found");
   }
}
