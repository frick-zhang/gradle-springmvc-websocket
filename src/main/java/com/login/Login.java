package com.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value="")
public class Login {

	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String getLogin(){
		return "login";
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(@RequestParam(value="username",required=true)String username,@RequestParam(value="password",required=true)String password){
		return "index";
	}
}
