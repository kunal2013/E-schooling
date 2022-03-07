/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author priya
 */
public class add_school extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            Random n=new Random();
            int code=n.nextInt();
            String pass=Integer.toHexString(code);
            pass=pass.substring(0,6);
                      
           String name=request.getParameter("school_name");
          
           String district=request.getParameter("district");
           
           String city=request.getParameter("city");
           String schooltype=request.getParameter("school_type");
           String status=request.getParameter("status");
           String year=request.getParameter("year_of_establishment");
           Class.forName("oracle.jdbc.driver.OracleDriver");
           Connection con=DriverManager.getConnection("Jdbc:Oracle:thin:@localhost:1521:XE","system","india");
           String sql="insert into school (school_id,school_name,district,city,type_of_school,status,year_of_establishment,password) values(school_seq.nextval,?,?,?,?,?,?,?)" ;
           PreparedStatement ps=con.prepareStatement(sql);        
           ps.setString(1,name);         
           ps.setString(2,district);         
           ps.setString(3,city);
           ps.setString(4,schooltype);
           ps.setString(5,status);
           ps.setString(6,year);
           ps.setString(7,pass);
                                int s=ps.executeUpdate();
                                out.println("record inserted");
                                String id=null;
                                if(s==1)
                                {
                                    Statement st = con.createStatement();
                                    ResultSet rs =st.executeQuery("select max(school_id) from school");
                                    if (rs.next())
                                    {
                                      id=rs.getString(1);
                                    }
                                    request.setAttribute("id",id);
                                   request.setAttribute("pass",pass);
                                    RequestDispatcher rd=request.getRequestDispatcher("school_success.jsp");
                                    rd.forward(request,response);
                                }
               
        } 
        catch(Exception e)
        {
            
            e.printStackTrace();
        }

        finally {
            out.close();
        }
    }


    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
