package com.url.shortner.security.jwt;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Component;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	@Autowired
	private JwtUtils jwtTokenProvider; 
	
	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, 
			HttpServletResponse response, 
			FilterChain filterChain)
			throws ServletException, IOException {
		try {
			String jwt = jwtTokenProvider.getJwtFromHeader(request);

			if (jwt != null && jwtTokenProvider.validateToken(jwt)) {
			    String username = jwtTokenProvider.getUserNameFromJwtToken(jwt);
			    UserDetails userDetails = userDetailsService.loadUserByUsername(username);

			    // Set authentication in the context if needed
			    if(userDetails != null) {
			    	UsernamePasswordAuthenticationToken authentication =
					      new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
					    SecurityContextHolder.getContext().setAuthentication(authentication);
			    }
			    
			}
		}
		catch(Exception e){
			e.printStackTrace();
			
		}
		filterChain.doFilter(request, response);
		
	}

}
