<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
div.generic {width:200px;background:#D8FBD6;border:1px dotted black;padding:8px;}
 div.gen1 {width:50%;background:#75CE6F;color:white;border:1px dotted green;text-align:left;padding:3px;}
 div.gen2 {width:90%;background:#75CE9F;color:white;border:1px dotted black;text-align:right;padding:6px;}
</style>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


<div class="generic">
<p>Generic content...</p>
<div class="gen1">
<p>Nested div content...</p>
</div>
<div class="gen2">
<table>
<tr>
<h3> <%out.println(getServletContext().getAttribute("name")); %></h3>
</tr>
<tr>
<h3> <%out.println(getServletContext().getAttribute("eid")); %></h3>
</tr>
<tr>
<h3> <%out.println(getServletContext().getAttribute("office")); %></h3>
</tr>
<tr>
<h3> <%out.println(getServletContext().getAttribute("designation")); %></h3>
</tr>
</table>
</div>
<div>


</div>
</div>

</body>
</html>