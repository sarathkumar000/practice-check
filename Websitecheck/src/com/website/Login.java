package com.website;
import java.util.*;
import java.lang.*;
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

//import com.sun.corba.se.pept.transport.Connection;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	int j=0;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession ses = request.getSession(true);
		String pname=request.getParameter("pname");
		String pcategory=request.getParameter("pcategory");
		String pdesc=request.getParameter("pdesc");
		Product p = new Product();
		p.setPname(pname);
		p.setPcategory(pcategory);
		p.setPdescription(pdesc);
		try {
			int n = Dao.saveProduct(p);
			if(n>0)
			{
				out.println("<h1>PRODUCT ADDED SUCCESSFULLY<h1>");
				out.println("<form action='index2.html' ><input type='submit' value='ADD PRODUCTS'></form>");
				out.println("<form action='Second' method ='post'><input type='submit' value='VIEW PRODUCTS'></form>");
				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		  
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String em = request.getParameter("email");
		String pwd=request.getParameter("password");
		PreparedStatement ps = null;
		Dao d = new Dao();
		Connection con = d.getConnection();
	    HttpSession ses = request.getSession(true);
	    if(ses.isNew())
	    {
	    	out.println("WELCOME");
	    	out.println("YOUR SESSION ID id "+ ses.getId());
	    }
	   ses.setAttribute("1", em);
	    ses.setAttribute("2", pwd);
	    
	   
	    String here;
		try {
			ps = con.prepareStatement("select password from login where email=?");
			ps.setString(1, em);
		    ResultSet rs = ps.executeQuery();
		    
			if(rs.next())
			{
				
		   here = rs.getString(1);
		   if(here.equals(pwd))
			{
				//out.println("WELCOME SARATH ");
			  
			   getServletContext().setAttribute("1",em);
			   Dao d1 = new Dao();
				Admin p1=null;
				try {
					p1 = d1.getAdminDetails(em);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				int i = p1.getId();
				 String na = p1.getName();
				 String of = p1.getOffice();
				 String desig = p1.getDesignation();
				getServletContext().setAttribute("eid",i);
				getServletContext().setAttribute("name",na);
				getServletContext().setAttribute("office",of);
				getServletContext().setAttribute("designation",desig);
			
			   
				request.getRequestDispatcher("NEW.jsp").forward(request,response);
			   
				
			}
		   else
		   {
			   j++;
			  // out.println("User or password incorrect");
			   if(j<3)
			   {
				   
			   request.getRequestDispatcher("index.html").forward(request,response);
			   }
			   else
			   {
				   out.println("accOunt blOked");
				  
				  
			   }
		   }
          ses.invalidate();
         
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
		}
		
		// for admin details

		
		
		
		
		
		    
			
		
		
		
	}

}
