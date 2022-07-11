package pratice.roon.misedirt.auth.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.DefaultJwtSignatureValidator;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.util.Date;

@Component
public class JwtTokenUtil {
    private String secretKey = "sample security key";

    //약 1달
    private final long EXPIRE_MINUTE = 60;

    private SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
    private SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), signatureAlgorithm.getJcaName());

    public boolean validate(String tokenWithoutSignature, String signature) {
        DefaultJwtSignatureValidator validator = new DefaultJwtSignatureValidator(signatureAlgorithm, secretKeySpec);

        return validator.isValid(tokenWithoutSignature, signature);
    }

    public String generateToken(String content){
        return Jwts.builder()
                    .setIssuedAt(new Date())
                    .setExpiration(Date.from(ZonedDateTime.now().plusMinutes(EXPIRE_MINUTE).toInstant()))
                    .claim("sub",content)
                    .signWith(signatureAlgorithm,secretKey.getBytes(StandardCharsets.UTF_8))
                    .compact();
    }
}
