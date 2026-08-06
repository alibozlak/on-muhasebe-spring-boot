package dev.bozlak.on_muhasebe_spring_boot.security;

import com.nimbusds.jose.jwk.source.ImmutableSecret;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.time.Duration;
import java.time.Instant;
import java.util.Base64;
import java.util.Map;

@Service
public class JwtServiceImpl implements JwtService {

    private static final Duration TOKEN_VALIDITY = Duration.ofHours(8);

    private final JwtEncoder jwtEncoder;
    private final JwtDecoder jwtDecoder;
    private final MacAlgorithm macAlgorithm;

    public JwtServiceImpl(@Value("${jwt.secret}") String secret) {
        byte[] keyBytes = decodeSecret(secret);
        this.macAlgorithm = macAlgorithmFor(keyBytes);

        SecretKey secretKey = new SecretKeySpec(keyBytes, jcaNameFor(this.macAlgorithm));

        this.jwtEncoder = new NimbusJwtEncoder(new ImmutableSecret<SecurityContext>(secretKey));
        this.jwtDecoder = NimbusJwtDecoder.withSecretKey(secretKey)
                .macAlgorithm(this.macAlgorithm)
                .build();
    }

    /**
     * Reproduces the lenient behaviour of jjwt's {@code Decoders.BASE64}: when the final
     * base64 group holds a single character (length % 4 == 1) that character is dropped,
     * because no whole byte can be built from it. The deployed secret is in exactly that
     * shape, so a strict decoder would fail at startup and invalidate every issued token.
     */
    private static byte[] decodeSecret(String secret) {
        String normalized = secret.trim();

        if (normalized.length() % 4 == 1)
            normalized = normalized.substring(0, normalized.length() - 1);

        return Base64.getDecoder().decode(normalized);
    }

    /**
     * Mirrors jjwt's {@code Keys.hmacShaKeyFor}, which picks the strongest HMAC-SHA
     * variant the key length allows. Keeping this rule means already issued tokens stay
     * verifiable: the deployed 384-bit key continues to use HS384 and the 288-bit test
     * key continues to use HS256.
     */
    private static MacAlgorithm macAlgorithmFor(byte[] keyBytes) {
        int bits = keyBytes.length * Byte.SIZE;

        if (bits >= 512) return MacAlgorithm.HS512;
        if (bits >= 384) return MacAlgorithm.HS384;
        if (bits >= 256) return MacAlgorithm.HS256;

        throw new IllegalStateException(
                "jwt.secret decodes to " + bits + " bits; HMAC-SHA needs at least 256."
        );
    }

    private static String jcaNameFor(MacAlgorithm macAlgorithm) {
        return "HmacSHA" + macAlgorithm.getName().substring(2);
    }

    @Override
    public String generateJwtToken(UserDetails userDetails, Map<String,Object> extraClaims) {
        return this.buildToken(extraClaims, userDetails);
    }

    @Override
    public Boolean isTokenValid(String jwtToken, UserDetails userDetails) {
        Jwt jwt = this.decode(jwtToken);

        return jwt.getExpiresAt() != null &&
                jwt.getExpiresAt().isAfter(Instant.now()) &&
                userDetails.getUsername().equals(jwt.getSubject());
    }

    private String buildToken(Map<String, Object> extraClaims, UserDetails userDetails){
        Instant issuedAt = Instant.now();

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .claims(allClaims -> allClaims.putAll(extraClaims))
                .subject(userDetails.getUsername())
                .issuedAt(issuedAt)
                .expiresAt(issuedAt.plus(TOKEN_VALIDITY))
                .build();

        JwsHeader header = JwsHeader.with(this.macAlgorithm).build();

        return this.jwtEncoder.encode(JwtEncoderParameters.from(header, claims)).getTokenValue();
    }

    /**
     * Throws {@link org.springframework.security.oauth2.jwt.JwtException} for a malformed,
     * badly signed or expired token, matching what jjwt's parser did before.
     */
    private Jwt decode(String jwtToken){
        return this.jwtDecoder.decode(jwtToken);
    }

    @Override
    public String getUserNameFromToken(String jwtToken){
        return this.decode(jwtToken).getSubject();
    }

    @Override
    public Short getAdminIdFromToken(String jwtToken) {
        Object adminId = this.decode(jwtToken).getClaim("adminId");
        return adminId == null ? null : ((Number) adminId).shortValue();
    }

    @Override
    public Integer getUserIdFromToken(String jwtToken) {
        Object userId = this.decode(jwtToken).getClaim("userId");
        return userId == null ? null : ((Number) userId).intValue();
    }
}
