package ven3.controllers.security.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import ven3.Ven3Application;
import ven3.models.MockUser;

public class UserDetailsServicesImpl implements UserDetailsService {

   @Override
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      if (username.startsWith(Ven3Application.MOCK_USER)) {
         for (MockUser u : Ven3Application.listMockUsers) {
            if (u.getUsername().equals(username)) {
               return new UserDetailsImpl(u.getUsername(), u.getPassword());
            }
         }
      } else {

      }
      throw new UsernameNotFoundException("username not found");
   }
}
