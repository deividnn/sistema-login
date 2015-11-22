<%-- 
    Document   : acesso
    Created on : 22/11/2015, 20:41:57
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
        <h1>Acessar!</h1>
        <br/>

        <c:url value="/acesso" var="acessoUrl"/>

        <form action="${acessoUrl}" method="post">

            <c:if test="${param.error!=null}">
                <p style="color: red">usuario/senha incorretos</p>
            </c:if>

            <c:if test="${param.sair!=null}">
                <p style="color: blue">  saiu do sistema</p>
            </c:if>

            <input name="usuario"
                   maxlength="20"
                   required="true"
                   placeholder="digite o usuario"
                   autofocus="true"
                   type="text"/>
            <br/>
            <input name="senha"
                   maxlength="20"
                   required="true"
                   placeholder="digite a senha"
                   type="password"/>
            <br/>

            <input type="hidden" 
                   name="${_csrf.parameterName}"
                   value="${_csrf.token}"/>

            <input type="submit" value="Acessar"/>


        </form>
        <br/>
        <a href="<c:url value="/inicio"/>">Inicio</a>
    </body>
</html>
