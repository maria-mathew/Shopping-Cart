<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<h2>Change Login Name</h2>
<p style="color:red;">${param.msg}</p></br>
<form method=post>
	UID: <input type=text name=uid value=<%=request.getAttribute("uid") %> readonly></br>
	Login Name: <input type=text name=loginname value=<%=request.getAttribute("loginname") %>></br>
	<input type=submit value=Submit>
	
</form>
<p><a href="Home">Go back to home</a></p>