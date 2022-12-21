package legacy.ven3.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import legacy.Application;
import legacy.framework.models.MenuItem;
import legacy.framework.utils.Util;
import legacy.ven3.models.OI;
import legacy.ven3.models.OkmanyFelhasznalo;
import legacy.ven3.models.SzervRec;
import legacy.ven3.models.db.jo.JoAufelh;
import legacy.ven3.models.db.jo.JoEngcsop;
import legacy.ven3.models.db.jo.JoJogcsop;
import legacy.ven3.models.db.ke.KeOI;
import legacy.ven3.models.db.ke.KeSzerv;
import legacy.ven3.servises.db.jo.JoAufelhService;
import legacy.ven3.servises.db.jo.JoEngcsopService;
import legacy.ven3.servises.db.jo.JoJogcsopService;
import legacy.ven3.servises.db.ke.KeOIService;
import legacy.ven3.servises.db.ke.KeSzervService;

@Service
public class JogService {

   private static Logger LOG = LoggerFactory.getLogger(JogService.class);

   @Value("${http.services.jog}")
   private boolean useHttp;

   @Autowired
   Util util;

   @Autowired
   JoAufelhService joAufelhService;

   @Autowired
   KeSzervService keSzervService;

   @Autowired
   KeOIService keOIService;

   @Autowired
   JoEngcsopService joEngcsopService;

   @Autowired
   JoJogcsopService joJogcsopService;

   public OkmanyFelhasznalo init(String sid, String username) {
      LOG.debug(username + ":" + sid);

      OkmanyFelhasznalo okmanyFelhasznalo = new OkmanyFelhasznalo();
      if (useHttp) {

      } else {
         // Felhasználó adatai
         JoAufelh joAufelh = joAufelhService.getByFelhazon(username);
         LOG.debug(joAufelh.toString());

         // Felhasználó szervezetének adatai
         KeSzerv keSzerv = keSzervService.getById(joAufelh.szkod);
         LOG.debug(keSzerv.toString());

         // Engedély csoportok
         JoEngcsop joEngcsop = joEngcsopService.getByFelhid(joAufelh.id);
         LOG.debug(joEngcsop.toString());

         // Jogok
         List<JoJogcsop> jogList = joJogcsopService.getByCsopkod(joEngcsop.csopkod);
         LOG.debug(jogList.toString());

         OI aktOI = new OI(keSzerv.szkod, keSzerv.nev,
               new SzervRec(keSzerv.szkod, keSzerv.nev, keSzerv.mbkod, keSzerv.tipus, keSzerv.iszkod, ""));

         int[] jogok = util.getJogok(jogList);
         boolean megyeJog = IntStream.of(jogok).anyMatch(x -> x == 21);
         boolean kozpontiJog = IntStream.of(jogok).anyMatch(x -> x == 22);
         boolean regioJog = IntStream.of(jogok).anyMatch(x -> x == 80);

         okmanyFelhasznalo.setUserCode(Integer.toString(joAufelh.id));
         okmanyFelhasznalo.setAktOI(aktOI);
         okmanyFelhasznalo.setUserName(username);
         okmanyFelhasznalo.setSzervtip(keSzerv.tipus);
         okmanyFelhasznalo.setSzigszkod("");
         okmanyFelhasznalo.setJogok(jogok);

         int szervTip = keSzerv.tipus;
         if (szervTip == 3 || szervTip == 4 || szervTip == 5 || szervTip == 7) {

            // Aktuális OI
            KeOI keoi = keOIService.getById(keSzerv.szkod);
            okmanyFelhasznalo.setSzigszkod(keoi.szigszkod);

            // Enabled OI
            if (!megyeJog && !regioJog && !kozpontiJog) {
               List<OI> l = new ArrayList<OI>();
               okmanyFelhasznalo.setEnabledOI(l);
            } else {
               if (szervTip == 5) {
                  okmanyFelhasznalo.setEnabledOI(util.getOImin(keSzervService.querySzervTip5By(joAufelh.id)));
               } else if (szervTip == 7) {
                  okmanyFelhasznalo.setEnabledOI(util.getOImin(keSzervService.querySzervTip7By(joAufelh.id)));
               } else if (szervTip == 4) {
                  okmanyFelhasznalo.setEnabledOI(util.getOImin(keSzervService.querySzervTip4By(joAufelh.id)));
               } else if (szervTip == 3) {
                  if (kozpontiJog)
                     okmanyFelhasznalo.setEnabledOI(util.getOI(keSzervService.getByTipus(3)));
                  else if (regioJog && !kozpontiJog)
                     okmanyFelhasznalo.setEnabledOI(util.getOImin(keSzervService.queryRegioBy(joAufelh.id)));
                  else if (megyeJog)
                     okmanyFelhasznalo.setEnabledOI(util.getOImin(keSzervService.queryMegyeBy(joAufelh.id)));
               }
            }
         }
      }

      List<MenuItem> list = util.getMenuItemsByJogkod(Application.listMenuItem, okmanyFelhasznalo.getJogok());
      Collections.sort(list, (o1, o2) -> o1.getId().compareTo(o2.getId()));
      okmanyFelhasznalo.setMenu(util.getOptimalMenuItems(list));

      return okmanyFelhasznalo;
   }
}
