
package ModeloDao;

import Modelo.CarrinhoDeCompra;
import Modelo.Cliente;
import Modelo.Compra;
import Modelo.ItemDeCompra;
import Modelo.Produto;
import Util.Conexao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;


public class ItemDeCompraDao {
    private static final String CADASTRARITEM = "insert into itemdecompra (idproduto,quantidade,total,idcomprafk) values (?,?,?,?)";
    private static final String LOCALIZARCARRINHO = "select * from itemdecompra where idcomprafk = ? ";  
    private static final String ATUALIZAR = "update compra set quantidade=?,total=? where iditemdecompra=? ";
    
    public void cadastrarItem(ItemDeCompra item, Compra compra){
       Connection conexao = null;
       try{
            conexao = Conexao.getConexao();              
            PreparedStatement pstmt = conexao.prepareStatement(CADASTRARITEM);        
            pstmt.setInt( 1 , item.getProduto().getIdProduto());
            pstmt.setInt( 2 , item.getQuantidade());
            pstmt.setDouble( 3 , item.getTotal());
            pstmt.setInt( 4 , compra.getIdCompra());            
           
            pstmt.execute();
           
       }catch (Exception e ){
           
           throw new RuntimeException(e);
           
       }finally{
           
           try{conexao.close();
           
           }catch (SQLException ex){
               
               throw new RuntimeException (ex);
           }
    }
   }//FIM CADASTRAR ITEM
    
    public CarrinhoDeCompra localizarItens(Compra compra) throws SQLException, ClassNotFoundException{
        CarrinhoDeCompra carrinho = new CarrinhoDeCompra();
        ProdutoDao pdao = new ProdutoDao();        
        //conexão
       Connection conexao = Conexao.getConexao();
       //criar comando SQL
       PreparedStatement pstmt = conexao.prepareStatement(LOCALIZARCARRINHO);     
        pstmt.setInt(1,compra.getIdCompra());
       //executa
       ResultSet rs = pstmt.executeQuery();     
      
       while (rs.next()){
            //a cada loop           
            int id = rs.getInt("idproduto");
            Produto produto = new Produto();
            produto = pdao.listarPorId(id);
            ItemDeCompra item = new ItemDeCompra();
            item.setIdItemDeCompra(rs.getInt("iditemdecompra"));
            item.setProduto(produto);            
            item.setQuantidade(rs.getInt("quantidade"));
            item.setTotal(rs.getDouble("total"));            
            carrinho.addNovoItem(item);         
        }
        
       return carrinho;
   } //FIM LOCALIZAR CARRINHO
    
    public void atualizaItem(ItemDeCompra item) throws SQLException, ClassNotFoundException, ParseException{
        //conexão
        Connection conexao = Conexao.getConexao();
        //criar comando SQL
        PreparedStatement pstmt = conexao.prepareStatement(ATUALIZAR);     
        pstmt.setInt( 1 , item.getQuantidade());
        pstmt.setDouble( 2 , item.getTotal());
        pstmt.setInt( 3 , item.getIdItemDeCompra());
        pstmt.execute();
   
        
       
   } //FIM ATUALIZAR
    
    
}

