package ven3.models;

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

   public String getJog() {
      return jog;
   }

   public void setJog(String jog) {
      this.jog = jog;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getUrl() {
      return url;
   }

   public void setUrl(String url) {
      this.url = url;
   }

   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getPid() {
      return pid;
   }

   public void setPid(String pid) {
      this.pid = pid;
   }

   @Override
   public String toString() {
      return "MenuItem [jog=" + jog + ", id=" + id + ", pid=" + pid + ", name=" + name + ", url=" + url + "]";
   }
}
