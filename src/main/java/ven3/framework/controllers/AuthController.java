package ven3.framework.controllers;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ven3.Ven3Application;
import ven3.framework.request.UserLoginRequest;
import ven3.framework.response.JwtResponse;
import ven3.framework.security.CustomAuthenticationProvider;
import ven3.framework.security.jwt.JwtUtils;
import ven3.framework.security.services.UserDetailsImpl;

@RestController
@RequestMapping("/api/service-auth")
public class AuthController {
   private static Logger LOG = LoggerFactory.getLogger(AuthController.class);

   @Autowired
   PasswordEncoder encoder;

   @Autowired
   AuthenticationManager authenticationManager;

   @Autowired
   CustomAuthenticationProvider customAuthenticationProvider;

   @Autowired
   JwtUtils jwtUtils;

   @PostMapping("/signin")
   public ResponseEntity<?> authenticateUser(@Valid @RequestBody UserLoginRequest loginRequest) {
      LOG.info(loginRequest.getUsername() + " " + loginRequest.getPassword());
      LOG.info(encoder.encode(loginRequest.getPassword()));

      Authentication authentication = null;
      UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
            loginRequest.getPassword());

      String username = loginRequest.getUsername();
      if (username != null && username.startsWith(Ven3Application.MOCK_USER)) {
         authentication = authenticationManager.authenticate(upToken);
      } else {
         authentication = customAuthenticationProvider.authenticate(upToken);
      }

      LOG.info("authentication ok: " + authentication.toString());

      SecurityContextHolder.getContext().setAuthentication(authentication);
      String jwt = jwtUtils.generateJwtToken(authentication);

      UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

      return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername()));
   }
}