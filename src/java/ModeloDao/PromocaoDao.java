
package ModeloDao;

import Modelo.Promocao;
import Util.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PromocaoDao {
    private static final String CADASTRARPROMOCAO = "update promocao set idproduto=?, nome=?, porcentagem=? ,ativo = true";
    private static final String LISTARPROMOCAO = "select * from promocao";
    private static final String DESATIVAR = "update promocao set ativo = false";
    
    public void cadastrar( Promocao p ){
        Connection conexao = null;
       try{
            conexao = Conexao.getConexao();           
            PreparedStatement pstmt = conexao.prepareStatement(CADASTRARPROMOCAO);     
            pstmt.setInt(1, p.getIdPromocao());
            pstmt.setString(2, p.getNome());
            pstmt.setDouble(3, p.getPorcentagem());                      
            pstmt.execute();    
       }catch (Exception e ){
           
           throw new RuntimeException(e);
           
       }finally{
           
           try{conexao.close();
           
           }catch (SQLException ex){
               
               throw new RuntimeException (ex);
           }
        }
    }   // FIM CADASTRAR PROMOCAO
    
    public Promocao listar() throws SQLException, ClassNotFoundException{       
       Connection conexao = Conexao.getConexao();
       PreparedStatement pstmt = conexao.prepareStatement(LISTARPROMOCAO);
       ResultSet rs = pstmt.executeQuery();       
       Promocao promo = new Promocao();
       while (rs.next()){         
            promo.setIdPromocao(rs.getInt("idproduto"));
            promo.setNome(rs.getString("nome"));
            promo.setPorcentagem(rs.getDouble("porcentagem"));
            promo.setAtivo(rs.getBoolean("ativo"));                             
       }
       return promo;
   } //FIM LISTAR PROMOCAO
    
    public void desativarPromocao(){
        Connection conexao = null;
       try{
            conexao = Conexao.getConexao();           
            PreparedStatement pstmt = conexao.prepareStatement(DESATIVAR);                                
            pstmt.execute();    
       }catch (Exception e ){
           
           throw new RuntimeException(e);
           
       }finally{
           
           try{conexao.close();
           
           }catch (SQLException ex){
               
               throw new RuntimeException (ex);
           }
        }
    }   // FIM DESATIVAR
    
}
