package ven3.controllers;

import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import ven3.models.MenuItem;

@RestController
@RequestMapping("/api/service-menu")
public class MenuController {
   @RequestMapping(value = "/menu", method = RequestMethod.GET, produces = "application/json")
   public List<MenuItem> listMenus() throws Exception {
      ObjectMapper objectMapper = new ObjectMapper();

      try {
         List<MenuItem> listMenuItems = objectMapper.readValue(new ClassPathResource("menu.json").getFile(),
               new TypeReference<List<MenuItem>>() {
               });
         return listMenuItems;
      } catch (Exception e) {
         e.printStackTrace();
         throw e;
      }
   }
}
