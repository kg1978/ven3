package legacy.framework.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import legacy.framework.security.jwt.AuthEntryPointJwt;
import legacy.framework.security.jwt.AuthTokenFilter;
import legacy.framework.security.services.UserDetailsServicesImpl;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {
   @Autowired
   UserDetailsServicesImpl userDetailsService;

   @Autowired
   private AuthEntryPointJwt unauthorizedHandler;

   @Bean
   public AuthTokenFilter authenticationJwtTokenFilter() {
      return new AuthTokenFilter();
   }

   @Bean
   public DaoAuthenticationProvider authenticationProvider() {
      DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

      authProvider.setUserDetailsService(userDetailsService);
      authProvider.setPasswordEncoder(passwordEncoder());

      return authProvider;
   }

   @Bean
   public ExternalAuthenticationProvider customAuthenticationProvider() {
      ExternalAuthenticationProvider authenticationProvider = new ExternalAuthenticationProvider();
      return authenticationProvider;
   }

   @Bean
   public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
      return authConfig.getAuthenticationManager();
   }

   @Bean
   public PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
   }

   @Bean
   public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
      http.cors().and().csrf().disable().exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
            .antMatchers("/").permitAll().antMatchers("/h2/**").permitAll().antMatchers("/api/service-mock/**")
            .permitAll().antMatchers("/api/service-auth/**").permitAll().antMatchers("/api/service-test/**").permitAll()
            .antMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll().anyRequest().authenticated();

      http.authenticationProvider(authenticationProvider());

      http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

      http.headers().frameOptions().disable();

      return http.build();
   }
}
