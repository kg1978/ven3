package legacy.ven3.repository.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import legacy.ven3.models.db.auth.UnPwAuth;

@Repository
public interface UnPwAuthRepository extends JpaRepository<UnPwAuth, String> {

}
