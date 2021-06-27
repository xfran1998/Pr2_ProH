/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p2.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import p2.AccesoBD;
import p2.Producto;
import p2.UserBD;
import p2.controlador.util.ProcesadorCarritoJSON;

/**
 *
 * @author Fran
 */
@WebServlet(name = "RecogerCarrito", urlPatterns = {"/procesar.html"})
public class RecogerCarrito extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletInputStream entrada = request.getInputStream();
        HttpSession sesion = request.getSession();
        
        if (sesion != null && sesion.getAttribute("usuario") != null){
            try{
            List<Producto> carrito = ProcesadorCarritoJSON.procesarCarrito(entrada);
            request.setAttribute("carrito", carrito);
            
            for (int i=0; i < carrito.size(); i++)
                System.out.println(carrito.get(i).getId());
            
            AccesoBD con = AccesoBD.getInstance(); //Instancia de la clase factoría AccesoBD
            UserBD user = (UserBD)con.conseguirUsarioBD((String)sesion.getAttribute("usuario"));
            request.setAttribute("form-user", user);
            sesion.setAttribute("carrito", carrito);
            request.getRequestDispatcher("resguardo.jsp").forward(request, response);
            }catch(Exception e){
                System.out.println(e);
            }
        }else{
            response.sendRedirect("login.html");
        }
        
        
    }
}

