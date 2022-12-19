package legacy.framework.security.exceptions;

public class UnPwException extends Exception {
   private static final long serialVersionUID = 1L;

   public UnPwException() {
      super();
   }

   public UnPwException(String errorMessage) {
      super(errorMessage);
   }
}
