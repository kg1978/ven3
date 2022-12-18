package legacy.mock.servises.ke;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import legacy.ven3.models.db.keret.KeSzervSzerv;
import legacy.ven3.repository.KeSzervSzervRepository;

@Service
public class KeSzervSzervMockService {
   @Autowired
   KeSzervSzervRepository repo;

   public List<KeSzervSzerv> getAll() {
      List<KeSzervSzerv> list = new ArrayList<KeSzervSzerv>();
      repo.findAll().forEach(e -> list.add(e));
      return list;
   }

   public KeSzervSzerv getById(int id) {
      return repo.findById(id).get();
   }

   public void saveOrUpdate(KeSzervSzerv d) {
      repo.save(d);
   }

   public void delete(int id) {
      repo.deleteById(id);
   }
}
