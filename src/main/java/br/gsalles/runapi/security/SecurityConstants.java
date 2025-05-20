package br.gsalles.runapi.security;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

public class SecurityConstants {

    public static final long JWT_EXPIRATION = 70000;
    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    public static final String JWT_SECRET = java.util.Base64.getEncoder().encodeToString(SECRET_KEY.getEncoded());
}
