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
import legacy.ven3.models.db.jo.JoEngcsop;
import legacy.ven3.models.db.jo.JoJogcsop;
import legacy.ven3.models.db.ke.KeOI;
import legacy.ven3.models.db.ke.KeSzerv;
import legacy.ven3.models.db.ke.KeSzervSzerv;
import legacy.ven3.servises.db.auth.UnPwAuthService;
import legacy.ven3.servises.db.jo.JoAufelhService;
import legacy.ven3.servises.db.jo.JoEngcsopService;
import legacy.ven3.servises.db.jo.JoJogcsopService;
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
   JoAufelhService joAufelhService;

   @Autowired
   JoEngcsopService joEngcsopService;

   @Autowired
   JoJogcsopService joJogcsopService;

   @Autowired
   KeOIService keOIService;

   @Autowired
   KeSzervService keSzervService;

   @Autowired
   KeSzervSzervService keSzervSzervService;

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
      joAufelhService.saveAll(JoAufelh);
      LOG.debug("loaded:" + JoAufelh);

      List<JoEngcsop> JoEngcsop = objectMapper.readValue(util.getFile(JO_PREFIX + "joengcsop.json"),
            new TypeReference<List<JoEngcsop>>() {
            });
      joEngcsopService.saveAll(JoEngcsop);
      LOG.debug("loaded:" + JoEngcsop);

      List<JoJogcsop> JoJogcsop = objectMapper.readValue(util.getFile(JO_PREFIX + "jojogcsop.json"),
            new TypeReference<List<legacy.ven3.models.db.jo.JoJogcsop>>() {
            });
      joJogcsopService.saveAll(JoJogcsop);
      LOG.debug("loaded:" + JoJogcsop);
   }

   public void initKe() throws StreamReadException, DatabindException, IOException {
      ObjectMapper objectMapper = new ObjectMapper();

      List<KeOI> KeOI = objectMapper.readValue(util.getFile(KE_PREFIX + "keoi.json"), new TypeReference<List<KeOI>>() {
      });
      keOIService.saveAll(KeOI);
      LOG.debug("loaded:" + KeOI);

      List<KeSzerv> KeSzerv = objectMapper.readValue(util.getFile(KE_PREFIX + "keszerv.json"),
            new TypeReference<List<KeSzerv>>() {
            });
      keSzervService.saveAll(KeSzerv);
      LOG.debug("loaded:" + KeSzerv);

      List<KeSzervSzerv> KeSzervSzerv = objectMapper.readValue(util.getFile(KE_PREFIX + "keszervszerv.json"),
            new TypeReference<List<KeSzervSzerv>>() {
            });
      keSzervSzervService.saveAll(KeSzervSzerv);
      LOG.debug("loaded:" + KeSzervSzerv);
   }
}
