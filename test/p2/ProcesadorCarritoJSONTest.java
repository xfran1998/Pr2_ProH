/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p2;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import p2.Producto;
import p2.controlador.util.ProcesadorCarritoJSON;

/**
 *
 * @author rpenya
 */
public class ProcesadorCarritoJSONTest {
    
    public ProcesadorCarritoJSONTest() {
    }

    private String DATA_TEST_1 = "{\"id\":\"3\",\"nombre\":\"Charizard \",\"precio\":220,\"cantidad\":1,\"imagen\":\"https://pokeres.bastionbot.org/images/pokemon/6.png\",\"stock\":1,\"iv\":98}";
    
    @Test
    public void testProcesarCarrito() throws Exception {
        
        System.out.println("Testing procesarCarrito with "+DATA_TEST_1);
        InputStream stream = new ByteArrayInputStream(DATA_TEST_1.getBytes());
        
        List<Producto> carrito = ProcesadorCarritoJSON.procesarCarrito(stream);
        
        assertNotNull(carrito);
        assertTrue(carrito.size()==1);
        assertEquals("Charizard", carrito.get(0).getNombre());
        assertTrue(carrito.get(0).getPrecio()==220);
        assertTrue(carrito.get(0).getCantidad()==1);
    }
}
