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
public class transfer_request extends HttpServlet {

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
            String scid=request.getParameter("scid");
            String fnm=request.getParameter("fname");
            String lnm=request.getParameter("lname");
            String scnm=request.getParameter("scnm");
            String i=request.getParameter("i");
            String ii=request.getParameter("ii");
            String iii=request.getParameter("iii");
            String date=request.getParameter("date");
            String district=request.getParameter("district");
            
            
               Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","india");
                
            String sql="insert into transfer_request(REQUEST_NO,FIRST_NAME,LAST_NAME,SCHOOL_NAME,CHOICE_I,CHOICE_II,CHOICE_III,DATE_OF_REQUEST,SCHOOL_ID,DISTRICT)values(trans_seq.nextval,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps=con.prepareStatement(sql);

                               
                                ps.setString(1,fnm);        
                                ps.setString(2,lnm);        
                                ps.setString(3,scnm);        
                                ps.setString(4,i);        
                                ps.setString(5,ii);        
                                ps.setString(6,iii);
                                ps.setString(7,date);
                                ps.setString(8,scid);  
                                ps.setString(9,district);
                                 
                                int s=ps.executeUpdate();
                                out.println("record inserted");
                                String id=null;
                                if(s==1)
                                {
                                    Statement st = con.createStatement();
                                    ResultSet rs =st.executeQuery("select max(request_no) from transfer_request");
                                    if (rs.next())
                                    {
                                      id=rs.getString(1);
                                    }
                                    request.setAttribute("id",id);
                                    RequestDispatcher rd=request.getRequestDispatcher("transfer_success.jsp");
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
