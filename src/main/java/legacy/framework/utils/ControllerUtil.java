package legacy.framework.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import legacy.Application;
import legacy.framework.models.Item;
import legacy.framework.models.MenuItem;
import legacy.framework.models.MockUser;
import legacy.framework.models.Role;

public class ControllerUtil {

   @Autowired
   Util util;

   public MockUser findUserByUsername(List<MockUser> listUsers, String username) {
      if (username != null && listUsers != null) {
         String un = username.trim();
         for (MockUser u : listUsers) {
            if (u.getUsername().equals(un))
               return u;
         }
      }
      return null;
   }

   public Role findRoleByUsername(List<Role> listRoles, String username) {
      if (username != null && listRoles != null) {
         String un = username.trim();
         for (Role r : listRoles) {
            if (r.getUsername().equals(un))
               return r;
         }
      }
      return null;
   }

   public List<MenuItem> getMenuItemsByRole(List<MenuItem> listMenuItem, Role role) {
      List<MenuItem> list = new ArrayList<>();
      if (listMenuItem != null && role != null) {
         for (MenuItem m : listMenuItem) {
            if (m.getJog().equals(Role.JOGKOD_PUBLIKUS) || role.getJogkod().contains(m.getJog())) {
               list.add(m);
            }
         }
      }
      return list;
   }

   public List<Item> getMenu(List<MenuItem> list) {
      Item item = new Item();
      Map<String, Item> map = new HashMap<>();

      map.put("0", item);
      for (int i = 0; i < list.size(); i++) {
         MenuItem m = list.get(i);
         Item it = m.getItem();
         map.put(m.getId(), it);
         map.get(m.getPid()).addItem(it);
      }
      return item.getSubmenu();
   }

   public List<Item> getMenuByMockUsername(String username) {
      MockUser user = findUserByUsername(Application.listMockUsers, username);
      if (user != null) {
         Role role = findRoleByUsername(Application.listMockRoles, username);
         if (role != null) {
            List<MenuItem> list = getMenuItemsByRole(Application.listMenuItem, role);
            Collections.sort(list, (o1, o2) -> o1.getId().compareTo(o2.getId()));
            List<MenuItem> listOptimal = util.getOptimalMenuItems(list);
            return getMenu(listOptimal);
         }
      }

      return null;
   }
}
