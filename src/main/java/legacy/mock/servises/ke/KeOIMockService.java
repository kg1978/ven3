package legacy.mock.servises.ke;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import legacy.ven3.models.db.keret.KeOI;
import legacy.ven3.repository.KeOIRepository;

@Service
public class KeOIMockService {
   @Autowired
   KeOIRepository repo;

   public List<KeOI> getAll() {
      List<KeOI> list = new ArrayList<KeOI>();
      repo.findAll().forEach(e -> list.add(e));
      return list;
   }

   public KeOI getById(String id) {
      return repo.findById(id).get();
   }

   public void saveOrUpdate(KeOI d) {
      repo.save(d);
   }

   public void delete(String id) {
      repo.deleteById(id);
   }
}
