<%-- 
    Document   : usuario_pedidos
    Created on : 27-jun-2021, 11:19:08
    Author     : Fran
--%>

<!DOCTYPE html>
<%@page import="p2.PedidoBD"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <main id="main-body">
            <%
            //Se obtiene el usuario actual registrado en el servicio web, del entorno de sesión
                String usuarioActual = (String) session.getAttribute("usuario");
                List<PedidoBD> pedidos = (List<PedidoBD>)request.getAttribute("pedidos");
                if (usuarioActual != null) //No hay usuario registrado
                { 
            %>
            <div id="container-body-user">
                <div class="container-user user">
                    <div class="container-user-datos">
                        <h1><span class="poke-text high">Pedidos Realizados</span></h1>
                        <i onclick="Cargar('cerrar_sesion.html','cuerpo')" class="fas fa-user-slash fa-3x icon-user icon-user-left"></i>
                        <i onclick="Cargar('login.html','cuerpo')" class="fas fa-address-card fa-3x icon-user icon-user-right"></i>
                        <ul class="cambiarPedido">
                            <%
                        for (PedidoBD ped : pedidos) { 
                            java.util.Formatter formatter = new java.util.Formatter();
                         %>
                            <li class="pedido-item">
                                <div class="pedido-eliminar">
                                    <%
                                        if (ped.getEstado() == 1){
                                    %>
                                    <form id="user-pedidos<%=ped.getCodigo()%>" method="post" onsubmit="ProcesarForm(this, 'usuario_pedidos.html', 'cuerpo');return false">
                                        <input id="codigo" name="codigo" value="<%=ped.getCodigo()%>">
                                    </form>
                                    
                                    
                                    <button type="sumbit" form="user-pedidos<%=ped.getCodigo()%>"><i class="fas fa-trash fa-lg"></i></button>
                                    <%  
                                    }
                                    %>  
                                </div>
                                <div class="pedido-resumen">
                                    <p>Codigo <span class="id-prod"><%=ped.getCodigo()%></span></p>
                                    <p>Fecha <span class="id-prod"><%=ped.getFecha()%></span></p>
                                    <p>Importe <span class="id-prod"><%=formatter.format("%.2f", ped.getImporte())%>€</span></p>
                                    <p>Estado <span class="id-prod"><%=ped.getEstado()%></span></p>
                                </div>
                                <%  
                                    }
                                %>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <%
                }
                else
                {
            %>
            <script>Cargar('login.html','cuerpo')</script>
            <%
                }
                %>
            </div>
        </main>
    </body>
</html>
