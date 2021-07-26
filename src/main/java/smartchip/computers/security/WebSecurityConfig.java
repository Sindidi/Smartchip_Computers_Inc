package smartchip.computers.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Qualifier("userDetailsServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
    private static final String[] PUBLIC_MATCHERS = {
 
    };
    
    private static final String[] PRIVATE_MATCHERS = {
    		"/admin",
    		"/dashbord",
    		"/produit",
    		"/produitAdd",
    		"/produitUpdat",
    };
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers(PUBLIC_MATCHERS).permitAll();
		http.authorizeRequests().antMatchers(PRIVATE_MATCHERS).hasAuthority("ADMIN");
		
		http
		.csrf().disable().cors().disable()
		.formLogin().failureUrl("/login?error")
		.defaultSuccessUrl("/")
		.loginPage("/login").permitAll()
		.and()
		.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/").deleteCookies("remember-me").permitAll()
		.and()
		.rememberMe();
       
    }

    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }
}