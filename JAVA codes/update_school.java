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
public class update_school extends HttpServlet {

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
           String id=request.getParameter("school_id");
           String name=request.getParameter("school_name");
           String district=request.getParameter("district");
           String city=request.getParameter("city");
           String schooltype=request.getParameter("school_type");
           String status=request.getParameter("status");
           String year=request.getParameter("year_of_establishment");
           String principal=request.getParameter("principal");
           String i=request.getParameter("i");
           String ii=request.getParameter("ii");
           String iii=request.getParameter("iii");
           String iv=request.getParameter("iv");
           String v=request.getParameter("v");
           String vi=request.getParameter("vi");
           String vii=request.getParameter("vii");
           String viii=request.getParameter("viii");
           String ix=request.getParameter("ix");
           String x=request.getParameter("x");
           String maths=request.getParameter("mathematics");
           String physics=request.getParameter("physics");
           String chemistry=request.getParameter("chemistry");
           String biology=request.getParameter("biology");
           String english=request.getParameter("english");
           String history=request.getParameter("history");
           String geography=request.getParameter("geography");
           String mil=request.getParameter("mil");
           String hindi=request.getParameter("hindi");
           String sanskrit=request.getParameter("sanskrit");
           String labs=request.getParameter("labs");
           String ground=request.getParameter("playground");
           
           
           
           Class.forName("oracle.jdbc.driver.OracleDriver");
           Connection con=DriverManager.getConnection("Jdbc:Oracle:thin:@localhost:1521:XE","system","india");
           String sql="update school set SCHOOL_NAME=?,DISTRICT=?,CITY=?,TYPE_OF_SCHOOL=?,STATUS=?,YEAR_OF_ESTABLISHMENT=?,PRINCIPAL_NAME=?,CLASS_I=?,CLASS_II=?,CLASS_III=?,CLASS_IV=?,CLASS_V=?,CLASS_VI=?,CLASS_VII=?,CLASS_VIII=?,CLASS_IX=?,CLASS_X=?,MATHS_TEACHER=?,PHY_TEACHER=?,CHE_TEACHER=?,BIO_TEACHER=?,ENG_TEACHER=?,HIST_TEACHER=?,GEO_TEACHER=?,MIL_TEACHER=?,HIN_TEACHER=?,SANS_TEACHER=?,LABS=?,PLAYGROUND=? where school_id=?";
           PreparedStatement ps=con.prepareStatement(sql);
                        
                        
           ps.setString(1,name);
           ps.setString(2,district);
           ps.setString(3,city);
           ps.setString(4,schooltype);
           ps.setString(5,status);
           ps.setString(6,year);
           ps.setString(7,principal);
           ps.setString(8,i);
           ps.setString(9,ii);
           ps.setString(10,iii);
           ps.setString(11,iv);
           ps.setString(12,v);
           ps.setString(13,vi);
           ps.setString(14,vii);
           ps.setString(15,viii);
           ps.setString(16,ix);
           ps.setString(17,x);
           ps.setString(18,maths);
           ps.setString(19,physics);
           ps.setString(20,chemistry);
           ps.setString(21,biology);
           ps.setString(22,english);
           ps.setString(23,history);
           ps.setString(24,geography);
           ps.setString(25,mil);
           ps.setString(26,hindi);
           ps.setString(27,sanskrit);
           ps.setString(28,labs);
           ps.setString(29,ground);
           ps.setString(30,id);
           
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
