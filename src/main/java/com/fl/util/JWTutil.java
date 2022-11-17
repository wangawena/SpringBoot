package com.fl.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fl.pojo.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class JWTutil {

    //密钥
    //private String jwtSecret="XDD6897!&@H.,?";
    //@Value("${jwtSecret}")
    private String jwtSecret="XDD6897!&@H.,?";

    //随机字符串
    public String randomString()
    {
        String[] s={"1","2","3","4","5","6","7","8","9","0","A","B","C","D","E","F","G","H","I","J","K","L","M",
                "N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
        int size=s.length;
        String string="";
        Random random=new Random();
        for(int i=0;i<8;i++)
        {
            string+=s[random.nextInt(size)];
        }
        return string;
    }


    //获得Token
    public String getToken(String account,String randomString,String time)
    {
        System.out.println(jwtSecret);
        return JWT.create()
                .withClaim("account",account)
                .withClaim("randomString",randomString)
                .withClaim("expireAt",time+3000)
                .sign(Algorithm.HMAC256(jwtSecret));
    }

    //解析Token
    public Claims parseJWT(String token)
    {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
    }


    //校验token
    public Boolean isVerfify(String token)
    {
        try {
            Algorithm algorithm=Algorithm.HMAC256(jwtSecret);//加密算法
            JWTVerifier verifier=JWT.require(algorithm).build();
            DecodedJWT jwt=verifier.verify(token);

            /*Claims claims=Jwts.parser()
                    .setSigningKey(algorithm)
                    .parseClaimsJws(token).getBody();*/
            return true;
        }catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }

    }
}
