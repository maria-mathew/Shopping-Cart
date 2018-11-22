<%@ page import="testpack.Item" language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<h2 align="left">Item</h2>
<table class="center" width="70%">
	<tr>
		
		<td align=right>
			<a href="Home">Go Back to Home</a> 
		</td>
	</tr>
	<tr>
		<td colspan=2 align=center>
			
			<table class="center">
				<%
					
					Item i = (Item)request.getAttribute("item");
					
						%>
							<tr>
								<td>Item ID: <%= i.getId() %></td>	
							</tr>
							<tr>
								<td>Item Name: <%= i.getName() %></td>
							</tr>
							<tr>
								<td>Quantity: <%= i.getQty() %></td>
							</tr>
						
			</table>
			
		</td>
	</tr>
</table>