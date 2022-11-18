package ven3;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import ven3.models.MenuItem;
import ven3.models.Role;
import ven3.models.User;

@SpringBootApplication
@RestController
public class Ven3Application implements CommandLineRunner {

   private static Logger LOG = LoggerFactory.getLogger(Ven3Application.class);

   public static List<User> listMockUsers;
   public static List<Role> listMockRoles;
   public static List<MenuItem> listMenuItem;

   public static void main(String[] args) {
      LOG.info("STARTING : Spring boot application starting");
      SpringApplication.run(Ven3Application.class, args);
   }

   @Override
   public void run(String... arg0) throws Exception {
      LOG.info("EXECUTING START: Run method of Application Runner");

      try {
         ObjectMapper objectMapper = new ObjectMapper();
         listMockUsers = objectMapper.readValue(new ClassPathResource("user-mock.json").getFile(),
               new TypeReference<List<User>>() {
               });
         listMockRoles = objectMapper.readValue(new ClassPathResource("role-mock.json").getFile(),
               new TypeReference<List<Role>>() {
               });
         listMenuItem = objectMapper.readValue(new ClassPathResource("menu.json").getFile(),
               new TypeReference<List<MenuItem>>() {
               });

         LOG.info("EXECUTING END: Run method of Application Runner");
      } catch (Exception e) {
         e.printStackTrace();
         throw e;
      }
   }

   @RequestMapping(value = "/")
   public String hello() {
      return "Hello World";
   }
}
