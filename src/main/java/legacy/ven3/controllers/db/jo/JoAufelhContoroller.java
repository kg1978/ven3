package legacy.ven3.controllers.db.jo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import legacy.ven3.models.db.jo.JoAufelh;
import legacy.ven3.servises.db.jo.JoAufelhService;

@RestController
@RequestMapping("/api/service-db/jo/aufelh")
public class JoAufelhContoroller {
   @Autowired
   JoAufelhService service;

   @GetMapping(value = "/all", produces = "application/json")
   private List<JoAufelh> getAll() {
      return service.getAll();
   }

   @GetMapping(value = "/get/{id}", produces = "application/json")
   private JoAufelh get(@PathVariable("id") int id) {
      return service.getById(id);
   }

   @DeleteMapping("/del/{id}")
   private void delete(@PathVariable("id") int id) {
      service.delete(id);
   }

   @PostMapping("/save")
   private int save(@RequestBody JoAufelh d) {
      service.saveOrUpdate(d);
      return d.fel_id;
   }
}
