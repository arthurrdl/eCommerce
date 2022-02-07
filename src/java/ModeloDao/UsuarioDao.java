
package ModeloDao;

import Modelo.Usuario;
import Util.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class UsuarioDao {
    private static final String TROCARSENHA = "update usuario set senha=? WHERE idUsuario=? ";
    private static final String ATUALIZAREMAIL = "update usuario set email=? WHERE idUsuario=? ";
    private static final String LISTAR = "select * from usuario";
    private static final String CADASTRAR = "insert into usuario (usuario,senha,email,ativo,credencial) values (?,?,?,?,?)";
    private static final String EXCLUIR = "update usuario set ativo=? where idUsuario = ?";
    private static final String LOGIN = "select * from usuario where usuario=? and senha=?";
    public static final String VALIDA = "update usuario set ativo=? where email = ? "; 
    public static final String BUSCAPOREMAIL = "select * from usuario where email=?"; 
    public static final String BUSCAPORID = "select * from usuario where idusuario=?"; 
    
    public void ativarUsuario (String email){
       Connection conexao = null;
       try{
           conexao = Conexao.getConexao();
           PreparedStatement pstmt = conexao.prepareStatement(VALIDA);
           boolean ativo = true;
           pstmt.setBoolean(1, ativo); 
           pstmt.setString(2, email);                      
           pstmt.execute();      
           
       }catch (Exception e ){           
           throw new RuntimeException(e);           
       }finally{           
           try{conexao.close();           
           }catch (SQLException ex){               
               throw new RuntimeException (ex);
           }
       }
    }        
    
    public void alterarSenha(Usuario usuario){
        Connection conexao = null;
        try {
            conexao = Conexao.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(TROCARSENHA);
            pstmt.setString(1, usuario.getSenha());            
            pstmt.setInt(2,usuario.getIdUsuario());            
            pstmt.execute();
           
        }catch(Exception e ){
            throw new RuntimeException(e);
        }finally{
                try {
                    conexao.close();

                    }catch (SQLException ex){
                        throw new RuntimeException (ex);    
                    }
                }
    }//FIM ATUALIZAR    
    
    public ArrayList<Usuario> listar() throws SQLException, ClassNotFoundException{
       ArrayList<Usuario> listaUsuario = new ArrayList<Usuario>();
        //conexão
       Connection conexao = Conexao.getConexao();
       //criar comando SQL
       PreparedStatement pstmt = conexao.prepareStatement(LISTAR);
       //executa
       ResultSet rs = pstmt.executeQuery();
       
       while (rs.next()){
           //a cada loop
           
           Usuario novoUsuario = new Usuario();
           novoUsuario.setIdUsuario(rs.getInt("idUsuario"));
           novoUsuario.setUsuario(rs.getString("usuario"));
           novoUsuario.setSenha(rs.getString("senha"));
           novoUsuario.setEmail(rs.getString("email"));            
           novoUsuario.setAtivo(rs.getBoolean("ativo"));     
           novoUsuario.setCredencial(rs.getString("credencial"));
           
           listaUsuario.add(novoUsuario);
           
       }
       return listaUsuario;
   } //FIM LISTAR  
    
    public void cadastrar (Usuario usuario){
       Connection conexao = null;
       try{
           conexao = Conexao.getConexao();
           PreparedStatement pstmt = conexao.prepareStatement(CADASTRAR);
           
           pstmt.setString(1, usuario.getUsuario());
           pstmt.setString(2, usuario.getSenha());
           pstmt.setString(3, usuario.getEmail());
           pstmt.setBoolean(4, usuario.getAtivo());
           pstmt.setString(5, usuario.getCredencial());
           
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
    
    public void excluir(Usuario usuario)throws SQLException, ClassNotFoundException{
        Connection conexao = null;
       try{
           conexao = Conexao.getConexao();
           PreparedStatement pstmt = conexao.prepareStatement(EXCLUIR);
           boolean ativo = false;
           pstmt.setBoolean(1, ativo); 
           pstmt.setInt(2, usuario.getIdUsuario());                      
           pstmt.execute();      
           
       }catch (Exception e ){           
           throw new RuntimeException(e);           
       }finally{           
           try{conexao.close();           
           }catch (SQLException ex){               
               throw new RuntimeException (ex);
           }
       }
}//FIM DELETE
    
    public Usuario logar (String  usuario, String senha)throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        Usuario usuarioLogado = new Usuario();        
        try{            
            conexao = Conexao.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(LOGIN);
            pstmt.setString(1,usuario);
            pstmt.setString(2,senha);
            ResultSet rs = pstmt.executeQuery();
        while (rs.next()){        //a cada loop                     
            usuarioLogado.setIdUsuario(rs.getInt("idUsuario"));
            usuarioLogado.setUsuario(rs.getString("usuario"));
            usuarioLogado.setSenha(rs.getString("senha"));
            usuarioLogado.setEmail(rs.getString("email"));            
            usuarioLogado.setAtivo(rs.getBoolean("ativo"));  
            usuarioLogado.setCredencial(rs.getString("credencial"));                  
        }
            return usuarioLogado;            
        }catch(Exception e ){
            throw new RuntimeException(e);
        }finally{
            try{
                conexao.close();
            }catch (SQLException ex){
                    throw new RuntimeException(ex);
            
                }            
            } 
    }
    
    public boolean emailExiste(String email)throws SQLException, ClassNotFoundException{
        boolean flag = false;
        Connection conexao = null;
        try{
            conexao = Conexao.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(BUSCAPOREMAIL);
            pstmt.setString(1,email);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()){
               flag = true;         
            }            
            
        }catch (Exception e ){           
           throw new RuntimeException(e);           
        }finally{           
           try{conexao.close();           
           }catch (SQLException ex){               
               throw new RuntimeException (ex);
           }
       }
        
        return flag;
        
    } //FIM EMAILEXISTE 
  
    public boolean usuarioExiste(String  usuario, String senha)throws SQLException, ClassNotFoundException{
        boolean flag = false;
        Connection conexao = null;
        try{
            conexao = Conexao.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(LOGIN);
            pstmt.setString(1,usuario);
            pstmt.setString(2,senha);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()){
               flag = true;         
            }  
            return flag;
        }catch (Exception e ){           
           throw new RuntimeException(e);           
        }finally{           
           try{conexao.close();           
           }catch (SQLException ex){               
               throw new RuntimeException (ex);
           }
       }
    }     
    
    public Usuario buscarPorId (int idUsuario)throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        Usuario usuarioLogado = new Usuario();        
        try{            
            conexao = Conexao.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(BUSCAPORID);
            pstmt.setInt(1,idUsuario);
            ResultSet rs = pstmt.executeQuery();
        while (rs.next()){        //a cada loop                     
            usuarioLogado.setIdUsuario(rs.getInt("idUsuario"));
            usuarioLogado.setUsuario(rs.getString("usuario"));
            usuarioLogado.setSenha(rs.getString("senha"));
            usuarioLogado.setEmail(rs.getString("email"));            
            usuarioLogado.setAtivo(rs.getBoolean("ativo"));  
            usuarioLogado.setCredencial(rs.getString("credencial"));                  
        }
            return usuarioLogado;            
        }catch(Exception e ){
            throw new RuntimeException(e);
        }finally{
            try{
                conexao.close();
            }catch (SQLException ex){
                    throw new RuntimeException(ex);
            
                }            
            } 
    }
    
    public void atualizarEmail (Usuario usuario){
       Connection conexao = null;
       try{
           conexao = Conexao.getConexao();
           PreparedStatement pstmt = conexao.prepareStatement(ATUALIZAREMAIL);        
           pstmt.setString(1, usuario.getEmail());
           pstmt.setInt(2, usuario.getIdUsuario());      
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
    
    
    
    
    
    
    
    
    
    
    
}