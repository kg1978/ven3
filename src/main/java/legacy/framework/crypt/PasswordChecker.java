/*  1:   */
package legacy.framework.crypt;

/*  2:   */
import cryptix.tools.UnixCrypt;

/*  5:   */
/*  6:   */ public class PasswordChecker
/* 7: */ {
   /* 8: */ public static boolean matchesIBM13(String encrypted, String pwd)
   /* 9: */ {
      /* 10:17 */ String salt = encrypted.substring(0, 2);
      /* 11:18 */ UnixCrypt uc = new UnixCrypt(salt);
      /* 12:19 */ String newEncrypted = uc.crypt(pwd);
      /* 13:20 */ return newEncrypted.equals(encrypted);
      /* 14: */ }

   /* 15: */
   /* 16: */ public static boolean matchesApache2(String encrypted, String pwd)
   /* 17: */ {
      /* 18:30 */ boolean retVal = false;
      /* 19:31 */ if ((encrypted != null) && (encrypted.length() > 14))
      /* 20: */ {
         /* 21:32 */ String salt = encrypted.substring(6, 14);
         /* 22:33 */ String newEncrypted = ApacheMD5Crypt.crypt(pwd, salt);
         /* 23:34 */ retVal = newEncrypted.equals(encrypted);
         /* 24: */ }
      /* 25:36 */ return retVal;
      /* 26: */ }

   /* 27: */
   /* 28: */ public static void main(String[] args)
   /* 29: */ {
      String pwd = "12345";
      String encrypted = ApacheMD5Crypt.crypt(pwd);

      System.out.println("encrypted1:" + encrypted);

      if (matchesApache2(encrypted, pwd)) {
         System.out.println("OK:" + encrypted);
      } else {
         System.out.println("FAILD");
      }

      /*
       * if (matchesApache2("I8JZxVn0wewCQ/053wlZj", "idomtest")) {
       * System.out.println("OK:" + encrypted); } else { System.out.println("FAILD");
       * }
       */

      UnixCrypt uc = new UnixCrypt("");
      encrypted = uc.crypt(pwd);
      System.out.println("encrypted2:" + encrypted);

      if (matchesIBM13(encrypted, pwd)) {
         System.out.println("OK2:" + encrypted);
      } else {
         System.out.println("FAILD2");
      }

      /* 30:40 */ // boolean x = matchesIBM13("SrBIonONLS8MQ", "helloleo");
      /* 31:41 */ // System.out.println("x: " + x);
      /* 32:42 */ // x = matchesApache2("$apr1$0e4.....$jPJAoaHYDvmPde78cG9Wq1", "x");
      /* 33:43 */ // System.out.println("x: " + x);
      /* 34: */ }
   /* 35: */ }
