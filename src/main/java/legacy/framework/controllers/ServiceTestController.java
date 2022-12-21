package legacy.framework.controllers;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import legacy.Application;
import legacy.framework.models.Item;
import legacy.framework.models.MenuItem;
import legacy.framework.models.MockUser;
import legacy.framework.models.Role;
import legacy.framework.utils.Util;

@RestController
@RequestMapping("/api/service-test")
public class ServiceTestController {

   @Autowired
   private Util util;

   @RequestMapping(value = "/mockusers", method = RequestMethod.GET, produces = "application/json")
   public List<MockUser> listUsers() {
      return Application.listMockUsers;
   }

   @RequestMapping(value = "/mockroles", method = RequestMethod.GET, produces = "application/json")
   public List<Role> listRoles() {
      return Application.listMockRoles;
   }

   @RequestMapping(value = "/mockmenuitems", method = RequestMethod.GET, produces = "application/json")
   public List<MenuItem> listMenus() {
      return Application.listMenuItem;
   }

   @RequestMapping(value = "/role/{username}", method = RequestMethod.GET, produces = "application/json")
   public Role roleByUsername(@PathVariable("username") String username) {
      try {
         if (username != null && username.startsWith(Application.MOCK_USER)) {
            MockUser user = util.findUserByUsername(Application.listMockUsers, username);
            if (user != null) {
               return util.findRoleByUsername(Application.listMockRoles, username);
            }
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
         if (username != null && username.startsWith(Application.MOCK_USER)) {
            MockUser user = util.findUserByUsername(Application.listMockUsers, username);
            if (user != null) {
               Role role = util.findRoleByUsername(Application.listMockRoles, username);
               if (role != null) {
                  return util.getMenuItemsByRole(Application.listMenuItem, role);
               }
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
         if (username != null && username.startsWith(Application.MOCK_USER)) {
            MockUser user = util.findUserByUsername(Application.listMockUsers, username);
            if (user != null) {
               Role role = util.findRoleByUsername(Application.listMockRoles, username);
               if (role != null) {
                  List<MenuItem> list = util.getMenuItemsByRole(Application.listMenuItem, role);
                  Collections.sort(list, (o1, o2) -> o1.getId().compareTo(o2.getId()));
                  return util.getOptimalMenuItems(list);
               }
            }
         }

         return null;
      } catch (Exception e) {
         e.printStackTrace();
         throw e;
      }
   }

   @RequestMapping(value = "/menu/{username}", method = RequestMethod.GET, produces = "application/json")
   public List<Item> getMenuByUsername(@PathVariable("username") String username) {
      return util.getMenuByMockUsername(username);
   }
}
