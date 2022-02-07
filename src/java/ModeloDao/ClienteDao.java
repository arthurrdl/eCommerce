
package ModeloDao;

import Modelo.Cliente;
import Modelo.Endereco;
import Modelo.Usuario;
import Util.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClienteDao {
    private static final String ATUALIZAR = "update cliente set nome=?, rg=?,cpf=?,telefone=?,celular=?  where idcliente=?";
    private static final String LISTAR = "select * from cliente order by idcliente";
    private static final String CADASTRAR = "insert into cliente (nome,rg,cpf,telefone,celular,idusuariofk) values (?,?,?,?,?,?) ";
    private static final String BUSCARPORID = "select * from cliente where idcliente = ?";
    private static final String BUSCARPORUSUARIO = "select * from cliente where idusuariofk = ?";   
    private static final String ADDENDERECO = "update cliente set idenderecofk=?  WHERE idcliente=?";  
    
    
    public void cadastrar (Cliente cliente){
       Connection conexao = null;
       try{
            conexao = Conexao.getConexao();           
            PreparedStatement pstmt = conexao.prepareStatement(CADASTRAR);
            
            
            pstmt.setString(1, cliente.getNome());
            pstmt.setString(2, cliente.getRg());
            pstmt.setString(3, cliente.getCpf());
            pstmt.setString(4, cliente.getTelefone());
            pstmt.setString(5, cliente.getCelular());
            pstmt.setInt(6,cliente.getUsuario().getIdUsuario());           
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
    
    public void atualizar(Cliente cliente){
        Connection conexao = null;
        try {
            conexao = Conexao.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(ATUALIZAR);
            pstmt.setString(1, cliente.getNome());
            pstmt.setString(2, cliente.getRg());
            pstmt.setString(3, cliente.getCpf());
            pstmt.setString(4, cliente.getTelefone());
            pstmt.setString(5, cliente.getCelular());      
            pstmt.setInt(6,cliente.getIdCliente());
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
    
    public ArrayList<Cliente> listar() throws SQLException, ClassNotFoundException{
       ArrayList<Cliente> listaCliente = new ArrayList<Cliente>();
       
        //conexão
       Connection conexao = Conexao.getConexao();
       //criar comando SQL
       PreparedStatement pstmt = conexao.prepareStatement(LISTAR);
       //executa
       ResultSet rs = pstmt.executeQuery();
       
       while (rs.next()){
           //a cada loop           
            Cliente novoCliente = new Cliente();
            Usuario usuario = new Usuario();
            Endereco endereco = new Endereco();
            novoCliente.setIdCliente(rs.getInt("idcliente"));
            novoCliente.setNome(rs.getString("nome"));
            novoCliente.setRg(rs.getString("rg"));
            novoCliente.setCpf(rs.getString("cpf"));
            novoCliente.setTelefone(rs.getString("telefone"));
            novoCliente.setCelular(rs.getString("celular")); 
            usuario.setIdUsuario(rs.getInt("idusuariofk"));
            endereco.setIdEndereco(rs.getInt("idenderecofk"));
            novoCliente.setUsuario(usuario);
            novoCliente.setEndereco(endereco);
            listaCliente.add(novoCliente);           
       }
       return listaCliente;
    } //FIM LISTAR    
    
    public Cliente localizarId(int idCliente)throws SQLException, ClassNotFoundException {
        //conexão
        Connection conexao = null;
        //Instancia de Array
        Cliente novoCliente = new Cliente(); 
        Endereco endereco = new Endereco();
        EnderecoDao edao = new EnderecoDao();
        Usuario usuario = new Usuario();
        UsuarioDao udao = new UsuarioDao();
        try{            
            conexao = Conexao.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(BUSCARPORID);
            pstmt.setInt(1,idCliente);
            ResultSet rs = pstmt.executeQuery();
        while (rs.next()){
            //a cada loop            
            novoCliente.setIdCliente(rs.getInt("idcliente"));
            novoCliente.setNome(rs.getString("nome"));
            novoCliente.setRg(rs.getString("rg"));
            novoCliente.setCpf(rs.getString("cpf"));
            novoCliente.setTelefone(rs.getString("telefone"));
            novoCliente.setCelular(rs.getString("celular"));
            int idusuario = rs.getInt("idusuariofk");
            int idendereco = rs.getInt("idenderecofk");
            usuario = udao.buscarPorId(idusuario);
            endereco = edao.localizarPorUsuario(usuario);
            novoCliente.setUsuario(usuario);
            novoCliente.setEndereco(endereco);
        }
        
       return novoCliente;
            
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
       
    public Cliente localizarIdUsuario(Usuario usuario)throws SQLException, ClassNotFoundException {
        //conexão
        Connection conexao = null;
        //Instancia de Array
        Cliente novoCliente = new Cliente();                
        try{            
            conexao = Conexao.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(BUSCARPORUSUARIO);
            pstmt.setInt(1,usuario.getIdUsuario());
            ResultSet rs = pstmt.executeQuery();
            Endereco endereco = new Endereco();
        while (rs.next()){
            //a cada loop            
            novoCliente.setIdCliente(rs.getInt("idcliente"));
            novoCliente.setNome(rs.getString("nome"));
            novoCliente.setRg(rs.getString("rg"));
            novoCliente.setCpf(rs.getString("cpf"));
            novoCliente.setTelefone(rs.getString("telefone"));
            novoCliente.setCelular(rs.getString("celular"));  
            usuario.setIdUsuario(rs.getInt("idusuariofk"));
            endereco.setIdEndereco(rs.getInt("idenderecofk"));
            novoCliente.setUsuario(usuario);
            novoCliente.setEndereco(endereco);
        }
        
       return novoCliente;
            
        }catch(Exception e ){
            throw new RuntimeException(e);
        }finally{
            try{
                conexao.close();
            }catch (SQLException ex){
                    throw new RuntimeException(ex);
            
                }            
            } 
    }//FIM LOCALIZAR POR IDUSUARIO    
    
    public void addEndereco(Cliente cliente)throws SQLException, ClassNotFoundException{
        Connection conexao = null;
        try {
            conexao = Conexao.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(ADDENDERECO);
            pstmt.setInt(1, cliente.getEndereco().getIdEndereco());
            pstmt.setInt(2, cliente.getIdCliente());            
            pstmt.execute();
           
        }catch (Exception e ){
           
           throw new RuntimeException(e);
           
        }finally{
           
           try{conexao.close();
           
        }catch (SQLException ex){
               
               throw new RuntimeException (ex);
        }
        }
        
    } //FIM ADD ENDERECO
   
}
