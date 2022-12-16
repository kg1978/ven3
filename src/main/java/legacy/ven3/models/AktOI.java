package legacy.ven3.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AktOI {
   private String key;
   private String value;
   private Object data;

   @Override
   public String toString() {
      return "AktOI [key=" + key + ", value=" + value + ", data=" + data + "]";
   }
}
