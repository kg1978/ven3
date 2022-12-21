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
   public String oiszkod;

   @NotBlank
   @Column(name = "oi_mkodbet")
   @Size(max = 3)
   public String mkodbet;

   @NotBlank
   @Column(name = "oi_nev")
   @Size(max = 60)
   public String nev;

   @Column(name = "oi_szekh_telnev")
   @Size(max = 27)
   public String szekh_telnev;

   @Column(name = "oi_szekh_taz")
   public int szekh_taz;

   @Column(name = "oi_musz")
   @Size(max = 6)
   public String musz;

   @Column(name = "oi_szervezet")
   public int szervezet;

   @Column(name = "oi_szigszkod")
   @Size(max = 6)
   public String szigszkod;

   @NotBlank
   @Column(name = "oi_ervtol")
   public Date ervtol;

   @Column(name = "oi_ervig")
   public Date ervig;

   @Column(name = "oi_cim")
   @Size(max = 60)
   public String cim;

   @Column(name = "oi_telefon")
   @Size(max = 30)
   public String telefon;

   @Column(name = "oi_fax")
   @Size(max = 30)
   public String fax;

   @Column(name = "oi_pir")
   @Size(max = 5)
   public String pir;

   @Column(name = "oi_kozt")
   @Size(max = 25)
   public String kozt;

   @Column(name = "oi_ktjell")
   @Size(max = 20)
   public String ktjell;

   @Column(name = "oi_hsz")
   @Size(max = 11)
   public String hsz;

   @Column(name = "oi_ep")
   @Size(max = 4)
   public String ep;

   @Column(name = "oi_lh")
   @Size(max = 4)
   public String lh;

   @Column(name = "oi_szint")
   @Size(max = 2)
   public String szint;

   @Column(name = "oi_ajto")
   @Size(max = 4)
   public String ajto;

   @Column(name = "oi_gcim")
   @Size(max = 60)
   public String gcim;

   @Column(name = "oi_gtelefon")
   @Size(max = 30)
   public String gtelefon;

   @Column(name = "oi_gfax")
   @Size(max = 30)
   public String gfax;

   @NotBlank
   @Column(name = "oi_okmnev")
   @Size(max = 40)
   public String okmnev;

   @Column(name = "oi_mddat")
   public Date mddat;

   @Column(name = "oi_mduser")
   public int mduser;

   @Column(name = "oi_hi_id")
   public int hi_id;

   @Column(name = "oi_jaras")
   @Size(max = 6)
   public String jaras;

   @Column(name = "oi_kiemelt")
   @Size(max = 2)
   public String kiemelt;

   @Column(name = "oi_telep_kiemelt")
   @Size(max = 2)
   public String telep_kiemelt;

   @Override
   public String toString() {
      return "KeOI [oiszkod=" + oiszkod + ", mkodbet=" + mkodbet + ", nev=" + nev + ", szekh_telnev=" + szekh_telnev
            + ", szekh_taz=" + szekh_taz + ", musz=" + musz + ", szervezet=" + szervezet + ", szigszkod=" + szigszkod
            + ", ervtol=" + ervtol + ", ervig=" + ervig + ", cim=" + cim + ", telefon=" + telefon + ", fax=" + fax
            + ", pir=" + pir + ", kozt=" + kozt + ", ktjell=" + ktjell + ", hsz=" + hsz + ", ep=" + ep + ", lh=" + lh
            + ", szint=" + szint + ", ajto=" + ajto + ", gcim=" + gcim + ", gtelefon=" + gtelefon + ", gfax=" + gfax
            + ", okmnev=" + okmnev + ", mddat=" + mddat + ", mduser=" + mduser + ", hi_id=" + hi_id + ", jaras=" + jaras
            + ", kiemelt=" + kiemelt + ", telep_kiemelt=" + telep_kiemelt + "]";
   }
}
