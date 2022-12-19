package legacy.ven3.models.db.jo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "jo_csopjog", uniqueConstraints = { @UniqueConstraint(columnNames = "csj_id") })
public class JoJogcsop {
   @Id
   @NotNull
   @Column(name = "csj_id")
   public int csj_id;

   @NotNull
   @Column(name = "csj_jogkod")
   public int csj_jogkod;

   @NotNull
   @Column(name = "csj_csopkod")
   public int csj_csopkod;

   @Override
   public String toString() {
      return "JoJogcsop [csj_id=" + csj_id + ", csj_jogkod=" + csj_jogkod + ", csj_csopkod=" + csj_csopkod + "]";
   }
}
