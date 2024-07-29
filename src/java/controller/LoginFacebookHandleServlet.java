/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import dal.CustomerDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Customer;
import model.FacebookConstants;
import model.UserFacebookDto;
import jakarta.servlet.annotation.WebServlet;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;

/**
 *
 * @author DELL DN
 */
@WebServlet(name = "LoginFacebookHandle", urlPatterns = {"/LoginFacebookHandle"})

public class LoginFacebookHandleServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public LoginFacebookHandleServlet() {
        super();
    }

    CustomerDAO customerdao = new CustomerDAO();

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
        String code = request.getParameter("code");

        if (code == null || code.isEmpty()) {
            RequestDispatcher dis = request.getRequestDispatcher("login.jsp");
            dis.forward(request, response);
        } else {
            String accessToken = getToken(code);
            UserFacebookDto user = getUserInfo(accessToken);
            Customer existedUser = customerdao.checkAccountExist(user.getEmail());

            if (existedUser == null) {
                request.setAttribute("givenname", user.getName());
                request.setAttribute("email", user.getEmail());
                request.getRequestDispatcher("register.jsp").forward(request, response);
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("auth", existedUser);
                response.sendRedirect("home");
            }
        }
    }

    public static String getToken(final String code) throws ClientProtocolException, IOException {
        String link = String.format(FacebookConstants.FACEBOOK_LINK_GET_TOKEN, FacebookConstants.FACEBOOK_APP_ID, FacebookConstants.FACEBOOK_APP_SECRET, FacebookConstants.FACEBOOK_REDIRECT_URL, code);
        String response = Request.Get(link).execute().returnContent().asString();
        JsonObject jobj = new Gson().fromJson(response, JsonObject.class);
        String accessToken = jobj.get("access_token").toString().replaceAll("\"", "");
        return accessToken;
    }

    public static UserFacebookDto getUserInfo(String accessToken) {
        FacebookClient facebookClient = new DefaultFacebookClient(accessToken, FacebookConstants.FACEBOOK_APP_SECRET, Version.LATEST);
        return facebookClient.fetchObject("me", UserFacebookDto.class, Parameter.with("fields", "name,id,email"));
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
