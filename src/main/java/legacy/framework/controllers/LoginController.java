package legacy.framework.controllers;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import legacy.framework.request.UserLoginRequest;
import legacy.framework.response.JwtResponse;
import legacy.framework.security.ExternalAuthenticationProvider;
import legacy.framework.security.jwt.JwtUtils;

@RestController
@RequestMapping("/api/service-auth")
public class LoginController {
   private static Logger LOG = LoggerFactory.getLogger(LoginController.class);

   @Autowired
   PasswordEncoder encoder;

   @Autowired
   AuthenticationManager authenticationManager;

   @Autowired
   ExternalAuthenticationProvider customAuthenticationProvider;

   @Autowired
   JwtUtils jwtUtils;

   @CrossOrigin(origins = { "${ven3.crossOrigin}" })
   @PostMapping("/signin")
   public ResponseEntity<?> authenticateUser(@Valid @RequestBody UserLoginRequest loginRequest) {
      LOG.info(loginRequest.getUsername() + " " + loginRequest.getPassword());

      UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
            loginRequest.getPassword());

      Authentication authentication = customAuthenticationProvider.authenticate(upToken);

      LOG.info("authentication ok: " + authentication.toString());

      SecurityContextHolder.getContext().setAuthentication(authentication);
      String jwt = jwtUtils.generateJwtToken(authentication);

      return ResponseEntity.ok(new JwtResponse(jwt));
   }
}
