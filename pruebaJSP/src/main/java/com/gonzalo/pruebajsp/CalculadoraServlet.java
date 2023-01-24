package com.gonzalo.pruebajsp;
import java.io.IOException;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calculadora")
public class CalculadoraServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String expression = request.getParameter("display");
        if (expression != null && !expression.equals("")) {
            if (request.getParameter("button").equals("=")) {
                try {
                    String result = new ScriptEngineManager().getEngineByName("JavaScript").eval(expression).toString();
                    request.setAttribute("result", result);
                    request.getRequestDispatcher("calculadora.jsp").forward(request, response);
                } catch (ScriptException e) {
                    request.setAttribute("result", "Error");
                    request.getRequestDispatcher("calculadora.jsp").forward(request, response);
                }
            } else {
                expression += request.getParameter("button");
                request.setAttribute("display", expression);
                request.getRequestDispatcher("calculadora.jsp").forward(request, response);
            }
        }
    }
}