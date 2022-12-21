package legacy.ven3.models;

public class SzervRec {
   public String kod;
   public String nev;
   public String mbkod;
   public int tipus;
   public String iszkod;
   public String iszkodnev;

   public SzervRec() {
   }

   public SzervRec(String kod, String nev, String mbkod, int tipus, String iszkod, String iszkodnev) {
      super();
      this.kod = kod;
      this.nev = nev;
      this.mbkod = mbkod;
      this.tipus = tipus;
      this.iszkod = iszkod;
      this.iszkodnev = iszkodnev;
   }

   @Override
   public String toString() {
      return "SzervRec [kod=" + kod + ", nev=" + nev + ", mbkod=" + mbkod + ", tipus=" + tipus + ", iszkod=" + iszkod
            + ", iszkodnev=" + iszkodnev + "]";
   }
}
