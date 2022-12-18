package legacy.mock.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import legacy.framework.utils.Util;
import legacy.mock.servises.ke.KeOIMockService;
import legacy.mock.servises.ke.KeSzervMockService;
import legacy.mock.servises.ke.KeSzervSzervMockService;
import legacy.ven3.models.db.keret.KeOI;
import legacy.ven3.models.db.keret.KeSzerv;
import legacy.ven3.models.db.keret.KeSzervSzerv;

@Component
public class LoadJson2Db {
   private static Logger LOG = LoggerFactory.getLogger(LoadJson2Db.class);

   private static String KE_PREFIX = "db/ke/";
   @Autowired
   Util util;

   @Autowired
   KeOIMockService keOIMockService;

   @Autowired
   KeSzervMockService keSzervMockService;

   @Autowired
   KeSzervSzervMockService keSzervSzervMockService;

   public void init() {
      KeOI keIO = util.json2Java(KE_PREFIX + "keoi_1.json", KeOI.class);
      keOIMockService.saveOrUpdate(keIO);
      LOG.debug("loaded:" + keIO);

      KeSzerv keSzerv = util.json2Java(KE_PREFIX + "keszerv_1.json", KeSzerv.class);
      keSzervMockService.saveOrUpdate(keSzerv);
      LOG.debug("loaded:" + keSzerv);

      keSzerv = util.json2Java(KE_PREFIX + "keszerv_2.json", KeSzerv.class);
      keSzervMockService.saveOrUpdate(keSzerv);
      LOG.debug("loaded:" + keSzerv);

      KeSzervSzerv keSzervSzerv = util.json2Java(KE_PREFIX + "keszervszerv_1.json", KeSzervSzerv.class);
      keSzervSzervMockService.saveOrUpdate(keSzervSzerv);
      LOG.debug("loaded:" + keSzervSzerv);
   }
}
