package legacy.ven3.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import legacy.framework.crypt.PasswordChecker;
import legacy.framework.security.ExternalServiceAuthentication;
import legacy.framework.security.exceptions.UnPwException;
import legacy.ven3.models.db.auth.UnPwAuth;
import legacy.ven3.servises.db.auth.UnPwAuthService;

@Service
@Component
public class LoginService implements ExternalServiceAuthentication {

   @Autowired
   UnPwAuthService service;

   @Override
   public boolean authentication(String username, String password) throws UnPwException {
      if (username == null || password == null || username.isBlank() || password.isBlank()) {
         return false;
      }

      UnPwAuth d = service.getById(username);
      if (d != null) {
         if ((PasswordChecker.matchesIBM13(d.password, password))
               || (PasswordChecker.matchesApache2(d.password, password))) {
            return true;
         }
      }

      throw new UnPwException();
   }
}
