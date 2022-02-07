package ModeloDao;

import Bll.BllCategoria;
import Modelo.Categoria;
import Modelo.Produto;
import Util.Conexao;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



public class ProdutoDao {
    private static final String CADASTRAR = "insert into produto (nome,idcategoriafk,valorcompra,valorvenda,imagem,descricao) values (?,?,?,?,?,?)";
    private static final String ATUALIZAR = "update produto set nome=?,idcategoriafk=?,valorvenda=?,imagem=?,descricao=? where idproduto=?";    
    private static final String LISTAR = "select * from produto order by idproduto";
    private static final String BUSCARPORID = "select * from produto where idproduto = ?";
    private static final String BUSCARPORNOME = "select * from produto where nome ilike ?";
    private static final String TOP6 = "select idproduto , sum(quantidade) as quantidade from itemdecompra  group by idproduto order by quantidade desc;";
    private static final String PROMOCAO = "update produto set nome=?, valorvenda=? where idproduto=?";      
    private static final String CATEGORIAS = "select * from produto where idcategoriafk = ?";
    
    BllCategoria bllCat = new BllCategoria();
    
    public void cadastrar (Produto p){
       Connection conexao = null;
       try{
            conexao = Conexao.getConexao();           
            PreparedStatement pstmt = conexao.prepareStatement(CADASTRAR);     
            pstmt.setString(1, p.getNome());
            pstmt.setInt(2, p.getCategoria().getIdCategoria());
            pstmt.setDouble(3, p.getValorCompra());
            pstmt.setDouble(4, p.getValorVenda());
            pstmt.setString(5, p.getImagem());
            pstmt.setString(6, p.getDescricao());           
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
    
    public void atualizar (Produto p){
       Connection conexao = null;
       try{
            conexao = Conexao.getConexao();           
            PreparedStatement pstmt = conexao.prepareStatement(ATUALIZAR);     
            pstmt.setString(1, p.getNome());
            pstmt.setInt(2, p.getCategoria().getIdCategoria());            
            pstmt.setDouble(3, p.getValorVenda());
            pstmt.setString(4, p.getImagem());
            pstmt.setString(5, p.getDescricao());
            pstmt.setInt(6, p.getIdProduto());
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
   
    public ArrayList<Produto> listar() throws SQLException, ClassNotFoundException{
       ArrayList<Produto> listaProduto = new ArrayList<Produto>();
        //conexão
       Connection conexao = Conexao.getConexao();
       //criar comando SQL
       PreparedStatement pstmt = conexao.prepareStatement(LISTAR);
       //executa
       ResultSet rs = pstmt.executeQuery();
       
       while (rs.next()){
           //a cada loop           
            Produto novoProduto = new Produto();
            novoProduto.setIdProduto(rs.getInt("idProduto"));                        
            novoProduto.setNome(rs.getString("nome"));
            novoProduto.setValorCompra(rs.getDouble("valorCompra"));
            novoProduto.setValorVenda(rs.getDouble("valorVenda"));
            novoProduto.setDescricao(rs.getString("descricao"));
            novoProduto.setImagem(rs.getString("imagem")); 
            int id = rs.getInt("idcategoriafk");
            Categoria cat = bllCat.buscarPorId(id);
            novoProduto.setCategoria(cat);
            listaProduto.add(novoProduto);           
       }
       return listaProduto;
   } //FIM LISTAR    
    
    public Produto listarPorId(int idProduto)throws SQLException, ClassNotFoundException {
        //conexão
        Connection conexao = null;
        //Instancia de Array
        Produto produto = new Produto();        
        try{            
            conexao = Conexao.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(BUSCARPORID);
            pstmt.setInt(1,idProduto);
            ResultSet rs = pstmt.executeQuery();
        while (rs.next()){
            //a cada loop           
            produto.setIdProduto(rs.getInt("idProduto"));                        
            produto.setNome(rs.getString("nome"));
            produto.setValorCompra(rs.getDouble("valorCompra"));
            produto.setValorVenda(rs.getDouble("valorVenda"));
            produto.setDescricao(rs.getString("descricao"));
            produto.setImagem(rs.getString("imagem"));
            int id = rs.getInt("idcategoriafk");
            Categoria cat = bllCat.buscarPorId(id);
            produto.setCategoria(cat);
            
                     
        }
       return produto;
            
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
    
    public ArrayList<Produto> buscar(String busca) throws SQLException, ClassNotFoundException{
       ArrayList<Produto> listaProduto = new ArrayList<Produto>();       
       Connection conexao = Conexao.getConexao();
       PreparedStatement pstmt = conexao.prepareStatement(BUSCARPORNOME);
       String consulta = "%"+busca+"%";
       pstmt.setString(1,consulta);
       ResultSet rs = pstmt.executeQuery();
       
       while (rs.next()){
           //a cada loop           
            Produto novoProduto = new Produto();
            novoProduto.setIdProduto(rs.getInt("idProduto"));                        
            novoProduto.setNome(rs.getString("nome"));
            novoProduto.setValorCompra(rs.getDouble("valorCompra"));
            novoProduto.setValorVenda(rs.getDouble("valorVenda"));
            novoProduto.setDescricao(rs.getString("descricao"));
            novoProduto.setImagem(rs.getString("imagem")); 
            int id = rs.getInt("idcategoriafk");
            Categoria cat = bllCat.buscarPorId(id);
            novoProduto.setCategoria(cat);
            listaProduto.add(novoProduto);           
       }
       return listaProduto;
   } //FIM LISTAR  
   
    public ArrayList<Produto> top6() throws SQLException, ClassNotFoundException{
       ArrayList<Produto> listaProduto = new ArrayList<Produto>();
        //conexão
       Connection conexao = Conexao.getConexao();
       //criar comando SQL
       PreparedStatement pstmt = conexao.prepareStatement(TOP6);
       //executa
       ResultSet rs = pstmt.executeQuery();
       
       while (rs.next()){
           //a cada loop           
            int idproduto = rs.getInt("idProduto");           
            Produto novoProduto = listarPorId(idproduto);                           
            listaProduto.add(novoProduto);           
       }
       return listaProduto;
   } //FIM TOP6   
    
    public void promocao (Produto p){
       Connection conexao = null;
       try{
            conexao = Conexao.getConexao();           
            PreparedStatement pstmt = conexao.prepareStatement(PROMOCAO);     
            pstmt.setString(1, p.getNome());
            pstmt.setDouble(2, p.getValorVenda());            
            pstmt.setInt(3, p.getIdProduto());
            pstmt.execute();    
       }catch (Exception e ){
           
           throw new RuntimeException(e);
           
       }finally{
           
           try{conexao.close();
           
           }catch (SQLException ex){
               
               throw new RuntimeException (ex);
           }
        }
   }//FIM PROMOCAO
    
    public ArrayList<Produto> listarPorCategoria( Categoria categoria) throws SQLException, ClassNotFoundException{
       ArrayList<Produto> listaProduto = new ArrayList<Produto>();
       Connection conexao = Conexao.getConexao();
       PreparedStatement pstmt = conexao.prepareStatement(CATEGORIAS);
       pstmt.setInt(1, categoria.getIdCategoria());
       ResultSet rs = pstmt.executeQuery();
       
       while (rs.next()){
           //a cada loop           
            Produto novoProduto = new Produto();
            novoProduto.setIdProduto(rs.getInt("idProduto"));                        
            novoProduto.setNome(rs.getString("nome"));
            novoProduto.setValorCompra(rs.getDouble("valorCompra"));
            novoProduto.setValorVenda(rs.getDouble("valorVenda"));
            novoProduto.setDescricao(rs.getString("descricao"));
            novoProduto.setImagem(rs.getString("imagem")); 
            int id = rs.getInt("idcategoriafk");
            Categoria cat = bllCat.buscarPorId(id);
            novoProduto.setCategoria(cat);
            listaProduto.add(novoProduto);           
       }
       return listaProduto;
   } //FIM CATEGORIAS    
    

     
}//FIM LISTAR