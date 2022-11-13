package com.clothingstore.Jwt;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.clothingstore.DTO.MyUser;
import com.clothingstore.DTO.Short.APINotFilter;
import com.clothingstore.exception.NotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private JwtTokenProvider provider;

	@Autowired
	private UserDetailsService detailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		//bỏ qua các path không cần filter
		if(!alwaysFilter(request, response, filterChain)){
			try {
				// lấy jwt từ request
				String token = getJwtToRequest(request);
					// lấy thông tin username
					String userName = provider.getUserNameFromJWT(token);
					MyUser user = (MyUser) detailsService.loadUserByUsername(userName);
					if (user != null) {
						UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
								user, null, user.getAuthorities());
						authenticationToken.setDetails(new WebAuthenticationDetails(request));
						SecurityContextHolder.getContext().setAuthentication(authenticationToken);
					}else {
						throw new NotFoundException("Invalid token");
					}
					filterChain.doFilter(request, response);
			} catch (Exception e) {
				response.setHeader("error", e.getMessage());
                response.setStatus(FORBIDDEN.value());
                Map<String, String> error = new HashMap<>();
                error.put("message", e.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
			}
		}

	}

	private String getJwtToRequest(HttpServletRequest request) {
		// lấy bearer từ header
		String bearerToken = request.getHeader("Authorization");
		if(bearerToken !=null && bearerToken.startsWith("Bearer ")) {
			if (StringUtils.hasText(bearerToken) && provider.validateToken(bearerToken.substring(7))) {
				return bearerToken.substring(7);
			}
		}
		return null;
	}
	
	@SuppressWarnings("unlikely-arg-type")
	private boolean alwaysFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		APINotFilter api = new APINotFilter("/trang-chu",HttpMethod.GET);
		api.addAPI("/index.jsp",HttpMethod.GET).addAPI("/api/account/register").addAPI("/api/account/login");
		api.addAPI("/api/color",HttpMethod.GET)
		.addAPI("/api/category",HttpMethod.GET)
		.addAPI("/api/product",HttpMethod.GET)
		.addAPI("/api/size",HttpMethod.GET)
		.addAPI("/api/account/login-gg")
		.addAPI("/api/account/create-reset-password")
		.addAPI("/api/account/reset-password")
		.addAPI("/api/account/check-code")
		.addAPI("/to-tinh");
		List<APINotFilter> listAPI = api.getList();
		String s = request.getServletPath();
		if(listAPI !=null && listAPI.size() > 0) {
				for(APINotFilter a : listAPI) {
					if(request.getServletPath().contains(a.getServletPath())) {
						if(a.getHttpMethod() == null) {
							filterChain.doFilter(request, response);
							return true;
						}else if(a.getHttpMethod().name().equals(request.getMethod())) {
							filterChain.doFilter(request, response);
							return true;
						}
					}
				}
			
		}
		return false;
	}
	
}
