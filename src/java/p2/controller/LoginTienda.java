/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p2.controller;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession; //Para acceder al entorno de sesión
import javax.servlet.annotation.WebServlet;
import p2.AccesoBD;
import p2.UserBD;

/**
 *
 * @author Fran
 */
@WebServlet(name = "LoginTienda", urlPatterns = {"/login.html"})
public class LoginTienda extends HttpServlet {
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String usuario = request.getParameter("usuario"); //Se obtiene el nombre de usuario
        String clave = request.getParameter("clave"); //Se obtiene la clave de usuario del formulario
        HttpSession sesion = request.getSession(true); //Se accede al entorno de la sesión
        
        System.out.println("Usuario: " + usuario);
        System.out.println("Pass: " + clave);
        
        AccesoBD con = AccesoBD.getInstance(); //Instancia de la clase factoría AccesoBD

        if ((usuario != null) && (clave != null)) //Se si se han pasado usuario y clave
        {
            if (con.comprobarUsuarioBD(usuario, clave)) // Se comprueba en la base de datos
            { // Registramos al usuario en el entorno de la sesión
                sesion.setAttribute("usuario", usuario);
                
            } else // El usuario/clave no se encuentra en la BD
            { // Registramos el error en el entorno de la sesión
                sesion.setAttribute("mensaje", "Usuario y/o clave incorrectos");
            }
        } else { // Registramos el error en el entorno de la sesión
            sesion.setAttribute("mensaje", "Falta introducir el usuario o la clave");
        }
        
        UserBD user = con.conseguirUsarioBD(usuario);
        request.setAttribute("form-user", user);
        request.getRequestDispatcher("login.jsp").forward(request,response);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession sesion = request.getSession(true);
        AccesoBD con = AccesoBD.getInstance(); //Instancia de la clase factoría AccesoBD
        UserBD user = (UserBD)con.conseguirUsarioBD((String)sesion.getAttribute("usuario"));
        request.setAttribute("form-user", user);
        request.getRequestDispatcher("login.jsp").forward(request,response);
    }
}
