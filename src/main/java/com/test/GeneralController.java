package com.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.model.Person;  
  
@Controller  
public class GeneralController {  
  
	String message = "Welcome to Spring MVC";
	
	@ModelAttribute
	public void Add(Model model) {
		
		model.addAttribute("message", message);
	}
	
    @RequestMapping(value="/index")  
    //public String index_jsp(@ModelAttribute Person person){
    public String index_jsp(@RequestParam(value="name",required=false,defaultValue="Spring")String name,Model model){
//    	ModelAndView mv = new ModelAndView("test");
//    	mv.addObject("name", name);
//    	return mv;
    	model.addAttribute("name", name);
    	return "test";
    }
    
    /**
     * 重定向到另一个Requestmapping
     * @param name 传递到另一个Requestmapping的参数*/
    @RequestMapping(value="/redirect")
    public String redirect(@RequestParam(value="name",required=false,defaultValue="redirect")String name){
    	return "redirect:/index?name="+name;
    }
    
    /**
     * 文件上传（包括图片）
     * @author 章驰
     * @exception IOException IllegalStateException
     * @param image 上传的图片
     * @param request 
     */
    @RequestMapping(value="/file")
    @ResponseBody
    public Map<String, Object> fileUpload(@RequestParam(value="image",required=false)MultipartFile image,HttpServletRequest request){
    	
    	Map<String, Object> result = new HashMap<String, Object>();
    	if(image!=null&&!image.isEmpty()){
    		String path = request.getSession().getServletContext().getRealPath("upload");
    		String name = image.getOriginalFilename();
    		System.out.println(path);
    		System.out.println(name);
    		System.out.println(image.getContentType());
    		System.out.println(image.getSize()/1024);
    		File targetFile = new File(path, name);
    		if(!targetFile.exists())
    			targetFile.mkdirs();
    		try {
				image.transferTo(targetFile);
				result.put("success", true);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				result.put("success", true);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				result.put("success", true);
			}
    		return result;
    	}
    	return null;
    }
    
    /**
     * 多文件上传
     * @param request 多文件上传请求
     * @return 每个文件上传成功与否的json
     * @exception IOException
     * @exception IllegalStateException*/
    @RequestMapping("/multifile")
    @ResponseBody
    public Map<String, Object> multiFile(MultipartHttpServletRequest request){
    	Map<String, Object> result = new HashMap<String,Object>();
    	Map<String, MultipartFile> fileMap = request.getFileMap();
    	System.out.println("文件个数为:"+fileMap.size());
    	Set<String> fileSet = fileMap.keySet();
    	Iterator<String> fileNameIterator = fileSet.iterator();
    	while(fileNameIterator.hasNext()){
    		String uploadFileName = fileNameIterator.next();
    		System.out.println(uploadFileName);
    		MultipartFile file = fileMap.get(uploadFileName);
    		uploadFileName = file.getOriginalFilename();
    		System.out.println(uploadFileName);
    		uploadFileName = (new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss").format(new Date()))+System.nanoTime()+uploadFileName;
    		String path = request.getSession().getServletContext().getRealPath("upload");
    		File targetFile = new File(path, uploadFileName);
    		if(!targetFile.exists())
    			targetFile.mkdirs();
    		try {
    			file.transferTo(targetFile);
				result.put(uploadFileName, true);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				result.put(uploadFileName, false);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				result.put(uploadFileName, false);
			}
    	}
		return result;
    }
    
    /**
     * 文件下载
     * @param fileName 文件名
     * @param type 文件类型,默认jpg
     * @param request request请求
     * @return ResponseEntity
     * @exception IOException*/
    @RequestMapping(value="/download/{fileName}",method=RequestMethod.GET)
    public ResponseEntity<byte[]> download(@PathVariable String fileName,@RequestParam(value="type",required=false,defaultValue="jpg")String type,HttpServletRequest request){
    	try {
    		//转换成ISO8859-1编码,用以中文文件名下载
    		fileName = fileName==null?"1":fileName;
    		System.out.println(fileName);
			String fileNameEncode = new String(fileName.getBytes(), "ISO8859-1");
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
    		String path = request.getSession().getServletContext().getRealPath("download");
    		File file = new File(path+"/"+fileName+"."+type);
    		headers.setContentDispositionFormData("attachment", fileNameEncode+"."+type);
    		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers,HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return null;
    }
    
}  
