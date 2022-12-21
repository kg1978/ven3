package legacy.ven3.servises.db.auth;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import legacy.ven3.models.db.auth.UnPwAuth;
import legacy.ven3.repository.auth.UnPwAuthRepository;

@Service
public class UnPwAuthService {

   @Value("${mock.services.auth}")
   private boolean mock;

   @Autowired
   UnPwAuthRepository repo;

   public List<UnPwAuth> getAll() {
      List<UnPwAuth> list = new ArrayList<UnPwAuth>();
      if (mock) {
         repo.findAll().forEach(e -> list.add(e));
      }
      return list;
   }

   public UnPwAuth getById(String id) throws NoSuchElementException {
      return repo.findById(id).get();
   }

   public void saveOrUpdate(UnPwAuth d) {
      if (mock) {
         repo.save(d);
      }
   }

   public void saveAll(List<UnPwAuth> d) {
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
