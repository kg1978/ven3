package legacy.ven3.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import legacy.framework.security.jwt.JwtUtils;
import legacy.ven3.models.OkmanyFelhasznalo;
import legacy.ven3.services.JogService;

@RestController
@RequestMapping("/api/service-ven/jog")
public class JogController {

   @Autowired
   private JwtUtils jwtUtils;

   @Autowired
   private JogService jogService;

   @RequestMapping(value = "/init", method = RequestMethod.GET, produces = "application/json")
   public OkmanyFelhasznalo getUser(HttpServletRequest request) throws Exception {
      try {
         String jwt = jwtUtils.parseJwt(request);
         if (jwtUtils.validateJwtToken(jwt)) {
            return jogService.init(jwtUtils.getSidFromJwtToken(jwt), jwtUtils.getUserNameFromJwtToken(jwt));
         }

         return null;
      } catch (Exception e) {
         e.printStackTrace();
         throw new Exception(e);
      }
   }
}
