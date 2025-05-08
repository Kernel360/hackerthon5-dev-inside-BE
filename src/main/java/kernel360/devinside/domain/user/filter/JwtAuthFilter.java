package kernel360.devinside.domain.user.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kernel360.devinside.common.exception.CustomException;
import kernel360.devinside.common.exception.ErrorCode;
import kernel360.devinside.domain.user.domain.LoginUser;
import kernel360.devinside.domain.user.util.CookieUtil;
import kernel360.devinside.domain.user.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import static kernel360.devinside.common.exception.ErrorCode.UNKNOWN_TOKEN_ERROR;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = CookieUtil.findToken(request);

            if (!verifyToken(request, token)) {
                filterChain.doFilter(request, response);
                return;
            }

            LoginUser loginUser = getUser(token);
            setSecuritySession(loginUser);
            filterChain.doFilter(request, response);
        } catch (CustomException e) {
            ErrorCode errorCode = e.getErrorCode();
            switch (errorCode) {
                case WRONG_TYPE_TOKEN, UNSUPPORTED_TOKEN, EXPIRED_TOKEN, UNKNOWN_TOKEN_ERROR ->
                        setResponse(response, errorCode);
                default -> {
                    log.error("알 수 없는 에러 코드: {}", errorCode);
                    setResponse(response, UNKNOWN_TOKEN_ERROR); // 기본 예외 처리
                }
            }
        }
    }

    private static void setSecuritySession(LoginUser loginUser) {
        log.info("SessionLoginUser: {}", loginUser.getUsername());

        Collection<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(loginUser.getRole().getRoleSecurity()));

        Authentication authToken = new UsernamePasswordAuthenticationToken(loginUser, null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authToken);
    }

    private LoginUser getUser(String token) {
        Long userId = jwtUtil.getUserId(token);
        String email = jwtUtil.getUsername(token);
        String role = jwtUtil.getRole(token);

        return new LoginUser(userId, email, role);
    }

    private boolean verifyToken(HttpServletRequest request, String token) {
        Boolean isValid = (Boolean) request.getAttribute("isTokenValid");
        if (isValid != null) return isValid;

        if (token == null || !jwtUtil.validateToken(token)) {
            log.debug("token null or not validate");
            request.setAttribute("isTokenValid", false);
            return false;
        }

        request.setAttribute("isTokenValid", true);
        return true;
    }

    private void setResponse(HttpServletResponse response, ErrorCode errorCode) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(errorCode.getStatus().value());
        response.getWriter().print(objectMapper.writeValueAsString(errorCode.getMessage()));
    }
}
