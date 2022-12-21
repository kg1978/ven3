package legacy.ven3.models.db.ke;

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
   public int id;

   @NotBlank
   @Column(name = "sz_kapcstip")
   @Size(max = 2)
   public String kapcstip;

   @Column(name = "sz_ervig")
   public Date ervig;

   @NotNull
   @Column(name = "sz_ervtol")
   public Date ervtol;

   @NotBlank
   @Column(name = "sz_szkod")
   @Size(max = 6)
   public String szkod;

   @NotBlank
   @Column(name = "sz_fszkod")
   @Size(max = 6)
   public String fszkod;

   @Column(name = "sz_mduser")
   public int mduser;

   @NotNull
   @Column(name = "sz_mddat")
   public Date mddat;

   @Column(name = "sz_torl")
   public Date torl;

   @Override
   public String toString() {
      return "KeSzervSzerv [id=" + id + ", kapcstip=" + kapcstip + ", ervig=" + ervig + ", ervtol=" + ervtol
            + ", szkod=" + szkod + ", fszkod=" + fszkod + ", mduser=" + mduser + ", mddat=" + mddat + ", torl=" + torl
            + "]";
   }
}
