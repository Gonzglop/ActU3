<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Pedidos Pizza</title>
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen, Ubuntu, Cantarell, "Open Sans", "Helvetica Neue", sans-serif;
        }
        h1 {
            text-align: center;
        }
        a {
            font-size: 16px;
            padding: 10px 20px;
            background-color: #007aff;
            color: #fff;
            border-radius: 4px;
            text-decoration: none;
            margin: 10px;
        }
    </style>
</head>
<body>
<h1>Pedidos</h1>
<p>
    <a href="order?action=new">Nuevo pedido</a>
</p>
<p>
    <a href="order?action=list">Lista de pedidos</a>
</p>
</body>
</html>