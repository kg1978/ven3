package ven3.framework.security;

public interface ExternalServiceAuthentication {
   public boolean authentication(String username, String password);
}