<%-- 
    Document   : pedido_finalizado
    Created on : 23-jun-2021, 0:00:15
    Author     : Fran
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <main id="main-body">
            <div id="container-body" class="carrito">
                <div class="container-recibo">
                    <h1 <span class="poke-text">Transacción finalizada</span></h1>
                    <h3>La id del pedido es <span class="id-prod"><%=request.getAttribute("id-pedido")%></span></h3>
                </div>
            </div>
            <script>resetCarrito()</script>
        </main>
    </body>
</html>
