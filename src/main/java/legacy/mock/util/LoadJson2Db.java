package legacy.mock.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import legacy.framework.utils.Util;
import legacy.mock.servises.ke.KeOIMockService;
import legacy.ven3.models.db.keret.KeOI;

@Component
public class LoadJson2Db {
   private static Logger LOG = LoggerFactory.getLogger(LoadJson2Db.class);

   private static String KE_PREFIX = "db/ke/";
   @Autowired
   Util util;

   @Autowired
   KeOIMockService keOIMockService;

   public void init() {
      KeOI keoi = util.json2Java(KE_PREFIX + "keoi_1.json", KeOI.class);
      keOIMockService.saveOrUpdate(keoi);
      LOG.debug("loaded:" + keoi);
   }
}
