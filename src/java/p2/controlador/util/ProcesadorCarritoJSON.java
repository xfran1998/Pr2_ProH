/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p2.controlador.util;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import p2.Producto;

/**
 *
 * @author rpenya
 */
public class ProcesadorCarritoJSON {
    
    /**
     * Procesa un JSON que contiene el carrito de la compra.
     * 
     * A partir de un stream de acceso al  JSON intenta obtener la lista de 
     * productos que forman el carrito contenido en la cadena. 
     * Si no hay problemas devuelve la misma, en caso contrario genera errores.
     * 
     * @param json Stream JSON con el carrito
     * @return Lista de productos del carrito
     * @throws Exception Indicador de errores
     */
    public static List<Producto> procesarCarrito(InputStream json) throws Exception {
        
        List<Producto> carrito = new <Producto>ArrayList();
        
        JsonReader jsonReader = Json.createReader(new InputStreamReader(json));
        JsonObject jobj = jsonReader.readObject();
        
        for (String key : jobj.keySet()) { // Se recorren todos los productos pasados en el JSON 
            JsonObject prod = (JsonObject)jobj.getJsonObject(key);
            System.out.println(prod); 
            
            Integer id = Integer.parseInt(prod.getString("id"));
            String nombre = prod.getString("nombre");
            Float precio = Float.parseFloat(prod.get("precio").toString());
            Integer cantidad = Integer.parseInt(prod.get("cantidad").toString());
            String imagen = prod.getString("imagen");;
            int stock = Integer.parseInt(prod.get("stock").toString());
            int iv = Integer.parseInt(prod.get("iv").toString());
            
            Producto nuevo = new Producto(); 
            nuevo.setId(id); 
            nuevo.setNombre(nombre); 
            nuevo.setPrecio(precio); 
            nuevo.setCantidad(cantidad);
            nuevo.setImagen(imagen);
            nuevo.setStock(stock);
            nuevo.setIv(iv);
            
            
            carrito.add(nuevo);
        }
        
        return carrito;
    }    
}
