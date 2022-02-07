package ModeloDao;

import Modelo.Categoria;
import Util.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoriaDao {
    private static final String CADASTRAR = "insert into categoria (nome) values (?)";
    private static final String ATUALIZAR = "update categoria set nome = ? where idcategoria=?";
    private static final String LISTAR = "select * from categoria order by idcategoria";
    private static final String BUSCARPORID = "select * from categoria where idcategoria = ?";
    private static final String BUSCARPORNOME = "select * from categoria where nome = ?"; 
   
    public void cadastrar(Categoria c){
       Connection conexao = null;
       try{
            conexao = Conexao.getConexao();           
            PreparedStatement pstmt = conexao.prepareStatement(CADASTRAR);                
            pstmt.setString(1, c.getNome());                    
            pstmt.execute();         
           
       }catch (Exception e ){
           
           throw new RuntimeException(e);
           
       }finally{
           
           try{conexao.close();
           
           }catch (SQLException ex){
               
               throw new RuntimeException (ex);
           }
        }
   }//FIM CADASTRAR
   
    public ArrayList<Categoria> listar() throws SQLException, ClassNotFoundException{
       ArrayList<Categoria> listaCategoria = new ArrayList<Categoria>();
       Connection conexao = Conexao.getConexao();
       PreparedStatement pstmt = conexao.prepareStatement(LISTAR);
       ResultSet rs = pstmt.executeQuery();       
       while (rs.next()){
           //a cada loop           
            Categoria novaCategoria = new Categoria();
            novaCategoria.setIdCategoria(rs.getInt("idcategoria"));                        
            novaCategoria.setNome(rs.getString("nome"));
                     
           listaCategoria.add(novaCategoria);           
       }
       return listaCategoria;
   } //FIM LISTAR    
    
    public Categoria buscarPorId(int idCategoria)throws SQLException, ClassNotFoundException {
        Connection conexao = null;  
        try{            
            conexao = Conexao.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(BUSCARPORID);
            pstmt.setInt(1,idCategoria);
            ResultSet rs = pstmt.executeQuery();
            Categoria novaCategoria = new Categoria();
        while (rs.next()){            
            novaCategoria.setIdCategoria(rs.getInt("idCategoria"));
            novaCategoria.setNome(rs.getString("nome"));                               
        }
       return novaCategoria;
            
        }catch(Exception e ){
            throw new RuntimeException(e);
        }finally{
            try{
                conexao.close();
            }catch (SQLException ex){
                    throw new RuntimeException(ex);
            
                }            
            } 
    }//FIM BUSCARPORID
    
    public void atualizar(Categoria c){
       Connection conexao = null;
       try{
            conexao = Conexao.getConexao();           
            PreparedStatement pstmt = conexao.prepareStatement(ATUALIZAR);                
            pstmt.setString(1, c.getNome());  
            pstmt.setInt(2, c.getIdCategoria());   
            pstmt.execute();         
           
       }catch (Exception e ){
           
           throw new RuntimeException(e);
           
       }finally{
           
           try{conexao.close();
           
           }catch (SQLException ex){
               
               throw new RuntimeException (ex);
           }
        }
   }//FIM ATUALIZAR
    
    public Categoria buscarPorNome(String nome)throws SQLException, ClassNotFoundException {
        Connection conexao = null;  
        try{            
            conexao = Conexao.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(BUSCARPORNOME);
            pstmt.setString(1,nome);
            ResultSet rs = pstmt.executeQuery();
            Categoria novaCategoria = new Categoria();
        while (rs.next()){            
            novaCategoria.setIdCategoria(rs.getInt("idCategoria"));
            novaCategoria.setNome(rs.getString("nome"));                               
        }
       return novaCategoria;
            
        }catch(Exception e ){
            throw new RuntimeException(e);
        }finally{
            try{
                conexao.close();
            }catch (SQLException ex){
                    throw new RuntimeException(ex);
            
                }            
            } 
    }//FIM BUSCARPORID
}
