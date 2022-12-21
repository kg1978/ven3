package legacy.ven3.servises.db.ke;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import legacy.ven3.models.KeSzervMin;
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

   public KeSzerv getById(String id) throws NoSuchElementException {
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

   public List<KeSzerv> getByTipus(int tipus) {
      List<KeSzerv> list = new ArrayList<KeSzerv>();
      repo.findByTipus(tipus).forEach(e -> list.add(e));
      return list;
   }

   public List<KeSzervMin> querySzervTip4By(int fel_id) {
      List<KeSzervMin> list = new ArrayList<KeSzervMin>();
      repo.querySzervTip4By(fel_id).forEach(e -> list.add(e));
      return list;
   }

   public List<KeSzervMin> querySzervTip5By(int fel_id) {
      List<KeSzervMin> list = new ArrayList<KeSzervMin>();
      repo.querySzervTip5By(fel_id).forEach(e -> list.add(e));
      return list;
   }

   public List<KeSzervMin> querySzervTip7By(int fel_id) {
      List<KeSzervMin> list = new ArrayList<KeSzervMin>();
      repo.querySzervTip7By(fel_id).forEach(e -> list.add(e));
      return list;
   }

   public List<KeSzervMin> queryRegioBy(int fel_id) {
      List<KeSzervMin> list = new ArrayList<KeSzervMin>();
      repo.queryRegioBy(fel_id).forEach(e -> list.add(e));
      return list;
   }

   public List<KeSzervMin> queryMegyeBy(int fel_id) {
      List<KeSzervMin> list = new ArrayList<KeSzervMin>();
      repo.queryMegyeBy(fel_id).forEach(e -> list.add(e));
      return list;
   }
}
