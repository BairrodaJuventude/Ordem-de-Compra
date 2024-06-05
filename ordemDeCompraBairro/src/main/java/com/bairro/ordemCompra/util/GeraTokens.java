package com.bairro.ordemCompra.util;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.JWTVerifier;

import java.util.Date;

public class GeraTokens {
    private static final String SECRET = "secreta";
    private static final Algorithm ALGORITHM = Algorithm.HMAC256(SECRET);
    public static String gerarToken(String subject) {
        Algorithm algorithm = Algorithm.HMAC256("secreta");

        // Crie o token
        String gerarToken = JWT.create()
                .withIssuer("auth0")
                .withSubject("user123")
                .withClaim("role", "admin")
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + 3600 * 1000)) // 1 hora de validade
                .sign(algorithm);

        System.out.println("Token: " + gerarToken);
        return gerarToken;
    }
    public static void verificaToken(String token) {
        JWTVerifier verifier = JWT.require(ALGORITHM)
                .withIssuer("auth0")
                .build();

        verifier.verify(token);
    }
}