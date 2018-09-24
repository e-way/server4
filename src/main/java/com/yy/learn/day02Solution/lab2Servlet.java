package com.yy.learn.day02Solution;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class lab2Servlet
 */
public class lab2Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public lab2Servlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		if (method !=null && method.equals("reset"))
		{
			reset(request, response);
		}
		else
		{
			List<String>gifFiles = (List<String>)getServletContext().getAttribute("gifList");
	        String name =(String)request.getAttribute("nameFromFilter");
	        if (name != null)
	        {
	        	if (gifFiles.contains(name + ".GIF"))
	        	{
	        		request.setAttribute("nameback", name);
	        		request.setAttribute("imageFile", name+".GIF");
	        	}
	        	else
	        	{
	        		request.setAttribute("nameback", name);
	        	}
	        }
	        request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
		
 	}
	
	private void reset(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setAttribute("imageFile", null);
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
