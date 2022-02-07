
package Bll;

import Modelo.Produto;
import Modelo.Promocao;
import ModeloDao.PromocaoDao;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class BllPromocao {
    PromocaoDao pdao = new PromocaoDao();
    BllProduto bllP = new BllProduto();
    
    public void cadastrar(double porcento) throws SQLException, ClassNotFoundException{        
        ArrayList<Produto> lista = bllP.buscarProdutos();             
        Random rad= new Random();       
        Produto sorteado = lista.get(rad.nextInt(lista.size()));                
        Promocao promo = new Promocao();
        promo.setIdPromocao(sorteado.getIdProduto());
        promo.setPorcentagem(porcento);
        promo.setNome(sorteado.getNome());
        pdao.cadastrar(promo);
        sorteado.setNome(sorteado.getNome()+" "+porcento+"% OFF");
        double valorVenda = sorteado.getValorVenda()-(sorteado.getValorVenda()*porcento/100);
        sorteado.setValorVenda(valorVenda);
        bllP.gerarPromocao(sorteado);
    }
    
    public Promocao buscar() throws SQLException, ClassNotFoundException{
        Promocao promo = pdao.listar();
        return promo;
    }
    
    public void removerPromocao() throws SQLException, ClassNotFoundException{
        Promocao promo =pdao.listar();
        Produto prod = bllP.buscarPorId(promo.getIdPromocao());
        prod.setNome(promo.getNome());
        prod.setValorVenda(prod.getValorCompra()*1.25);
        bllP.gerarPromocao(prod);
        pdao.desativarPromocao();
    }
    
    
    
}
