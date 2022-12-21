package legacy.ven3.servises.db.ke;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import legacy.ven3.models.db.ke.KeSzervSzerv;
import legacy.ven3.repository.ke.KeSzervSzervRepository;

@Service
public class KeSzervSzervService {

   @Value("${mock.services.ke}")
   private boolean mock;

   @Autowired
   KeSzervSzervRepository repo;

   public List<KeSzervSzerv> getAll() {
      List<KeSzervSzerv> list = new ArrayList<KeSzervSzerv>();
      if (mock) {
         repo.findAll().forEach(e -> list.add(e));
      }
      return list;
   }

   public KeSzervSzerv getById(int id) throws NoSuchElementException {
      return repo.findById(id).get();
   }

   public void saveOrUpdate(KeSzervSzerv d) {
      if (mock) {
         repo.save(d);
      }
   }

   public void saveAll(List<KeSzervSzerv> d) {
      if (mock) {
         repo.saveAll(d);
      }
   }

   public void delete(int id) {
      if (mock) {
         repo.deleteById(id);
      }
   }
}
