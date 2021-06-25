<%@page import="java.util.List"%>
<%@page contentType="text/html" import="p2.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://kit.fontawesome.com/997ce922a1.js" crossorigin="anonymous"></script>
        <title>Productos</title>
    </head>
    <body>
        <%
            List<ProductoBD> productos = (List<ProductoBD>)request.getAttribute("lista");
        %>
        <main id="main-body">
        <div id="container-body-producto">
            <section>
                <h1><span class="poke-text high">Elige tu pokemon favorito</span></h1>
                <article>
                    <div id="poke-container" class="poke-container">
                        <%
                        for (ProductoBD producto : productos) {
                            int ID = producto.getId();
                            String nombre = producto.getNombre();
                            int pokedex = producto.getPokedex();
                            float precio = producto.getPrecio();
                            int existencias = producto.getStock();
                            String imagen = producto.getImagen();
                            int stock = producto.getStock();
                            int iv = producto.getIv();
                         %>
                        <div class="poke-card">
                            <div class="card-inner">
                                <div class="card-face card-front">
                                    <div class="pokemon" style="background-color: rgb(222, 253, 224);">
                                        <div class="img-container">
                                            <img src="<%=imagen%>" alt="<%=nombre%>">
                                        </div>
                                        <div class="info">
                                            <span class="number">#
                                                <%
                                                if (pokedex < 10)
                                                {
                                                %>
                                                00<% 
                                                }
                                                else if(pokedex < 100){
                                                %>
                                                0<%  
                                                }
                                                %><%=pokedex%>
                                                </span>
                                            <h3 class="name poke-text"><%=nombre%></h3>
                                            <small class="type">Iv: <span><%=iv%>%</span></small>
                                        </div>
                                    </div>
                                </div>
                                <div class="card-face card-back" style="background-color: rgb(222, 253, 224);">
                                    <h2><span class="poke-text price-text"><%=precio%><%
                                            int decimal = (int)(precio*100%100);
                                            System.out.println("decimal: " + decimal);
                                            if (decimal%10 == 0)  
                                            {
                                            %>0<%
                                            }
                                            %>€</span></h2>
                                    <%
                                    if (existencias > 0) {
                                    %>
                                    <div class="btn-buy" onclick="addItem(<%=ID%>,<%=true%>)"><i class="fas fa-play"></i><a>Buy Now</a></div>
                                    <div class="btn-add" onclick="addItem('<%=ID%>','<%=nombre%>','<%=precio%>','<%=imagen%>','<%=stock%>','<%=iv%>',<%=false%>)"><i class="fas fa-shopping-cart" href="#"></i><a>Add to Cart</a></div>
                                    <%
                                    } else {
                                    %>
                                    <br>
                                    <div class="sin-existencias">
                                        <h3 class="name poke-text">Sin existencias</h3>
                                    </div>
                                    <%
                                    }
                                    %>
                                </div>
                            </div>
                        </div>
                        <%
                        }
                        %>
                    </div>
                </article>
            </section>
        </div>
    </main>
    <script src="js/productos.js"></script> 
    <script src="js/localStorage.js"></script>
    </body>
</html>