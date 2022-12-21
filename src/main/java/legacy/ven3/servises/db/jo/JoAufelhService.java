package legacy.ven3.servises.db.jo;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import legacy.ven3.models.db.jo.JoAufelh;
import legacy.ven3.repository.jo.JoAufelhRepository;

@Service
public class JoAufelhService {
   private static String OKMANYIRODA_FORRAS = "O";

   @Value("${mock.services.jo}")
   private boolean mock;

   @Autowired
   JoAufelhRepository repo;

   public List<JoAufelh> getAll() {
      List<JoAufelh> list = new ArrayList<JoAufelh>();
      if (mock) {
         repo.findAll().forEach(e -> list.add(e));
      }
      return list;
   }

   public JoAufelh getById(int id) throws NoSuchElementException {
      return repo.findById(id).get();
   }

   public void saveOrUpdate(JoAufelh d) {
      if (mock) {
         repo.save(d);
      }
   }

   public void saveAll(List<JoAufelh> d) {
      if (mock) {
         repo.saveAll(d);
      }
   }

   public void delete(int id) {
      if (mock) {
         repo.deleteById(id);
      }
   }

   public JoAufelh getByFelhazon(String felhazon) throws NoSuchElementException {
      return repo.findByFelhazon(felhazon).get();
   }

   public JoAufelh getByFelhazonOkmany(String id) throws NoSuchElementException {
      return repo.findByFelhazonAndForras(id, OKMANYIRODA_FORRAS).get();
   }

}
