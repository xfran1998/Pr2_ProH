/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p2;

import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Fran
 */
public class AccesoBDTest {
    
    public AccesoBDTest() {
    }

    /**
     * Test of getInstance method, of class AccesoBD.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        AccesoBD result = AccesoBD.getInstance();
        assertNotNull(result);
    }
    /**
     * Test of comprobarAcceso method, of class AccesoBD.
     */

    /**
     * Test of cerrarConexionBD method, of class AccesoBD.
     */
    @Test
    public void testCerrarConexionBD() {
        System.out.println("cerrarConexionBD");
        AccesoBD instance = AccesoBD.getInstance();
        instance.cerrarConexionBD();
    }

    /**
     * Test of obtenerProductosBD method, of class AccesoBD.
     *//*
    @Test
    public void testObtenerProductosBD() {
        System.out.println("obtenerProductosBD");
        AccesoBD instance = null;
        List<ProductoBD> expResult = null;
        List<ProductoBD> result = instance.obtenerProductosBD();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }*/

    /**
     * Test of comprobarUsuarioBD method, of class AccesoBD.
     */
    @Test
    public void testComprobarUsuarioBD() {
        System.out.println("comprobarUsuarioBD");
        
        //Incorrecto
        String usuario = "";
        String clave = "";
        AccesoBD instance = AccesoBD.getInstance();
        Boolean expResult = false;
        Boolean result = instance.comprobarUsuarioBD(usuario, clave);
        assertEquals(expResult, result);
        usuario = "test";
        clave = "dasdasdasdasd";
        result = instance.comprobarUsuarioBD(usuario, clave);
        assertEquals(expResult, result);
        
        //Correcto
        expResult = true;
        usuario = "test";
        clave = "funciona";
        result = instance.comprobarUsuarioBD(usuario, clave);
        assertEquals(expResult, result);
    }
    
}
