package ven3.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ven3.models.MenuItem;
import ven3.models.Role;
import ven3.models.User;

public class ControllerUtil {
   private static Logger LOG = LoggerFactory.getLogger(ControllerUtil.class);

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

   public static List<MenuItem> getOptimalMenuItems(List<MenuItem> list) {
      List<MenuItem> listOptimal = new ArrayList<>();
      for (int i = 0; i < list.size(); i++) {
         MenuItem m1 = list.get(i);
         MenuItem m2 = null;
         MenuItem m3 = null;
         MenuItem m4 = null;

         if (i < list.size() - 2) {
            m2 = list.get(i + 1);
         }

         if (i < list.size() - 3) {
            m3 = list.get(i + 2);
         }

         if (i < list.size() - 4) {
            m4 = list.get(i + 3);
         }

         if (m1.getJog().equals(Role.JOGKOD_PUBLIKUS)) {
            if (m2 != null) {
               if (m2.getJog().equals(Role.JOGKOD_PUBLIKUS)) {
                  if (m3 != null) {
                     if (m3.getJog().equals(Role.JOGKOD_PUBLIKUS)) {
                        if (m4 != null) {
                           if (!m3.getJog().equals(Role.JOGKOD_PUBLIKUS) && m4.getPid().equals(m3.getId())) {
                              listOptimal.add(m1);
                              LOG.info("m4: " + m1.toString());
                           }
                        }
                     } else {
                        if (m3.getPid().equals(m2.getId())) {
                           listOptimal.add(m1);
                           LOG.info("m3: " + m1.toString());
                        }
                     }
                  }
               } else {
                  if (m2.getPid().equals(m1.getId())) {
                     listOptimal.add(m1);
                     LOG.info("m2: " + m1.toString());
                  }
               }
            }
         } else {
            listOptimal.add(m1);
            LOG.info("m1: " + m1.toString());
         }
      }

      return listOptimal;
   }
}
