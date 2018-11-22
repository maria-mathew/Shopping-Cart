<%@ page import="testpack.Item" language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<h2 align="left">Edit Item</h2>
<p style="color:red;">${param.msg}</p></br>
<% 
	Item i = (Item) request.getAttribute("item");
%>

<form method="post">
	Item Id: <input type=text value= <%=i.getId() %> name=id readonly></br>
	Name: <input type=text value= <%=i.getName() %> name=name></br>
	Quantity: <input type=text value= <%=i.getQty() %> name=qty></br></br>
	<input type=submit value=Submit></br>
</form>
<a href=Home>Go back to home</a>