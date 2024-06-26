package com.example.backendbusbookingservice.security;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.backendbusbookingservice.entity.Roles;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class JwtUtil {
    private static final String SECRET_KEY="secret_key";

    public String generateToken(String  userName, Set<Roles> roles) {
        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);

        Map<String,Object> rolesMap = roles.stream().collect(Collectors.toMap(Roles::getName,Roles::getId));


        String jwt = JWT.create()
                .withClaim("username", userName)
                .withClaim("roles", rolesMap)
                .withClaim("datetime-claim", Instant.now())
                .sign(algorithm);
        return  jwt;
    }

    public DecodedJWT  verifyJwt(String jwtToken){
        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);

        JWTVerifier verifier = JWT.require(algorithm)
                .withClaimPresence("username")
                .build();
        return verifier.verify(jwtToken);
    }
}
