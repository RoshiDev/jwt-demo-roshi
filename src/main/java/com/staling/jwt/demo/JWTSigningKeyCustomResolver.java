package com.staling.jwt.demo;

import java.security.Key;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SigningKeyResolverAdapter;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JWTSigningKeyCustomResolver extends SigningKeyResolverAdapter {

    @Autowired
    private Environment env;

    @Override
    @SuppressWarnings("rawtypes")
    public Key resolveSigningKey(JwsHeader jwsHeader, Claims claims) {
       String secretString = env.getProperty("application.secretKeys." + claims.getIssuer());
        //return Keys.secretKeyFor(Decoders.BASE64.decode(secretString));
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretString));
        /*try {
            String privateKey = env.getProperty("application.secretKeys." + claims.getIssuer());
            String publicKey= "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAou7LqbtFbp7+vZR4lvNW1ph6xVYN22SAlp7wDcO0eIWRM8uR0iCHZ7Gg0U/5rO/hgAYNZZhO+JT69fE714LrnEOckKV382KOZ0H+tc0/MODivRSl/4V4J1mJv9E079MsWyWALxiYvJNHXf3kKR9L6FVGZq6witwyhhGcBXNih6A/HiemFyOLmcwHGFNix85RGkxR7NkcRuV5qX2HRa5dgGMOwakKBcZT/Vvbakw5/J0oUttddVUM8Bs5U1RTW/sw2SEFQha1DCgbXCjyof13U3kIY+cxq9ABvjjiSd3psX6SfIplsNkxROzxRVFCJtFnqDQD3rdCB13/PBEDHh4nhwIDAQAB";
            SignatureAlgorithm algorithm = ValidateSecret.ASYMMETRIC(publicKey, privateKey);
           /* JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("auth0")
                .build(); //Reusable verifier instance
            /*DecodedJWT jwt = verifier.verify(token);
        } catch (JWTVerificationException exception){
            //Invalid signature/claims
        }*/
    }

}
