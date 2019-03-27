package com.website;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

/**
 * Servlet implementation class Second
 */
@WebServlet("/Second")
public class Second extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Second() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession ses = request.getSession(true);
		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html");
		PrintWriter out= response.getWriter();
		int id = Integer.parseInt(request.getParameter("id"));
		if(id==0)
		{
			ses.invalidate();
			out.println("<a href='index.html' taget='_top'>LOGOUT</a>");
		}
		try {
			if(Dao.deleteProduct(id)>0)
			{
				out.println("SUCCESSFULLY DELETED");
				request.getRequestDispatcher("show.html").forward(request,response);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession ses = request.getSession(true);
		response.setContentType("text/html");
		PrintWriter out= response.getWriter();
		int id=0;
		
		Dao d = new Dao();
		
		ArrayList<Product> al = null;
		try {
			al = d.getProducts();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(int i=0;i<al.size();i++)
		{
			Product p1 = al.get(i);
			//out.println(p1.getPid()+"<br>");
			out.println(p1.getPname()+"<br>");
			out.println(p1.getPcategory()+"<br>");
			out.println(p1.getPdescription()+"<br>");
			out.println("<form action='Second' method='get'>");
			out.println("<a href='Second?id="+p1.getPid()+"'>delete</a>");
			out.println("</form>");
		}
		out.println("<a href='Second?id="+id+"'>LOGOUT</a>");
		
		
		
		
		
	}

}
