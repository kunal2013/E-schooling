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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author priya
 */
public class transfer_order extends HttpServlet {

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
            String scid=request.getParameter("school_id");
            String id=request.getParameter("staff_id");
            String fname=request.getParameter("fname");
            String lname=request.getParameter("lname");
            String oldsc=request.getParameter("from_school");
            String newsc=request.getParameter("to_school");
            String post=request.getParameter("on_post");
            String issue=request.getParameter("date_of_issue");
            String join=request.getParameter("date_of_joinning");
            
            Class.forName("oracle.jdbc.driver.OracleDriver");
           Connection con=DriverManager.getConnection("Jdbc:Oracle:thin:@localhost:1521:XE","system","india");
           String sql="insert into transfer_order(letter_no,staff_id,first_name,last_name,from_school,to_school,on_post,date_of_issue,date_of_joinning,school_id) values(transorder_seq.nextval,?,?,?,?,?,?,?,?,?)" ;
           PreparedStatement ps=con.prepareStatement(sql);
           
           
           ps.setString(1,scid);
           ps.setString(2,id);
           ps.setString(3,fname);
            ps.setString(4,lname);
           ps.setString(5,oldsc);
           ps.setString(6,newsc);
           ps.setString(7,post);
           ps.setString(8,issue);
           ps.setString(9,join);
          
           
           int s=ps.executeUpdate();
           out.println("RECORD IS ADDED");
           String lid=null;
                                if(s==1)
                                {
                                    Statement st = con.createStatement();
                                    ResultSet rs =st.executeQuery("select max(letter_no) from transfer_order");
                                    if (rs.next())
                                    {
                                      lid=rs.getString(1);
                                    }
                                    request.setAttribute("uid",lid);
                                    RequestDispatcher rd=request.getRequestDispatcher("transfer_order_success.jsp");
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
