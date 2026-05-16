package org.ycc;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {
    /**
     * 生成JWT令牌
     */
    @Test
    public void testGenerate() {

        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("id", 1);
        dataMap.put("username", "admin");
        String jwt = Jwts.builder().signWith(SignatureAlgorithm.HS256, "eWNj")  //指定加密算法，密钥"ycc"
                .addClaims(dataMap) //添加自定义信息
                .setExpiration(new Date((System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7))) //设置令牌有效期
                .compact();//生成令牌
        System.out.println(jwt);
    }

    /**
     * 解析JWT令牌
     */
    @Test
    public void testParseJWT() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJhZG1pbiIsImV4cCI6MTc3NzA0MDMxM30.OqLtSPDFzHBL1LRueCTSbKPVARt04TVtJgBvWlGgZME";
        Claims claims = Jwts.parser()
                .setSigningKey("eWNj")//指定密钥
                .parseClaimsJws(token)//解析令牌
                .getBody();//获取自定义信息
        System.out.println(claims);
    }
}
