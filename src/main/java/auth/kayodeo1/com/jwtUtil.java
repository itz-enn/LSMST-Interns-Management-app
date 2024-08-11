package auth.kayodeo1.com;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.security.SignatureException;
import smaApi.studentModel;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;
import java.util.Set;

public class jwtUtil {

	private static final String SECRET_KEY = "LagosStateMinistryOfScienceAndTechnologyStudentsManagementApp";
    private static final long EXPIRATION_TIME = 86400000; // 1 day in milliseconds

    @SuppressWarnings("deprecation")
	public static String createJWT(studentModel user) {
        return Jwts.builder()
                .setSubject(user.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .claim("email", user.getEmail())
                .claim("role", "Admin")
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY.getBytes())
                .compact();
    }

    public static Claims parseJWT(String jwt) throws JwtException {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY.getBytes())
                    .build()
                    .parseClaimsJws(jwt)
                    .getBody();
        } catch (ExpiredJwtException e) {
            throw new JwtException("Token expired", e);
        } catch (SignatureException e) {
            throw new JwtException("Invalid signature", e);
        } catch (JwtException e) {
            throw new JwtException("Error parsing JWT", e);
        }
    }

   
}
     
