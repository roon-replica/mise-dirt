package pratice.roon.misedirt.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import pratice.roon.misedirt.auth.service.MemberService;

import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity  // enable Spring Security’s web security support and provide the Spring MVC integration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private MemberService memberService;

    @Autowired
    private JwtAuthCheckFilter jwtAuthCheckFilter;

    private final String JWT_GENERATE_URL = "/jwt/generate";

    // TODO
    // JwtLoginFilter can't be set as '@Component'. But need to be spring bean. So I tried this way.
    // Not familiar. Is this right?
    @Bean
    public JwtLoginFilter jwtLoginFilter() throws Exception {
        JwtLoginFilter jwtLoginFilter = new JwtLoginFilter(JWT_GENERATE_URL, jwtUtil());

        // TODO: why this code needed?
        jwtLoginFilter.setAuthenticationManager(authenticationManager());

        return jwtLoginFilter;
    }

    @Bean
    public JwtTokenUtil jwtUtil() {
        return new JwtTokenUtil();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(memberService);
    }

    // defines which URL paths should be secured
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Enable CORS and disable CSRF... ?
        http = http.cors().and()
                .csrf().disable();

        // Set session management to stateless
        http = http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and();

        // Set unauthorized requests exception handler ??
        http = http.exceptionHandling()
                .authenticationEntryPoint(
                        (request, response, e) -> {
                            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
                        }
                )
                .and();


        // Add JWT token filter
        http.addFilterBefore(jwtAuthCheckFilter, UsernamePasswordAuthenticationFilter.class);

        // add JWT login(+ generate) filter
        http.addFilterBefore(jwtLoginFilter(), UsernamePasswordAuthenticationFilter.class);

        http.authorizeRequests()
                .antMatchers("/", "/enroll").permitAll();

        http.authorizeRequests()
                .anyRequest().authenticated();

        http.formLogin()
                .loginPage("/login")
//                .loginProcessingUrl("/processLogin")
                .defaultSuccessUrl("/", true)
//                .failureUrl("/login")
                .permitAll();

        http.logout()
                .logoutSuccessUrl("/");

    }
}
