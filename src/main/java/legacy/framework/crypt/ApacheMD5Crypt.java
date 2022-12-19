/*   1:    */ package legacy.framework.crypt;
/*   2:    */ 
/*   3:    */ import java.util.Random;
/*   4:    */ 
/*   5:    */ public final class ApacheMD5Crypt
/*   6:    */ {
/*   7:    */   //private static final String SALTCHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
/*   8:    */   //private static final String itoa64 = "./0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
/*   9:    */   
/*  10:    */   private static final String to64(long v, int size)
/*  11:    */   {
/*  12: 80 */     StringBuffer result = new StringBuffer();
/*  13:    */     for (;;)
/*  14:    */     {
/*  15: 81 */       size--;
/*  16: 81 */       if (size < 0) {
/*  17:    */         break;
/*  18:    */       }
/*  19: 82 */       result.append("./0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".charAt((int)(v & 0x3F)));
/*  20: 83 */       v >>>= 6;
/*  21:    */     }
/*  22: 85 */     return result.toString();
/*  23:    */   }
/*  24:    */   
/*  25:    */   private static final void clearbits(byte[] bits)
/*  26:    */   {
/*  27: 90 */     for (int i = 0; i < bits.length; i++) {
/*  28: 91 */       bits[i] = 0;
/*  29:    */     }
/*  30:    */   }
/*  31:    */   
/*  32:    */   private static final int bytes2u(byte inp)
/*  33:    */   {
/*  34: 99 */     return inp & 0xFF;
/*  35:    */   }
/*  36:    */   
/*  37:    */   public static final String crypt(String password)
/*  38:    */   {
/*  39:110 */     StringBuffer salt = new StringBuffer();
/*  40:111 */     Random randgen = new Random();
/*  41:113 */     while (salt.length() < 8)
/*  42:    */     {
/*  43:114 */       int index = (int)(randgen.nextFloat() * "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".length());
/*  44:115 */       salt.append("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".substring(index, index + 1));
/*  45:    */     }
/*  46:117 */     return crypt(password, salt.toString());
/*  47:    */   }
/*  48:    */   
/*  49:    */   public static final String crypt(String password, String salt)
/*  50:    */   {
/*  51:133 */     String magic = "$apr1$";
/*  52:141 */     if (salt.startsWith(magic)) {
/*  53:142 */       salt = salt.substring(magic.length());
/*  54:    */     }
/*  55:145 */     if (salt.indexOf('$') != -1) {
/*  56:146 */       salt = salt.substring(0, salt.indexOf('$'));
/*  57:    */     }
/*  58:148 */     if (salt.length() > 8) {
/*  59:149 */       salt = salt.substring(0, 8);
/*  60:    */     }
/*  61:151 */     MD5 ctx = new MD5();
/*  62:152 */     ctx.update(password.getBytes());
/*  63:153 */     ctx.update(magic.getBytes());
/*  64:154 */     ctx.update(salt.getBytes());
/*  65:    */     
/*  66:156 */     MD5 ctx1 = new MD5();
/*  67:157 */     ctx1.update(password.getBytes());
/*  68:158 */     ctx1.update(salt.getBytes());
/*  69:159 */     ctx1.update(password.getBytes());
/*  70:160 */     byte[] finalState = ctx1.digest();
/*  71:162 */     for (int pl = password.length(); pl > 0; pl -= 16) {
/*  72:163 */       for (int i = 0; i < (pl > 16 ? 16 : pl); i++) {
/*  73:164 */         ctx.update(finalState[i]);
/*  74:    */       }
/*  75:    */     }
/*  76:171 */     clearbits(finalState);
/*  77:174 */     for (int i = password.length(); i != 0; i >>>= 1) {
/*  78:175 */       if ((i & 0x1) != 0) {
/*  79:176 */         ctx.update(finalState[0]);
/*  80:    */       } else {
/*  81:178 */         ctx.update(password.getBytes()[0]);
/*  82:    */       }
/*  83:    */     }
/*  84:181 */     finalState = ctx.digest();
/*  85:188 */     for (int i = 0; i < 1000; i++)
/*  86:    */     {
/*  87:189 */       ctx1 = new MD5();
/*  88:191 */       if ((i & 0x1) != 0) {
/*  89:192 */         ctx1.update(password.getBytes());
/*  90:    */       } else {
/*  91:194 */         for (int c = 0; c < 16; c++) {
/*  92:195 */           ctx1.update(finalState[c]);
/*  93:    */         }
/*  94:    */       }
/*  95:198 */       if (i % 3 != 0) {
/*  96:199 */         ctx1.update(salt.getBytes());
/*  97:    */       }
/*  98:202 */       if (i % 7 != 0) {
/*  99:203 */         ctx1.update(password.getBytes());
/* 100:    */       }
/* 101:206 */       if ((i & 0x1) != 0) {
/* 102:207 */         for (int c = 0; c < 16; c++) {
/* 103:208 */           ctx1.update(finalState[c]);
/* 104:    */         }
/* 105:    */       } else {
/* 106:210 */         ctx1.update(password.getBytes());
/* 107:    */       }
/* 108:213 */       finalState = ctx1.digest();
/* 109:    */     }
/* 110:218 */     StringBuffer result = new StringBuffer();
/* 111:    */     
/* 112:220 */     result.append(magic);
/* 113:221 */     result.append(salt);
/* 114:222 */     result.append("$");
/* 115:    */     
/* 116:224 */     long l = bytes2u(finalState[0]) << 16 | bytes2u(finalState[6]) << 8 | bytes2u(finalState[12]);
/* 117:225 */     result.append(to64(l, 4));
/* 118:    */     
/* 119:227 */     l = bytes2u(finalState[1]) << 16 | bytes2u(finalState[7]) << 8 | bytes2u(finalState[13]);
/* 120:228 */     result.append(to64(l, 4));
/* 121:    */     
/* 122:230 */     l = bytes2u(finalState[2]) << 16 | bytes2u(finalState[8]) << 8 | bytes2u(finalState[14]);
/* 123:231 */     result.append(to64(l, 4));
/* 124:    */     
/* 125:233 */     l = bytes2u(finalState[3]) << 16 | bytes2u(finalState[9]) << 8 | bytes2u(finalState[15]);
/* 126:234 */     result.append(to64(l, 4));
/* 127:    */     
/* 128:236 */     l = bytes2u(finalState[4]) << 16 | bytes2u(finalState[10]) << 8 | bytes2u(finalState[5]);
/* 129:237 */     result.append(to64(l, 4));
/* 130:    */     
/* 131:239 */     l = bytes2u(finalState[11]);
/* 132:240 */     result.append(to64(l, 2));
/* 133:    */     
/* 134:    */ 
/* 135:243 */     clearbits(finalState);
/* 136:244 */     return result.toString();
/* 137:    */   }
/* 138:    */ }


/* Location:           F:\VEN\trunk\tomcat_lib\idom-tomcat.jar
 * Qualified Name:     hu.idom.struts.ApacheMD5Crypt
 * JD-Core Version:    0.7.1
 */