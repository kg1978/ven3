package ven3.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ven3.framework.security.ExternalServiceAuthentication;

public class AuthService implements ExternalServiceAuthentication {
   private static Logger LOG = LoggerFactory.getLogger(AuthService.class);

   @Override
   public boolean authentication(String username, String password) {
      LOG.info("authentication");

      if (username.equals("alma")) {
         return true;
      }
      return false;
   }
}
