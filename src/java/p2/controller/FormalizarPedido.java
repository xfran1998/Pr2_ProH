package p2.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import p2.AccesoBD;
import p2.Producto;
import p2.UserBD;

/**
 *
 * @author Fran
 */
@WebServlet(urlPatterns = {"/pedido_finalizado.html"})
public class FormalizarPedido extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sesion = request.getSession();
        
        if (sesion != null && sesion.getAttribute("usuario") != null){
            AccesoBD con = AccesoBD.getInstance(); //Instancia de la clase factoría AccesoBD
            UserBD user = (UserBD)con.conseguirUsarioBD((String)sesion.getAttribute("usuario"));

            String nombre = request.getParameter("name"); //Se obtiene el nombre del usuario
            String apellidos = request.getParameter("apellidos"); //Se obtiene los apellidos del usuario
            String domicilio = request.getParameter("domicilio"); //Se obtiene los domicilio del usuario
            String poblacion = request.getParameter("poblacion"); //Se obtiene los poblacion del usuario
            String provincia = request.getParameter("provincia"); //Se obtiene los provincia del usuario
            int cp = Integer.parseInt(request.getParameter("cp")); //Se obtiene el cp del usuario
            int telefono = Integer.parseInt(request.getParameter("telef")); //Se obtiene el cp del usuario
            
            System.out.println("Nombre: " + nombre);
            System.out.println("Apellidos: " + apellidos);
            System.out.println("Domicilio: " + domicilio);
            System.out.println("Poblacion: " + poblacion);
            System.out.println("Provincia: " + provincia);
            System.out.println("CP: " + cp);
            System.out.println("Telefono: " + telefono);

            
            Boolean cambiarUser = false;
            
            if (!user.getNombre().equals(nombre)){
                System.out.println("Entra nombre");
                user.setNombre(nombre);
                cambiarUser = true;
            }

            if (!user.getApellidos().equals(apellidos)){
                System.out.println("Entra apellidos");
                user.setApellidos(apellidos);
                cambiarUser = true;
            }

            if (user.getDomicilio() != null){
                if (!user.getDomicilio().equals(domicilio)){
                    System.out.println("Entra domicilio");
                    user.setDomicilio(domicilio);
                    cambiarUser = true;
                }
            }
            else if (domicilio != null){
                user.setDomicilio(domicilio);
                cambiarUser = true;
            }
            
            if (user.getPoblacion() != null){
                if (!user.getPoblacion().equals(poblacion)){
                    System.out.println("Entra poblacion");
                    user.setPoblacion(poblacion);
                    cambiarUser = true;
                }
            }else if (poblacion != null){
                user.setPoblacion(poblacion);
                cambiarUser = true;
            }

            if (user.getProvincia() != null){
                if (!user.getProvincia().equals(provincia)){
                    System.out.println("Entra provincia");
                    user.setProvincia(provincia);
                    cambiarUser = true;
                }
            }else if (provincia != null){
                user.setProvincia(provincia);
                cambiarUser = true;
            }
            

            if (user.getCp()!= cp){
                System.out.println("Entra cp");
                user.setCp(cp);
                cambiarUser = true;
            }

            if (user.getTelefono()!= telefono){
                System.out.println("Entra telefono");
                user.setTelefono(telefono);
                cambiarUser = true;
            }

            if (cambiarUser){
                System.out.println("Ha habido cambios en el usuario (FormalizarPedido.java)");

                // Guardar cambios del usuario en la base de datos
                con.cambiarUsarioBD(user, (String)sesion.getAttribute("usuario"));
            }
            else{
                System.out.println("No ha habido cambios en el usuario (FormalizarPedido.java)");
            }

            // Generar recibo
            List<Producto> productos = (List<Producto>)sesion.getAttribute("carrito");
            float precioTotal = 0;
            
            if (productos != null)
                for (int i=0; i < productos.size(); i++)
                    precioTotal += productos.get(i).getPrecio() * productos.get(i).getCantidad();
            else
                System.out.println("Prod nulo");

            int codPedido = con.generarRecibo(user, productos, precioTotal);
            sesion.removeAttribute("carrito");

            if (codPedido != -1)
                request.setAttribute("id-pedido", codPedido);
            else
                request.setAttribute("id-pedido", "No se ha podido formalizar el pedido");

            request.getRequestDispatcher("pedido_finalizado.jsp").forward(request,response);
        }else{
            response.sendRedirect("login.html");
        }
       
    }
}

