package ven3.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ven3.controllers.security.jwt.JwtUtils;
import ven3.controllers.security.services.UserDetailsImpl;
import ven3.request.MockUserLoginRequest;
import ven3.response.JwtResponse;

@RestController
@RequestMapping("/api/service")
public class ServiceController {
   @Autowired
   AuthenticationManager authenticationManager;

   @Autowired
   JwtUtils jwtUtils;

   @PostMapping("/signin")
   public ResponseEntity<?> authenticateUser(@Valid @RequestBody MockUserLoginRequest loginRequest) {
      Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

      SecurityContextHolder.getContext().setAuthentication(authentication);
      String jwt = jwtUtils.generateJwtToken(authentication);

      UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

      return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername()));
   }
}
