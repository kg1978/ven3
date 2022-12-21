package legacy.ven3.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OI {
   private String key;
   private String value;
   private Object data;

   public OI() {
   }

   public OI(String key, String value, Object data) {
      super();
      this.key = key;
      this.value = value;
      this.data = data;
   }

   @Override
   public String toString() {
      return "AktOI [key=" + key + ", value=" + value + ", data=" + data + "]";
   }
}
