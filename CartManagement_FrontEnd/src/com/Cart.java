package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;



public class Cart {
	
	private int price;
	private int noofproduct;
	
	private Connection connect() 
	 { 
	 Connection con = null; 
	 try
	 { 
	 Class.forName("com.mysql.cj.jdbc.Driver"); 
	 
	 //Provide the correct details: DBServer/DBName, username, password 
	 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/paf", "root", ""); 
	 } 
	 catch (Exception e) 
	 {e.printStackTrace();} 
	 return con; 
	 } 
	
	
	

	public String readItems() {
		
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 {return "Error while connecting to the database for reading."; } 
		 // Prepare the html table to be displayed
		 output = "<table border='1'><tr><th>Product Code</th><th>Product Name</th>" +
		 "<th>Product Price</th>" + 
		 "<th>Product Quantity</th>" +
		 "<th> Total price</th>"+
		 "<th>Update</th><th>Remove</th></tr>"; 
		 
		 String query = "select * from product"; 
		 Statement stmt = con.createStatement(); 
		 ResultSet rs = stmt.executeQuery(query); 
		 // iterate through the rows in the result set
		 while (rs.next()) 
		 { 
		 String id = Integer.toString(rs.getInt("id")); 
		 String code = rs.getString("code"); 
		 String name = rs.getString("name"); 
		 price = rs.getInt("price");
		 noofproduct = rs.getInt("noofproduct");
		 
		System.out.println(id);
		 // Add into the html table
		 output += "<tr><td><input id='hidItemIDUpdate' name='hidItemIDUpdate' type='hidden' value='" + id + "'>"
				 + code + "</td>";
		 output += "<td>" + name + "</td>"; 
		 output += "<td>" + price + "</td>"; 
		 output += "<td>" + noofproduct + "</td>";
		 output +="<td>" +getTotalPrice()+ "</td>";
		 // buttons
	 output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary' data-id='" + id + "'></td>"
	 + "<td><form method='post' action='Cart.jsp'>"
	 + "<input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-id='" + id + "'>"
	 + "<input name='hidItemIDDelete' type='hidden' "
	 + " value='" + id + "'>" + "</form></td></tr>"; 
		 } 
		 con.close(); 
		 // Complete the html table
		 output += "</table>"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "Error while reading the items."; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 } 
	
	public int getTotalPrice() {
		
		return price*noofproduct;
		
	}
	
	
	public String insertItem(String code, String name, String price, String noofproduct) 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for inserting."; } 
	 // create a prepared statement
	 String query = " insert into product(id,code,name,price,noofproduct)"
	 + " values (?, ?, ?, ?, ?)"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 preparedStmt.setInt(1, 0); 
	 preparedStmt.setString(2, code); 
	 preparedStmt.setString(3, name); 
	 preparedStmt.setDouble(4, Double.parseDouble(price)); 
	 preparedStmt.setString(5, noofproduct); 
	// execute the statement
	 preparedStmt.execute(); 
	 con.close(); 
	 String newItems = readItems();
	 output =  "{\"status\":\"success\", \"data\": \"" + 
			 newItems + "\"}"; 
	 } 

	catch (Exception e) 
	 { 
		output = "{\"status\":\"error\", \"data\": \"Error while inserting the item.\"}";  
	 System.err.println(e.getMessage()); 
	 } 
	return output; 
	}
	
	public String updateItem(String id, String name, String noofproduct)
	{ 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 {return "Error while connecting to the database for updating."; } 
		 // create a prepared statement
		 String query = "UPDATE product SET name=?,noofproduct=? WHERE id=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 
		 // binding values
		
		 preparedStmt.setString(1, name); 
		 preparedStmt.setString(2, noofproduct); 
	     preparedStmt.setString(3, id); 
		 
		 
		 
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 String newItems = readItems();
		 output =  "{\"status\":\"success\", \"data\": \"" + 
				 newItems + "\"}"; 
		 } 

		catch (Exception e) 
		 { 
			output = "{\"status\":\"error\", \"data\": \"Error while Updating the item.\"}";  
		
		System.err.println(e.getMessage());
		}
		return output;
		}
	
	public String deleteItem(String id) 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for deleting."; } 
	 // create a prepared statement
	 String query = "delete from product where id=?"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(id));
	 // execute the statement
	 preparedStmt.execute(); 
	 con.close(); 
	 String newItems = readItems();
	 output =  "{\"status\":\"success\", \"data\": \"" + 
			 newItems + "\"}"; 
	 } 

	catch (Exception e) 
	 { 
		output = "{\"status\":\"error\", \"data\": \"Error while deleting the item.\"}";  
	 System.err.println(e.getMessage()); 
	 } 
	return output; 
		}

}


