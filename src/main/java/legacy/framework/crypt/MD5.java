/*   1:    */ package legacy.framework.crypt;
/*   2:    */ 
/*   3:    */ import java.security.MessageDigest;
/*   4:    */ 
/*   5:    */ public class MD5
/*   6:    */   extends MessageDigest
/*   7:    */   implements Cloneable
/*   8:    */ {
/*   9: 47 */   private final int[] W = new int[16];
/*  10:    */   private long bytecount;
/*  11:    */   private int A;
/*  12:    */   private int B;
/*  13:    */   private int C;
/*  14:    */   private int D;
/*  15:    */   
/*  16:    */   public MD5()
/*  17:    */   {
/*  18: 55 */     super("MD5");
/*  19: 56 */     engineReset();
/*  20:    */   }
/*  21:    */   
/*  22:    */   public Object clone()
/*  23:    */   {
/*  24: 60 */     return new MD5(this);
/*  25:    */   }
/*  26:    */   
/*  27:    */   private MD5(MD5 copy)
/*  28:    */   {
/*  29: 64 */     this();
/*  30: 65 */     this.bytecount = copy.bytecount;
/*  31: 66 */     this.A = copy.A;
/*  32: 67 */     this.B = copy.B;
/*  33: 68 */     this.C = copy.C;
/*  34: 69 */     this.D = copy.D;
/*  35: 70 */     System.arraycopy(copy.W, 0, this.W, 0, 16);
/*  36:    */   }
/*  37:    */   
/*  38:    */   public int engineGetDigestLength()
/*  39:    */   {
/*  40: 74 */     return 20;
/*  41:    */   }
/*  42:    */   
/*  43:    */   public void engineReset()
/*  44:    */   {
/*  45: 79 */     this.bytecount = 0L;
/*  46: 80 */     this.A = 1732584193;
/*  47: 81 */     this.B = -271733879;
/*  48: 82 */     this.C = -1732584194;
/*  49: 83 */     this.D = 271733878;
/*  50: 84 */     for (int i = 0; i < 16; i++) {
/*  51: 85 */       this.W[i] = 0;
/*  52:    */     }
/*  53:    */   }
/*  54:    */   
/*  55:    */   public void engineUpdate(byte b)
/*  56:    */   {
/*  57: 89 */     int i = (int)this.bytecount % 64;
/*  58: 90 */     int shift = (3 - i % 4) * 8;
/*  59: 91 */     int idx = i / 4;
/*  60:    */     
/*  61:    */ 
/*  62: 94 */     this.W[idx] = (this.W[idx] & (255 << shift ^ 0xFFFFFFFF) | (b & 0xFF) << shift);
/*  63: 97 */     if (++this.bytecount % 64L == 0L) {
/*  64: 98 */       munch();
/*  65:    */     }
/*  66:    */   }
/*  67:    */   
/*  68:    */   public void engineUpdate(byte[] bytes, int off, int len)
/*  69:    */   {
/*  70:102 */     if (len < 0) {
/*  71:103 */       throw new ArrayIndexOutOfBoundsException();
/*  72:    */     }
/*  73:105 */     int end = off + len;
/*  74:106 */     while (off < end) {
/*  75:107 */       engineUpdate(bytes[(off++)]);
/*  76:    */     }
/*  77:    */   }
/*  78:    */   
/*  79:    */   public byte[] engineDigest()
/*  80:    */   {
/*  81:111 */     long bitcount = this.bytecount * 8L;
/*  82:112 */     engineUpdate((byte)Byte.MIN_VALUE);
/*  83:116 */     while ((int)this.bytecount % 64 != 56) {
/*  84:117 */       engineUpdate((byte)0);
/*  85:    */     }
/*  86:121 */     this.W[14] = SWAP((int)(0xFFFFFFFF & bitcount));
/*  87:122 */     this.W[15] = SWAP((int)(0xFFFFFFFF & bitcount >>> 32));
/*  88:123 */     this.bytecount += 8L;
/*  89:    */     
/*  90:    */ 
/*  91:126 */     munch();
/*  92:    */     
/*  93:128 */     this.A = SWAP(this.A);
/*  94:129 */     this.B = SWAP(this.B);
/*  95:130 */     this.C = SWAP(this.C);
/*  96:131 */     this.D = SWAP(this.D);
/*  97:132 */     byte[] result = { (byte)(this.A >>> 24), (byte)(this.A >>> 16), (byte)(this.A >>> 8), (byte)this.A, (byte)(this.B >>> 24), (byte)(this.B >>> 16), (byte)(this.B >>> 8), (byte)this.B, (byte)(this.C >>> 24), (byte)(this.C >>> 16), (byte)(this.C >>> 8), (byte)this.C, (byte)(this.D >>> 24), (byte)(this.D >>> 16), (byte)(this.D >>> 8), (byte)this.D };
/*  98:    */     
/*  99:    */ 
/* 100:    */ 
/* 101:    */ 
/* 102:    */ 
/* 103:    */ 
/* 104:    */ 
/* 105:    */ 
/* 106:141 */     engineReset();
/* 107:142 */     return result;
/* 108:    */   }
/* 109:    */   
/* 110:    */   private int F(int X, int Y, int Z)
/* 111:    */   {
/* 112:146 */     return X & Y | (X ^ 0xFFFFFFFF) & Z;
/* 113:    */   }
/* 114:    */   
/* 115:    */   private int G(int X, int Y, int Z)
/* 116:    */   {
/* 117:150 */     return X & Z | Y & (Z ^ 0xFFFFFFFF);
/* 118:    */   }
/* 119:    */   
/* 120:    */   private int H(int X, int Y, int Z)
/* 121:    */   {
/* 122:154 */     return X ^ Y ^ Z;
/* 123:    */   }
/* 124:    */   
/* 125:    */   private int I(int X, int Y, int Z)
/* 126:    */   {
/* 127:158 */     return Y ^ (X | Z ^ 0xFFFFFFFF);
/* 128:    */   }
/* 129:    */   
/* 130:    */   private int rotateLeft(int i, int count)
/* 131:    */   {
/* 132:163 */     return i << count | i >>> 32 - count;
/* 133:    */   }
/* 134:    */   
/* 135:    */   private int FF(int a, int b, int c, int d, int k, int s, int i)
/* 136:    */   {
/* 137:169 */     a += F(b, c, d) + k + i;
/* 138:170 */     return b + rotateLeft(a, s);
/* 139:    */   }
/* 140:    */   
/* 141:    */   private int GG(int a, int b, int c, int d, int k, int s, int i)
/* 142:    */   {
/* 143:175 */     a += G(b, c, d) + k + i;
/* 144:176 */     return b + rotateLeft(a, s);
/* 145:    */   }
/* 146:    */   
/* 147:    */   private int HH(int a, int b, int c, int d, int k, int s, int i)
/* 148:    */   {
/* 149:181 */     a += H(b, c, d) + k + i;
/* 150:182 */     return b + rotateLeft(a, s);
/* 151:    */   }
/* 152:    */   
/* 153:    */   private int II(int a, int b, int c, int d, int k, int s, int i)
/* 154:    */   {
/* 155:188 */     a += I(b, c, d) + k + i;
/* 156:189 */     return b + rotateLeft(a, s);
/* 157:    */   }
/* 158:    */   
/* 159:    */   private int SWAP(int n)
/* 160:    */   {
/* 161:194 */     return (0xFF & n) << 24 | (n & 0xFF00) << 8 | n >>> 8 & 0xFF00 | n >>> 24;
/* 162:    */   }
/* 163:    */   
/* 164:    */   private void munch()
/* 165:    */   {
/* 166:199 */     int[] X = new int[16];
/* 167:202 */     for (int j = 0; j < 16; j++) {
/* 168:203 */       X[j] = SWAP(this.W[j]);
/* 169:    */     }
/* 170:206 */     int AA = this.A;
/* 171:207 */     int BB = this.B;
/* 172:208 */     int CC = this.C;
/* 173:209 */     int DD = this.D;
/* 174:    */     
/* 175:    */ 
/* 176:    */ 
/* 177:    */ 
/* 178:    */ 
/* 179:    */ 
/* 180:    */ 
/* 181:217 */     this.A = FF(this.A, this.B, this.C, this.D, X[0], 7, -680876936);
/* 182:218 */     this.D = FF(this.D, this.A, this.B, this.C, X[1], 12, -389564586);
/* 183:219 */     this.C = FF(this.C, this.D, this.A, this.B, X[2], 17, 606105819);
/* 184:220 */     this.B = FF(this.B, this.C, this.D, this.A, X[3], 22, -1044525330);
/* 185:    */     
/* 186:222 */     this.A = FF(this.A, this.B, this.C, this.D, X[4], 7, -176418897);
/* 187:223 */     this.D = FF(this.D, this.A, this.B, this.C, X[5], 12, 1200080426);
/* 188:224 */     this.C = FF(this.C, this.D, this.A, this.B, X[6], 17, -1473231341);
/* 189:225 */     this.B = FF(this.B, this.C, this.D, this.A, X[7], 22, -45705983);
/* 190:    */     
/* 191:227 */     this.A = FF(this.A, this.B, this.C, this.D, X[8], 7, 1770035416);
/* 192:228 */     this.D = FF(this.D, this.A, this.B, this.C, X[9], 12, -1958414417);
/* 193:229 */     this.C = FF(this.C, this.D, this.A, this.B, X[10], 17, -42063);
/* 194:230 */     this.B = FF(this.B, this.C, this.D, this.A, X[11], 22, -1990404162);
/* 195:    */     
/* 196:232 */     this.A = FF(this.A, this.B, this.C, this.D, X[12], 7, 1804603682);
/* 197:233 */     this.D = FF(this.D, this.A, this.B, this.C, X[13], 12, -40341101);
/* 198:234 */     this.C = FF(this.C, this.D, this.A, this.B, X[14], 17, -1502002290);
/* 199:235 */     this.B = FF(this.B, this.C, this.D, this.A, X[15], 22, 1236535329);
/* 200:    */     
/* 201:    */ 
/* 202:    */ 
/* 203:    */ 
/* 204:    */ 
/* 205:241 */     this.A = GG(this.A, this.B, this.C, this.D, X[1], 5, -165796510);
/* 206:242 */     this.D = GG(this.D, this.A, this.B, this.C, X[6], 9, -1069501632);
/* 207:243 */     this.C = GG(this.C, this.D, this.A, this.B, X[11], 14, 643717713);
/* 208:244 */     this.B = GG(this.B, this.C, this.D, this.A, X[0], 20, -373897302);
/* 209:    */     
/* 210:246 */     this.A = GG(this.A, this.B, this.C, this.D, X[5], 5, -701558691);
/* 211:247 */     this.D = GG(this.D, this.A, this.B, this.C, X[10], 9, 38016083);
/* 212:248 */     this.C = GG(this.C, this.D, this.A, this.B, X[15], 14, -660478335);
/* 213:249 */     this.B = GG(this.B, this.C, this.D, this.A, X[4], 20, -405537848);
/* 214:    */     
/* 215:251 */     this.A = GG(this.A, this.B, this.C, this.D, X[9], 5, 568446438);
/* 216:252 */     this.D = GG(this.D, this.A, this.B, this.C, X[14], 9, -1019803690);
/* 217:253 */     this.C = GG(this.C, this.D, this.A, this.B, X[3], 14, -187363961);
/* 218:254 */     this.B = GG(this.B, this.C, this.D, this.A, X[8], 20, 1163531501);
/* 219:    */     
/* 220:256 */     this.A = GG(this.A, this.B, this.C, this.D, X[13], 5, -1444681467);
/* 221:257 */     this.D = GG(this.D, this.A, this.B, this.C, X[2], 9, -51403784);
/* 222:258 */     this.C = GG(this.C, this.D, this.A, this.B, X[7], 14, 1735328473);
/* 223:259 */     this.B = GG(this.B, this.C, this.D, this.A, X[12], 20, -1926607734);
/* 224:    */     
/* 225:    */ 
/* 226:    */ 
/* 227:    */ 
/* 228:    */ 
/* 229:265 */     this.A = HH(this.A, this.B, this.C, this.D, X[5], 4, -378558);
/* 230:266 */     this.D = HH(this.D, this.A, this.B, this.C, X[8], 11, -2022574463);
/* 231:267 */     this.C = HH(this.C, this.D, this.A, this.B, X[11], 16, 1839030562);
/* 232:268 */     this.B = HH(this.B, this.C, this.D, this.A, X[14], 23, -35309556);
/* 233:    */     
/* 234:270 */     this.A = HH(this.A, this.B, this.C, this.D, X[1], 4, -1530992060);
/* 235:271 */     this.D = HH(this.D, this.A, this.B, this.C, X[4], 11, 1272893353);
/* 236:272 */     this.C = HH(this.C, this.D, this.A, this.B, X[7], 16, -155497632);
/* 237:273 */     this.B = HH(this.B, this.C, this.D, this.A, X[10], 23, -1094730640);
/* 238:    */     
/* 239:275 */     this.A = HH(this.A, this.B, this.C, this.D, X[13], 4, 681279174);
/* 240:276 */     this.D = HH(this.D, this.A, this.B, this.C, X[0], 11, -358537222);
/* 241:277 */     this.C = HH(this.C, this.D, this.A, this.B, X[3], 16, -722521979);
/* 242:278 */     this.B = HH(this.B, this.C, this.D, this.A, X[6], 23, 76029189);
/* 243:    */     
/* 244:280 */     this.A = HH(this.A, this.B, this.C, this.D, X[9], 4, -640364487);
/* 245:281 */     this.D = HH(this.D, this.A, this.B, this.C, X[12], 11, -421815835);
/* 246:282 */     this.C = HH(this.C, this.D, this.A, this.B, X[15], 16, 530742520);
/* 247:283 */     this.B = HH(this.B, this.C, this.D, this.A, X[2], 23, -995338651);
/* 248:    */     
/* 249:    */ 
/* 250:    */ 
/* 251:    */ 
/* 252:    */ 
/* 253:289 */     this.A = II(this.A, this.B, this.C, this.D, X[0], 6, -198630844);
/* 254:290 */     this.D = II(this.D, this.A, this.B, this.C, X[7], 10, 1126891415);
/* 255:291 */     this.C = II(this.C, this.D, this.A, this.B, X[14], 15, -1416354905);
/* 256:292 */     this.B = II(this.B, this.C, this.D, this.A, X[5], 21, -57434055);
/* 257:    */     
/* 258:294 */     this.A = II(this.A, this.B, this.C, this.D, X[12], 6, 1700485571);
/* 259:295 */     this.D = II(this.D, this.A, this.B, this.C, X[3], 10, -1894986606);
/* 260:296 */     this.C = II(this.C, this.D, this.A, this.B, X[10], 15, -1051523);
/* 261:297 */     this.B = II(this.B, this.C, this.D, this.A, X[1], 21, -2054922799);
/* 262:    */     
/* 263:299 */     this.A = II(this.A, this.B, this.C, this.D, X[8], 6, 1873313359);
/* 264:300 */     this.D = II(this.D, this.A, this.B, this.C, X[15], 10, -30611744);
/* 265:301 */     this.C = II(this.C, this.D, this.A, this.B, X[6], 15, -1560198380);
/* 266:302 */     this.B = II(this.B, this.C, this.D, this.A, X[13], 21, 1309151649);
/* 267:    */     
/* 268:304 */     this.A = II(this.A, this.B, this.C, this.D, X[4], 6, -145523070);
/* 269:305 */     this.D = II(this.D, this.A, this.B, this.C, X[11], 10, -1120210379);
/* 270:306 */     this.C = II(this.C, this.D, this.A, this.B, X[2], 15, 718787259);
/* 271:307 */     this.B = II(this.B, this.C, this.D, this.A, X[9], 21, -343485551);
/* 272:    */     
/* 273:    */ 
/* 274:    */ 
/* 275:    */ 
/* 276:312 */     this.A += AA;
/* 277:313 */     this.B += BB;
/* 278:314 */     this.C += CC;
/* 279:315 */     this.D += DD;
/* 280:    */   }
/* 281:    */ }


/* Location:           F:\VEN\trunk\tomcat_lib\idom-tomcat.jar
 * Qualified Name:     hu.idom.struts.MD5
 * JD-Core Version:    0.7.1
 */