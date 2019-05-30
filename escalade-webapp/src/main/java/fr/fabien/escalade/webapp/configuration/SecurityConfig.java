package fr.fabien.escalade.webapp.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Qualifier("utilisateurManagement")
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(new BCryptPasswordEncoder());
        return provider;
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("*/edit/").authenticated()
                .antMatchers("/profil").authenticated()
                .antMatchers("/profil/").authenticated()
                .antMatchers("/anonymous*").anonymous()
                .antMatchers("/profil/{id}").permitAll()
                .antMatchers("/connexion*").permitAll()
                .antMatchers("/inscription*").permitAll()
                .antMatchers("/topo/{id}").permitAll()
                .antMatchers("/topo").authenticated()
                .antMatchers("/site/{id}").permitAll()
                .antMatchers("/site").authenticated()
                .antMatchers("/secteur/{id}").permitAll()
                .antMatchers("/voie/{id}").permitAll()
                .antMatchers("/*").permitAll()
                .antMatchers("/css/*").permitAll()
                .antMatchers("/static/img/*").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/connexion")
                .loginProcessingUrl("/connexion")
                .defaultSuccessUrl("/", true)
                //.failureUrl("/login.html?error=true")
                .and()
                .logout().invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/").permitAll();
    }
}
