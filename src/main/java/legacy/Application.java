package legacy;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import legacy.framework.models.MenuItem;
import legacy.framework.models.MockUser;
import legacy.framework.models.Role;
import legacy.mock.util.LoadJson2Db;

@SpringBootApplication
@RestController
public class Application implements CommandLineRunner {
   public static List<MockUser> listMockUsers;
   public static List<Role> listMockRoles;
   public static List<MenuItem> listMenuItem;
   public static String MOCK_USER = "mock_";

   private static Logger LOG = LoggerFactory.getLogger(Application.class);

   public static void main(String[] args) {
      LOG.info("STARTING : Spring boot application starting");

      SpringApplication.run(Application.class, args);
   }

   @Value("${mock.services}")
   private boolean mockServices;

   @Autowired
   LoadJson2Db loadJson2Db;

   /*
    * @Bean public ExternalServiceAuthentication externalServiceAuthentication() {
    * ExternalServiceAuthentication externalServiceAuthentication = new
    * AuthService(); return externalServiceAuthentication; }
    * 
    * @Bean public ExternalServiceMenu externalServiceMenu() { ExternalServiceMenu
    * externalServiceMenu = new MenuService(); return externalServiceMenu; }
    */

   @Override
   public void run(String... arg0) throws Exception {
      try {
         ObjectMapper objectMapper = new ObjectMapper();
         listMenuItem = objectMapper.readValue(new ClassPathResource("menu.json").getFile(),
               new TypeReference<List<MenuItem>>() {
               });

         if (mockServices) {
            loadJson2Db.init();

            listMockUsers = objectMapper.readValue(new ClassPathResource("user-mock.json").getFile(),
                  new TypeReference<List<MockUser>>() {
                  });
            listMockRoles = objectMapper.readValue(new ClassPathResource("role-mock.json").getFile(),
                  new TypeReference<List<Role>>() {
                  });
         }

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
