package ven3.framework.models;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Item {
   private String title;
   private String url;
   private List<Item> submenu = new ArrayList<>();

   public Item() {
   }

   public Item(String title, String url) {
      super();
      this.title = title;
      this.url = url;
   }

   public void addItem(Item item) {
      submenu.add(item);
   }

   @Override
   public String toString() {
      return "Item [title=" + title + ", url=" + url + ", submenu=" + submenu + "]";
   }

}
