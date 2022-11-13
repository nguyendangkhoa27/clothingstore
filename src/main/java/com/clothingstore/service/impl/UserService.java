package com.clothingstore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.clothingstore.Convert.UserConvert;
import com.clothingstore.DTO.MyUser;
import com.clothingstore.DTO.UserDTO;
import com.clothingstore.DTO.Short.AccountGoogle;
import com.clothingstore.DTO.Short.ChangePassword;
import com.clothingstore.Jwt.JwtTokenProvider;
import com.clothingstore.entity.EntityCart;
import com.clothingstore.entity.EntityUser;
import com.clothingstore.exception.BadRequestException;
import com.clothingstore.exception.NotFoundException;
import com.clothingstore.repository.ICartRepository;
import com.clothingstore.repository.IRoleRepository;
import com.clothingstore.repository.IUserRepository;
import com.clothingstore.service.IUserService;

@Service
public class UserService implements IUserService {

	@Autowired
	private PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder() ;
	}
	@Autowired
	private IRoleRepository iRoleRepository;

	@Autowired
	private IUserRepository iUserRepository;

	@Autowired
	private ICartRepository cartRepository;
	@Autowired
	private UserConvert userConvert;
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Override
	public UserDTO save(UserDTO myUser) {
		try {// kiểm tra đăng nhập
			Authentication authen = SecurityContextHolder.getContext().getAuthentication();
			boolean isAdmin = false;
			// nếu có đăng nhập => admin đang thêm tài khoản
			if (authen != null) {
				if (authen.getAuthorities() != null && authen.getAuthorities().size() > 0) {
					for (GrantedAuthority author : authen.getAuthorities()) {
						if (author.getAuthority().equalsIgnoreCase("ADMIN")) {
							isAdmin = true;
							break;
						}
					}
				}
			}
			if(iUserRepository.findOneByEmail(myUser.getEmail())==null) {
				EntityUser entityUser = userConvert.toEntity(myUser);
				if (!isAdmin) {// nếu không phải admin -> user đang đăng kí tài khoản chỉ được set role là user
					entityUser.setRoles(iRoleRepository.findByCode("USER"));
				}
				// nếu user không nhập role thì luôn set role là user
				entityUser = iUserRepository.save(entityUser);
				EntityCart cart = new EntityCart();
				cart.setUser(entityUser);
				cart.setId(entityUser.getId());
				cart = cartRepository.save(cart);
				entityUser.setCart(cart);
				return userConvert.toDTO(entityUser);
			}else {
				throw new BadRequestException("Email is already exists!!!");
			}
		} catch (Exception e) {
			throw e ;
		}
	}

	@Override
	public UserDTO update(UserDTO user) {
		try {
			// chỉ đăng nhập mới được update
			Authentication authen = SecurityContextHolder.getContext().getAuthentication();
			boolean isAdmin = false;
			if (authen.getAuthorities() != null && authen.getAuthorities().size() > 0) {
				for (GrantedAuthority author : authen.getAuthorities()) {
					if (author.getAuthority().equalsIgnoreCase("ADMIN")) {
						isAdmin = true;
						break;
					}
				}
			}
			EntityUser oldUser = iUserRepository.findById(user.getId()).get();
			EntityUser newUser = userConvert.toEntity(user);
			oldUser = userConvert.newToOld(oldUser, newUser);
			if (!isAdmin) {
				//nếu user thì chỉ được chỉnh sửa tài khoản của mình
				MyUser myUser = (MyUser) authen.getPrincipal();
				if(oldUser.getEmail().equalsIgnoreCase(myUser.getUsername())) {
					oldUser.setRoles(iRoleRepository.findByCode("USER"));
					return userConvert.toDTO(iUserRepository.save(oldUser));	
				}else {
					throw new AccessDeniedException("accessDenied");
				}
				
			} else {
				if (oldUser.getRoles() == null || oldUser.getRoles().size() <= 0) {
					oldUser.setRoles(iRoleRepository.findByCode("USER"));
				}
				return userConvert.toDTO(iUserRepository.save(oldUser));
			}

		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<UserDTO> findAll(Pageable pageable) {
		try {
			List<UserDTO> user = null;
			if (pageable != null) {
				user = userConvert.toDTO(iUserRepository.findAll(pageable).getContent());
			} else {
				user = userConvert.toDTO(iUserRepository.findAll());
			}
			return user;
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public UserDTO findUserByUserNameOrEmail(String userNameOrEmail) {
		try {
			UserDTO user = null;
			if (userNameOrEmail.contains("@")) {
				user = userConvert.toDTO(iUserRepository.findOneByEmail(userNameOrEmail));
			} else {
				user = userConvert.toDTO(iUserRepository.findOneByUserName(userNameOrEmail));
			}
			if (user == null) {
				throw new NotFoundException("User not found!");
			}
			return user;
		} catch (Exception e) {
			throw e;
		}
	}
	
	@Override
	public UserDTO findUserByEmailAndUId(String email, String uId) {
		return userConvert.toDTO(iUserRepository.findOneByEmailAndUserGoogleIdAndIsActive(email, uId,true));
	}
	
	@SuppressWarnings("serial")
	@Override
	public String loginGG(AccountGoogle accountGoogle) {
		try {
		UserDTO user = userConvert.toDTO(iUserRepository.findOneByEmail(accountGoogle.getEmail()));
		if(user==null) {
			EntityUser entityUser = userConvert.AccountGGtoEntity(accountGoogle);
			entityUser.setRoles(iRoleRepository.findByCode("USER"));
			user =userConvert.toDTO(iUserRepository.save(entityUser));
			EntityCart cart = new EntityCart();
			cart.setUser(entityUser);
			cart.setId(entityUser.getId());
			cart = cartRepository.save(cart);
			entityUser.setCart(cart);
		}
		if(user.getIsActive()==true && user.getUserGoogleId().equals(accountGoogle.getuId())) {
			MyUser myUser = userConvert.UserDTOToMyUser(user);
			String token = jwtTokenProvider.genarateToken(myUser);
			return token;
			}
			throw new AuthenticationException("loginFail") {};
		}catch (Exception e) {
			throw e;
		}
	}
	
	@Override
	public Integer count() {
		return (int) iUserRepository.count();
	}
	
	@Override
	public UserDTO resetPassword(EntityUser entityUser, String newPassword) {
		entityUser.setPassword(passwordEncoder().encode(newPassword));
		return userConvert.toDTO(iUserRepository.save(entityUser));
	}
	
	@Override
	public UserDTO changePassword(ChangePassword changePassword) {
		MyUser myUser =(MyUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		EntityUser user = iUserRepository.findOneByEmail(myUser.getUsername());
		if(passwordEncoder().matches(changePassword.getOldPassword(), user.getPassword())) {
			user.setPassword(passwordEncoder().encode(changePassword.getNewPassword()));
			user = iUserRepository.save(user);
		}else {
			throw new BadRequestException("old password incorrect!");
		}
		return userConvert.toDTO(user);
	}

}
