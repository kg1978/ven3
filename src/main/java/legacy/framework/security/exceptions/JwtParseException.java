package legacy.framework.security.exceptions;

public class JwtParseException extends Exception {
   private static final long serialVersionUID = 1L;

   public JwtParseException(String errorMessage) {
      super(errorMessage);
   }
}
