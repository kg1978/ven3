package legacy.ven3.models.db.jo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "jo_engcsop", uniqueConstraints = { @UniqueConstraint(columnNames = "ecs_id") })
public class JoEngcsop {
   @Id
   @NotNull
   @Column(name = "ecs_id")
   public int id;

   @NotNull
   @Column(name = "ecs_csopkod")
   public int csopkod;

   @NotNull
   @Column(name = "ecs_felhid")
   public int felhid;

   @Override
   public String toString() {
      return "JoEngcsop [id=" + id + ", csopkod=" + csopkod + ", felhid=" + felhid + "]";
   }
}
