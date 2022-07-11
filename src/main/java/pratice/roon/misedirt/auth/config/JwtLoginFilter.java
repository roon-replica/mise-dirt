package pratice.roon.misedirt.auth.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.stereotype.Component;
import pratice.roon.misedirt.auth.dto.AuthDTO;
import pratice.roon.misedirt.auth.repository.MemberRepository;
import pratice.roon.misedirt.auth.service.MemberService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Slf4j
public class JwtLoginFilter extends AbstractAuthenticationProcessingFilter {
    private JwtTokenUtil jwtUtil;

    private MemberService memberService;

    public JwtLoginFilter(String defaultFilterProcessesUrl, JwtTokenUtil jwtUtil, MemberService memberService) {
        super(defaultFilterProcessesUrl);
        this.jwtUtil = jwtUtil;
        this.memberService = memberService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

//        SecurityContextImpl securityContext = (SecurityContextImpl) request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
//        return securityContext.getAuthentication();

//        UserDetails userDetails = (UserDetails) securityContext.getAuthentication().getPrincipal();
//        String username = userDetails.getUsername();
//        String password = memberService.loadUserByUsername(username).getPassword();

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);
        return getAuthenticationManager().authenticate(authToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        log.info("authResult = " + authResult);

        String username = ((AuthDTO) authResult.getPrincipal()).getUsername();

        String token = null;

        try {
            token = jwtUtil.generateToken(username);
            response.setContentType("text/plain");
//            response.getOutputStream().write(token.getBytes(StandardCharsets.UTF_8));
            response.setHeader("jwt", token);

            log.info("generated token = " + token);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
