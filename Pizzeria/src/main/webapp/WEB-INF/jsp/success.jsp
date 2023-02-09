<%--
  Created by IntelliJ IDEA.
  User: gonzalolopez
  Date: 4/2/23
  Time: 13:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Pedido finalizado</title>
    <style>
        body {
            display: flex;
            flex-direction: column;
            align-items: center;
            font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen, Ubuntu, Cantarell, "Open Sans", "Helvetica Neue", sans-serif;
        }

        h1 {
            font-size: 2em;
            margin-bottom: 0.5em;
        }

        p {
            font-size: 1.2em;
            margin-bottom: 1em;
        }

        a {
            font-size: 1.2em;
            padding: 0.5em 1em;
            background-color: lightgray;
            color: black;
            text-decoration: none;
            border-radius: 5px;
            margin-right: 1em;
        }
    </style>
</head>
<body>
<h1>Pedido realizado con éxito</h1>
<p>Su pedido ha sido registrado correctamente.</p>
<p>
    <a href="order?action=list">Mostrar pedidos</a>
    <a href="index.jsp">Volver</a>
</p>
</body>
</html>
