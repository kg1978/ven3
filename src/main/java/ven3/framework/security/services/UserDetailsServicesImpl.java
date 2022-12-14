package ven3.framework.security.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ven3.Ven3Application;
import ven3.framework.models.MockUser;

@Service
public class UserDetailsServicesImpl implements UserDetailsService {

   private static final Logger LOG = LoggerFactory.getLogger(UserDetailsServicesImpl.class);

   @Override
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      LOG.info(username);

      if (username.startsWith(Ven3Application.MOCK_USER)) {
         LOG.info("mockuser: " + username);

         for (MockUser u : Ven3Application.listMockUsers) {
            LOG.info("   u: " + u.getUsername());

            if (u.getUsername().equals(username)) {
               LOG.info("   u ok: " + u.getUsername());

               return new UserDetailsImpl(u.getUsername(), u.getPassword());
            }
         }
      } else {
         throw new UsernameNotFoundException("only mock username enabled");
      }
      throw new UsernameNotFoundException("username not found");
   }
}
