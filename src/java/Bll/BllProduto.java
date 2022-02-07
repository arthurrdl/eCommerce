    package Bll;

import Modelo.CarrinhoDeCompra;
import Modelo.Categoria;
import Modelo.Compra;
import Modelo.Email;
import Modelo.ItemDeCompra;
import Modelo.Produto;
import Modelo.Upload;
import ModeloDao.CompraDao;
import ModeloDao.ItemDeCompraDao;
import ModeloDao.ProdutoDao;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.http.Part;

public class BllProduto {
    ProdutoDao pdao = new ProdutoDao();
    ItemDeCompraDao idao = new ItemDeCompraDao();
    CompraDao cdao = new CompraDao();
    SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    public Produto buscarPorId(int id) throws SQLException, ClassNotFoundException{
        Produto p = pdao.listarPorId(id);
        return p;
    }
    
    public ArrayList<Produto> buscarProdutos() throws SQLException, ClassNotFoundException{
        ArrayList<Produto> produtos = pdao.listar();
        return produtos;
    }
    
    public ArrayList<Produto> buscarTop6() throws SQLException, ClassNotFoundException{
        ArrayList<Produto> produtos = pdao.top6();
        return produtos;
    }
    
    public void gerarRelatorioDatado(String dataInicial, String dataFinal , String email) throws ParseException, SQLException, ClassNotFoundException, MessagingException, AddressException, InterruptedException, Exception{
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        Date di = f.parse(dataInicial);
        Date df = f.parse(dataFinal);
        String relatorio = relatorioDatado(di, df);
        Email enviar = new Email();
        enviar.enviaRelatorioDatadoPorEmail(email, dataInicial, dataFinal, relatorio);        
    }
    
    public void gerarRelatorioMensal(String email) throws ParseException, SQLException, ClassNotFoundException, MessagingException, AddressException, InterruptedException, Exception{
        String relatorio = relatorioMensal();
        Email enviar = new Email();
        enviar.enviaRelatorioMensal(email, relatorio);        
    }
    
    public void gerarPromocao(Produto produto ){
        pdao.promocao(produto);        
    }    
    
    public String relatorioMensal() throws SQLException, ClassNotFoundException{
        ArrayList<Compra> listaDeCompras = cdao.relatorioMensal();
        for(Compra c : listaDeCompras){
            CarrinhoDeCompra carrinho = idao.localizarItens(c);
            c.setCarrinho(carrinho);
        }
        Double total = 0.0;
        String retorno = " "+"_________Data________|__Quantidade__|____Valor____|____Nome do Produto______   \n\n";
        for(Compra c : listaDeCompras){
            for (ItemDeCompra item : c.getCarrinho().getItens()){
                retorno = retorno + "    "+f.format(c.getDataCompra()) +                 
                "     |"+"           "+item.getQuantidade()         +
                "              |"+"  "+"R$ "+item.getTotal()          +
                "     |"+"    "+item.getProduto().getNome()  + 
                "\n";
                total = total + item.getTotal() ;
            }
        }
        retorno = retorno + "\n\n\n";
        retorno = retorno + "                                                    Valor Total = R$ "+total+"  \n";
        retorno = retorno + "                                                    Lucro Total = R$ "+total/4+"  \n";
        return retorno;
    }
    
    public String relatorioDatado(Date dataInicial , Date dataFinal) throws SQLException, ClassNotFoundException{
        ArrayList<Compra> listaDeCompras = cdao.relatorioDatado(dataInicial, dataFinal);
        for(Compra c : listaDeCompras){
            CarrinhoDeCompra carrinho = idao.localizarItens(c);
            c.setCarrinho(carrinho);
        }
        Double total = 0.0;
        String retorno = " "+"_________Data________|__Quantidade__|____Valor____|____Nome do Produto______   \n\n";
        for(Compra c : listaDeCompras){
            for (ItemDeCompra item : c.getCarrinho().getItens()){
                retorno = retorno + "    "+f.format(c.getDataCompra()) +                 
                "     |"+"           "+item.getQuantidade()         +
                "              |"+"  "+"R$ "+item.getTotal()          +
                "     |"+"    "+item.getProduto().getNome()  + 
                "\n";
                total = total + item.getTotal() ;
            }
        }
        retorno = retorno + "\n\n\n";
        retorno = retorno + "                                                    Valor Total = R$ "+total+"  \n";
        retorno = retorno + "                                                    Lucro Total = R$ "+total/4+"  \n";
        return retorno;
    }  
    
    public void cadastrar(Produto produto , Part imagem) throws IOException{
        String nome = imagem.getSubmittedFileName();
        File img = new File(Upload.getCaminhoAtual()+nome);
        Upload.tratarImagem(img, nome);        
        pdao.cadastrar(produto);
    
    }
    
    public ArrayList<Produto> filtrarPorNome(String nome) throws SQLException, ClassNotFoundException{
        ArrayList<Produto> lista = pdao.buscar(nome);
        return lista;
    }
    
    public void atualizar(Produto produto , Part imagem) throws IOException{
        String nome = imagem.getSubmittedFileName();
        File img = new File(Upload.getCaminhoAtual()+nome);
        Upload.tratarImagem(img, nome);        
        pdao.atualizar(produto);    
    }
    
    public ArrayList<Produto> filtrarPorCategoria( Categoria categoria) throws SQLException, ClassNotFoundException{
        ArrayList<Produto> lista = pdao.listarPorCategoria(categoria);
        return lista;
    }
      
}
