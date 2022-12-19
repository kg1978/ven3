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
   public int ecs_id;

   @NotNull
   @Column(name = "ecs_csopkod")
   public int ecs_csopkod;

   @NotNull
   @Column(name = "ecs_felhid")
   public int ecs_felhid;

   @Override
   public String toString() {
      return "JoEngcsop [ecs_id=" + ecs_id + ", ecs_csopkod=" + ecs_csopkod + ", ecs_felhid=" + ecs_felhid + "]";
   }
}
