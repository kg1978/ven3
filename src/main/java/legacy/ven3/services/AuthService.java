package legacy.ven3.services;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import legacy.framework.security.ExternalServiceAuthentication;

@Service
@Component
public class AuthService implements ExternalServiceAuthentication {

   @Override
   public boolean authentication(String username, String password) {
      if (username.equals("alma")) {
         return true;
      }
      return false;
   }
}
