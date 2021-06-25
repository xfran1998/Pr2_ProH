<%@page contentType="text/html" import="p2.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Página de comprobación</title>
    </head>
    <body>
        <%
            AccesoBD con = AccesoBD.getInstance();
            boolean res = con.comprobarAcceso();
        %>
        <h1><%=res%></h1>
    </body>
</html>

