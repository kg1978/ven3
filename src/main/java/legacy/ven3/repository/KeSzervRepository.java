package legacy.ven3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import legacy.ven3.models.db.keret.KeSzerv;

@Repository
public interface KeSzervRepository extends JpaRepository<KeSzerv, String> {
}