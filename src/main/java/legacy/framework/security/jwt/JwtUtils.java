package legacy.framework.security.jwt;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import legacy.framework.security.services.UserDetailsImpl;

@Component
public class JwtUtils {
   private static final Logger LOG = LoggerFactory.getLogger(JwtUtils.class);

   @Value("${ven3.app.jwtSecret}")
   private String jwtSecret;

   @Value("${ven3.app.jwtExpirationMs}")
   private int jwtExpirationMs;

   public String parseJwt(HttpServletRequest request) {
      String headerAuth = request.getHeader("Authorization");

      if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
         return headerAuth.substring(7, headerAuth.length());
      }

      return null;
   }

   public String generateJwtToken(Authentication authentication) {

      UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

      return Jwts.builder().setSubject((userPrincipal.getUsername())).setId(userPrincipal.getSid())
            .setIssuedAt(new Date()).setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
            .signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
   }

   public String getUserNameFromJwtToken(String token) {
      return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
   }

   public String getSidFromJwtToken(String token) {
      return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getId();
   }

   public boolean validateJwtToken(String authToken) {
      if (authToken == null) {
         return false;
      }

      try {
         Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
         return true;
      } catch (SignatureException e) {
         LOG.error("Invalid JWT signature: {}", e.getMessage());
      } catch (MalformedJwtException e) {
         LOG.error("Invalid JWT token: {}", e.getMessage());
      } catch (ExpiredJwtException e) {
         LOG.error("JWT token is expired: {}", e.getMessage());
      } catch (UnsupportedJwtException e) {
         LOG.error("JWT token is unsupported: {}", e.getMessage());
      } catch (IllegalArgumentException e) {
         LOG.error("JWT claims string is empty: {}", e.getMessage());
      }

      return false;
   }
}
