package legacy.ven3.models.db.auth;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "jo_password", uniqueConstraints = { @UniqueConstraint(columnNames = "username") })
public class UnPwAuth {
   @Id
   @NotBlank
   @Column(name = "username")
   @Size(max = 20)
   public String username;

   @NotBlank
   @Column(name = "password")
   @Size(max = 20)
   public String password;
}
