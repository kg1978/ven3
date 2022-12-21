package legacy.ven3.repository.ke;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import legacy.ven3.models.KeSzervMin;

@Repository
public interface KeSzervMinRepository extends JpaRepository<KeSzervMin, String> {

   @Query(value = "select sze_szkod, sze_nev, sze_mbkod from jo_aufelh, ke_szerv "
         + "where fel_id = :felId and sze_tipus = '3' order by sze_nev", nativeQuery = true)
   List<KeSzervMin> querySzervTip5By(@Param("felId") int felId);

   @Query(value = "select b.sze_szkod, b.sze_nev, b.sze_mbkod "
         + "from jo_aufelh, ke_szerv a, ke_szerv b, ke_szervszerv " 
         + "where fel_id = :felId and sz_kapcstip = '02' "
         + "and sz_fszkod = fel_szkod and a.sze_szkod = sz_szkod "
         + "and b.sze_mbkod = a.sze_mbkod and b.sze_tipus = '3' order by sze_nev", nativeQuery = true)
   List<KeSzervMin> querySzervTip7By(@Param("felId") int felId);

   @Query(value = "select b.sze_szkod, b.sze_nev, b.sze_mbkod " 
         + "from jo_aufelh, ke_szerv a, ke_szerv b "
         + "where fel_id = :felId and a.sze_szkod = fel_szkod "
         + "and b.sze_mbkod = a.sze_mbkod and b.sze_tipus = '3' order by sze_nev", nativeQuery = true)
   List<KeSzervMin> querySzervTip4By(@Param("felId") int felId);

   @Query(value = "select d.sze_szkod, d.sze_nev, d.sze_mbkod "
         + "from jo_aufelh, ke_szerv a, ke_szerv b, ke_szerv c, ke_szerv d, "
         + "      ke_szervszerv e, ke_szervszerv f "
         + "where fel_id = :felId and fel_szkod = a.sze_szkod and a.sze_mbkod = b.sze_mbkod "
         + "and b.sze_tipus = '4' and e.sz_kapcstip = '02' and e.sz_szkod = b.sze_szkod "
         + "and e.sz_fszkod = f.sz_fszkod and f.sz_kapcstip = '02' and c.sze_szkod = f.sz_szkod "
         + "and d.sze_mbkod = c.sze_mbkod and d.sze_tipus = '3' order by d.sze_nev", nativeQuery = true)
   List<KeSzervMin> queryRegioBy(@Param("felId") int felId);

   @Query(value = "select d.sze_szkod, d.sze_nev, d.sze_mbkod " 
         + "from jo_aufelh, ke_szerv a, ke_szerv b, ke_szerv d "
         + "where fel_id = :felId and fel_szkod = a.sze_szkod " 
         + "and a.sze_mbkod = b.sze_mbkod and b.sze_tipus = '4' "
         + "and d.sze_mbkod = b.sze_mbkod and d.sze_tipus = '3' order by d.sze_nev", nativeQuery = true)
   List<KeSzervMin> queryMegyeBy(@Param("felId") int felId);
}