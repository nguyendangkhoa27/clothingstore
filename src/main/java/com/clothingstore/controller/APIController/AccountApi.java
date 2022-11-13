package com.clothingstore.controller.APIController;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.clothingstore.DTO.MyUser;
import com.clothingstore.DTO.UserDTO;
import com.clothingstore.DTO.Short.Account;
import com.clothingstore.DTO.Short.AccountGoogle;
import com.clothingstore.DTO.Short.ChangePassword;
import com.clothingstore.DTO.Short.passwordReset;
import com.clothingstore.Jwt.JwtTokenProvider;
import com.clothingstore.entity.PasswordResetToken;
import com.clothingstore.exception.BadRequestException;
import com.clothingstore.exception.MessageResponse;
import com.clothingstore.service.IMailService;
import com.clothingstore.service.IPasswordResetTokenService;
import com.clothingstore.service.IUserService;

@RestController("accountLogin")
@RequestMapping("/api/account")
public class AccountApi {

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private IUserService userService;
	@Autowired
	private JwtTokenProvider provider;
	
	@Autowired
	private IMailService iMailService;
	
	@Autowired
	private IPasswordResetTokenService passwordResetTokenService;

	@PostMapping("/login")
	public MessageResponse<String> login(@RequestBody Account account, HttpServletResponse resp) {
		Authentication authen = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(account.getUserName(), account.getPassword()));
		MyUser myUser = (MyUser) authen.getPrincipal();
		String jwt = provider.genarateToken(myUser);
		return new MessageResponse<String>(HttpStatus.OK.value(), HttpStatus.OK, "login successful", jwt);
	}
	
	@PostMapping("/login-gg")
	public MessageResponse<String> loginGG(@RequestBody AccountGoogle accountGoogle){
		return new MessageResponse<String>(HttpStatus.OK.value(),HttpStatus.OK,"login successful",userService.loginGG(accountGoogle));
	}
	@GetMapping("/find-user")
	public MessageResponse<UserDTO> findUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		MyUser myUser = null;
		if (auth != null) {
			myUser = (MyUser) auth.getPrincipal();
		}
		return new MessageResponse<UserDTO>(HttpStatus.OK.value(), HttpStatus.OK, "find successful",
				userService.findUserByUserNameOrEmail(myUser.getUsername()));
	}
	@GetMapping("/all")
	public MessageResponse<List<UserDTO>> findAll(@RequestParam(name ="page",required = false) Integer page
			,@RequestParam(name="size",required = false) Integer size) {
		Pageable pageable = null;
		if(page !=null && page > 0 && size !=null && size > 0) {
			page=page-1;
			pageable = PageRequest.of(page, size);
		}
		return new MessageResponse<List<UserDTO>>(HttpStatus.OK.value(), HttpStatus.OK, "find successful", userService.findAll(pageable),userService.count());
	}

	@PostMapping("/register")
	public MessageResponse<UserDTO> signUp(@RequestBody UserDTO myuser) {
		try {
			return new MessageResponse<UserDTO>(HttpStatus.OK.value(), HttpStatus.OK, "register successfull",
					userService.save(myuser));
		} catch (Exception e) {
			throw e;
		}
	}
	
	@PostMapping("/add-user")
	public MessageResponse<UserDTO> addUser(@RequestBody UserDTO myuser) {
		try {
			return new MessageResponse<UserDTO>(HttpStatus.OK.value(), HttpStatus.OK, "add user successfull",
					userService.save(myuser));
		} catch (Exception e) {
			throw e;
		}
	}
	@PutMapping("/update")
	public MessageResponse<UserDTO> update(@RequestBody UserDTO user) {
		try {
			return new MessageResponse<UserDTO>(HttpStatus.OK.value(), HttpStatus.OK, "update user successfull",
					userService.update(user));
		} catch (Exception e) {
			throw e;
		}
	}
	
	@PostMapping("/create-reset-password")
	public MessageResponse<Integer> createResetPassword(@RequestBody Account account){
		try {
			PasswordResetToken token = passwordResetTokenService.genarateTokenResetPassword(account.getUserName());
			iMailService.sendMailResetPassword(token);
			return new MessageResponse<>(HttpStatus.OK.value(), HttpStatus.OK,"Confirm the code send to email",1);
		}catch (Exception e) {
			return new MessageResponse<>(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST, e.getMessage(),0);
		}
	} 
	
	@PostMapping("/reset-password")
	public MessageResponse<Integer> resetPassword(@RequestBody passwordReset passwordReset){
		try {
			passwordResetTokenService.updatePassword(passwordReset);
			return new MessageResponse<>(HttpStatus.OK.value(), HttpStatus.OK,"Reset password successful",1);
		}catch (Exception e) {
			return new MessageResponse<>(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST, e.getMessage(),0);
		}
	}
	
	@PostMapping("/check-code")
	public MessageResponse<Integer> checkCode(@RequestBody passwordReset passwordReset){
		try {
			PasswordResetToken passwordResetToken = passwordResetTokenService.checkValidToken(passwordReset.getToken());
			return new MessageResponse<>(HttpStatus.OK.value(), HttpStatus.OK,"valid code!",1);
		}catch (Exception e) {
			return new MessageResponse<>(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST, e.getMessage(),0);
		}
	}
	
	@PutMapping("/change-password")
	public MessageResponse<?> changePassword(@RequestBody ChangePassword changePassword){
		try {
			UserDTO dto = userService.changePassword(changePassword);
			return new MessageResponse<UserDTO>(HttpStatus.OK.value(), HttpStatus.OK,"Reset password successful",dto);
		}catch (Exception e) {
			return new MessageResponse<>(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
	
}
