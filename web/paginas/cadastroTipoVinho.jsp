<%-- 
    Document   : formularioUva
    Created on : 30/05/2018, 09:56:00
    Author     : Angelo
    JSTL
--%>

<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="../Estilos/Cadastros.css" rel="stylesheet" type="text/css"/>
        <link href="../Estilos/Menus.css" rel="stylesheet" type="text/css"/>
        <!-- Bootstrap Core CSS -->
        <link href="../vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <form method="post" action="${pageContext.request.contextPath}/tipoVinho">
            <nav id="menu">
                <ul>
                    <li><a href="cadastroUva.jsp">Cadastro uva</a></li>
                    <li><a href="cadastroCliente.jsp">Cadastro cliente</a></li>
                    <li><a href="cadastroTipoVinho.jsp">Cadastro tipo vinho</a></li>
                    <li><a href="uvaLista.jsp">Lista uva</a></li>
                    <li><a href="clienteLista.jsp">Lista cliente</a></li>
                    <li><a href="tipoVinhoLista.jsp">Lista tipo Vinho</a></li>
                    
                </ul>
            </nav>
            <div>
            <h1 align="center">
            <img src="wine-glass.png" alt="" width="100px" height="100px" float="left"/>
            Cadastro de Tipo do Vinho
            </h1>
            
            </div>
            
            
            
            <label for="nome_tipo"> Nome vinho: </label>
            <input type="text" name="nomeTipoVinho"/>
            </br>
            <jsp:useBean id="daouva" class="DAOs.DAOUva"/>
            <label for="Uva_id_uva"> Uva: </label>
            <select class = "form-control" name="uvaTipoVinho"/>
                <option value=""> SELECIONE </option>
                <c:forEach var="u" items="${daouva.listInOrderNome()}">
                    <option value="${u.getIdUva()}">${u.getNomeUva()}</option>
                </c:forEach>
            </select>            
                
                
            </br>
            <input type="submit" name="ok"/>
        </form>
    </body>
</html>
