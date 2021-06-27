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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import p2.AccesoBD;
import p2.PedidoBD;

/**
 *
 * @author Fran
 */
@WebServlet(name = "UsuarioPedidos", urlPatterns = {"/usuario_pedidos.html"})
public class UsuarioPedidos extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession sesion = request.getSession(true);
        AccesoBD con = AccesoBD.getInstance();

        String user = (String)sesion.getAttribute("usuario");
        List<PedidoBD> pedidos = con.conseguirPedidos(user);

        request.setAttribute("pedidos", pedidos);

        request.getRequestDispatcher("usuario_pedidos.jsp").forward(request,response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            HttpSession sesion = request.getSession(true);
            AccesoBD con = AccesoBD.getInstance();
            String user = (String)sesion.getAttribute("usuario");
            
            con.cancelarPedido(Integer.parseInt(request.getParameter("codigo")), user);
            
            List<PedidoBD> pedidos = con.conseguirPedidos(user);
            request.setAttribute("pedidos", pedidos);
            request.getRequestDispatcher("usuario_pedidos.jsp").forward(request,response);
        }catch (Exception e) {
            System.err.println("Error doGet UsuarioPedidos.java");
          
        }
    }
}
