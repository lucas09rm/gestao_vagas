package com.br.lucasrod.gestao_vagas.providers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

@Service
public class JWTCandidateProvider {
    
    @Value("${security.token.secret.candidate}")
    private String secretKey;

    public DecodedJWT validateToken(String token){
        token = token.replace("Bearer ", "");
        
        System.out.println("============= TK "+ token);
        
        System.out.println("=============  SK "+ secretKey);

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        try {   
            var tokenDecoded = JWT.require(algorithm).build().verify(token);
            System.out.println("============= TKD  "+ tokenDecoded);
            return tokenDecoded;
            
        } catch (JWTVerificationException e) {
            e.printStackTrace();
            return null;
        }
    }
}
