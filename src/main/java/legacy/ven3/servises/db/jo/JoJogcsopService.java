package legacy.ven3.servises.db.jo;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import legacy.ven3.models.db.jo.JoJogcsop;
import legacy.ven3.repository.jo.JoJogcsopRepository;

@Service
public class JoJogcsopService {

   @Value("${mock.services.jo}")
   private boolean mock;

   @Autowired
   JoJogcsopRepository repo;

   public List<JoJogcsop> getAll() {
      List<JoJogcsop> list = new ArrayList<JoJogcsop>();
      if (mock) {
         repo.findAll().forEach(e -> list.add(e));
      }
      return list;
   }

   public JoJogcsop getById(int id) throws NoSuchElementException {
      return repo.findById(id).get();
   }

   public void saveOrUpdate(JoJogcsop d) {
      if (mock) {
         repo.save(d);
      }
   }

   public void saveAll(List<JoJogcsop> d) {
      if (mock) {
         repo.saveAll(d);
      }
   }

   public void delete(int id) {
      if (mock) {
         repo.deleteById(id);
      }
   }

   public List<JoJogcsop> getByCsopkod(int csopkod) {
      List<JoJogcsop> list = new ArrayList<JoJogcsop>();
      repo.findByCsopkod(csopkod).forEach(e -> list.add(e));
      return list;
   }
}
