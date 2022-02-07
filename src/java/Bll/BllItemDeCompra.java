
package Bll;

import Modelo.CarrinhoDeCompra;
import Modelo.Compra;
import ModeloDao.ItemDeCompraDao;
import java.sql.SQLException;

public class BllItemDeCompra {
    ItemDeCompraDao idao = new ItemDeCompraDao();
    
    public CarrinhoDeCompra localizarItens(Compra compra) throws SQLException, ClassNotFoundException{
            CarrinhoDeCompra carrinho = idao.localizarItens(compra);
            return carrinho;
    }
    
}
