<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<h2>Create Account</h2>
    <p style="color:red;">${param.msg}</p></br>
<form method=post>
	
	Name: <input type=text name=name ></br>
	UserName: <input type=text name=username ></br>
	Password: <input type=password name=password1 ></br>
	Retype Password: <input type=password name=password2 ></br>
	<input type=submit value=Submit>
	
</form>
<p><a href="Login">Login</a></p>