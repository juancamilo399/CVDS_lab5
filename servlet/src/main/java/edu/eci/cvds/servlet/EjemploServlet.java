package edu.eci.cvds.servlet;

import java.io.IOException;
import java.io.Writer;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.net.MalformedURLException;
import java.io.FileNotFoundException;
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
       Optional<String> optName = Optional.ofNullable(req.getParameter("id"));
       String id = optName.isPresent() && !optName.get().isEmpty() ? optName.get() : null;
       int ids=-1;
	   resp.setContentType("text/html");
	try{
	ids=Integer.parseInt(id);
	Todo todo=Service.getTodo(ids);
	resp.setStatus(HttpServletResponse.SC_OK);
	ArrayList<Todo> todos=new ArrayList<Todo>();
	todos.add(todo);
    responseWriter.write(Service.todosToHTMLTable(todos));
	}
	catch(NumberFormatException e){
	resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	}
	catch(FileNotFoundException e){
		resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		 responseWriter.write(NotFoundMessage());
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
   
   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
	   Writer responseWriter = resp.getWriter();
       Optional<String> optName = Optional.ofNullable(req.getParameter("id"));
       String id = optName.isPresent() && !optName.get().isEmpty() ? optName.get() : null;
       int ids=-1;
	   resp.setContentType("text/html");
	try{
	ids=Integer.parseInt(id);
	Todo todo=Service.getTodo(ids);
	resp.setStatus(HttpServletResponse.SC_OK);
	ArrayList<Todo> todos=new ArrayList<Todo>();
	todos.add(todo);
    responseWriter.write(Service.todosToHTMLTable(todos));
	}
	catch(NumberFormatException e){
	resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	}
	catch(FileNotFoundException e){
		resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		 responseWriter.write(NotFoundMessage());
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
	   
	
   private String NotFoundMessage(){
	  String mensaje="<!DOCTYPE html> <html> <head> <title>Start Page</title></head><body><h1>No existe un item con el identificador dado</h1></body></html>";
	  return mensaje; 
	 
   }
   
}
