package com.example.springsecurity.configurations;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class JWTAuthorizationFilter extends OncePerRequestFilter{
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        response.addHeader("Access-Control-Allow-Origin", "*");// les domaines autorisés pour régler CORS si on a un domaine précis on le mets sinon on autorise tout avec *
        response.addHeader("Access-Control-Allow-Headers", "Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Contol-Request-Headers,authorization");// l'autorisation d'envoyer les headers(les headers autorisées du partie front
        response.addHeader("Access-Control-Expose-Headers", "Access-Controle-Allow-Origin, Access-Controle-Allow-Credentials, authorization"); // le droit de lire les headers indiqués
        if (request.getMethod().equals("OPTIONS")) {
            response.setStatus(HttpServletResponse.SC_OK);
        }
        ///////////////////////////////////////////////////////////
        String jwt = request.getHeader(SecurityConstants.HEADER_STRING);
    if(jwt == null || !jwt.startsWith(SecurityConstants.TOCKEN_PREFIX)){
        // doFilter c'à dire passe au filtre suivant
        filterChain.doFilter(request,response);// je quite le filtre
        return;//    c'est je quite la méthode
    }
        Claims claims = Jwts.parser()
                .setSigningKey(SecurityConstants.SECRET) // enlever le base64 codage
                .parseClaimsJws(jwt.replace(SecurityConstants.TOCKEN_PREFIX, ""))
                .getBody();

        String username = claims.getSubject(); // on prend le username qu'on le stocke dans le subject

        ArrayList<Map<String, String>> roles = (ArrayList<Map<String, String>>) claims.get("roles"); // avoir tous les roles dans une map
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        roles.forEach((r -> {
            authorities.add(new SimpleGrantedAuthority(r.get("authority")));
        }));

        // vérifier est ce que le token est valide ou pas

        // remplir l'objet global de spring security principale, on lui fournir le username et les roles
        // username pour connaitre l'utilisateur et role pour savoir ce droit d'execition
        UsernamePasswordAuthenticationToken authenticatedUser = new UsernamePasswordAuthenticationToken(username, null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authenticatedUser); // on charge cet utilisateur authentifier dans le context de security on dit a spring voila cet utilisateur est celui qui a fait l'authentification
        filterChain.doFilter(request, response);

    }
}
