package com.website;

import java.sql.*;
import java.util.ArrayList;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

//import com.sun.corba.se.pept.transport.Connection;

public class Dao {
	
	public static   Connection getConnection()
	{
	Connection con=null;
	
		try
		{  
			Class.forName("com.mysql.jdbc.Driver");  
			 con =  DriverManager.getConnection("jdbc:mysql://localhost:3306/website","root","root"); 
		}
		catch(Exception e)
		{ 
			System.out.println(e);
	    }
		return con;
	}
public static int saveProduct(Product p) throws SQLException
{
	int id=0;
	//Dao d=new Dao();
	Connection con=getConnection();
	PreparedStatement ps=con.prepareStatement("insert into product12(pname,pcategory,pdescription) values(?,?,?)");
	
	ps.setString(1, p.getPname());
	ps.setString(2, p.getPcategory());
	ps.setString(3, p.getPdescription());
	id=ps.executeUpdate();
	
	return id;
	
	
	
	
}
public static int deleteProduct(int id) throws SQLException
{
	Connection con=getConnection();
	PreparedStatement ps=con.prepareStatement("delete from product12 where id = ? ");
	ps.setString(1, Integer.toString(id));
	return ps.executeUpdate();
}

public  ArrayList<Product> getProducts() throws SQLException
{
	Connection con=getConnection();
	PreparedStatement ps = con.prepareStatement("select * from product12");
	ResultSet re = ps.executeQuery();
	ArrayList<Product> al = new ArrayList();
	while(re.next())
	{
		
			int id = re.getInt(1);
		String pname=re.getString(2);
		String pcat=re.getString(3);
		String pdesc=re.getString(4);
		Product p = new Product();
		
			p.setPid(id);
		   p.setPname(pname);
		   p.setPcategory(pcat);
		   p.setPdescription(pdesc);
		   al.add(p);
		   
	}
	return al;
}
public Admin getAdminDetails(String s) throws SQLException
{
	Connection con=getConnection();
	PreparedStatement ps = con.prepareStatement("select * from admin where email=?");
	ps.setString(1, s);
	ResultSet re = ps.executeQuery();
	
	Admin a=new Admin();
	while(re.next())
	{
		
		String  name = re.getString(1);
		String designation = re.getString(2);
		int id=re.getInt(3);
		String office=re.getString(4);
		
		
			a.setId(id);
		   a.setName(name);
		   a.setDesignation(designation);
		   a.setOffice(office);
		   
		   
	}
	return a;
}

}
