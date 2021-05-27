package com.lys.publicpro.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.lys.publicpro.common.db.entity.AccountInfo;

import java.util.Date;

/**
 * @author 李无敌
 * @date 2021年04月16日 9:10
 */
public class JWTUtils {
    public static String getToken(AccountInfo accountInfo) {
        Date start = new Date();
        long currentTime = System.currentTimeMillis() + 60* 60 * 1000 * 24;//一天有效时间
        Date end = new Date(currentTime);
        String token = "";

        token = JWT.create().withAudience(accountInfo.getId().toString()).withIssuedAt(start).withExpiresAt(end)
                .sign(Algorithm.HMAC256(accountInfo.getAccountPassword()));
        return token;
    }
}
