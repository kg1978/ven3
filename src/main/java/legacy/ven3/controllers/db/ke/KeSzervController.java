package legacy.ven3.controllers.db.ke;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import legacy.ven3.models.db.ke.KeSzerv;
import legacy.ven3.servises.db.ke.KeSzervService;

@RestController
@RequestMapping("/api/service-db/ke/szerv")
public class KeSzervController {
   @Autowired
   KeSzervService service;

   @GetMapping(value = "/all", produces = "application/json")
   private List<KeSzerv> getAll() {
      return service.getAll();
   }

   @GetMapping(value = "/get/{id}", produces = "application/json")
   private KeSzerv get(@PathVariable("id") String id) {
      return service.getById(id);
   }

   @DeleteMapping("/del/{id}")
   private void delete(@PathVariable("id") String id) {
      service.delete(id);
   }

   @PostMapping("/save")
   private String save(@RequestBody KeSzerv keSzerv) {
      service.saveOrUpdate(keSzerv);
      return keSzerv.szkod;
   }
}
