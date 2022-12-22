package legacy.framework.security.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import legacy.framework.utils.Util;
import legacy.ven3.models.db.jo.JoAufelh;
import legacy.ven3.servises.db.jo.JoAufelhService;

@Service
public class UserDetailsServicesImpl implements UserDetailsService {
   private static final Logger LOG = LoggerFactory.getLogger(UserDetailsServicesImpl.class);

   @Autowired
   JoAufelhService joAufelhService;

   @Autowired
   Util util;

   @Override
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      LOG.info(username);

      JoAufelh joAufelh = joAufelhService.getByFelhazonOkmany(username);
      if (util.checkDateFromTo(joAufelh.ervtol, joAufelh.ervig)) {
         return new UserDetailsImpl(username, "");
      }
      throw new UsernameNotFoundException("username not found");
   }
}
