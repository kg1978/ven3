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
@Table(name = "ke_szerv", uniqueConstraints = { @UniqueConstraint(columnNames = "sze_szkod") })
public class KeSzerv {
   @Id
   @NotBlank
   @Column(name = "sze_szkod")
   @Size(max = 6)
   public String szkod;

   @NotNull
   @Column(name = "sze_tipus")
   public int tipus;

   @NotNull
   @Column(name = "sze_jelleg")
   public int jelleg;

   @NotNull
   @Column(name = "sze_allapot")
   public int allapot;

   @NotNull
   @Column(name = "sze_szekh_taz")
   public int szekh_taz;

   @Column(name = "sze_szekh_mbkod")
   @Size(max = 3)
   public String szekh_mbkod;

   @NotNull
   @Column(name = "sze_ervtol")
   public Date ervtol;

   @Column(name = "sze_ervig")
   public Date ervig;

   @NotBlank
   @Column(name = "sze_iszkod")
   @Size(max = 6)
   public String iszkod;

   @Column(name = "sze_avnyaz")
   @Size(max = 9)
   public String avnyaz;

   @Column(name = "sze_mbkod")
   @Size(max = 3)
   public String mbkod;

   @Column(name = "sze_nev")
   @Size(max = 100)
   public String nev;

   @Column(name = "sze_cim")
   @Size(max = 70)
   public String cim;

   @Column(name = "sze_pir")
   @Size(max = 4)
   public String pir;

   @Column(name = "sze_szlszkod")
   public int szlszkod;

   @Column(name = "sze_telnev")
   @Size(max = 27)
   public String telnev;

   @Column(name = "sze_mduser")
   public int mduser;

   @Column(name = "sze_mddat")
   public Date mddat;

   @Column(name = "sze_nevszla")
   @Size(max = 100)
   public String nevszla;

   @Column(name = "sze_pirszla")
   @Size(max = 4)
   public String pirszla;

   @Column(name = "sze_telnevszla")
   @Size(max = 27)
   public String telnevszla;

   @Column(name = "sze_cimszla")
   @Size(max = 70)
   public String cimszla;

   @Column(name = "sze_szlaszla")
   @Size(max = 24)
   public String szlaszla;

   @Column(name = "sze_teljesnev1")
   @Size(max = 55)
   public String teljesnev1;

   @Column(name = "sze_teljesnev2")
   @Size(max = 55)
   public String teljesnev2;

   @Column(name = "sze_teljesnev3")
   @Size(max = 55)
   public String teljesnev3;

   @Column(name = "sze_teljesnev4")
   @Size(max = 55)
   public String teljesnev4;

   @Column(name = "sze_fix_nev")
   @Size(max = 100)
   public String fix_nev;

   @Column(name = "sze_merit")
   @Size(max = 4)
   public String merit;

   @Column(name = "sze_fk_id")
   public int fk_id;

   @Column(name = "sze_adoszam")
   @Size(max = 11)
   public String adoszam;

   @Column(name = "sze_kiemelt")
   @Size(max = 2)
   public String kiemelt;

   @Column(name = "sze_telep_kiemelt")
   @Size(max = 2)
   public String telep_kiemelt;

   @Override
   public String toString() {
      return "KeSzerv [szkod=" + szkod + ", tipus=" + tipus + ", jelleg=" + jelleg + ", allapot=" + allapot
            + ", szekh_taz=" + szekh_taz + ", szekh_mbkod=" + szekh_mbkod + ", ervtol=" + ervtol + ", ervig=" + ervig
            + ", iszkod=" + iszkod + ", avnyaz=" + avnyaz + ", mbkod=" + mbkod + ", nev=" + nev + ", cim=" + cim
            + ", pir=" + pir + ", szlszkod=" + szlszkod + ", telnev=" + telnev + ", mduser=" + mduser + ", mddat="
            + mddat + ", nevszla=" + nevszla + ", pirszla=" + pirszla + ", telnevszla=" + telnevszla + ", cimszla="
            + cimszla + ", szlaszla=" + szlaszla + ", teljesnev1=" + teljesnev1 + ", teljesnev2=" + teljesnev2
            + ", teljesnev3=" + teljesnev3 + ", teljesnev4=" + teljesnev4 + ", fix_nev=" + fix_nev + ", merit=" + merit
            + ", fk_id=" + fk_id + ", adoszam=" + adoszam + ", kiemelt=" + kiemelt + ", telep_kiemelt=" + telep_kiemelt
            + "]";
   }
}
