package ven3.framework.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ven3.Ven3Application;
import ven3.framework.models.Item;
import ven3.framework.security.jwt.JwtUtils;

@RestController
@RequestMapping("/api/service-menu")
public class MenuController {
   @Autowired
   private JwtUtils jwtUtils;

   private static final Logger LOG = LoggerFactory.getLogger(MenuController.class);

   @RequestMapping(value = "/menu", method = RequestMethod.GET, produces = "application/json")
   public List<Item> listMenus(HttpServletRequest request) {

      try {
         String jwt = jwtUtils.parseJwt(request);
         if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
            String username = jwtUtils.getUserNameFromJwtToken(jwt);

            if (username.startsWith(Ven3Application.MOCK_USER)) {
               return ControllerUtil.getMenuByMockUsername(username);
            } else {

            }
         }
      } catch (Exception e) {
         LOG.error("Cannot set user authentication: {}", e);
      }
      return null;
   }
}
