package legacy.framework.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MenuItem {
   private String jog;
   private String id;
   private String pid;
   private String name;
   private String url;

   public MenuItem() {
   }

   public MenuItem(String jog, String id, String pid, String name, String url) {
      super();
      this.jog = jog;
      this.id = id;
      this.pid = pid;
      this.name = name;
      this.url = url;
   }

   public Item getItem() {
      return new Item(name, url);
   }

   @Override
   public String toString() {
      return "MenuItem [jog=" + jog + ", id=" + id + ", pid=" + pid + ", name=" + name + ", url=" + url + "]";
   }
}
