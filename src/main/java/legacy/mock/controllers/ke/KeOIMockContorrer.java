package legacy.mock.controllers.ke;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import legacy.mock.servises.ke.KeOIMockService;
import legacy.ven3.models.db.keret.KeOI;

@RestController
@RequestMapping("/api/service-mock/keoi")
public class KeOIMockContorrer {
   @Autowired
   KeOIMockService service;

   @GetMapping(value = "/all", produces = "application/json")
   private List<KeOI> getAllStudent() {
      return service.getAll();
   }

   @GetMapping(value = "/keoi/{id}", produces = "application/json")
   private KeOI getStudent(@PathVariable("id") String id) {
      return service.getById(id);
   }

   @DeleteMapping("/keoi/{id}")
   private void deleteStudent(@PathVariable("id") String id) {
      service.delete(id);
   }

   @PostMapping("/keoi")
   private String saveStudent(@RequestBody KeOI keOI) {
      service.saveOrUpdate(keOI);
      return keOI.oi_oiszkod;
   }
}
