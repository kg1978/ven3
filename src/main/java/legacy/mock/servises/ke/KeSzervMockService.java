package legacy.mock.servises.ke;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import legacy.ven3.models.db.keret.KeSzerv;
import legacy.ven3.repository.KeSzervRepository;

@Service
public class KeSzervMockService {
   @Autowired
   KeSzervRepository repo;

   public List<KeSzerv> getAll() {
      List<KeSzerv> list = new ArrayList<KeSzerv>();
      repo.findAll().forEach(e -> list.add(e));
      return list;
   }

   public KeSzerv getById(String id) {
      return repo.findById(id).get();
   }

   public void saveOrUpdate(KeSzerv d) {
      repo.save(d);
   }

   public void delete(String id) {
      repo.deleteById(id);
   }
}
