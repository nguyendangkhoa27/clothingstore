package com.clothingstore.Convert;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.management.relation.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.clothingstore.DTO.MyUser;
import com.clothingstore.DTO.UserDTO;
import com.clothingstore.DTO.Short.Account;
import com.clothingstore.DTO.Short.AccountGoogle;
import com.clothingstore.DTO.Short.RoleShort;
import com.clothingstore.entity.EntityCart;
import com.clothingstore.entity.EntityRole;
import com.clothingstore.entity.EntityUser;
import com.clothingstore.service.IRoleService;

@Component
public class UserConvert implements abstractConvert<UserDTO, EntityUser> {

	@Autowired
	private PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder() ;
	}
	
	@Autowired
	private IRoleService roleService;
	
	@Autowired
	private RoleConvert roleConvert;
	
	@Autowired
	private CartConvert cartConvert;
	@Override
	public UserDTO toDTO(EntityUser convert) {
		try {
			if(convert!=null) {
			List<RoleShort> authors = new ArrayList<>();
			convert.getRoles().forEach(e ->
					authors.add(new RoleShort(e.getCode()))
			);
			UserDTO DTO = new UserDTO();
			DTO.setRole(authors);
			DTO.setEmail(convert.getEmail());
			DTO.setFirstName(convert.getFirstName());
			DTO.setLastName(convert.getLastName());
			DTO.setFullName(convert.getFirstName() + " "+ convert.getLastName());
			DTO.setPhoneNumber(convert.getPhoneNumber());
			DTO.setId(convert.getId());
			DTO.setIsActive(convert.getIsActive());
			DTO.setUserName(convert.getUserName());
			DTO.setUserGoogleId(convert.getUserGoogleId());
			return DTO;
			}else {
				return null;
			}
		}catch (Exception e) {
			throw e;
		}
		
	}

	@Override
	public EntityUser toEntity(UserDTO convert) {
		try {
			EntityUser user = new EntityUser();
			//nếu user không nhập role thì không convert
			if(convert.getRole()!=null) {//role khác null
				List<String> Roles = new ArrayList<>(); //tạo danh sách roleName
				convert.getRole().forEach(e -> //add  roleName 
					Roles.add(e.getRole())
				);
				//lấy danh sách role Enitity 
				List<EntityRole> entityRoles = roleService.findByRoleCode(Roles);
				//set role
				user.setRoles(entityRoles);
			}
			
			if(convert.getEmail()!=null) {
				user.setEmail(convert.getEmail().toLowerCase());	
			}
			
			if(convert.getFirstName() != null) {
			user.setFirstName(convert.getFirstName());
			}
			if(convert.getLastName() != null) {
			user.setLastName(convert.getLastName());
			}
			if(convert.getPassword() != null) {
				user.setPassword(passwordEncoder().encode(convert.getPassword()));
			}
			if(convert.getPhoneNumber()!=null) {
				user.setPhoneNumber(convert.getPhoneNumber());
			}
			if(convert.getUserName() != null) {
				user.setUserName(convert.getUserName());
			}else {
				Random random = new Random();
				String userName = convert.getEmail().split("@")[0];
					   userName =  userName + (random.nextInt(9999) + 1000);
				user.setUserName(userName);
			}
			if(convert.getIsActive() != null) {
				user.setIsActive(convert.getIsActive());
			}else if(convert.getId() == null) {
				//nếu client không nhập active => true
				user.setIsActive(true);
			}
			return user;
		}catch (Exception e) {
			throw e;
		}
	}

	@Override
	public EntityUser newToOld(EntityUser old, EntityUser neww) {
		try {
			if(neww.getEmail()!=null) {
				old.setEmail(neww.getEmail());	
			}
			if(neww.getRoles()!=null) {
				old.setRoles(neww.getRoles());
			}
			if(neww.getFirstName() != null) {
				old.setFirstName(neww.getFirstName());
			}
			if(neww.getLastName() != null) {
				old.setLastName(neww.getLastName());
			}
			if(neww.getPhoneNumber()!=null) {
				old.setPhoneNumber(neww.getPhoneNumber());
			}
			if(neww.getIsActive() != null) {
				old.setIsActive(neww.getIsActive());
			}
			return old;
		} catch (Exception e) {
			throw e;
		}
		
	}

	@Override
	public List<UserDTO> toDTO(List<EntityUser> entities) {
		try {
		List<UserDTO> dtos = new ArrayList<>();
		for (EntityUser User : entities) {
			dtos.add(toDTO(User));
		}
		return dtos;
		}catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<EntityUser> toEntity(List<UserDTO> DTOs) {
		// TODO Auto-generated method stub
		return null;
	}

	public MyUser UserDTOToMyUser(UserDTO dto) {
		List<GrantedAuthority> author = new  ArrayList<>();
		dto.getRole().forEach(e->author.add(new SimpleGrantedAuthority(e.getRole())));
		MyUser myUser = new MyUser(dto.getEmail(), "",author);
		return myUser;
	}
	
	public EntityUser AccountGGtoEntity(AccountGoogle accountGoogle) {
		try {
		EntityUser user = new EntityUser();
		String[] name = accountGoogle.getDisplayName().split(" "); 
		String firstName = name[name.length-1] ;
		String lastName = accountGoogle.getDisplayName().substring(0, (accountGoogle.getDisplayName().indexOf(firstName)-1));
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(accountGoogle.getEmail());
		user.setUserGoogleId(accountGoogle.getuId());
		Random random = new Random();
		String userName = accountGoogle.getEmail().split("@")[0];
			   userName =  userName + (random.nextInt(9999) + 1000);
		user.setUserName(userName);
		user.setPassword(passwordEncoder().encode("A123456@"));
		user.setIsActive(true);
		return user;
		}catch (Exception e) {
			throw e;
		}
	}
}
