package legacy.ven3.repository.ke;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import legacy.ven3.models.db.ke.KeSzerv;

@Repository
public interface KeSzervRepository extends JpaRepository<KeSzerv, String> {
   public static String Q_querySzervTip4By = "querySzervTip4By";
   public static String Q_querySzervTip5By = "querySzervTip5By";
   public static String Q_querySzervTip7By = "querySzervTip7By";
   public static String Q_queryRegio = "queryRegio";
   public static String Q_queryMegye = "queryMegye";

   public static String querySzervTip4By = "select b.sze_szkod as szkod, b.sze_nev as nev, b.sze_mbkod  as mbkod "
         + "from jo_aufelh, ke_szerv a, ke_szerv b " + "where fel_id = :felId and a.sze_szkod = fel_szkod "
         + "and b.sze_mbkod = a.sze_mbkod and b.sze_tipus = '3' order by nev";

   public static String querySzervTip5By = "select sze_szkod as szkod, sze_nev as nev, sze_mbkod as mbkod "
         + "from jo_aufelh, ke_szerv where fel_id = :felId and sze_tipus = '3' order by sze_nev";

   public static String querySzervTip7By = "select b.sze_szkod as szkod, b.sze_nev as nev, b.sze_mbkod as mbkod "
         + "from jo_aufelh, ke_szerv a, ke_szerv b, ke_szervszerv " + "where fel_id = :felId and sz_kapcstip = '02' "
         + "and sz_fszkod = fel_szkod and a.sze_szkod = sz_szkod "
         + "and b.sze_mbkod = a.sze_mbkod and b.sze_tipus = '3' order by nev";

   public static String queryRegio = "select d.sze_szkod as szkod, d.sze_nev as nev, d.sze_mbkod as mbkod "
         + "from jo_aufelh, ke_szerv a, ke_szerv b, ke_szerv c, ke_szerv d, "
         + "      ke_szervszerv e, ke_szervszerv f "
         + "where fel_id = :felId and fel_szkod = a.sze_szkod and a.sze_mbkod = b.sze_mbkod "
         + "and b.sze_tipus = '4' and e.sz_kapcstip = '02' and e.sz_szkod = b.sze_szkod "
         + "and e.sz_fszkod = f.sz_fszkod and f.sz_kapcstip = '02' and c.sze_szkod = f.sz_szkod "
         + "and d.sze_mbkod = c.sze_mbkod and d.sze_tipus = '3' order by nev";

   public static String queryMegye = "select d.sze_szkod as szkod, d.sze_nev as nev, d.sze_mbkod as mbkod "
         + "from jo_aufelh, ke_szerv a, ke_szerv b, ke_szerv d " + "where fel_id = :felId and fel_szkod = a.sze_szkod "
         + "and a.sze_mbkod = b.sze_mbkod and b.sze_tipus = '4' "
         + "and d.sze_mbkod = b.sze_mbkod and d.sze_tipus = '3' order by nev";

   List<KeSzerv> findByTipus(int tipus);

   @Query(nativeQuery = true, name = Q_querySzervTip4By)
   List<KeSzerv> querySzervTip4By(@Param("felId") int felId);

   @Query(nativeQuery = true, name = Q_querySzervTip5By)
   List<KeSzerv> querySzervTip5By(@Param("felId") int felId);

   @Query(nativeQuery = true, name = Q_querySzervTip7By)
   List<KeSzerv> querySzervTip7By(@Param("felId") int felId);

   @Query(nativeQuery = true, name = Q_queryRegio)
   List<KeSzerv> queryRegioBy(@Param("felId") int felId);

   @Query(nativeQuery = true, name = Q_queryMegye)
   List<KeSzerv> queryMegyeBy(@Param("felId") int felId);
}