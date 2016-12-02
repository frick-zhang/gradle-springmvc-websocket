<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
</head>
<body>
<p>hello</p>
<form name="Form2" action="multifile" method="post"  enctype="multipart/form-data">
<h1>使用spring mvc提供的类的方法上传文件</h1>
<input type="file" name="image">
<input type="file" name="image2">
<input type="submit" value="upload"/>
</form>
<script type="text/javascript">
   $(function(){
	   $.ajax({
		   url:'model',
		   type:'post',
		   dataType:'json',
		   contentType : 'application/json',
		   //data:JSON.stringify({id:1,name:'a'}),
		   data:{id:1,name:'a'},
		   success:function(data){
			   console.log(data);
		   },
		   error:function(e){
			   console.log(e);
		   }
	   })
   })
</script>
</body>
</html>