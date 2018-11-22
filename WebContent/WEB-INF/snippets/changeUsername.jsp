<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<h2>Change User Name</h2>
<p style="color:red;">${param.msg}</p></br>
<form method=post>
	UID: <input type=text name=uid value=<%=request.getAttribute("uid") %> readonly></br>
	UserName: <input type=text name=username value=<%=request.getAttribute("username") %>></br>
	<input type=submit value=Submit>
	
</form>
<p><a href="Home">Go back to home</a></p>