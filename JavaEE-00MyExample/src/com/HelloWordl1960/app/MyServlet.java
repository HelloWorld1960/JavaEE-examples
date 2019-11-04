package com.HelloWordl1960.app;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MyServlet
 */
@WebServlet("/MyServlet")
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Ingresando al metodo GET de MyServlet.");
		//Obtener parametros del metodo GET.
		System.out.println(request.getParameter("var1"));
		System.out.println(request.getParameter("var2"));
		
		
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Ingresando al metodo POST de MyServlet.");
		//Obtener parametros del metodo POST.
		System.out.println(request.getParameter("nombre"));
		
		//Scope request.
		//Obtener atributo para pasarlo al JSP.
		request.setAttribute("nameRequest", request.getParameter("nombre"));
		
		//Scope session.
		request.getSession().setAttribute("nameSession", request.getParameter("nombre"));
		
		//Scope context.
		getServletContext().setAttribute("nameContext", request.getParameter("nombre"));
		
		//Redireccionando al JSP.
		RequestDispatcher rd = request.getRequestDispatcher("/presentacion.jsp");
		rd.forward(request, response);
		
		//doGet(request, response);
	}

}
