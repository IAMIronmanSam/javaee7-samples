/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sample.create;

import java.io.IOException;
import java.io.PrintWriter;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author arungup
 */
@WebServlet(urlPatterns = {"/DOMTestServlet"})
public class DOMTestServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet DOMTestServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DOMTestServlet at " + request.getContextPath() + "</h1>");
            
            out.println("Creating an empty object (using DOM generator)...<br>");
            JsonObject jsonObject = new JsonObjectBuilder().build();
            new JsonWriter(out).writeObject(jsonObject);
            out.println("<br>...done<br>");

            out.println("<br>Creating a simple object (using DOM generator)...<br>");
            jsonObject = new JsonObjectBuilder()
                    .add("apple", "red")
                    .add("banana", "yellow")
                    .build();
            new JsonWriter(out).writeObject(jsonObject);
            out.println("<br>...done<br>");
            
            out.println("<br>Creating a simple array (using streaming generator)...<br>");
            JsonArray jsonArray = new JsonArrayBuilder()
                    .add(new JsonObjectBuilder().add("apple","red"))
                    .add(new JsonObjectBuilder().add("banana","yellow"))
                    .build();
            new JsonWriter(out).writeArray(jsonArray);
            out.println("<br>...done<br>");
            
            out.println("Creating a nested structure (using builder)...<br>");
            JsonArray json = new JsonArrayBuilder()
                    .add(new JsonObjectBuilder()
                        .add("title", "The Matrix")
                        .add("year", 1999)
//                    .add(new JsonObjectBuilder()
                        .add("cast", new JsonArrayBuilder()
                            .add("Keanu Reaves")
                            .add("Laurence Fishburne")
                            .add("Carrie-Anne Moss")))
                .build();
            new JsonWriter(out).writeArray(json);
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
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
     * Handles the HTTP
     * <code>POST</code> method.
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
