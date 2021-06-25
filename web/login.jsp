<%@page language="java" import="p2.*" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>&nbsp;</title>
    </head>
    <body>
        <main id="main-body">
            <%
            //Se obtiene el usuario actual registrado en el servicio web, del entorno de sesión
                String usuarioActual = (String) session.getAttribute("usuario");
                if (usuarioActual == null) //No hay usuario registrado
                { //Mostramos el formulario para la introducción del usuario y la clave
                System.out.println("Inicio Sesion");
            %>
            <div id="container-body-user">
                <div class="container-login inicio">
                    <!-- Login Interface -->
                    <div class="container-sign">
                        <div class="sign-content">
                            <h1><span class="poke-text">MAESTRO</span> POKEMON</h1>
                            <h2>Inicia sesión para defender tu gimnasio</h2>
                            <button class="btn-usuario master btn-login">
                                <a><i class="e"></i>&nbsp; Log in with Facebook</a>
                            </button>
                            <div class="or-usuario">
                                <hr class="hr-left">
                                <h2 class="or">O</h2>
                                <hr class="hr-right"> 
                            </div>
                            <form method="post" class="input-usuario" onsubmit="ProcesarForm(this, 'login.html', 'cuerpo');return false">
                                <%
                                //Utilizamos una variable en la sesión para informar de los mensajes de Error
                                String mensaje = (String) session.getAttribute("mensaje");
                                if (mensaje != null) { //Eliminamos el mensaje consumido
                                    session.removeAttribute("mensaje");
                                %>
                                <p><span class="pass-incorrecta"> <%=mensaje%> </span></p>
                                <%
                                }
                                %>
                                <input type="text" name="usuario" placeholder="Usuario">
                                <input type="password" name="clave" placeholder="Contraseña">
                                <button class="btn-usuario master btn-login">
                                    <a>Entrar</a>
                                </button>
                            </form>
                            <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Aut, laudantium?</p>
                        </div>
                    </div>
                </div>
            </div>
            <%
            } else {
            //Si existe un usuario, se mostrará las opciones del apartado del usuario
                //Comentar para cancelar cerrar sesion
                //session.setAttribute("usuario",null);
                UserBD user = (UserBD)request.getAttribute("form-user");
            %>
            <div id="container-body-user">
                <div class="container-user user">
                    <div class="container-user-datos">
                        <h1><span class="poke-text high">Datos Personales</span></h1>
                        <div class="cambio-info-usuario">
                            <form action="procesar.cgi." method="POST">
                                <div>
                                    <label for="name">Nombre</label>
                                    <input type="text" name="name" id="name" value="<%=user.getNombre()%>" required>
                                </div>
                                <div>
                                    <label for="apellido">Apellidos</label>
                                    <input type="text" name="apellido" id="apellido" value="<%=user.getApellidos()%>" required>
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
            </div>
            <%
                }
            %>
            </div>
        </main>
    </body>
</html>