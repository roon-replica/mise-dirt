package pratice.roon.misedirt.auth.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;

@Slf4j
@RequiredArgsConstructor
//@Component
public class JwtAuthCheckFilter extends OncePerRequestFilter {
    private final JwtTokenUtil jwtTokenUtil;
//    private final MemberRepository memberRepository;

    private final String AUTH_CHECK_PATH = "/mise/";

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getServletPath();
        return !path.startsWith(AUTH_CHECK_PATH);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

//        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
////        if (header.isEmpty() || !header.startsWith("Bearer ")) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        String[] chunks = header.split("\\.");

        if(request.getParameter("jwt") == null){
            filterChain.doFilter(request,response);
            return;
        }

        String[] chunks = request.getParameter("jwt").split("\\.");

        Base64.Decoder decoder = Base64.getUrlDecoder();

        String HEADER = new String(decoder.decode(chunks[0]));
        String PAY_LOAD = new String(decoder.decode(chunks[1]));
        String SIGNATURE = null;

        if (chunks.length == 3) {
            SIGNATURE = new String(decoder.decode(chunks[2]));
        }

        log.info("token : " + HEADER + " " + PAY_LOAD + " " + SIGNATURE);

        String tokenWithoutSignature = chunks[0] + "." + chunks[1];
        String signature = chunks[2];

        boolean isValid = jwtTokenUtil.validate(tokenWithoutSignature, signature);

        if (isValid) {
            filterChain.doFilter(request, response);
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json;charset=utf-8");

            JSONObject errorJson = new JSONObject();
            errorJson.put("code", "401");
            errorJson.put("message", "INVALID AUTH");

            PrintWriter printWriter = response.getWriter();
            printWriter.print(errorJson);
        }

    }
}
