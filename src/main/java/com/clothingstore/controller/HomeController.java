package com.clothingstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.clothingstore.entity.EntityUser;
import com.clothingstore.repository.IUserRepository;

@Controller
public class HomeController {
	@Autowired
	private IUserRepository u;
	@RequestMapping(value = "/trang-chu", method = RequestMethod.GET)
	public ModelAndView homePage() {
		ModelAndView mav = new ModelAndView("/WEB-INF/views/index.jsp");
//		EntityUser user= u.findByUserName("ADMIN").get(0);
//		mav.addObject("model", user);
		return mav;
	}
}
