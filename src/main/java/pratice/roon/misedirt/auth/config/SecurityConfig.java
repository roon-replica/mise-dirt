package pratice.roon.misedirt.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import pratice.roon.misedirt.auth.service.MemberService;

@Configuration
@EnableWebSecurity  // enable Spring Security’s web security support and provide the Spring MVC integration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private MemberService memberService;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(memberService);
    }

    // defines which URL paths should be secured
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/enroll").permitAll();

        http.authorizeRequests()
                .anyRequest().authenticated();

        http.formLogin()
                .loginPage("/login")
//                .loginProcessingUrl("/processLogin")
                .defaultSuccessUrl("/mise/main", true)
//                .failureUrl("/login")
                .permitAll();

        http.logout()
                .logoutSuccessUrl("/");

    }
}
