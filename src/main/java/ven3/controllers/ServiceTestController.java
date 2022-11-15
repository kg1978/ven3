package ven3.controllers;

import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import ven3.models.MenuItem;
import ven3.models.Role;
import ven3.models.User;

@RestController
@RequestMapping("/api/service-test")
public class ServiceTestController {

   @RequestMapping(value = "/users", method = RequestMethod.GET, produces = "application/json")
   public List<User> listUsers() throws Exception {
      ObjectMapper objectMapper = new ObjectMapper();

      try {
         return objectMapper.readValue(new ClassPathResource("user.json").getFile(), new TypeReference<List<User>>() {
         });
      } catch (Exception e) {
         e.printStackTrace();
         throw e;
      }
   }

   @RequestMapping(value = "/roles", method = RequestMethod.GET, produces = "application/json")
   public List<Role> listRoles() throws Exception {
      ObjectMapper objectMapper = new ObjectMapper();

      try {
         return objectMapper.readValue(new ClassPathResource("role.json").getFile(), new TypeReference<List<Role>>() {
         });
      } catch (Exception e) {
         e.printStackTrace();
         throw e;
      }
   }

   @RequestMapping(value = "/menus", method = RequestMethod.GET, produces = "application/json")
   public List<MenuItem> listMenus() throws Exception {
      ObjectMapper objectMapper = new ObjectMapper();

      try {

         return objectMapper.readValue(new ClassPathResource("menu.json").getFile(),
               new TypeReference<List<MenuItem>>() {
               });
      } catch (Exception e) {
         e.printStackTrace();
         throw e;
      }
   }

   @RequestMapping(value = "/role/{username}", method = RequestMethod.GET, produces = "application/json")
   public Role roleByUsername(@PathVariable("username") String username) throws Exception {

      try {
         ObjectMapper objectMapper = new ObjectMapper();
         List<User> listUsers = objectMapper.readValue(new ClassPathResource("user.json").getFile(),
               new TypeReference<List<User>>() {
               });
         User user = ControllerUtil.findUserByUsername(listUsers, username);

         if (user != null) {
            List<Role> listRoles = objectMapper.readValue(new ClassPathResource("role.json").getFile(),
                  new TypeReference<List<Role>>() {
                  });
            return ControllerUtil.findRoleByUsername(listRoles, username);
         }

         return null;
      } catch (Exception e) {
         e.printStackTrace();
         throw e;
      }
   }

   @RequestMapping(value = "/menus/{username}", method = RequestMethod.GET, produces = "application/json")
   public List<MenuItem> listMenusByUsername(@PathVariable("username") String username) throws Exception {

      try {
         ObjectMapper objectMapper = new ObjectMapper();
         List<User> listUsers = objectMapper.readValue(new ClassPathResource("user.json").getFile(),
               new TypeReference<List<User>>() {
               });
         User user = ControllerUtil.findUserByUsername(listUsers, username);

         if (user != null) {
            List<Role> listRoles = objectMapper.readValue(new ClassPathResource("role.json").getFile(),
                  new TypeReference<List<Role>>() {
                  });
            Role role = ControllerUtil.findRoleByUsername(listRoles, username);

            if (role != null) {
               List<MenuItem> listMenuItem = objectMapper.readValue(new ClassPathResource("menu.json").getFile(),
                     new TypeReference<List<MenuItem>>() {
                     });
               return ControllerUtil.getMenuItemsByRole(listMenuItem, role);
            }
         }

         return null;
      } catch (Exception e) {
         e.printStackTrace();
         throw e;
      }
   }
}