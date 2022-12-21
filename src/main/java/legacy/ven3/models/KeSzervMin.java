package legacy.ven3.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class KeSzervMin {
   public String szkod;
   public String mbkod;
   public String nev;

   @Override
   public String toString() {
      return "KeSzervMin [szkod=" + szkod + ", mbkod=" + mbkod + ", nev=" + nev + "]";
   }
}
