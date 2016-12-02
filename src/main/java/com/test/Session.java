package com.test;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.model.User;

/**
 * 通过@SessionAttributes存储和获取临时的session
 * @author 章驰
 * @version 1.0*/
@Controller
@SessionAttributes(value="currentUser")
public class Session {

	@RequestMapping("/setSession")
	public String setUserSession(Model model,User user){
		model.addAttribute("currentUser", user);
		return null;
	}
	
	@RequestMapping("/setSession/default")
	@ResponseBody
	public Map<String, Object> setDefaultUserSession(Model model){
		Map<String, Object> map = new HashMap<String,Object>();
		User user = new User();
		user.setId(1);
		user.setUsername("default");
		user.setPassword("default");
		model.addAttribute("currentUser", user);
		map.put("success", true);
		map.put("user", user);
		return map;
	}
	
	@RequestMapping("/getUserSession")
	@ResponseBody
	public User getUserSession(@ModelAttribute("currentUser") User user){
		return user;
	}
}
