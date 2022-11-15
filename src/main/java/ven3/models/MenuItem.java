package ven3.models;

public class MenuItem {
   private int jog;
   private int id;
   private int pid;
   private String name;
   private String url;

   public MenuItem() {
   }

   public MenuItem(int jog, int id, int pid, String name, String url) {
      super();
      this.jog = jog;
      this.id = id;
      this.pid = pid;
      this.name = name;
      this.url = url;
   }

   public int getJog() {
      return jog;
   }

   public void setJog(int jog) {
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

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public int getPid() {
      return pid;
   }

   public void setPid(int pid) {
      this.pid = pid;
   }

   @Override
   public String toString() {
      return "MenuItem [jog=" + jog + ", id=" + id + ", pid=" + pid + ", name=" + name + ", url=" + url + "]";
   }
}
