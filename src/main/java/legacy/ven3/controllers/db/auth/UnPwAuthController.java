package legacy.ven3.controllers.db.auth;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import legacy.ven3.models.db.auth.UnPwAuth;
import legacy.ven3.servises.db.auth.UnPwAuthService;

@RestController
@RequestMapping("/api/service-db/auth/unpwauth")
public class UnPwAuthController {
   @Autowired
   UnPwAuthService service;

   @GetMapping(value = "/all", produces = "application/json")
   private List<UnPwAuth> getAll() {
      return service.getAll();
   }

   @GetMapping(value = "/get/{id}", produces = "application/json")
   private UnPwAuth get(@PathVariable("id") String id) {
      return service.getById(id);
   }

   @DeleteMapping("/del/{id}")
   private void delete(@PathVariable("id") String id) {
      service.delete(id);
   }

   @PostMapping("/save")
   private String save(@RequestBody UnPwAuth d) {
      service.saveOrUpdate(d);
      return d.username;
   }
}
