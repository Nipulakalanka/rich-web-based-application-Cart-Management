	package com;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/CartAPI")
public class CartAPI extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	Cart cartObj =new Cart();
	
       
   
    public CartAPI() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String output = cartObj.insertItem(request.getParameter("id"), 
				 request.getParameter("name"), 
				request.getParameter("price"), 
				request.getParameter("noofproducts")); 
				response.getWriter().write(output); 
		
	
		
		
	
		
	}

	
	private Map getParasMap(HttpServletRequest request) {
		 Map<String, String> map = new HashMap<String, String>(); 
			try
			 { 
			 Scanner scanner = new Scanner(request.getInputStream(), "UTF-8"); 
			 String queryString = scanner.hasNext() ? 
			 scanner.useDelimiter("\\A").next() : ""; 
			 scanner.close(); 
			 String[] params = queryString.split("&"); 
			 for (String param : params) 
			 { 
			String[] p = param.split("=");
			 map.put(p[0], p[1]); 
			 } 
			 } 
			catch (Exception e) 
			 { 
			 } 
			return map; 
	}


	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map paras = getParasMap(request); 
		 String output = cartObj.updateItem(paras.get("hidItemIDSave").toString(), 
		 paras.get("name").toString(), 
		 paras.get("noofproducts").toString()); 
		 response.getWriter().write(output); 
		
		
	}


	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map paras = getParasMap(request); 
		 String output = cartObj.deleteItem(paras.get("id").toString()); 
		response.getWriter().write(output);
	}
	
}


