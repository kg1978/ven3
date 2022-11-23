package ven3.controllers.security;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;

import ven3.Ven3Application;
import ven3.models.MockUser;

public class Ven3AuthenticationProvider implements AuthenticationProvider {

   @Resource
   UserDetailsService userDetailsService;

   @Override
   public Authentication authenticate(Authentication authentication) throws AuthenticationException {
      String name = authentication.getName();
      String password = authentication.getCredentials().toString();

      if (name != null && password != null && !name.isEmpty() && !password.isEmpty()) {
         name = name.trim();
         password = password.trim();

         if (name.startsWith(Ven3Application.MOCK_USER)) {
            userDetailsService.loadUserByUsername(name);
         } else {
            for (MockUser u : Ven3Application.listMockUsers) {
               if (u.getUsername().equals(name) && u.getPassword().equals(password)) {
                  return new UsernamePasswordAuthenticationToken(name, password, new ArrayList<>());
               }
            }
         }
      }

      throw new BadCredentialsException("invalid login details");
   }

   @Override
   public boolean supports(Class<?> authentication) {
      return authentication.equals(UsernamePasswordAuthenticationToken.class);
   }

}
