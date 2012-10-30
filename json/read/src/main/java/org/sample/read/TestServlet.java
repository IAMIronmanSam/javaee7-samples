/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sample.read;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonBuilder;
import javax.json.JsonReader;
import javax.json.JsonNumber;
import javax.json.JsonWriter;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParser.Event;
import javax.json.stream.JsonParserFactory;
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
    
    private static final String JSON = "[{\"title\":\"The Matrix\",\"year\":1999,\"cast\":[\"Keanu Reaves\",\"Laurence Fishburne\",\"Carrie-Anne Moss\"]}]";

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
            out.println("<title>Reading JSON structures</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Reading JSON structures " + request.getContextPath() + "</h1>");
            out.println("Reading a JSON (using streaming parser)...<br>");
            out.println("Object: " + JSON);
            JsonReader reader = new JsonReader(new StringReader(JSON));
            JsonArray array = reader.readArray();
            JsonParserFactory factory = Json.createParserFactory();
            JsonParser parser = factory.createParser(array);
            for (Event it : parser) {
                switch (it) {
                    case START_ARRAY:
                        out.println("Starting an array<br>");
                        break;
                    case START_OBJECT:
                        out.println("Starting an object<br>");
                        break;
                    case END_ARRAY:
                        out.println("Ending an array<br>");
                        break;
                    case END_OBJECT:
                        out.println("Ending an object<br>");
                        break;
                    case KEY_NAME:
                        out.format("Found key: <b>%1$s</b><br>", parser.getString());
                        break;
                    case VALUE_STRING:
                        out.format("Found value: <b>%1$s</b><br>", parser.getString());
                        break;
                    case VALUE_NUMBER:
                        switch (parser.getNumberType()) {
                            case INT:
                                out.format("Found value: <b>%1$d</b><br>", parser.getIntValue());
                                break;
                            case LONG:
                                out.format("Found value: <b>%1$d</b><br>", parser.getLongValue());
                                break;
                            case BIG_DECIMAL:
                                out.format("Found value: <b>%1$f</b><br>", parser.getBigDecimalValue());
                                break;
                        }
                        break;
                    case VALUE_TRUE:
                    case VALUE_FALSE:
                        out.format("Found boolean value: <b>%1$b</b><br>", parser.getString());
                        break;
                    case VALUE_NULL:
                        out.format("Found null value");
                        break;
                    default:
                        out.format("What did you find ?");
                        break;
                }
            }
            out.println("<br>...done<br>");
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
