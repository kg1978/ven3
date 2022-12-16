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
   KeOIRepository keOIRepository;

   public List<KeOI> getAll() {
      List<KeOI> list = new ArrayList<KeOI>();
      keOIRepository.findAll().forEach(student -> list.add(student));
      return list;
   }

   public KeOI getById(String id) {
      return keOIRepository.findById(id).get();
   }

   public void saveOrUpdate(KeOI keOI) {
      keOIRepository.save(keOI);
   }

   public void delete(String id) {
      keOIRepository.deleteById(id);
   }
}
