package com.example.cli.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.cli.constant.AuthEnum;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Date;
@Component
public class JWTUtil {

    @Value("${auth.expireTime}")
    private int expireTime;
    @Value("${auth.secret}")
    private String secret;
    /**
     * 校验token是否正确
     * @return 是否正确
     */
    public AuthEnum verify(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .build();
            verifier.verify(token);
            return AuthEnum.SUCCESS;
        } catch (ExpiredJwtException e){
            return AuthEnum.EXPIRE;
        } catch (Exception exception) {
            return AuthEnum.FAILED;
        }
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     * @return token中包含的用户名
     */
    public  String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    public  Integer getUserId(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("id").asInt();
        } catch (JWTDecodeException e) {
            return null;
        }
    }
    public  Integer getUserType(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("type").asInt();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    public  String getUserKey(String token,String key) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim(key).asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }
    /**
     * 生成签名
     * @param username 用户名
     * @return 加密的token
     */
    public  String sign(Integer id,String username) {
        try {
            Date date = new Date(System.currentTimeMillis()+expireTime);
            Algorithm algorithm = Algorithm.HMAC256(secret);
            // 附带username信息
            return JWT.create()
                    .withClaim("id",id)
                    .withClaim("username", username)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }
}
