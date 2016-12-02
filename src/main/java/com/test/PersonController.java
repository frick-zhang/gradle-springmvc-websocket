package com.test;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.model.Person;

@Controller
public class PersonController {

//	@RequestMapping(value="/person/{id}/{name}",method = RequestMethod.GET)
//	@ResponseBody
//	public Map<String, Object> json(@PathVariable int id,@PathVariable String name) throws Exception{
//		Map<String, Object> modelMap = new HashMap<String,Object>();
//		modelMap.put("id", id);
//		modelMap.put("name", name);
//		modelMap.put("success", "true");
//		return modelMap;
//	}
	
	@RequestMapping(value="/person",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> jsonPost(@RequestParam(value="id",required=false) Integer id,@RequestParam(value="name",required=false) String name,HttpServletRequest request){
		System.out.println(request.getParameter("id"));
		System.out.println(request.getParameter("name"));
		System.out.println(request.getParameterMap());
		Map<String, Object> modelMap = new HashMap<String,Object>();
		modelMap.put("id", id);
		modelMap.put("name", name);
		modelMap.put("success", "true");
		return modelMap;
	}
	
	@RequestMapping(value="/model",method = RequestMethod.POST)
	//可以设置属性consumes="application/json"限制传送的json格式;设置produces="application/json"设置返回的数据为json格式
	@ResponseBody//将json字符串作为响应处理
	public Map<String, Object> jsonModel(@RequestBody Person person){
		System.out.print(person);
		Map<String, Object> modelMap = new HashMap<String,Object>();
		modelMap.put("id", person.getId());
		modelMap.put("name", person.getName());
		modelMap.put("success", "true");
		return modelMap;
	}
	
	@RequestMapping(value="/person/{id}/{name}/{status}",method = RequestMethod.GET)
	@ResponseBody
	public Person porfile(@PathVariable int id,@PathVariable String name){
		return new Person(id, name);
	}
	
	@RequestMapping(value="/json",method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> get(@RequestParam(value="id") Integer id,@RequestParam(value="name") String name){
		Map<String, Object> model = new HashMap<String,Object>();
		model.put("id", id);
		model.put("name", name);
		return model;
	}
}
