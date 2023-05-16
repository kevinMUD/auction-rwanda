package com.auctionrwanda.securityconfig;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response
            , Authentication authentication) throws IOException, ServletException {

//        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
//        boolean isAdmin= authorities.stream()
//                .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));

        HttpSession session = request.getSession();
        String email = authentication.getName();
        if (email.contains("admin123")) {
            redirectStrategy.sendRedirect(request, response, "/admin");
        } else {
            redirectStrategy.sendRedirect(request, response, "/dashboard");
        }

    }
}
