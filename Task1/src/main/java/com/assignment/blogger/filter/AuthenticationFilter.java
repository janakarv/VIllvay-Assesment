/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.assignment.blogger.filter;


import org.apache.commons.lang.StringUtils;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 * @author admin
 */
@Component
public class AuthenticationFilter implements Filter {

    private final static Logger log = LogManager.getLogger(AuthenticationFilter.class);

    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {
        log.info("Initializing filter :{}", this);
    }

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String header = req.getHeader(HttpHeaders.AUTHORIZATION);
        log.info("Header value :: " + header);

        if (StringUtils.isBlank(header)) {
            log.info("Http header is blank.");
            rejectRequest(res, "Http header is blank.");
            return;
        }

        // handling JWT Authorization
        if (!StringUtils.contains(header, "Bearer ")) {
            log.info("Bearer token is missing");
            rejectRequest(res, "Bearer token is missing.");
            return;
        }
        final String jwTokenStr = header.replaceFirst("Bearer ", "");

        if (StringUtils.isBlank(jwTokenStr)) {
            log.error("Token is empty.");
            rejectRequest(res, "Token is empty.");
            return;
        }

        try {
            /*
            token :eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJKYW5ha2EiLCJuYW1lIjoiSmFuYWthIiwiaWF0IjoxNTE2MjM5MDIyfQ.4bLMSSLAZ_FP9WtD4_RWTRblo2JlCzhISo0hYtWVj7E
             */
            Algorithm algorithm = Algorithm.HMAC256("SecKey");
            JWTVerifier verifier = JWT.require(algorithm).build();

            DecodedJWT verifiedJwt = verifier.verify(jwTokenStr);
            String userName = verifiedJwt.getSubject();
            // TODO can be verifying the user
            // ..........
            request.setAttribute("userId", userName);

            log.info("setting attributes ::: " + verifiedJwt.getSubject());
        } catch (SignatureVerificationException | TokenExpiredException | JWTDecodeException | InvalidClaimException e) {
            log.error("Error in authentication : " + e.getMessage());
            rejectRequest(res, "Authentication failed : " + e.getMessage());
            return;
        }

        chain.doFilter(request, response);
    }

    private void rejectRequest(HttpServletResponse res, String message) throws JsonProcessingException, IOException {
        res.setStatus(HttpStatus.UNAUTHORIZED.value());
        res.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("rescode", "97");
        map.put("resdes", message);
        String errorResponse = mapper.writeValueAsString(map);
        res.getWriter().write(errorResponse);
    }

    @Override
    public void destroy() {
        log.warn("Destructing filter :{}", this);
    }
    
}
