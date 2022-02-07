
package Bll;

import Modelo.Cliente;
import Modelo.Compra;
import Modelo.ItemDeCompra;
import ModeloDao.CompraDao;
import ModeloDao.ItemDeCompraDao;
import java.sql.SQLException;
import java.util.ArrayList;


public class BllCompra {
    CompraDao cdao = new CompraDao();
    
    public void cadastroCompra(Compra compra) throws SQLException, ClassNotFoundException{        
        ItemDeCompraDao idao = new ItemDeCompraDao();        
        cdao.cadastrar(compra);
        compra = cdao.buscarCompra(compra.getCliente(), compra.getDataCompra(), compra.getCarrinho());     
        
        for (ItemDeCompra item : compra.getCarrinho().getItens()){
            idao.cadastrarItem(item, compra);
        }
    }
    
    public ArrayList<Compra> listarCompras(Cliente cliente) throws SQLException, ClassNotFoundException{
        ArrayList<Compra> listaDeCompras = cdao.listarCompras(cliente);
        return listaDeCompras;
    }
}
