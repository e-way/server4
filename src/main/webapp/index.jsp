<%@page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body bgcolor="#ffffff">

   <%
       String imageFile = (String)request.getAttribute("imageFile");
       if (imageFile == null)
       {
   %>
   		    <img src="duke.waving.gif" alt="Duke waving">
   <%
       }
       else
       {
   %>
            <img src = "<%=imageFile%>" alt="">
   <%
       }
   %>
	    <h2>My name is Duke. What is yours?</h2>
	    <form action="/day02Solution/lab2Servlet" method="get">
	        <input type="text" name="name"></input>
	        <input type="submit" value="Submit"></input>
	        <input type="submit" value="Reset" onclick="form.action='/day02Solution/lab2Servlet?method=reset';"></input>
	    </form>
    <%
        String name = (String)request.getAttribute("nameback");
        if (name!=null)
        {
    %>
    	<h2>Hello,<%=name%>!</h2>
    <%
        }
    %>
    
</body>
</html>
