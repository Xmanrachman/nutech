package com.nutech.test.sims.ppob.authapi.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import com.nutech.test.sims.ppob.authapi.interfaces.JwtServiceInterface;
import com.nutech.test.sims.ppob.authapi.interfaces.UserServiceInterface;
import com.nutech.test.sims.ppob.dto.response.error.ResponseHandlingError;
import com.nutech.test.sims.ppob.response.ResponseGeneral;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	private final HandlerExceptionResolver handlerExceptionResolver;
    private final JwtServiceInterface jwtService;
    private final UserServiceInterface userService;
    
    @Autowired
    private ResponseHandlingError handlingError;
   

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,@NonNull HttpServletResponse response,@NonNull FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        
        log.info("Checking is authHeader :"+authHeader);
        ResponseGeneral responseGeneral = new ResponseGeneral();
        if (authHeader == null || !authHeader.startsWith("Bearer")) {
        	 filterChain.doFilter(request, response);
        	 responseGeneral = unAuthenticationFaield();
            //return ;
        }
        
        try {
            final String jwt = authHeader.substring(7);
            final String userEmail = jwtService.extractUserName(jwt);

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (userEmail != null && authentication == null) {
                UserDetails userDetails = this.userService.userDetailsService().loadUserByUsername(userEmail);
                
                if (jwtService.isTokenValid(jwt, userDetails)) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );

                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            } else  if (userEmail != null && authentication != null) {
            	// () {
            		
            	//}
            }

            filterChain.doFilter(request, response);
        } catch (Exception exception) {
            handlerExceptionResolver.resolveException(request, response, null, exception);
        }
    }

    public ResponseGeneral unAuthenticationFaield() {
    	ResponseGeneral response = new ResponseGeneral();
    	response = handlingError.errorUnAuthorized();
    	
    	return response;
    	
    	
    }
}
