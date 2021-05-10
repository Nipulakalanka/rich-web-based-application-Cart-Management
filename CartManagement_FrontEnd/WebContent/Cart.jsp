<%@page import= "com.Cart" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="View/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/Cart.js"></script>
</head>
<body>

<div class="container"><div class="row"><div class="col-6">

<h1>Cart Management </h1>
<form id="formItem" name="formItem" method="post" action="Cart.jsp">
Cart Code:
<input id="id" name="id" type="text" 
 class="form-control form-control-sm">
<br> Item Name: 
<input id="name" name="name" type="text" 
 class="form-control form-control-sm">
<br> Item price: 
<input id="price" name="price" type="text" 
 class="form-control form-control-sm">
<br> Number Of Products: 
<input id="noofproducts" name="noofproducts" type="text" 
 class="form-control form-control-sm">
<br>
<input id="btnSave" name="btnSave" type="button" value="Save" 
 class="btn btn-primary">
<input type="hidden" id="hidItemIDSave" name="hidItemIDSave" value="">
</form>

<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>



<div id="divItemsGrid">

<%
 Cart itemObj = new Cart(); 
 out.print(itemObj.readItems()); 
%>
</div>

</div></div></div>

</body>
</html>