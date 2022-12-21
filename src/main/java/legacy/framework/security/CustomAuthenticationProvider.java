package legacy.framework.security;

import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

import legacy.framework.security.exceptions.UnPwException;
import legacy.framework.security.exceptions.UserExpiredException;
import legacy.framework.security.services.UserDetailsImpl;

public class CustomAuthenticationProvider implements AuthenticationProvider {

   private static Logger LOG = LoggerFactory.getLogger(CustomAuthenticationProvider.class);

   @Autowired
   private ExternalServiceAuthentication externalServiceAuthentication;

   @Override
   public Authentication authenticate(Authentication authentication) throws AuthenticationException {
      String username = authentication.getName();
      String password = authentication.getCredentials().toString();

      LOG.info("{" + username + ":" + password + "}");

      if (username != null && password != null && !username.isEmpty() && !password.isEmpty()) {

         try {
            if (externalServiceAuthentication.authentication(username, password)) {
               UserDetails user = new UserDetailsImpl(username, password);
               return createSuccessfulAuthentication(authentication, user);
            }
         } catch (UnPwException e) {
            throw new BadCredentialsException("UnPwException UnPwAuth exception");
         } catch (NoSuchElementException e) {
            throw new BadCredentialsException("NoSuchElementException JoAufelh exception");
         } catch (UserExpiredException e) {
            throw new BadCredentialsException("UserExpiredException JoAufelh exception");
         }
      }

      throw new BadCredentialsException("Invalid login details");
   }

   private Authentication createSuccessfulAuthentication(final Authentication authentication, final UserDetails user) {
      UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user,
            authentication.getCredentials(), user.getAuthorities());
      token.setDetails(authentication.getDetails());
      return token;
   }

   @Override
   public boolean supports(Class<?> authentication) {
      return authentication.equals(UsernamePasswordAuthenticationToken.class);
   }
}
