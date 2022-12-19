package legacy.framework.security.exceptions;

public class UserExpiredException extends Exception {
   private static final long serialVersionUID = 1L;

   public UserExpiredException() {
      super();
   }

   public UserExpiredException(String errorMessage) {
      super(errorMessage);
   }
}
