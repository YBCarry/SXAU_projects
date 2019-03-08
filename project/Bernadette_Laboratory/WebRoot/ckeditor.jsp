<%@ page language="java" contentType="text/html; charset=UTF-8"  
    pageEncoding="UTF-8"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">  
<html>  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
<script type="text/JavaScript" src="<%=request.getContextPath()%>/ckeditor/ckeditor.js"></script>  
<script type="text/javascript">       
    //<![CDATA[     
        CKEDITOR.replace('content',{toolbar:'Full', skin : 'kama'});     
    //]]>       
    </script>   
<title>USE CKEDITOR</title>  
</head>  
<body>  
    <textarea class="ckeditor" cols="80" id="content" name="content" rows="10">       
        CKEditor Test......(此处的内容会在编辑器中显示)  
     </textarea>    
</body>  
</html>  