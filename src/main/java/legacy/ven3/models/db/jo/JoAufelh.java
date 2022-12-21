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
@Table(name = "jo_aufelh", uniqueConstraints = @UniqueConstraint(columnNames = { "fel_id", "fel_felhazon" }))
public class JoAufelh {
   @Id
   @NotNull
   @Column(name = "fel_id")
   public int id;

   @NotBlank
   @Column(name = "fel_felhazon")
   @Size(max = 20)
   public String felhazon;

   @NotBlank
   @Column(name = "fel_szkod")
   @Size(max = 6)
   public String szkod;

   @NotBlank
   @Column(name = "fel_nev")
   @Size(max = 50)
   public String nev;

   @NotBlank
   @Column(name = "fel_ervtol")
   public Date ervtol;

   @NotBlank
   @Column(name = "fel_ervig")
   public Date ervig;

   @NotBlank
   @Column(name = "fel_forras")
   @Size(max = 3)
   public String forras;

   @Override
   public String toString() {
      return "JoAufelh [id=" + id + ", felhazon=" + felhazon + ", szkod=" + szkod + ", nev=" + nev + ", ervtol="
            + ervtol + ", ervig=" + ervig + ", forras=" + forras + "]";
   }
}
