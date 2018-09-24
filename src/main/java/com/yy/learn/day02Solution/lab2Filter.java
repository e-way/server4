package com.yy.learn.day02Solution;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Servlet Filter implementation class lab2Filter
 */
public class lab2Filter implements Filter {

    /**
     * Default constructor. 
     */
    public lab2Filter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		String name = (String)request.getParameter("name");
		if (name != null)
		{
			request.setAttribute("nameFromFilter", normalizeName(name));
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig config) throws ServletException {
		String path = config.getServletContext().getRealPath("/");
		List<String>imageFiles = getImageFiles(path);
		config.getServletContext().setAttribute("gifList", imageFiles);
	}
	
	private static String normalizeName(String name)
	{
		if (name!=null && !name.isEmpty())
		{
			return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
		}
		return null;
	}
	
	private static List<String> getImageFiles(String path)
	{
		if (path != null )
		{
		    File filePath = new File(path);
		    if (filePath.isDirectory())
		    {
		    	filePath.exists();
		    	List<File>files = Arrays.asList(filePath.listFiles());
				List<String>gifFiles = files.stream()
						.filter(file -> getFileExtension(file).equalsIgnoreCase("gif"))
						.map(file->file.getName())
						.collect(Collectors.toList());
				return gifFiles;
		    }
		}
		return new ArrayList<String>();
	}
	
	private static String getFileExtension(File file)
	{
		String extension = "";
		if (file != null && file.exists())
		{
			String fileName = file.getName();
			int i = fileName.lastIndexOf(".");
			if (i > 0)
			{
				extension = fileName.substring(i + 1);
			}
		}
		return extension;
	}
}
