package edu.eci.cvds.servlet;

import java.io.IOException;
import java.io.Writer;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.net.MalformedURLException;
import javax.servlet.http.HttpServletResponse;
import edu.eci.cvds.servlet.model.Todo;
import java.util.*;


@WebServlet(
    urlPatterns = "/ejemploServlet"
)
public class EjemploServlet extends HttpServlet{
    static final long serialVersionUID = 35L;

    @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       Writer responseWriter = resp.getWriter();
       Optional<String> optName = Optional.ofNullable(req.getParameter("ids"));
       String ids = optName.isPresent() && !optName.get().isEmpty() ? optName.get() : null;
       int id=-1;
	try{
	id=Integer.parseInt(ids);
	Todo todo=Service.getTodo(id);
	resp.setContentType("text/html");
	resp.setStatus(HttpServletResponse.SC_OK);
	ArrayList<Todo> todos=new ArrayList<Todo>();
	todos.add(todo);
        responseWriter.write(Service.todosToHTMLTable(todos));
	}
	catch(NumberFormatException e){
	resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	}
	catch(MalformedURLException e){
	resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);	
	}
	catch(Exception e){
	resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	}
	
		
       //resp.setStatus(HttpServletResponse.SC_OK);
       //responseWriter.write("Hello" + id + "!");
       responseWriter.flush();
   }
}
