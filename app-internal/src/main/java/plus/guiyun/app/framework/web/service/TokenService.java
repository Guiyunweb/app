package plus.guiyun.app.framework.web.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import plus.guiyun.app.common.code.domain.model.LoginUser;
import plus.guiyun.app.common.code.redis.RedisCache;
import plus.guiyun.app.common.constant.CacheConstants;
import plus.guiyun.app.common.constant.Constants;
import plus.guiyun.app.common.utils.StringUtils;
import plus.guiyun.app.common.utils.uuid.IdUtils;
import plus.guiyun.app.framework.config.TokenConfig;

import java.util.concurrent.TimeUnit;

@Component
public class TokenService {

    @Autowired
    RedisCache redisCache;

    protected static final long MILLIS_SECOND = 1000;

    protected static final long MILLIS_MINUTE = 60 * MILLIS_SECOND;


    /**
     * 创建Token
     *
     * @param loginUser 登录信息
     * @return token
     */
    public String createToken(LoginUser loginUser) {
        loginUser.setLoginTime(System.currentTimeMillis());
        loginUser.setExpireTime(loginUser.getLoginTime() + TokenConfig.expireTime * MILLIS_MINUTE);
        String uuid = IdUtils.fastUUID();
        String userKey = getTokenKey(uuid);
        loginUser.setToken(uuid);
        Algorithm algorithm = Algorithm.HMAC512(TokenConfig.secret);
        String token = JWT.create()
                .withIssuer(loginUser.getAccount())
                .withClaim(Constants.LOGIN_USER_KEY, uuid)
                .sign(algorithm);
        redisCache.setCacheObject(userKey, loginUser, TokenConfig.expireTime, TimeUnit.MINUTES);
        return token;
    }


    /**
     * @param request 请求
     * @return 登录参数
     */
    public LoginUser getLoginUser(HttpServletRequest request) {
        String token = getToken(request);
        if (StringUtils.isNotEmpty(token)) {
            DecodedJWT decodedJWT = parseToken(token);
            String uuid = decodedJWT.getClaim(Constants.LOGIN_USER_KEY).asString();
            String userKey = getTokenKey(uuid);
            LoginUser user = redisCache.getCacheObject(userKey);
            return user;
        }
        return null;
    }

    /**
     * 验证令牌有效期，自动刷新缓存
     *
     * @param loginUser
     * @return 令牌
     */
    public void verifyToken(LoginUser loginUser) {
        long expireTime = loginUser.getExpireTime();
        long currentTime = System.currentTimeMillis();
        if (expireTime - currentTime <= TokenConfig.expireTime) {
            refreshToken(loginUser);
        }
    }

    /**
     * 刷新令牌有效期
     *
     * @param loginUser 登录信息
     */
    public void refreshToken(LoginUser loginUser) {
        loginUser.setLoginTime(System.currentTimeMillis());
        loginUser.setExpireTime(loginUser.getLoginTime() + TokenConfig.expireTime * MILLIS_MINUTE);
        // 根据uuid将loginUser缓存
        String userKey = getTokenKey(loginUser.getToken());
        redisCache.setCacheObject(userKey, loginUser, TokenConfig.expireTime, TimeUnit.MINUTES);
    }

    /**
     * 删除用户身份信息
     */
    public void delLoginUser(String token) {
    }

    /**
     * 从令牌中获取数据声明
     *
     * @param token 令牌
     * @return 数据声明
     */
    private DecodedJWT parseToken(String token) {
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

    /**
     * 从令牌中获取账户
     *
     * @param token 令牌
     * @return 用户名
     */
    public String getUsernameFromToken(String token) {
        DecodedJWT decodedJWT = parseToken(token);
        return decodedJWT.getIssuer();
    }

    /**
     * 获取请求token
     *
     * @param request
     * @return token
     */
    private String getToken(HttpServletRequest request) {
        String token = request.getHeader(TokenConfig.header);
        if (StringUtils.isNotEmpty(token) && token.startsWith(Constants.TOKEN_PREFIX)) {
            token = token.replace(Constants.TOKEN_PREFIX, "");
        }
        return token;
    }


    private String getTokenKey(String uuid) {
        return CacheConstants.LOGIN_TOKEN_KEY + uuid;
    }


}
