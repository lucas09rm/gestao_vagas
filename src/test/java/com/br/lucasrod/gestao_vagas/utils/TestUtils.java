package com.br.lucasrod.gestao_vagas.utils;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.UUID;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestUtils {
    public static String objectToJSON(Object obj){
        final ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static String generateToken(UUID idCompany, String secret){
        var expiresIn = Instant.now().plus(Duration.ofHours(2));
        Algorithm algorithm = Algorithm.HMAC256(secret);
        var token =JWT.create().withIssuer("rod")
            .withExpiresAt(expiresIn)
            .withSubject(idCompany.toString())
            .withClaim("roles", Arrays.asList("COMPANY"))
            .sign(algorithm);
        return token;
    }
}
