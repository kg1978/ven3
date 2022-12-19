package legacy.mock.util;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import legacy.framework.utils.Util;
import legacy.ven3.models.db.auth.UnPwAuth;
import legacy.ven3.models.db.jo.JoAufelh;
import legacy.ven3.models.db.ke.KeOI;
import legacy.ven3.models.db.ke.KeSzerv;
import legacy.ven3.models.db.ke.KeSzervSzerv;
import legacy.ven3.servises.db.auth.UnPwAuthService;
import legacy.ven3.servises.db.jo.JoAufelhService;
import legacy.ven3.servises.db.ke.KeOIService;
import legacy.ven3.servises.db.ke.KeSzervService;
import legacy.ven3.servises.db.ke.KeSzervSzervService;

@Component
public class LoadJson2Db {
   private static Logger LOG = LoggerFactory.getLogger(LoadJson2Db.class);

   private static String AUTH_PREFIX = "db/auth/";
   private static String JO_PREFIX = "db/jo/";
   private static String KE_PREFIX = "db/ke/";

   @Autowired
   Util util;

   @Autowired
   UnPwAuthService unPwAuthService;

   @Autowired
   JoAufelhService joAufelhMockService;

   @Autowired
   KeOIService keOIMockService;

   @Autowired
   KeSzervService keSzervMockService;

   @Autowired
   KeSzervSzervService keSzervSzervMockService;

   public void initAuth() throws StreamReadException, DatabindException, IOException {
      ObjectMapper objectMapper = new ObjectMapper();

      List<UnPwAuth> UnPwAuth = objectMapper.readValue(util.getFile(AUTH_PREFIX + "pw.json"),
            new TypeReference<List<UnPwAuth>>() {
            });
      unPwAuthService.saveAll(UnPwAuth);
      LOG.debug("loaded:" + UnPwAuth);
   }

   public void initJo() throws StreamReadException, DatabindException, IOException {
      ObjectMapper objectMapper = new ObjectMapper();

      List<JoAufelh> JoAufelh = objectMapper.readValue(util.getFile(JO_PREFIX + "joaufelh.json"),
            new TypeReference<List<JoAufelh>>() {
            });
      joAufelhMockService.saveAll(JoAufelh);
      LOG.debug("loaded:" + JoAufelh);
   }

   public void initKe() throws StreamReadException, DatabindException, IOException {
      ObjectMapper objectMapper = new ObjectMapper();

      List<KeOI> KeOI = objectMapper.readValue(util.getFile(KE_PREFIX + "keoi.json"), new TypeReference<List<KeOI>>() {
      });
      keOIMockService.saveAll(KeOI);
      LOG.debug("loaded:" + KeOI);

      List<KeSzerv> KeSzerv = objectMapper.readValue(util.getFile(KE_PREFIX + "keszerv.json"),
            new TypeReference<List<KeSzerv>>() {
            });
      keSzervMockService.saveAll(KeSzerv);
      LOG.debug("loaded:" + KeSzerv);

      List<KeSzervSzerv> KeSzervSzerv = objectMapper.readValue(util.getFile(KE_PREFIX + "keszervszerv.json"),
            new TypeReference<List<KeSzervSzerv>>() {
            });
      keSzervSzervMockService.saveAll(KeSzervSzerv);
      LOG.debug("loaded:" + KeSzervSzerv);
   }
}
