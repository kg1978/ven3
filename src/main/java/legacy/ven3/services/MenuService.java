package legacy.ven3.services;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import legacy.framework.models.Item;
import legacy.framework.security.ExternalServiceMenu;

@Service
@Component
public class MenuService implements ExternalServiceMenu {

   @Override
   public List<Item> get(String sid) {
      return null;
   }
}
