/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p2.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import p2.AccesoBD;

/**
 *
 * @author Fran
 */
@WebServlet(name = "Registro", urlPatterns = {"/registro.html"})
public class Registro extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("registro.jsp");
    }
        
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String user = request.getParameter("user");
        String password = request.getParameter("password");
        String nombre = request.getParameter("nombre");
        String apellidos = request.getParameter("apellidos");
        
        HttpSession sesion = request.getSession();
        AccesoBD con = AccesoBD.getInstance();
        Boolean registro =  con.registrarUsuario(user, password, nombre, apellidos);
        
        if (registro){
            sesion.setAttribute("usuario", user);
            response.sendRedirect("login.html");
        }
        else{
            sesion.setAttribute("mensaje", "Ya existe un usario con ese nick");
            response.sendRedirect("registro.jsp");
        }
    }
}
