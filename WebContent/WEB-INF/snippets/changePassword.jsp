<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<h2>Change Password</h2>
<p style="color:red;">${param.msg}</p></br>
<form method=post>
	UID: <input type=text name=uid value=<%=request.getAttribute("uid") %> readonly></br>
	Current Password: <input type=text name=currentPassword></br>
	New Password: <input type=password name=newPass1></br>
	Retype Password: <input type=password name=newPass2></br>
	<input type=submit value=Submit>
	
</form>
<p><a href="Home">Go back to home</a></p>