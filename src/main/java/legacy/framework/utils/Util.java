package legacy.framework.utils;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class Util {
   private static Logger LOG = LoggerFactory.getLogger(Util.class);

   @Autowired
   ResourceLoader resourceLoader;

   public <T> T json2Java(String fileName, Class<T> classType) {
      T t = null;
      try {
         File file = resourceLoader.getResource("classpath:" + fileName).getFile();
         ObjectMapper mapper = new ObjectMapper();
         mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
         t = mapper.readValue(file, classType);
      } catch (Exception e) {
         LOG.error(e.getMessage(), e);
      }

      return t;
   }
}
