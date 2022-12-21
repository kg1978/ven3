package legacy.ven3.repository.jo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import legacy.ven3.models.db.jo.JoAufelh;

@Repository
public interface JoAufelhRepository extends JpaRepository<JoAufelh, Integer> {
   Optional<JoAufelh> findByFelhazon(String felhazon);

   Optional<JoAufelh> findByFelhazonAndForras(String felhazon, String forras);
}