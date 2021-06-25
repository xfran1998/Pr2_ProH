package p2;

import java.sql.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class JDBCUnitTest {
    
    /**
     * Constante de URI para conectar a BBDD
     */
    protected static String uriConexionBD = "jdbc:mysql://localhost:3306/pokeshop";
    
    @Test
    public void testJDBC() throws ClassNotFoundException, SQLException {    
        Connection conexionBD = null;
                
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexionBD = DriverManager.getConnection(uriConexionBD,"root","");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        assertNotNull(conexionBD);
        conexionBD.close();
    }
}