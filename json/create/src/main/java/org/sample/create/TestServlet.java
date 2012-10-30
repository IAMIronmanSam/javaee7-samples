/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sample.create;

import java.io.IOException;
import java.io.PrintWriter;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonBuilder;
import javax.json.JsonConfiguration;
import javax.json.JsonWriter;
import javax.json.stream.JsonGenerator;
import javax.json.stream.JsonGeneratorFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author arungup
 */
@WebServlet(name = "TestServlet", urlPatterns = {"/TestServlet"})
public class TestServlet extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Create JSON structures</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TestServlet at " + request.getContextPath() + "</h1>");
            JsonGeneratorFactory factory = Json.createGeneratorFactory(new JsonConfiguration().withPrettyPrinting());
            JsonGenerator gen = factory.createGenerator(out);
//            JsonGenerator gen = factory.createGenerator(System.out);
            out.println("Creating an empty object (using streaming generator)...<br>");
            gen.beginObject().endObject();
            out.println("<br>...done<br>");

            out.println("Creating a nested structure (using streaming generator)...<br>");
            gen
                .beginArray()
                    .beginObject()
                        .add("title", "The Matrix")
                        .add("year", 1999)
                        .beginArray("cast")
                            .add("Keanu Reaves")
                            .add("Laurence Fishburne")
                            .add("Carrie-Anne Moss")
                        .endArray()
                    .endObject()
                .endArray();
            out.println("<br>...done<br>");
            gen.close();
            
            out.println("Creating a nested structure (using builder)...<br>");
            JsonArray json = new JsonBuilder()
                .beginArray()
                    .beginObject()
                        .add("title", "The Matrix")
                        .add("year", 1999)
                        .beginArray("cast")
                            .add("Keanu Reaves")
                            .add("Laurence Fishburne")
                            .add("Carrie-Anne Moss")
                        .endArray()
                    .endObject()
                .endArray()
                .build();
            JsonWriter writer = new JsonWriter(out);
            writer.writeArray(json);
            out.println("<br>...done");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
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
