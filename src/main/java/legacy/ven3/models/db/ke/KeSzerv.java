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
   public String sze_szkod;

   @NotNull
   @Column(name = "sze_tipus")
   public int sze_tipus;

   @NotNull
   @Column(name = "sze_jelleg")
   public int sze_jelleg;

   @NotNull
   @Column(name = "sze_allapot")
   public int sze_allapot;

   @NotNull
   @Column(name = "sze_szekh_taz")
   public int sze_szekh_taz;

   @Column(name = "sze_szekh_mbkod")
   @Size(max = 3)
   public String sze_szekh_mbkod;

   @NotNull
   @Column(name = "sze_ervtol")
   public Date sze_ervtol;

   @Column(name = "sze_ervig")
   public Date sze_ervig;

   @NotBlank
   @Column(name = "sze_iszkod")
   @Size(max = 6)
   public String sze_iszkod;

   @Column(name = "sze_avnyaz")
   @Size(max = 9)
   public String sze_avnyaz;

   @Column(name = "sze_mbkod")
   @Size(max = 3)
   public String sze_mbkod;

   @Column(name = "sze_nev")
   @Size(max = 100)
   public String sze_nev;

   @Column(name = "sze_cim")
   @Size(max = 70)
   public String sze_cim;

   @Column(name = "sze_pir")
   @Size(max = 4)
   public String sze_pir;

   @Column(name = "sze_szlszkod")
   public int sze_szlszkod;

   @Column(name = "sze_telnev")
   @Size(max = 27)
   public String sze_telnev;

   @Column(name = "sze_mduser")
   public int sze_mduser;

   @Column(name = "sze_mddat")
   public Date sze_mddat;

   @Column(name = "sze_nevszla")
   @Size(max = 100)
   public String sze_nevszla;

   @Column(name = "sze_pirszla")
   @Size(max = 4)
   public String sze_pirszla;

   @Column(name = "sze_telnevszla")
   @Size(max = 27)
   public String sze_telnevszla;

   @Column(name = "sze_cimszla")
   @Size(max = 70)
   public String sze_cimszla;

   @Column(name = "sze_szlaszla")
   @Size(max = 24)
   public String sze_szlaszla;

   @Column(name = "sze_teljesnev1")
   @Size(max = 55)
   public String sze_teljesnev1;

   @Column(name = "sze_teljesnev2")
   @Size(max = 55)
   public String sze_teljesnev2;

   @Column(name = "sze_teljesnev3")
   @Size(max = 55)
   public String sze_teljesnev3;

   @Column(name = "sze_teljesnev4")
   @Size(max = 55)
   public String sze_teljesnev4;

   @Column(name = "sze_fix_nev")
   @Size(max = 100)
   public String sze_fix_nev;

   @Column(name = "sze_merit")
   @Size(max = 4)
   public String sze_merit;

   @Column(name = "sze_fk_id")
   public int sze_fk_id;

   @Column(name = "sze_adoszam")
   @Size(max = 11)
   public String sze_adoszam;

   @Column(name = "sze_kiemelt")
   @Size(max = 2)
   public String sze_kiemelt;

   @Column(name = "sze_telep_kiemelt")
   @Size(max = 2)
   public String sze_telep_kiemelt;

   @Override
   public String toString() {
      return "KeSzerv [sze_szkod=" + sze_szkod + ", sze_tipus=" + sze_tipus + ", sze_jelleg=" + sze_jelleg
            + ", sze_allapot=" + sze_allapot + ", sze_szekh_taz=" + sze_szekh_taz + ", sze_szekh_mbkod="
            + sze_szekh_mbkod + ", sze_ervtol=" + sze_ervtol + ", sze_ervig=" + sze_ervig + ", sze_iszkod=" + sze_iszkod
            + ", sze_avnyaz=" + sze_avnyaz + ", sze_mbkod=" + sze_mbkod + ", sze_nev=" + sze_nev + ", sze_cim="
            + sze_cim + ", sze_pir=" + sze_pir + ", sze_szlszkod=" + sze_szlszkod + ", sze_telnev=" + sze_telnev
            + ", sze_mduser=" + sze_mduser + ", sze_mddat=" + sze_mddat + ", sze_nevszla=" + sze_nevszla
            + ", sze_pirszla=" + sze_pirszla + ", sze_telnevszla=" + sze_telnevszla + ", sze_cimszla=" + sze_cimszla
            + ", sze_szlaszla=" + sze_szlaszla + ", sze_teljesnev1=" + sze_teljesnev1 + ", sze_teljesnev2="
            + sze_teljesnev2 + ", sze_teljesnev3=" + sze_teljesnev3 + ", sze_teljesnev4=" + sze_teljesnev4
            + ", sze_fix_nev=" + sze_fix_nev + ", sze_merit=" + sze_merit + ", sze_fk_id=" + sze_fk_id
            + ", sze_adoszam=" + sze_adoszam + ", sze_kiemelt=" + sze_kiemelt + ", sze_telep_kiemelt="
            + sze_telep_kiemelt + "]";
   }
}
