package ModeloDao;

import Modelo.Endereco;
import Modelo.Usuario;
import Util.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EnderecoDao {
    private static final String ATUALIZAR = "update endereco set logradouro=?,numero=?,bairro=?,cidade=?,cep=?  WHERE idendereco=?";
    private static final String LISTAR = "select * from endereco order by idendereco";
    private static final String CADASTRAR = "insert into endereco (logradouro,numero,bairro,cidade,cep) values (?,?,?,?,?) ";
    private static final String BUSCARPOENDERECO= "select * from endereco where logradouro = ? and numero=? "; 
    private static final String BUSCARPORUSUARIO= "select * from endereco where idendereco = (select idenderecofk from cliente where idusuariofk = ?) "; 
    private static final String BUSCARPOID = "select * from endereco where idendereco = ?";
    
    public void cadastrar (Endereco endereco){
       Connection conexao = null;
       try{
            conexao = Conexao.getConexao();           
            PreparedStatement pstmt = conexao.prepareStatement(CADASTRAR);
            
            pstmt.setString(1, endereco.getLogradouro());
            pstmt.setInt(2, endereco.getNumero());
            pstmt.setString(3, endereco.getBairro());
            pstmt.setString(4, endereco.getCidade());
            pstmt.setInt(5, endereco.getCep());                 
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
    
    public void atualizar(Endereco endereco){
        Connection conexao = null;
        try {
            conexao = Conexao.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(ATUALIZAR);
                  
            pstmt.setString(1, endereco.getLogradouro());
            pstmt.setInt(2, endereco.getNumero());
            pstmt.setString(3, endereco.getBairro());
            pstmt.setString(4, endereco.getCidade());
            pstmt.setInt(5, endereco.getCep()); 
            pstmt.setInt(6, endereco.getIdEndereco());
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
    
    public ArrayList<Endereco> listar() throws SQLException, ClassNotFoundException{
       ArrayList<Endereco> listaEndereco = new ArrayList<Endereco>();
        //conexão
       Connection conexao = Conexao.getConexao();
       //criar comando SQL
       PreparedStatement pstmt = conexao.prepareStatement(LISTAR);
       //executa
       ResultSet rs = pstmt.executeQuery();
       
       while (rs.next()){
           //a cada loop           
            Endereco novoEndereco = new Endereco();
            novoEndereco.setIdEndereco(rs.getInt("idendereco"));
            novoEndereco.setLogradouro(rs.getString("logradouro"));
            novoEndereco.setNumero(rs.getInt("numero"));
            novoEndereco.setBairro(rs.getString("bairro"));
            novoEndereco.setCidade(rs.getString("cidade"));
            novoEndereco.setCep(rs.getInt("cep"));           
            listaEndereco.add(novoEndereco);    
            
            
            
       }
       return listaEndereco;
   } //FIM LISTAR    
    
    public Endereco localizarPorEndereco(Endereco endereco)throws SQLException, ClassNotFoundException {
        //conexão
        Connection conexao = null;
        //Instancia de Array
        Endereco novoEndereco = new Endereco();                
        try{            
            conexao = Conexao.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(BUSCARPOENDERECO);
            pstmt.setString(1,endereco.getLogradouro());
            pstmt.setInt(2,endereco.getNumero());
            ResultSet rs = pstmt.executeQuery();
        while (rs.next()){
            //a cada loop            
            novoEndereco.setIdEndereco(rs.getInt("idendereco"));
            novoEndereco.setLogradouro(rs.getString("logradouro"));
            novoEndereco.setNumero(rs.getInt("numero"));
            novoEndereco.setBairro(rs.getString("bairro"));
            novoEndereco.setCidade(rs.getString("cidade"));
            novoEndereco.setCep(rs.getInt("cep")); 
        }
        
       return novoEndereco;
            
        }catch(Exception e ){
            throw new RuntimeException(e);
        }finally{
            try{
                conexao.close();
            }catch (SQLException ex){
                    throw new RuntimeException(ex);
            
                }            
            } 
    }//FIM LOCALIZAR POR Endereco
         
    public Endereco localizarPorUsuario(Usuario usuario )throws SQLException, ClassNotFoundException {
        //conexão
        Connection conexao = null;
        //Instancia de Array
        Endereco novoEndereco = new Endereco();                
        try{            
            conexao = Conexao.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(BUSCARPORUSUARIO);
            pstmt.setInt(1,usuario.getIdUsuario());
           
            ResultSet rs = pstmt.executeQuery();
        while (rs.next()){
            //a cada loop            
            novoEndereco.setIdEndereco(rs.getInt("idendereco"));
            novoEndereco.setLogradouro(rs.getString("logradouro"));
            novoEndereco.setNumero(rs.getInt("numero"));
            novoEndereco.setBairro(rs.getString("bairro"));
            novoEndereco.setCidade(rs.getString("cidade"));
            novoEndereco.setCep(rs.getInt("cep")); 
        }
        
       return novoEndereco;
            
        }catch(Exception e ){
            throw new RuntimeException(e);
        }finally{
            try{
                conexao.close();
            }catch (SQLException ex){
                    throw new RuntimeException(ex);
            
                }            
            } 
    }//FIM LOCALIZAR POR CPF
    
    public Endereco localizarPorId(int idendereco)throws SQLException, ClassNotFoundException {
        //conexão
        Connection conexao = null;
        //Instancia de Array
        Endereco novoEndereco = new Endereco();                
        try{            
            conexao = Conexao.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(BUSCARPOID);
            pstmt.setInt(1, idendereco);            
            ResultSet rs = pstmt.executeQuery();
        while (rs.next()){
            //a cada loop            
            novoEndereco.setIdEndereco(rs.getInt("idendereco"));
            novoEndereco.setLogradouro(rs.getString("logradouro"));
            novoEndereco.setNumero(rs.getInt("numero"));
            novoEndereco.setBairro(rs.getString("bairro"));
            novoEndereco.setCidade(rs.getString("cidade"));
            novoEndereco.setCep(rs.getInt("cep")); 
        }
        
       return novoEndereco;
            
        }catch(Exception e ){
            throw new RuntimeException(e);
        }finally{
            try{
                conexao.close();
            }catch (SQLException ex){
                    throw new RuntimeException(ex);
            
                }            
            } 
    }//FIM LOCALIZAR POR CPF
        
}
