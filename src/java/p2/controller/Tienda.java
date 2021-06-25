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
import p2.AccesoBD;
import p2.ProductoBD;

/**
 *
 * @author Fran
 */
@WebServlet(name = "tienda", urlPatterns = {"/tienda.html"})
public class Tienda extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Entrando en productos");
        List<ProductoBD> productos = AccesoBD.getInstance().obtenerProductosBD();
        request.setAttribute("lista", productos);
        
        request.getRequestDispatcher("tienda.jsp").forward(request, response);
    }
}