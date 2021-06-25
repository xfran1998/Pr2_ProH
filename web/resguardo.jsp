<%-- 
    Document   : resguardo.jsp
    Created on : 06-jun-2021, 19:23:11
    Author     : Fran
--%>

<%@page import="p2.UserBD"%>
<%@page import="p2.Producto"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<body>
    <%
        float precioTotal = 0;
        List<Producto> productos = (List<Producto>)request.getAttribute("carrito");
        UserBD user = (UserBD)request.getAttribute("form-user");
    %>
    <main id="main-body">
        <div id="container-body" class="procesar">
            <div class="procesar-carrito">
                <h1 class="poke-text">Procesando Carrito</h1>
                <br>
                <hr>
                <br>
                <div class="procesar-user">
                    <h2><span class="poke-text">Datos usuario</span></h2>
                    <div class="container-user-datos">
                        <div class="cambio-info-usuario">
                            <form id="form-user" method="post" onsubmit="ProcesarForm(this, 'pedido_finalizado.html', 'cuerpo');return false">
                                <div>
                                    <label for="name">Nombre</label>
                                    <input type="text" name="name" id="name" value="<%=user.getNombre()%>" required>
                                </div>
                                <div>
                                    <label for="apellidos">Apellidos</label>
                                    <input type="text" name="apellidos" id="apellidos" value="<%=user.getApellidos()%>" required>
                                </div>
                                <div>
                                    <label for="domicilio">Domicilio</label>
                                    <input type="text" name="domicilio" id="domicilio" value="<%=user.getDomicilio()%>" required>
                                </div>
                                <div>
                                    <label for="poblacion">Poblacion</label>
                                    <input type="text" name="poblacion" id="poblacion" value="<%=user.getPoblacion()%>" required>
                                </div>
                                <div>
                                    <label for="provincia">Provincia</label>
                                    <input type="text" name="provincia" id="provincia" value="<%=user.getProvincia()%>" required>
                                </div>
                                <div>
                                    <label for="cp">CP</label>
                                    <input type="number" pattern="[0-9]{5}" name="cp" id="cp" value="<%=user.getCp()%>" required>
                                </div>
                                <div>
                                    <label for="telef">Teléfono</label>
                                    <input type="tel" pattern="[0-9]{9}" name="telef" id="telef" value="<%=user.getTelefono()%>" required>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <h2><span class="poke-text">Datos recibo</span></h2>
                <div class="check-out">
                    <h3>Productos:</h3>
                    <%
                        for (Producto prod : productos){
                            String nombre = prod.getNombre();
                            int cantidad = prod.getCantidad();
                            float precio = cantidad * prod.getPrecio();
                            int iv = prod.getIv();
                    %>
                        <div>
                            <%
                                if (precioTotal == 0){
                                    %>
                                    <hr>
                                    <br>
                            <%
                                }
                            %>
                            <p>Producto: <%=nombre%></p>
                            <p>Iv: <%=iv%></p>
                            <p>Cantidad: <%=cantidad%></p>
                            <p>Precio: <%=precio%> €</p>
                            <br>
                            <hr>
                            <br>
                        </div>

                        <%
                            precioTotal += precio;
                        }
                        %>
                        <h3>Precio Total: <%=precioTotal%> €</h3>
                </div>
                <div class="botones-recibo">
                    <%
                        if (precioTotal > 0){
                    %>
                    <input form="form-user" class="btn" type="submit" value="Continuar">
                    <%
                        }
                    %>
                    <input class="btn" type="button" value="Cancelar" onclick="Cargar('carrito.html','cuerpo')">
                </div>
            </div>
        </div>
    </main>
</body>
</html>