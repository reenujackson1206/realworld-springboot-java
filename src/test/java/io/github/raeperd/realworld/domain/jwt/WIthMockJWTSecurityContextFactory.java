package io.github.raeperd.realworld.domain.jwt;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

import static org.mockito.Mockito.mock;

final class WIthMockJWTSecurityContextFactory implements WithSecurityContextFactory<WithMockJWT> {

    @Override
    public SecurityContext createSecurityContext(WithMockJWT withMockJWT) {
        final var mockContext = SecurityContextHolder.createEmptyContext();
        mockContext.setAuthentication(new MockJWTAuthenticationToken(mockJWTPayload(withMockJWT)));
        return mockContext;
    }

    private JWTPayload mockJWTPayload(WithMockJWT withMockJWT) {
        return mock(JWTPayload.class);
    }

    private static class MockJWTAuthenticationToken extends AbstractAuthenticationToken {

        private final JWTPayload jwtPayload;

        public MockJWTAuthenticationToken(JWTPayload jwtPayload) {
            super(null);
            this.jwtPayload = jwtPayload;
            this.setAuthenticated(true);
        }

        @Override
        public Object getPrincipal() {
            return jwtPayload;
        }

        @Override
        public Object getCredentials() {
            return null;
        }

    }
}
