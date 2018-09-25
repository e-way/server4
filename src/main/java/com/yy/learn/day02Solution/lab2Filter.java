package com.yy.learn.day02Solution;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.AsyncContext;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.PushBuilder;

/**
 * Servlet Filter implementation class lab2Filter
 */
public class lab2Filter implements Filter {
	private List<String> imageFiles;
	/**
	 * Default constructor.
	 */
	public lab2Filter() {
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		final AsyncContext asyncContext = request.startAsync();
		asyncContext.addListener(new ImageFileFindListener());
		asyncContext.start(new Runnable(){
			@Override
			public void run() {
				String path = request.getServletContext().getRealPath("/");
				imageFiles = getImageFiles(path);
			}
		});
		asyncContext.complete();
		
		String name = (String) request.getParameter("name");
		if (name != null) {
			String inputName = normalizeName(name);
			{
				if (imageFiles !=null && imageFiles.contains(inputName + ".GIF")) {
					request.setAttribute("nameback", inputName);
					request.setAttribute("imageFile", inputName + ".GIF");
				} else {
					request.setAttribute("nameback", inputName);
				}
			}
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig config) throws ServletException {
	}

	private static String normalizeName(String name) {
		if (name != null && !name.isEmpty()) {
			return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
		}
		return null;
	}

	private static List<String> getImageFiles(String path) {
		if (path != null) {
			File filePath = new File(path);
			if (filePath.isDirectory()) {
				filePath.exists();
				List<File> files = Arrays.asList(filePath.listFiles());
				List<String> gifFiles = files.stream().filter(file -> getFileExtension(file).equalsIgnoreCase("gif"))
						.map(file -> file.getName()).collect(Collectors.toList());
				return gifFiles;
			}
		}
		return new ArrayList<String>();
	}

	private static String getFileExtension(File file) {
		String extension = "";
		if (file != null && file.exists()) {
			String fileName = file.getName();
			int i = fileName.lastIndexOf(".");
			if (i > 0) {
				extension = fileName.substring(i + 1);
			}
		}
		return extension;
	}
}
