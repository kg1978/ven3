package legacy.ven3.models.db.keret;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "ke_szervszerv", uniqueConstraints = { @UniqueConstraint(columnNames = "sz_id") })
public class KeSzervSzerv {
   @Id
   @NotNull
   @Column(name = "sz_id")
   public int sz_id;

   @NotBlank
   @Column(name = "sz_kapcstip")
   @Size(max = 2)
   public String sz_kapcstip;

   @Column(name = "sz_ervig")
   public Date sz_ervig;

   @NotNull
   @Column(name = "sz_ervtol")
   public Date sz_ervtol;

   @NotBlank
   @Column(name = "sz_szkod")
   @Size(max = 6)
   public String sz_szkod;

   @NotBlank
   @Column(name = "sz_fszkod")
   @Size(max = 6)
   public String sz_fszkod;

   @Column(name = "sz_mduser")
   public int sz_mduser;

   @NotNull
   @Column(name = "sz_mddat")
   public Date sz_mddat;

   @Column(name = "sz_torl")
   public Date sz_torl;

   @Override
   public String toString() {
      return "KeSzervSzerv [sz_id=" + sz_id + ", sz_kapcstip=" + sz_kapcstip + ", sz_ervig=" + sz_ervig + ", sz_ervtol="
            + sz_ervtol + ", sz_szkod=" + sz_szkod + ", sz_fszkod=" + sz_fszkod + ", sz_mduser=" + sz_mduser
            + ", sz_mddat=" + sz_mddat + ", sz_torl=" + sz_torl + "]";
   }
}
