package com.jalaramrakhi.erpjr.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jalaramrakhi.erpjr.Utils.LocalstorageWrapper;
import com.jalaramrakhi.erpjr.entity.Company;
import com.jalaramrakhi.erpjr.entity.User;
import com.jalaramrakhi.erpjr.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import static com.jalaramrakhi.erpjr.config.Constants.ACCESS_TOKEN_VALIDITY_SECONDS;
import static com.jalaramrakhi.erpjr.config.Constants.HEADER_STRING;
import static com.jalaramrakhi.erpjr.config.Constants.SIGNING_KEY;
import static com.jalaramrakhi.erpjr.config.Constants.TOKEN_PREFIX;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;
    private com.jalaramrakhi.erpjr.entity.User creds;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }


    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest request,
            HttpServletResponse response)
            throws AuthenticationException {

        UsernamePasswordAuthenticationToken authRequest
                = getAuthRequest(request);
        setDetails(request, authRequest);

        return authenticationManager
                .authenticate(authRequest);
    }

    private UsernamePasswordAuthenticationToken getAuthRequest(
            HttpServletRequest request) {
        try {
            creds = new ObjectMapper().readValue(request.getInputStream(), com.jalaramrakhi.erpjr.entity.User.class);
            String username = creds.getUser_name();
            String password = creds.getUser_password();
            Long company = creds.getCompany().getCompany_id();

            String usernameDomain = String.format("%s%s%s", username.trim(),
                    String.valueOf(Character.LINE_SEPARATOR), company.toString());
            return new UsernamePasswordAuthenticationToken(
                    usernameDomain, password);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest req,
//                                                HttpServletResponse res) throws AuthenticationException {
//        try {
//            com.jalaramrakhi.erpjr.entity.User creds = new ObjectMapper()
//                    .readValue(req.getInputStream(), com.jalaramrakhi.erpjr.entity.User.class);
//
//            return authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(
//                            creds.getUser_name(),
//                            creds.getUser_password(),
//                            new ArrayList<>())
//            );
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {
        LocalstorageWrapper localstorageWrapper = new LocalstorageWrapper();
//        System.out.println(userRepository.toString());
//        User newUser = userRepository.findByUsername(creds.getUser_name());
        String token = Jwts.builder()
                .setSubject(creds.getUser_name())
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY_SECONDS))
                .signWith(SignatureAlgorithm.HS512, SIGNING_KEY)
                .compact();
//        res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
        localstorageWrapper.setCompany(creds.getCompany());
        localstorageWrapper.setToken(token);
        localstorageWrapper.setUser(creds);
        res.getWriter().write(localstorageWrapper.toString());
    }
}