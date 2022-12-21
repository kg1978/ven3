package legacy.framework.utils;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import legacy.Application;
import legacy.framework.models.Item;
import legacy.framework.models.MenuItem;
import legacy.framework.models.MockUser;
import legacy.framework.models.Role;
import legacy.ven3.models.KeSzervMin;
import legacy.ven3.models.OI;
import legacy.ven3.models.SzervRec;
import legacy.ven3.models.db.jo.JoJogcsop;
import legacy.ven3.models.db.ke.KeSzerv;

@Component
public class Util {
   private static Logger LOG = LoggerFactory.getLogger(Util.class);

   @Autowired
   ResourceLoader resourceLoader;

   @Value("${ven3.timezone}")
   private String timezone;

   public <T> T json2Java(String fileName, Class<T> classType) {
      T t = null;
      try {
         File file = resourceLoader.getResource("classpath:" + fileName).getFile();
         ObjectMapper mapper = new ObjectMapper();
         mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
         t = mapper.readValue(file, classType);
      } catch (Exception e) {
         LOG.error(e.getMessage(), e);
      }

      return t;
   }

   public File getFile(String fileName) throws IOException {
      return resourceLoader.getResource("classpath:" + fileName).getFile();
   }

   private Date getNow() {
      LocalDate l = LocalDate.now(ZoneId.of(timezone));
      return Date.from(l.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
   }

   public boolean checkDateFromTo(Date validFrom, Date validto) {
      Date now = getNow();

      Calendar calendar = Calendar.getInstance();
      calendar.setTime(validFrom);
      calendar.set(Calendar.HOUR_OF_DAY, 0);
      Date vFrom = calendar.getTime();

      calendar.setTime(validto);
      calendar.set(Calendar.HOUR_OF_DAY, 23);
      calendar.set(Calendar.MINUTE, 59);
      calendar.set(Calendar.SECOND, 59);
      Date tFrom = calendar.getTime();

      if (vFrom.before(now) && tFrom.after(now)) {
         return true;
      }

      return false;
   }

   public int[] getJogok(List<JoJogcsop> list) {
      int[] ia = new int[list.size()];
      for (int i = 0; i < list.size(); i++) {
         ia[i] = list.get(i).jogkod;
      }
      return ia;
   }

   public String nvl(String value) {
      return value == null ? "" : value;
   }

   public List<OI> getOI(List<KeSzerv> szervlist) {
      List<OI> oIList = new ArrayList<OI>();

      for (KeSzerv keSzerv : szervlist) {
         oIList.add(new OI(keSzerv.szkod, keSzerv.nev,
               new SzervRec(keSzerv.szkod, keSzerv.nev, keSzerv.mbkod, 3, keSzerv.iszkod, keSzerv.nev)));
      }
      return oIList;
   }

   public List<OI> getOImin(List<KeSzervMin> szervlist) {
      List<OI> oIList = new ArrayList<OI>();

      for (KeSzervMin keSzerv : szervlist) {
         oIList.add(new OI(keSzerv.szkod, keSzerv.nev,
               new SzervRec(keSzerv.szkod, keSzerv.nev, keSzerv.mbkod, 3, keSzerv.szkod, keSzerv.nev)));
      }
      return oIList;
   }

   public List<MenuItem> getMenuItemsByJogkod(List<MenuItem> listMenuItem, int[] jogok) {
      List<MenuItem> list = new ArrayList<>();
      if (listMenuItem != null && jogok != null) {
         for (MenuItem m : listMenuItem) {
            if (m.getJog().equals(Role.JOGKOD_PUBLIKUS)
                  || IntStream.of(jogok).anyMatch(x -> x == Integer.parseInt(m.getJog()))) {
               list.add(m);
            }
         }
      }
      return list;
   }

   public List<MenuItem> getOptimalMenuItems(List<MenuItem> list) {
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
            if (m2 != null && m2.getPid().equals(m1.getId())) {
               if (m2.getJog().equals(Role.JOGKOD_PUBLIKUS)) {
                  if (m3 != null && m3.getPid().equals(m2.getId())) {
                     if (m3.getJog().equals(Role.JOGKOD_PUBLIKUS)) {
                        if (m4 != null && m4.getPid().equals(m3.getId())) {
                           listOptimal.add(m1);
                           // LOG.info("m4: " + m1.toString());
                        }
                     } else {
                        listOptimal.add(m1);
                        // LOG.info("m3: " + m1.toString());
                     }
                  }
               } else {
                  listOptimal.add(m1);
                  // LOG.info("m2: " + m1.toString());
               }
            }
         } else {
            listOptimal.add(m1);
            // LOG.info("m1: " + m1.toString());
         }
      }

      return listOptimal;
   }

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
            List<MenuItem> listOptimal = getOptimalMenuItems(list);
            return getMenu(listOptimal);
         }
      }

      return null;
   }
}
