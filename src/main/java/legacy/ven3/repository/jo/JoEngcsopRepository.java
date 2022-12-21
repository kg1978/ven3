package legacy.ven3.repository.jo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import legacy.ven3.models.db.jo.JoEngcsop;

@Repository
public interface JoEngcsopRepository extends JpaRepository<JoEngcsop, Integer> {
   Optional<JoEngcsop> findByFelhid(int felhid);
}