package plus.guiyun.app.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import plus.guiyun.app.framework.config.TokenConfig;


public class JWTUtil {
    public static DecodedJWT parseToken(String token) {
        DecodedJWT decodedJWT;
        try {
            Algorithm algorithm = Algorithm.HMAC512(TokenConfig.secret);
            JWTVerifier verifier = JWT.require(algorithm).build();
            decodedJWT = verifier.verify(token);
            return decodedJWT;
        } catch (Exception e) {
            throw new RuntimeException("");
        }
    }

    public static String getUsernameFromToken(String token) {
        DecodedJWT decodedJWT = parseToken(token);
        return decodedJWT.getIssuer();
    }

}
