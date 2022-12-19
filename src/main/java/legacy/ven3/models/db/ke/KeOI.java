package legacy.ven3.models.db.ke;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "ke_oi", uniqueConstraints = { @UniqueConstraint(columnNames = "oi_oiszkod") })
public class KeOI {
   @Id
   @NotBlank
   @Column(name = "oi_oiszkod")
   @Size(max = 6)
   public String oi_oiszkod;

   @NotBlank
   @Column(name = "oi_mkodbet")
   @Size(max = 3)
   public String oi_mkodbet;

   @NotBlank
   @Column(name = "oi_nev")
   @Size(max = 60)
   public String oi_nev;

   @Column(name = "oi_szekh_telnev")
   @Size(max = 27)
   public String oi_szekh_telnev;

   @Column(name = "oi_szekh_taz")
   public int oi_szekh_taz;

   @Column(name = "oi_musz")
   @Size(max = 6)
   public String oi_musz;

   @Column(name = "oi_szervezet")
   public int oi_szervezet;

   @Column(name = "oi_szigszkod")
   @Size(max = 6)
   public String oi_szigszkod;

   @NotBlank
   @Column(name = "oi_ervtol")
   public Date oi_ervtol;

   @Column(name = "oi_ervig")
   public Date oi_ervig;

   @Column(name = "oi_cim")
   @Size(max = 60)
   public String oi_cim;

   @Column(name = "oi_telefon")
   @Size(max = 30)
   public String oi_telefon;

   @Column(name = "oi_fax")
   @Size(max = 30)
   public String oi_fax;

   @Column(name = "oi_pir")
   @Size(max = 5)
   public String oi_pir;

   @Column(name = "oi_kozt")
   @Size(max = 25)
   public String oi_kozt;

   @Column(name = "oi_ktjell")
   @Size(max = 20)
   public String oi_ktjell;

   @Column(name = "oi_hsz")
   @Size(max = 11)
   public String oi_hsz;

   @Column(name = "oi_ep")
   @Size(max = 4)
   public String oi_ep;

   @Column(name = "oi_lh")
   @Size(max = 4)
   public String oi_lh;

   @Column(name = "oi_szint")
   @Size(max = 2)
   public String oi_szint;

   @Column(name = "oi_ajto")
   @Size(max = 4)
   public String oi_ajto;

   @Column(name = "oi_gcim")
   @Size(max = 60)
   public String oi_gcim;

   @Column(name = "oi_gtelefon")
   @Size(max = 30)
   public String oi_gtelefon;

   @Column(name = "oi_gfax")
   @Size(max = 30)
   public String oi_gfax;

   @NotBlank
   @Column(name = "oi_okmnev")
   @Size(max = 40)
   public String oi_okmnev;

   @Column(name = "oi_mddat")
   public Date oi_mddat;

   @Column(name = "oi_mduser")
   public int oi_mduser;

   @Column(name = "oi_hi_id")
   public int oi_hi_id;

   @Column(name = "oi_jaras")
   @Size(max = 6)
   public String oi_jaras;

   @Column(name = "oi_kiemelt")
   @Size(max = 2)
   public String oi_kiemelt;

   @Column(name = "oi_telep_kiemelt")
   @Size(max = 2)
   public String oi_telep_kiemelt;

   @Override
   public String toString() {
      return "KeOI [oi_oiszkod=" + oi_oiszkod + ", oi_mkodbet=" + oi_mkodbet + ", oi_nev=" + oi_nev
            + ", oi_szekh_telnev=" + oi_szekh_telnev + ", oi_szekh_taz=" + oi_szekh_taz + ", oi_musz=" + oi_musz
            + ", oi_szervezet=" + oi_szervezet + ", oi_szigszkod=" + oi_szigszkod + ", oi_ervtol=" + oi_ervtol
            + ", oi_ervig=" + oi_ervig + ", oi_cim=" + oi_cim + ", oi_telefon=" + oi_telefon + ", oi_fax=" + oi_fax
            + ", oi_pir=" + oi_pir + ", oi_kozt=" + oi_kozt + ", oi_ktjell=" + oi_ktjell + ", oi_hsz=" + oi_hsz
            + ", oi_ep=" + oi_ep + ", oi_lh=" + oi_lh + ", oi_szint=" + oi_szint + ", oi_ajto=" + oi_ajto + ", oi_gcim="
            + oi_gcim + ", oi_gtelefon=" + oi_gtelefon + ", oi_gfax=" + oi_gfax + ", oi_okmnev=" + oi_okmnev
            + ", oi_mddat=" + oi_mddat + ", oi_mduser=" + oi_mduser + ", oi_hi_id=" + oi_hi_id + ", oi_jaras="
            + oi_jaras + ", oi_kiemelt=" + oi_kiemelt + ", oi_telep_kiemelt=" + oi_telep_kiemelt + "]";
   }
}
