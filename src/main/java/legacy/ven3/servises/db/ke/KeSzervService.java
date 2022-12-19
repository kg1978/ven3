package legacy.ven3.servises.db.ke;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import legacy.ven3.models.db.ke.KeSzerv;
import legacy.ven3.repository.ke.KeSzervRepository;

@Service
public class KeSzervService {

   @Value("${mock.services.ke}")
   private boolean mock;

   @Autowired
   KeSzervRepository repo;

   public List<KeSzerv> getAll() {
      List<KeSzerv> list = new ArrayList<KeSzerv>();
      {
         repo.findAll().forEach(e -> list.add(e));
      }
      return list;
   }

   public KeSzerv getById(String id) {
      return repo.findById(id).get();
   }

   public void saveOrUpdate(KeSzerv d) {
      if (mock) {
         repo.save(d);
      }
   }

   public void saveAll(List<KeSzerv> d) {
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
