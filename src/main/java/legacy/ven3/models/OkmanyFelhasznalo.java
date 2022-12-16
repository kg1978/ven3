package legacy.ven3.models;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OkmanyFelhasznalo {
   private String userCode;
   private String userAzon;
   private String userName;
   private int[] jogok;
   private AktOI aktOI;
   private String szigszkod;
   private int szervtip;
   private List<AktOI> enabledOI;

   @Override
   public String toString() {
      return "OkmanyFelhasznalo [userCode=" + userCode + ", userAzon=" + userAzon + ", userName=" + userName
            + ", jogok=" + Arrays.toString(jogok) + ", aktOI=" + aktOI + ", szigszkod=" + szigszkod + ", szervtip="
            + szervtip + ", enabledOI=" + enabledOI + "]";
   }
}
