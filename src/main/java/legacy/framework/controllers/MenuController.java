package legacy.framework.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import legacy.Application;
import legacy.framework.models.Item;
import legacy.framework.security.ExternalServiceMenu;
import legacy.framework.security.jwt.JwtUtils;
import legacy.framework.utils.ControllerUtil;

@RestController
@RequestMapping("/api/service-menu")
public class MenuController {
   @Autowired
   private JwtUtils jwtUtils;

   @Autowired
   private ControllerUtil controllerUtil;

   @Autowired
   private ExternalServiceMenu externalServiceMenu;

   @RequestMapping(value = "/menu", method = RequestMethod.GET, produces = "application/json")
   public List<Item> listMenus(HttpServletRequest request) throws Exception {
      try {
         String jwt = jwtUtils.parseJwt(request);
         if (jwtUtils.validateJwtToken(jwt)) {
            String username = jwtUtils.getUserNameFromJwtToken(jwt);

            if (username.startsWith(Application.MOCK_USER)) {
               return controllerUtil.getMenuByMockUsername(username);
            } else {
               return externalServiceMenu.get(jwtUtils.getUserNameFromJwtToken(jwt));
            }
         }

         return null;
      } catch (Exception e) {
         e.printStackTrace();
         throw new Exception(e);
      }
   }
}
