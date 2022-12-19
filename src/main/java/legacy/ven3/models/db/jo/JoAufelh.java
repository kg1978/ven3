package legacy.ven3.models.db.jo;

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
@Table(name = "jo_aufelh", uniqueConstraints = { @UniqueConstraint(columnNames = "fel_id") })
public class JoAufelh {
   @Id
   @NotNull
   @Column(name = "fel_id")
   public int fel_id;

   @NotBlank
   @Column(name = "fel_felhazon")
   @Size(max = 20)
   public String fel_felhazon;

   @NotBlank
   @Column(name = "fel_szkod")
   @Size(max = 6)
   public String fel_szkod;

   @NotBlank
   @Column(name = "fel_nev")
   @Size(max = 50)
   public String fel_nev;

   @NotBlank
   @Column(name = "fel_ervtol")
   public Date fel_ervtol;

   @NotBlank
   @Column(name = "fel_ervig")
   public Date fel_ervig;

   @NotBlank
   @Column(name = "fel_forras")
   @Size(max = 3)
   public String fel_forras;

   @Override
   public String toString() {
      return "JoAufelh [fel_id=" + fel_id + ", fel_felhazon=" + fel_felhazon + ", fel_szkod=" + fel_szkod + ", fel_nev="
            + fel_nev + ", fel_ervtol=" + fel_ervtol + ", fel_ervig=" + fel_ervig + ", fel_forras=" + fel_forras + "]";
   }
}
