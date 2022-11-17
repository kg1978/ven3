package ven3.controllers;

import java.util.Collections;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ven3.Ven3Application;
import ven3.models.MenuItem;
import ven3.models.Role;
import ven3.models.User;

@RestController
@RequestMapping("/api/service-test")
public class ServiceTestController {

   @RequestMapping(value = "/users", method = RequestMethod.GET, produces = "application/json")
   public List<User> listUsers() throws Exception {
      return Ven3Application.listUsers;
   }

   @RequestMapping(value = "/roles", method = RequestMethod.GET, produces = "application/json")
   public List<Role> listRoles() throws Exception {
      return Ven3Application.listRoles;
   }

   @RequestMapping(value = "/menuitems", method = RequestMethod.GET, produces = "application/json")
   public List<MenuItem> listMenus() throws Exception {
      return Ven3Application.listMenuItem;
   }

   @RequestMapping(value = "/role/{username}", method = RequestMethod.GET, produces = "application/json")
   public Role roleByUsername(@PathVariable("username") String username) throws Exception {
      try {
         User user = ControllerUtil.findUserByUsername(Ven3Application.listUsers, username);
         if (user != null) {
            return ControllerUtil.findRoleByUsername(Ven3Application.listRoles, username);
         }

         return null;
      } catch (Exception e) {
         e.printStackTrace();
         throw e;
      }
   }

   @RequestMapping(value = "/menusitems/{username}", method = RequestMethod.GET, produces = "application/json")
   public List<MenuItem> listMenuItemsByUsername(@PathVariable("username") String username) throws Exception {
      try {
         User user = ControllerUtil.findUserByUsername(Ven3Application.listUsers, username);
         if (user != null) {
            Role role = ControllerUtil.findRoleByUsername(Ven3Application.listRoles, username);
            if (role != null) {
               return ControllerUtil.getMenuItemsByRole(Ven3Application.listMenuItem, role);
            }
         }

         return null;
      } catch (Exception e) {
         e.printStackTrace();
         throw e;
      }
   }

   @RequestMapping(value = "/menu/{username}", method = RequestMethod.GET, produces = "application/json")
   public List<MenuItem> getMenuByUsername(@PathVariable("username") String username) throws Exception {

      try {
         User user = ControllerUtil.findUserByUsername(Ven3Application.listUsers, username);
         if (user != null) {
            Role role = ControllerUtil.findRoleByUsername(Ven3Application.listRoles, username);
            if (role != null) {
               List<MenuItem> list = ControllerUtil.getMenuItemsByRole(Ven3Application.listMenuItem, role);
               Collections.sort(list, (o1, o2) -> o1.getId().compareTo(o2.getId()));
               return ControllerUtil.getOptimalMenuItems(list);
            }
         }

         return null;
      } catch (Exception e) {
         e.printStackTrace();
         throw e;
      }
   }
}
