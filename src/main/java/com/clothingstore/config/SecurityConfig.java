package com.clothingstore.config;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.clothingstore.Jwt.JwtAuthenticationFilter;
import com.clothingstore.exception.MessageResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	public UserDetailsService customUserDetail;
	
	private AuthenticationEntryPoint authenticationEntryPoint() {
		return new AuthenticationEntryPoint() {
			
			@Override
			public void commence(HttpServletRequest request, HttpServletResponse response,
					AuthenticationException authException) throws IOException, ServletException {
				response.setHeader("error", authException.getMessage());
				response.setStatus(HttpStatus.FORBIDDEN.value());
				response.setContentType(MediaType.APPLICATION_JSON_VALUE);
				new ObjectMapper().writeValue(response.getOutputStream(),new MessageResponse<String>(HttpStatus.BAD_REQUEST.value(),HttpStatus.BAD_REQUEST,authException.getMessage(),null)) ;
				
			}
		};
	}
	private AccessDeniedHandler accessDeniedHandler() {
		return new AccessDeniedHandler() {
			
			@Override
			public void handle(HttpServletRequest request, HttpServletResponse response,
					AccessDeniedException accessDeniedException) throws IOException, ServletException {
				response.setHeader("error", accessDeniedException.getMessage());
				response.setStatus(HttpStatus.FORBIDDEN.value());
				response.setContentType(MediaType.APPLICATION_JSON_VALUE);
				new ObjectMapper().writeValue(response.getOutputStream(),new MessageResponse<String>(HttpStatus.BAD_REQUEST.value(),HttpStatus.BAD_REQUEST,accessDeniedException.getMessage(),null)) ;
				
			}
		};
	}
	@Bean
	public JwtAuthenticationFilter authenticationFilter() {
		return new JwtAuthenticationFilter();
	}
	
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
//	@Bean
//	CorsConfigurationSource corsConfigurationSource() {
//		CorsConfiguration configuration = new CorsConfiguration();
//		configuration.setAllowedOrigins(Arrays.asList("*"));
//		configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE"));
//		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		source.registerCorsConfiguration("/**", configuration);
//		return source;
//	}

	@Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//auth.inMemoryAuthentication().withUser("aaa").password(encoder().encode("aaa")).roles("ADMIN");
		auth.userDetailsService(customUserDetail).passwordEncoder(encoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
			http.csrf().disable();
			http.cors();
			http.authorizeRequests().antMatchers("/api/account/register").permitAll()
			.antMatchers("/to-tinh").permitAll()
			.antMatchers("/api/account/create-reset-password").permitAll()
			.antMatchers("/api/account/reset-password").permitAll()
			.antMatchers("/api/account/check-code").permitAll()
			.antMatchers("/api/account/login").permitAll()
			.antMatchers("/api/account/login-gg").permitAll()
			.antMatchers("/api/account/find-user").permitAll()
			.antMatchers(HttpMethod.GET, "/","/*.jsp").permitAll()
			.antMatchers(HttpMethod.GET,"/api/product/**").permitAll()
			.antMatchers(HttpMethod.GET,"/api/color/**").permitAll()
			.antMatchers(HttpMethod.GET,"/api/size/**").permitAll()
			.antMatchers(HttpMethod.GET,"/api/category/**").permitAll()
			.antMatchers(HttpMethod.POST,"/api/account/add-user").hasAnyAuthority("ADMIN")
			.antMatchers("/api/product/").hasAnyAuthority("ADMIN")
			.antMatchers("/api/color/").hasAnyAuthority("ADMIN")
			.antMatchers("/api/size/").hasAnyAuthority("ADMIN")
			.antMatchers("/api/category/").hasAnyAuthority("ADMIN")
			.antMatchers("/api/account/all").hasAnyAuthority("ADMIN")
			.antMatchers("/api/order/admin/**").hasAnyAuthority("ADMIN")
			.antMatchers(HttpMethod.GET,"/trang-chu").permitAll().antMatchers("/swagger-ui.html").permitAll()
			.and().exceptionHandling().authenticationEntryPoint(authenticationEntryPoint()).and()
			.exceptionHandling().accessDeniedHandler(accessDeniedHandler());
			//khai báo filter kiểm tra jwt
			http.addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter.class);
		
//		http
//		.formLogin().loginPage("/login")
//		.defaultSuccessUrl("/trang-chu").failureUrl("/login?success=fail").usernameParameter("username")
//		.passwordParameter("password");
//		http.exceptionHandling().authenticationEntryPoint((request,respone,e)->{
//			respone.setHeader("error","Access Denied");
//			respone.setStatus(HttpStatus.FORBIDDEN.value());
//			respone.setContentType(MediaType.APPLICATION_JSON_VALUE);
//			Map<String, String> error = new HashMap<>();
//			error.put("message", "Access Denied");
//			
//			
//		});
		}
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**");
	}
	
	
	
	
	
	
	
//	@Bean
//    public AuthenticationManager authenticationManagerBean(HttpSecurity http) throws Exception {
//        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
//        authenticationManagerBuilder.inMemoryAuthentication().withUser("aaa").password(encoder().encode("aaa")).roles("ADMIN");
//        return authenticationManagerBuilder.build();
//    }
//	@Bean
//	protected InMemoryUserDetailsManager userDetailsService() {
//        List<UserDetails> myUsers = new ArrayList<>();
//        List<GrantedAuthority> author = new ArrayList<>();
//        author.add(new SimpleGrantedAuthority("admin"));
//        UserDetails user = new User("user", "123456", author);
//        myUsers.add(user);
//        return new InMemoryUserDetailsManager(myUsers);
//    }
//	@Bean
//	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//		http.csrf().disable();
//		http.authorizeHttpRequests().antMatchers("/dang-nhap").permitAll()
//		.anyRequest().authenticated().and()
//		.formLogin().loginPage("/dang-nhap").defaultSuccessUrl("/trang-chu").failureUrl("/dang-nhap?success=faild")
//		.usernameParameter("username").passwordParameter("password").permitAll()
//		;
//		 http.headers().frameOptions().sameOrigin();
//		return http.build();
//	}

	

}
