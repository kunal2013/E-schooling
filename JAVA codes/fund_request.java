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
public class fund_request extends HttpServlet {

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
            String scnm=request.getParameter("school_name");
            String district=request.getParameter("district");
            String amt=request.getParameter("amount");
            String reason=request.getParameter("reason");
            String date=request.getParameter("date");
            
            
            
               Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","india");
                
            String sql="insert into fund_request(REQUEST_ID,SCHOOL_NAME,DISTRICT,AMOUNT,REASON,DATE_OF_REQUEST)values(fund_seq.nextval,?,?,?,?,?)";
            PreparedStatement ps=con.prepareStatement(sql);

                                ps.setString(1,scnm);
                                ps.setString(2,district);
                                ps.setString(3,amt);        
                                ps.setString(4,reason);        
                                ps.setString(5,date); 
                               
                                int s=ps.executeUpdate();
                                out.println("record inserted");
                                String id=null;
                                if(s==1)
                                {
                                    Statement st = con.createStatement();
                                    ResultSet rs =st.executeQuery("select max(request_id) from fund_request");
                                    if (rs.next())
                                    {
                                      id=rs.getString(1);
                                    }
                                    request.setAttribute("id",id);
                                    RequestDispatcher rd=request.getRequestDispatcher("fund_success.jsp");
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
