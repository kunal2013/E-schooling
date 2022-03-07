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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author priya
 */
public class update_staffs extends HttpServlet {

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
           String staff_id=request.getParameter("staff_id");
           String school_id=request.getParameter("school_id");
           String fnm=request.getParameter("fname");
           String lnm =request.getParameter("lname");
           String dob=request.getParameter("dob");
           String phone=request.getParameter("phone");
           String address=request.getParameter("address");
           String quali=request.getParameter("quali");
           String post=request.getParameter("post");
           String sub=request.getParameter("sub");
           String date=request.getParameter("date");
           String sal=request.getParameter("sal");
           
            Class.forName("oracle.jdbc.driver.OracleDriver");
           Connection con=DriverManager.getConnection("Jdbc:Oracle:thin:@localhost:1521:XE","system","india");
           String sql="update staff set SCHOOL_ID=?,FIRST_NAME=?,LAST_NAME=?,DATE_OF_BIRTH=?,PHONE_NUMBER=?,ADDRESS=?,QUALIFICATION=?,DESIGNATION=?,SUBJECTS_TAUGHT=?,DATE_OF_JOINNING=?,SALARY=? where staff_id=?";
            PreparedStatement ps=con.prepareStatement(sql);
                        
                        
           ps.setString(1,school_id);
           ps.setString(2,fnm);
           ps.setString(3,lnm);
           ps.setString(4,dob);
           ps.setString(5,phone);
           ps.setString(6,address);
           ps.setString(7,quali);
           ps.setString(8,post);
           ps.setString(9,sub);
           ps.setString(10,date);
           ps.setString(11,sal);
           ps.setString(12,staff_id);
           
           ps.executeUpdate();
           out.println("record updated");
             
                }
        catch(Exception e)
        {
            e.printStackTrace();
        
        } finally {
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
