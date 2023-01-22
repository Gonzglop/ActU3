<%@page import="java.sql.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <title>Formulario de inicio de sesión y registro</title>
</head>
<body>
<div class="container">
    <form action="login.jsp" method="post">
        <div class="form-group">
            <label for="username">Nombre de usuario</label>
            <input type="text" class="form-control" id="username" name="username" placeholder="Ingrese su nombre de usuario">
        </div>
        <div class="form-group">
            <label for="password">Contraseña</label>
            <input type="password" class="form-control" id="password" name="password" placeholder="Ingrese su contraseña">
        </div>
        <button type="submit" class="btn btn-primary">Iniciar sesión</button>
    </form>
    <form action="register.jsp" method="post">
        <div class="form-group">
            <label for="registerUsername">Nombre de usuario</label>
            <input type="text" class="form-control" id="registerUsername" name="registerUsername" placeholder="Ingrese su nombre de usuario">
        </div>
        <div class="form-group">
            <label for="registerPassword">Contraseña</label>
            <input type="password" class="form-control" id="registerPassword" name="registerPassword" placeholder="Ingrese su contraseña">
        </div>
        <div class="form-group">
            <label for="registerEmail">Email</label>
            <input type="email" class="form-control" id="registerEmail" name="registerEmail" placeholder="Ingrese su email">
        </div>
        <button type="submit" class="btn btn-success">Registrarse</button>
    </form>
</div>
