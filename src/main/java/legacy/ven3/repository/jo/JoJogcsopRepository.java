package legacy.ven3.repository.jo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import legacy.ven3.models.db.jo.JoJogcsop;

@Repository
public interface JoJogcsopRepository extends JpaRepository<JoJogcsop, Integer> {
}