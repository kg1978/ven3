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

import legacy.ven3.models.db.ke.KeSzervSzerv;
import legacy.ven3.servises.db.ke.KeSzervSzervService;

@RestController
@RequestMapping("/api/service-db/ke/szervszerv")
public class KeSzervSzervController {
   @Autowired
   KeSzervSzervService service;

   @GetMapping(value = "/all", produces = "application/json")
   private List<KeSzervSzerv> getAll() {
      return service.getAll();
   }

   @GetMapping(value = "/get/{id}", produces = "application/json")
   private KeSzervSzerv get(@PathVariable("id") int id) {
      return service.getById(id);
   }

   @DeleteMapping("/del/{id}")
   private void delete(@PathVariable("id") int id) {
      service.delete(id);
   }

   @PostMapping("/save")
   private int save(@RequestBody KeSzervSzerv keSzervSzerv) {
      service.saveOrUpdate(keSzervSzerv);
      return keSzervSzerv.id;
   }
}
