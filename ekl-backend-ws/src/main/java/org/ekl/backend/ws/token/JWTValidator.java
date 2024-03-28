package org.ekl.backend.ws.token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.jwt.BadJwtException;
import org.springframework.stereotype.Component;

//TODO refactoring
@NoArgsConstructor
@Slf4j
@Component
public class JWTValidator {

    public boolean validateJWT(String jwt){
        Jws<Claims> jws;
        try{
         jws = Jwts.parserBuilder()
                 .setSigningKey(Keys.hmacShaKeyFor(Decoders.BASE64.decode("/UB0VoEUQn9ecTqPaE6XlbtpbsupRaAtifHXfxEFSDA=")))
                 .build()
                 .parseClaimsJws(jwt);
         return true;
        }catch(JwtException ex){
            log.warn("JWT invalid", ex);
            return false;
        }
    }

    public Claims readJWT(String jwt){
        Jws<Claims> jws;
        try{
            jws = Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(Decoders.BASE64.decode("/UB0VoEUQn9ecTqPaE6XlbtpbsupRaAtifHXfxEFSDA=")))
                    .build()
                    .parseClaimsJws(jwt);
         return jws.getBody();
        }catch(JwtException ex){
            log.warn("JWT invalid, Claims could not read");
            if(log.isDebugEnabled()){
                log.debug("Token invalid", ex);
            }
            return null;
        }
    }

    public Jws<Claims> readJWS(String jwt) throws BadJwtException{
        try{
            Jws<Claims> jws = Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(Decoders.BASE64.decode("/UB0VoEUQn9ecTqPaE6XlbtpbsupRaAtifHXfxEFSDA=")))
                    .build()
                    .parseClaimsJws(jwt);
            return jws;
        }catch(JwtException ex){
            log.warn("JWT invalid, Claims could not be be read");
            if(log.isDebugEnabled()){
                log.debug("Token invalid", ex);
            }
            throw new BadJwtException(ex.getMessage());
        }
    }

}
