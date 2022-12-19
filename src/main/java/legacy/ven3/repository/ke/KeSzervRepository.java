package legacy.ven3.repository.ke;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import legacy.ven3.models.db.ke.KeSzerv;

@Repository
public interface KeSzervRepository extends JpaRepository<KeSzerv, String> {
}