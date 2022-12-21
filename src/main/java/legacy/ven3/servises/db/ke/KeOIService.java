package legacy.ven3.servises.db.ke;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import legacy.ven3.models.db.ke.KeOI;
import legacy.ven3.repository.ke.KeOIRepository;

@Service
public class KeOIService {

   @Value("${mock.services.ke}")
   private boolean mock;

   @Autowired
   KeOIRepository repo;

   public List<KeOI> getAll() {
      List<KeOI> list = new ArrayList<KeOI>();
      if (mock) {
         repo.findAll().forEach(e -> list.add(e));
      }
      return list;
   }

   public KeOI getById(String id) throws NoSuchElementException {
      return repo.findById(id).get();
   }

   public void saveOrUpdate(KeOI d) {
      if (mock) {
         repo.save(d);
      }
   }

   public void saveAll(List<KeOI> d) {
      if (mock) {
         repo.saveAll(d);
      }
   }

   public void delete(String id) {
      if (mock) {
         repo.deleteById(id);
      }
   }
}
