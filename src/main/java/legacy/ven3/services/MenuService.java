package legacy.ven3.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import legacy.framework.models.Item;
import legacy.framework.security.ExternalServiceMenu;

@Service
@Component
public class MenuService implements ExternalServiceMenu {

   @Value("${http.services.menu}")
   private boolean useHttp;

   @Override
   public List<Item> getMenu(String sid) {
      if (useHttp) {
         return null;
      } else {

      }
      return null;
   }
}
