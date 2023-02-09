package com.gonz.pizzeria;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "OrderServlet", urlPatterns = {"/order"})
public class OrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static List<Order> orders = new ArrayList<>();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "create":
                createOrder(request, response);
                request.getRequestDispatcher("/WEB-INF/jsp/success.jsp").forward(request, response);
                break;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "new":
                showOrderForm(request, response);
                break;
            case "list":
                listOrders(request, response);
                break;
        }
    }

    private void showOrderForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/order-form.jsp").forward(request, response);
    }

    private void createOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = orders.size() + 1;
        String customerName = request.getParameter("customerName");
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phoneNumber");
        List<Pizza> pizzas = new ArrayList<>();
        String[] pizzaSizes = request.getParameterValues("pizzaSize");
        String[] pizzaTypes = request.getParameterValues("pizzaType");
        String[] extraIngredients = request.getParameterValues("extraIngredients");
        if (pizzaSizes != null) {
            for (int i = 0; i < pizzaSizes.length; i++) {
                Pizza pizza = new Pizza();
                List<String> extras = new ArrayList<>();
                pizza.setSize(Integer.parseInt(pizzaSizes[i]));
                pizza.setType(pizzaTypes[i]);
                if (extraIngredients != null) {
                    for (String extraIngredient : extraIngredients) {
                        extras.add(extraIngredient);
                    }
                    pizza.setExtras(extras);
                }else {
                    extras.add("-");
                    pizza.setExtras(extras);
                }
                pizzas.add(pizza);
            }
        }
        Order order = new Order(id, pizzas, customerName, address, phoneNumber);
        orders.add(order);
        //response.sendRedirect(request.getContextPath() + "/order");
    }

    private void listOrders(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("order", orders);
        request.getRequestDispatcher("/WEB-INF/jsp/order-list.jsp").forward(request, response);
    }
}
