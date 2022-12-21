package legacy.ven3.servises.db.jo;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import legacy.ven3.models.db.jo.JoEngcsop;
import legacy.ven3.repository.jo.JoEngcsopRepository;

@Service
public class JoEngcsopService {

   @Value("${mock.services.jo}")
   private boolean mock;

   @Autowired
   JoEngcsopRepository repo;

   public List<JoEngcsop> getAll() {
      List<JoEngcsop> list = new ArrayList<JoEngcsop>();
      if (mock) {
         repo.findAll().forEach(e -> list.add(e));
      }
      return list;
   }

   public JoEngcsop getById(int id) throws NoSuchElementException {
      return repo.findById(id).get();
   }

   public void saveOrUpdate(JoEngcsop d) {
      if (mock) {
         repo.save(d);
      }
   }

   public void saveAll(List<JoEngcsop> d) {
      if (mock) {
         repo.saveAll(d);
      }
   }

   public void delete(int id) {
      if (mock) {
         repo.deleteById(id);
      }
   }

   public JoEngcsop getByFelhid(int id) throws NoSuchElementException {
      return repo.findByFelhid(id).get();
   }
}
