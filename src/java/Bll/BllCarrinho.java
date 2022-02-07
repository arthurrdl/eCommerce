package Bll;
import Modelo.CarrinhoDeCompra;
import Modelo.Usuario;
import Modelo.Endereco;
import Modelo.ItemDeCompra;
import Modelo.Produto;
import ModeloDao.ProdutoDao;
import java.sql.SQLException;

public class BllCarrinho {
    
    private String pagina;

    public String getPagina() {
        return pagina;
    }     
    
    public void FinalizarCompra(CarrinhoDeCompra carrinho , Usuario usuario, Endereco endereco){          
        if (carrinho.getItens()==null | carrinho.getItens().size()==0){                  
                pagina= "carrinhoVazio.jsp";
            }else if(usuario == null){              
                    pagina="cadastro.jsp";
            }else if ( endereco==null | endereco.getIdEndereco() == 0 ){                    
                pagina="ControleCliente?acao=ChecarCliente";
            }else{
                pagina="finalizarCompra.jsp";
            }
    }

    public CarrinhoDeCompra AddProduto(CarrinhoDeCompra carrinho, int idProduto) throws SQLException, ClassNotFoundException{           
               boolean existe = false;        
              if (carrinho == null) {                                                                                       
                    carrinho = new CarrinhoDeCompra();                                      
                }
                if (carrinho.getItens()!=null){
                        for(ItemDeCompra item : carrinho.getItens()){
                                if(item.getProduto().getIdProduto()==idProduto){
                                    //incrementa a quantidade
                                    item.setQuantidade(item.getQuantidade()+1);
                                    existe = true;
                                }                    
                        }
                }
                
               
                if(existe==false){
                    Produto produto = new ProdutoDao().listarPorId(idProduto);
                    ItemDeCompra novoItem = new ItemDeCompra();
                    novoItem.setProduto(produto);
                    novoItem.setQuantidade(1);
                    carrinho.addNovoItem(novoItem);                
                } 
                
                return carrinho;
    }          
                    
    public CarrinhoDeCompra RemoveProduto(CarrinhoDeCompra carrinho , int idProduto){        
        ItemDeCompra itemRemove = new ItemDeCompra();
        for (ItemDeCompra item : carrinho.getItens()){
            if (item.getProduto().getIdProduto() == idProduto){
                itemRemove = item;                               
            }            
        }      
        carrinho.removerItem(itemRemove);                 
        return carrinho;
    }
    
    public CarrinhoDeCompra DiminuiProduto(CarrinhoDeCompra carrinho, int idProduto) throws SQLException, ClassNotFoundException{
        for(ItemDeCompra item : carrinho.getItens()){
                    if(item.getProduto().getIdProduto()==idProduto){                     
                        item.setQuantidade(item.getQuantidade()-1);
                        
                    }                              
        }                       
         
        return carrinho;
    }  
       
    public void VerrificarCarrinho(CarrinhoDeCompra carrinho){         
        pagina="carrinho.jsp";            
         if (carrinho == null || carrinho.getItens().isEmpty()){
           pagina="carrinhoVazio.jsp";
        }         
        
    }
    
    public CarrinhoDeCompra AtualizarCarrinho(CarrinhoDeCompra carrinho, int idProduto){             
        int x =0;
        for (ItemDeCompra item : carrinho.getItens()){
            if(item.getProduto().getIdProduto()==idProduto){
                if(item.getQuantidade()==0){
                    x = item.getProduto().getIdProduto();
                }
            }            
        }
        if (x!=0){
            RemoveProduto(carrinho, x);
        }       
        return carrinho;
    }
    
}
