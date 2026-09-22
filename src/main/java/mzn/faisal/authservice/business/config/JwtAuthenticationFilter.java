package mzn.faisal.authservice.business.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mzn.faisal.authservice.business.service.common.RedisService;
import mzn.faisal.authservice.business.service.security.TokenService;
import org.jspecify.annotations.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.http.HttpHeaders;

import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final RedisService redisService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        String token = tokenService.extractTokenFromHeader(authHeader);

        if(token != null){
            String blackListKey = "blacklist:" + token;
            if(redisService.hasKey(blackListKey)){
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }

            if(tokenService.validateToken(token)){
                String username = tokenService.getUsernameFromToken(token);

                var authentication = UsernamePasswordAuthenticationToken.authenticated(
                        username, null, List.of()
                );

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }
}
