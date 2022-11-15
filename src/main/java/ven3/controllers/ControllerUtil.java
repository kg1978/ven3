package ven3.controllers;

import java.util.ArrayList;
import java.util.List;

import ven3.models.MenuItem;
import ven3.models.Role;
import ven3.models.User;

public class ControllerUtil {

   public static User findUserByUsername(List<User> listUsers, String username) {
      if (username != null && listUsers != null) {
         String un = username.trim();
         for (User u : listUsers) {
            if (u.getUsername().equals(un))
               return u;
         }
      }
      return null;
   }

   public static Role findRoleByUsername(List<Role> listRoles, String username) {
      if (username != null && listRoles != null) {
         String un = username.trim();
         for (Role r : listRoles) {
            if (r.getUsername().equals(un))
               return r;
         }
      }
      return null;
   }

   public static List<MenuItem> getMenuItemsByRole(List<MenuItem> listMenuItem, Role role) {
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
}
