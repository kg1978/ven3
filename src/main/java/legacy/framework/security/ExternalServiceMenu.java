package legacy.framework.security;

import java.util.List;

import legacy.framework.models.Item;

public interface ExternalServiceMenu {
   public List<Item> get(String sid);
}
