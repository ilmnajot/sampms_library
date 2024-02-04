package uz.ilmnajot.sampms_library.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import uz.ilmnajot.sampms_library.exception.BaseException;
import uz.ilmnajot.sampms_library.service.CustomUserDetailsService;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.Date;

@Component
public class JwtProvider {


    private static final String securityKey = "sdkjbskjhbfkajsbfadvakjsh,fvblaiFGHwilayrkbgvk,xjbgv,hkzsdvbz,nskjdf.dh";
    private static final long expireTime = 5 * 5 * 5 * 60 * 1000L;

    public String generateToken(String email) {

        return Jwts
                .builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expireTime))
                .signWith(SignatureAlgorithm.HS256, securityKey)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts
                    .parser()
                    .setSigningKey(securityKey)
                    .parseClaimsJws(token)
                    .getBody();
            return true;
        } catch (Exception e) {
            e.getStackTrace();
        }
        return false;
    }

    public String getUsernameFromToken(String token) {
        try {
            return Jwts
                    .parser()
                    .setSigningKey(securityKey)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (Exception e) {
            throw new BaseException("there is a problem with parsing username from token");
        }
    }

}
