<%@ page import="com.gonz.pizzeria.Order" %>
<%@ page import="com.gonz.pizzeria.Pizza" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: gonzalolopez
  Date: 4/2/23
  Time: 13:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Pedidos</title>
</head>
<body>
<h1>Pedidos de Pizzas</h1>
<table>
    <tr>
        <th>ID</th>
        <th>Nombre</th>
        <th>Dirección</th>
        <th>Teléfono</th>
        <th>Tamaño</th>
        <th>Tipo</th>
        <th>Ingred. Extra</th>
    </tr>
    <%
        List<Order> orders = (List<Order>) request.getAttribute("order");

        if(orders!=null){
    %>
    <%
        for (Order order : orders) {
    %>
    <tr>
        <td><%= order.getId() %></td>
        <td><%= order.getCustomerName() %></td>
        <td><%= order.getAddress() %></td>
        <td><%= order.getPhoneNumber() %></td>
        <%
            List<Pizza> pizzas = order.getPizzas();
            for (Pizza pizza : pizzas) {
        %>
        <td><%= pizza.getSize() %></td>
        <td><%= pizza.getType() %></td>
        <td><%= pizza.getExtras() %></td>
        <%
            }
        %>
    </tr>
    <%
        }
    %>
    <%
        }
    %>

</table>
</body>
</html>
