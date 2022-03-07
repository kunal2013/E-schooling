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
import java.util.Scanner;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author priya
 */
public class update_admin_details extends HttpServlet {

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
                        String id =request.getParameter("id");
                        String fname =request.getParameter("fname");
                        String lname =request.getParameter("lname");
                        String age =request.getParameter("age");
                        String gender =request.getParameter("gender");
                        String mobile =request.getParameter("mobile");
                        String city =request.getParameter("city");
                        String district =request.getParameter("district"); 
                        String date=request.getParameter("date_of_joinning");
                      
                        
            
                        Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","india");
                        String sql="update dao set FIRST_NAME=?,LAST_NAME=?,AGE=?,GENDER=?,MOBILE_NUMBER=?,CITY	=?,DISTRICT=?,DATE_OF_JOINNING=? where id=?";
                        PreparedStatement ps=con.prepareStatement(sql);
                        
                        ps.setString(1,fname);
                        ps.setString(2,lname);
                        ps.setString(3,age);
                        ps.setString(4,gender);
                        ps.setString(5,mobile);
                        ps.setString(6,city);
                        ps.setString(7,district);
                        ps.setString(8,date);
                        ps.setString(9,id);
                     
                         ps.executeUpdate();
                        out.println("record updated");
                        
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
