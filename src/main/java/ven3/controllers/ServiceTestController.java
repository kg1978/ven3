package ven3.controllers;

import java.util.Collections;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ven3.Ven3Application;
import ven3.models.Item;
import ven3.models.MockUser;
import ven3.models.MenuItem;
import ven3.models.Role;

@RestController
@RequestMapping("/api/service-test")
public class ServiceTestController {

   @RequestMapping(value = "/users", method = RequestMethod.GET, produces = "application/json")
   public List<MockUser> listUsers() throws Exception {
      return Ven3Application.listMockUsers;
   }

   @RequestMapping(value = "/roles", method = RequestMethod.GET, produces = "application/json")
   public List<Role> listRoles() throws Exception {
      return Ven3Application.listMockRoles;
   }

   @RequestMapping(value = "/menuitems", method = RequestMethod.GET, produces = "application/json")
   public List<MenuItem> listMenus() throws Exception {
      return Ven3Application.listMenuItem;
   }

   @RequestMapping(value = "/role/{username}", method = RequestMethod.GET, produces = "application/json")
   public Role roleByUsername(@PathVariable("username") String username) throws Exception {
      try {
         MockUser user = ControllerUtil.findUserByUsername(Ven3Application.listMockUsers, username);
         if (user != null) {
            return ControllerUtil.findRoleByUsername(Ven3Application.listMockRoles, username);
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
         MockUser user = ControllerUtil.findUserByUsername(Ven3Application.listMockUsers, username);
         if (user != null) {
            Role role = ControllerUtil.findRoleByUsername(Ven3Application.listMockRoles, username);
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

   @RequestMapping(value = "/optmenusitems/{username}", method = RequestMethod.GET, produces = "application/json")
   public List<MenuItem> getSortMenuByUsername(@PathVariable("username") String username) throws Exception {

      try {
         MockUser user = ControllerUtil.findUserByUsername(Ven3Application.listMockUsers, username);
         if (user != null) {
            Role role = ControllerUtil.findRoleByUsername(Ven3Application.listMockRoles, username);
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

   @RequestMapping(value = "/menu/{username}", method = RequestMethod.GET, produces = "application/json")
   public List<Item> getMenuByUsername(@PathVariable("username") String username) throws Exception {

      try {
         MockUser user = ControllerUtil.findUserByUsername(Ven3Application.listMockUsers, username);
         if (user != null) {
            Role role = ControllerUtil.findRoleByUsername(Ven3Application.listMockRoles, username);
            if (role != null) {
               List<MenuItem> list = ControllerUtil.getMenuItemsByRole(Ven3Application.listMenuItem, role);
               Collections.sort(list, (o1, o2) -> o1.getId().compareTo(o2.getId()));
               List<MenuItem> listOptimal = ControllerUtil.getOptimalMenuItems(list);
               return ControllerUtil.getMenu(listOptimal);
            }
         }

         return null;
      } catch (Exception e) {
         e.printStackTrace();
         throw e;
      }
   }
}
