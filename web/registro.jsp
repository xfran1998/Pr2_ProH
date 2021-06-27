<%-- 
    Document   : registro
    Created on : 27-jun-2021, 17:55:21
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
            <%
            //Se obtiene el usuario actual registrado en el servicio web, del entorno de sesión
                String usuarioActual = (String) session.getAttribute("usuario");
                String mensaje = (String) session.getAttribute("mensaje");
                if (usuarioActual == null) //No hay usuario registrado
                { //Mostramos el formulario para la introducción del usuario y la clave
            %>
            <div id="container-body-user">
                <div class="container-sign active registro">
                    <div class="sign-content">
                        <h1><span class="poke-text">ENTRENADOR</span> POKEMON</h1>
                        <h2>Registrate para comprar en nuestra tienda pokemon</h2>
                        <div class="btn-usuario button-registro" onclick="Cargar('login.html','cuerpo')">
                            <a><i class="fab fa-facebook-square"></i>&nbsp; Inicia sesion</a>
                        </div>
                        <div class="or-usuario">
                            <hr class="hr-left">
                            <h2 class="or">O</h2>
                            <hr class="hr-right"> 
                        </div>
                        <%
                        if (mensaje != null) { //Eliminamos el mensaje consumido
                                session.removeAttribute("mensaje");
                                %>
                                <p><span class="pass-incorrecta"> <%=mensaje%> </span></p>
                                <%
                                }
                                %>
                        <form id="form-registro" method="POST" class="input-usuario registrar" onsubmit="ProcesarForm(this, 'registro.html', 'cuerpo');return false">
                            <input type="text" name="user" placeholder="Usuario" required>
                            <input type="password" name="password" placeholder="Contraseña" required>
                            <input type="text" name="nombre" placeholder="Nombre" required>
                            <input type="text" name="apellidos" placeholder="Apellidos" required>
                        </form>
                        <button form="form-registro" type="submit" class="btn-usuario registro button-registro">Registrarte</button>
                        <p>¿Tienes una cuenta ya? <a class="button inicio" onclick="Cargar('login.html','cuerpo')">Inicia Sesión</a> para acceder al contenido de usuario</p>
                    </div>
                </div>
            </div>
            <%
                }
            %>
        </main>
    </body>
</html>
