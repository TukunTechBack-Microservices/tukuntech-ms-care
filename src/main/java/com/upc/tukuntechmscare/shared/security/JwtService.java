package com.upc.tukuntechmscare.shared.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;
import java.security.Key;
import java.util.List;


@Service
public class JwtService {

    private final JwtProperties props;
    private final Key signingKey;

    public JwtService(JwtProperties props) {
        this.props = props;
        byte[] keyBytes = Decoders.BASE64.decode(props.secret());
        this.signingKey = Keys.hmacShaKeyFor(keyBytes);
    }

    private Claims parseClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(signingKey)
                .requireIssuer(props.issuer())
                .setAllowedClockSkewSeconds(60)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean validateToken(String token) {
        try {
            parseClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getEmailFromToken(String token) {
        return parseClaims(token).getSubject();
    }

    public Long getUserIdFromToken(String token) {
        Object uid = parseClaims(token).get("uid");
        if (uid == null) return null;
        if (uid instanceof Integer i) return i.longValue();
        if (uid instanceof Long l) return l;
        return Long.parseLong(uid.toString());
    }

    @SuppressWarnings("unchecked")
    public List<String> getRolesFromToken(String token) {
        Object roles = parseClaims(token).get("roles");
        if (roles instanceof List<?> list) {
            return list.stream().map(Object::toString).toList();
        }
        return List.of();
    }
}