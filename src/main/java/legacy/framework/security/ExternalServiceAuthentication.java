package legacy.framework.security;

import legacy.framework.security.exceptions.UnPwException;
import legacy.framework.security.exceptions.UserExpiredException;

public interface ExternalServiceAuthentication {
   public boolean authentication(String username, String password) throws UnPwException, UserExpiredException;
}
