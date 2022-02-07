
package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    public static Connection getConexao()throws ClassNotFoundException,SQLException{  
        
        Class.forName("org.postgresql.Driver");
        Connection conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/eCommerce","postgres","postgres");
      
    return conexao;
    }
}
