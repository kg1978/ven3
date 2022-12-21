package legacy.ven3.services;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import legacy.framework.crypt.PasswordChecker;
import legacy.framework.security.ExternalServiceAuthentication;
import legacy.framework.security.exceptions.UnPwException;
import legacy.framework.security.exceptions.UserExpiredException;
import legacy.framework.utils.Util;
import legacy.ven3.models.db.auth.UnPwAuth;
import legacy.ven3.models.db.jo.JoAufelh;
import legacy.ven3.servises.db.auth.UnPwAuthService;
import legacy.ven3.servises.db.jo.JoAufelhService;

@Service
@Component
public class LoginService implements ExternalServiceAuthentication {

   @Autowired
   UnPwAuthService unPwAuthService;

   @Autowired
   JoAufelhService joAufelhService;

   @Autowired
   Util util;

   @Override
   public boolean authentication(String username, String password)
         throws UnPwException, UserExpiredException, NoSuchElementException {
      if (username == null || password == null || username.isBlank() || password.isBlank()) {
         return false;
      }

      UnPwAuth d = unPwAuthService.getById(username);
      if (d != null) {
         if ((PasswordChecker.matchesIBM13(d.password, password))
               || (PasswordChecker.matchesApache2(d.password, password))) {

            JoAufelh joAufelh = joAufelhService.getByFelhazonOkmany(username);

            if (util.checkDateFromTo(joAufelh.ervtol, joAufelh.ervig)) {
               return true;
            }
            throw new UserExpiredException();
         }
      }
      throw new UnPwException();
   }
}
