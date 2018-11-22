<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<h2>Add new item:</h2></br>
<p style="color:red;">${param.msg}</p></br>
<form method="post">
	
	UID: <input type=text name="uid" value=<%=request.getAttribute("uid")  %> readonly></br>
	Item Name: <input type=text name="name"></br>
	Quantity: <input type=text name="qty"></br>
	<input type=submit value=Submit></br></br>
	
	<a href="Home">Go Back Home</a>

	
</form>