<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Calculadora</title>
    <style>
        /* Estilos de la calculadora */
        .calculator {
            width: 300px;
            margin: 0 auto;
            text-align: center;
        }
        /* Estilos de los botones */
        .calculator button {
            width: 60px;
            height: 60px;
            font-size: 20px;
            margin: 5px;
        }
    </style>
</head>
<body>
<div class="calculator">
    <form name="calc" action="calculadora" method="post">
        <input type="text" name="display" value="<%=request.getAttribute("result")%>" disabled>
        <br>
        <button name="button" value="7">7</button>
        <button name="button" value="8">8</button>
        <button name="button" value="9">9</button>
        <button name="button" value="/">/</button>
        <br>
        <button name="button" value="4">4</button>
        <button name="button" value="5">5</button>
        <button name="button" value="6">6</button>
        <button name="button" value="*">*</button>
        <br>
        <button name="button" value="1">1</button>
        <button name="button" value="2">2</button>
        <button name="button" value="3">3</button>
        <button name="button" value="-">-</button>
        <br>
        <button name="button" value="0">0</button>
        <button name="button" value=".">.</button>
        <button name="button" value="=">=</button>
        <button name="button" value="+">+</button>
    </form>
</div>
</body>
</html>
