package legacy.framework.security;

import legacy.framework.security.exceptions.UnPwException;
import legacy.framework.security.exceptions.UserExpiredException;

public interface ExternalServiceAuthentication {
   /**
    * @return String sid
    */
   public String authentication(String username, String password) throws UnPwException, UserExpiredException;
}
