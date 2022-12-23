package legacy.ven3.models;

import java.util.Arrays;
import java.util.List;

import legacy.framework.models.Item;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OkmanyFelhasznalo {
   private String userCode;
   private String userAzon;
   private String userName;
   private int[] jogok;
   private OI aktOI;
   private String szigszkod;
   private int szervtip;
   private List<OI> enabledOI;
   private List<Item> menu;

   @Override
   public String toString() {
      return "OkmanyFelhasznalo [userCode=" + userCode + ", userAzon=" + userAzon + ", userName=" + userName
            + ", jogok=" + Arrays.toString(jogok) + ", aktOI=" + aktOI + ", szigszkod=" + szigszkod + ", szervtip="
            + szervtip + ", enabledOI=" + enabledOI + "]";
   }
}
