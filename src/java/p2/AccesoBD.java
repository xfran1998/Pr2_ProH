package p2;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public final class AccesoBD {

    private static AccesoBD instanciaUnica = null;
    private Connection conexionBD = null;

    public static AccesoBD getInstance() {
        if (instanciaUnica == null) {
            instanciaUnica = new AccesoBD();
        }
        return instanciaUnica;
    }

    private AccesoBD() {
        abrirConexionBD();
    }

    public void abrirConexionBD() {
        if (conexionBD == null) { // daw es el nombre de la base de datos que hemos creado con anterioridad.
            String nombreConexionBD = "jdbc:mysql://localhost/pokeshop";
            try { // root y sin clave es el usuario por defecto que crea XAMPP.
                Class.forName("com.mysql.cj.jdbc.Driver");
                conexionBD = DriverManager.getConnection(nombreConexionBD, "root", "");
            } catch (Exception e) {
                System.err.println("No se ha podido conectar a la base de datos");
                System.err.println(e.getMessage());
            }
        }
    }
    
    public boolean comprobarAcceso() {
        abrirConexionBD();
        return conexionBD != null;
    }
    
    public void cerrarConexionBD()
    {
        if (conexionBD != null)
        {
            try{
                conexionBD.close();
                conexionBD = null;
            }
            catch(Exception e){ //Error en la conexión con la BD
                System.err.println("No se ha completado la desconexión a la base de datos");
                System.err.println(e.getMessage());
            }
        }
    }
    
    public List<ProductoBD> obtenerProductosBD() {
        abrirConexionBD();
        ArrayList<ProductoBD> productos = new ArrayList<>();
        try {
            String con;
            Statement s = conexionBD.createStatement();
            // hay que tener en cuenta las columnas de vuestra tabla de productos
            // también se puede utilizar una consulta del tipo:
            // con = "SELECT * FROM productos";
            con = "SELECT * FROM productos";
            ResultSet resultado = s.executeQuery(con);
            while (resultado.next()) {
                ProductoBD producto = new ProductoBD();
                producto.setId(resultado.getInt("codigo"));
                producto.setNombre(resultado.getString("nombre"));
                producto.setDescripcion(resultado.getString("descripcion"));
                producto.setPokedex(resultado.getInt("pokedex"));
                producto.setPrecio(resultado.getFloat("precio"));
                producto.setStock(resultado.getInt("existencias"));
                producto.setImagen(resultado.getString("imagen"));
                producto.setEvolucion(resultado.getInt("evolucion"));
                producto.setIv(resultado.getInt("iv"));
                productos.add(producto);
            }
        } catch (Exception e) {
            System.err.println("Error ejecutando la consulta a la base de datos");
            System.err.println(e.getMessage());
        }
        return productos;
    }
    
    public boolean comprobarUsuarioBD(String usuario, String clave) {
        abrirConexionBD();
        try {
            String con;
            Statement s = conexionBD.createStatement();
            // Consulta, buscamos una correspondencia usuario/clave
            con = "SELECT * FROM usuarios WHERE usuario='" + usuario + "' and clave='" + clave + "'";
            ResultSet resultado = s.executeQuery(con);
            if (resultado.next()) // El usuario/clave se encuentra en la BD
            {
                return true;
            } else // El usuario/clave no se encuentra en la BD
            {
                
                return false;
            }
        } catch (Exception e) { // Error en la conexión con la BD
            System.err.println("Error verificando usuaro/clave");
            System.err.println(e.getMessage());
            return false;
        }
    }
    
    public UserBD conseguirUsarioBD(String usuario){
        abrirConexionBD();
        UserBD user = new UserBD();
        try {
            String con;
            Statement s = conexionBD.createStatement();
            // Consulta, buscamos una correspondencia usuario/clave
            con = "SELECT * FROM usuarios WHERE usuario='" + usuario + "'";
            ResultSet resultado = s.executeQuery(con);
            
            if (resultado.next()) // El usuario/clave se encuentra en la BD
            {
                user.setId(resultado.getInt("codigo"));
                user.setNombre(resultado.getString("nombre"));
                
                String ap = resultado.getString("apellidos");
                System.out.println("ap: " + ap);
                user.setApellidos(ap);
                user.setDomicilio(resultado.getString("domicilio"));
                user.setPoblacion(resultado.getString("poblacion"));
                user.setProvincia(resultado.getString("provincia"));
                user.setCp(Integer.parseInt(resultado.getString("cp")));
                user.setTelefono(Integer.parseInt(resultado.getString("telefono")));
                System.out.println("User1: " + user.getApellidos());
            } 
        } catch (Exception e) { // Error en la conexión con la BD
            System.err.println("Error consiguiendo UsuarioBD");
            System.err.println(e.getMessage());
        }
         
        return user;
    }
    
    public void cambiarUsarioBD(UserBD user, String nick){
        abrirConexionBD();
        try {
            String con;
            Statement s = conexionBD.createStatement();
            // Consulta, buscamos una correspondencia usuario/clave
            
            // UPDATE `usuarios` SET `cp` = '46000' WHERE `usuarios`.`codigo` = 5;
            con = "UPDATE usuarios SET nombre='" + user.getNombre() + "', apellidos='" + user.getApellidos() + "', domicilio='" + user.getDomicilio()+ "', poblacion='" + user.getPoblacion()+ "', provincia='" + user.getProvincia()+ "', cp='" + user.getCp()+ "', telefono='" + user.getTelefono() + "' WHERE usuarios.usuario='" + nick + "'";
            System.out.println(con);
            s.execute(con);
            
            System.out.println("Usuario Actalizado");
        } catch (Exception e) {
            System.err.println("Error cambiando usuario");
            System.err.println(e.getMessage());
        }
    }
    
    public int generarRecibo(UserBD user, List<Producto> productos, float precioTotal){
        abrirConexionBD();
        int codigoPedido = -1;
        
        try{
            System.out.println("Recibo user: " + user.getId());
            String conPedido = "INSERT INTO `pedidos` (`codigo`, `persona`, `fecha`, `importe`, `estado`) VALUES (NULL, '" + user.getId() + "', NOW(), '" + precioTotal + "', '1');";
            Statement s = conexionBD.createStatement();
            s.execute(conPedido);
            
            String conCodigo = "SELECT p.codigo FROM pedidos p WHERE p.persona='"+ user.getId() + "' and p.importe='" + precioTotal + "' and p.estado='1'";
            ResultSet resultado = s.executeQuery(conCodigo);
            
            while (resultado.next()) {
                codigoPedido = resultado.getInt("codigo");
            }
        
            if (codigoPedido != -1){
                System.out.println("Codigo: " + codigoPedido);
                String conDetalle = "";
                for (Producto prod : productos){
                    conDetalle = "INSERT INTO `detalle` (`codigo_pedido`, `codigo_producto`, `unidades`, `precio_unitario`) VALUES ('" + codigoPedido + "','" + prod.getId() + "','" + prod.getCantidad() + "','" + prod.getPrecio() + "')";
                    s.execute(conDetalle);
                }
            }
            else{
                System.out.println("Error al recibir el codigo pedido");
            }
        }catch (Exception e) {
            System.err.println("Error al generar recibo");
            System.err.println(e.getMessage());
        }
        
        return codigoPedido;
    }
    
    public List<PedidoBD> conseguirPedidos(String user){
        List<PedidoBD> pedidos = new ArrayList<PedidoBD>();
        
        try{
            String con = "SELECT * FROM pedidos p WHERE p.persona = (SELECT u.codigo FROM usuarios u WHERE u.usuario = '" + user + "')";
            Statement s = conexionBD.createStatement();
            ResultSet resultado = s.executeQuery(con);

            while (resultado.next()) {
                int codPedido = resultado.getInt("codigo");
                String fecha = resultado.getString("fecha");
                float importe = resultado.getFloat("importe");
                int estado = resultado.getInt("estado");
                
                PedidoBD ped = new PedidoBD();
                
                ped.setCodigo(codPedido);
                ped.setFecha(fecha);
                ped.setImporte(importe);
                ped.setEstado(estado);
                
                pedidos.add(ped);
            }
        }catch (Exception e) {
            System.err.println("Error al conseguir pedidos");
            System.err.println(e.getMessage());
        }
        
        return pedidos;
    }
    
    public void cancelarPedido(int id, String user){
        try{
            String con = "SELECT p.estado FROM pedidos p WHERE p.codigo='"+ id + "' AND p.persona=(SELECT u.codigo FROM usuarios u WHERE u.usuario='" + user + "')";
            Statement s = conexionBD.createStatement();
            ResultSet resultado = s.executeQuery(con);
            int estado = 0;
                        
            if (resultado.next()) {
                estado = resultado.getInt("estado");                
            }
            
            if (estado == 1){
                // Eliminando primero clave ajena de productos en detalles
                con = "DELETE FROM detalle WHERE codigo_pedido = '" + id + "'";
                s.execute(con);
                
                // Eliminando el pedido
                con = "DELETE FROM pedidos WHERE codigo = '" + id + "'";
                s.execute(con);
            }
        }catch (Exception e) {
            System.err.println("Error al eliminar productos");
            System.err.println(e.getMessage());
        }
    }
    
    public Boolean registrarUsuario(String user, String password, String nombre, String apellidos){
        Boolean registroCompleto;
        
        try{
            String con = "SELECT * FROM usuarios WHERE usuarios.usuario='" + user + "'";
            Statement s = conexionBD.createStatement();
            ResultSet resultado = s.executeQuery(con);
            
            if (resultado.next()) {
               registroCompleto = false; // Ya existe un usuario
            }
            else {
                con = "INSERT INTO usuarios (codigo, activo, admin, usuario, clave, nombre, apellidos, domicilio, poblacion, provincia, cp, telefono) VALUES (NULL, '1', '0', '" + user + "','" + password + "','" + nombre + "','" + apellidos + "', NULL, NULL, NULL, NULL, NULL)";
                s.execute(con);
                registroCompleto = true;
            }
        }catch (Exception e) {
            System.err.println("Error al crear usuario");
            System.err.println(e.getMessage());
            registroCompleto = false;
        }
        
        return registroCompleto;
    }
}