<%-- 
    Document   : admin
    Created on : 22/11/2015, 20:38:05
    Author     : DeividnN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sistema</title>
    </head>
    <body>
        <h1>Bem vindo, ${usuario}!,voce esta na area administrativa</h1>
        
        <br/>
        
        <a href="<c:url value="/user"/>">Area do usuario</a>
        <br/>
         <a href="<c:url value="/sair"/>">Sair</a>
    </body>
</html>
