
package Controle;

import Bll.BllCompra;
import Bll.BllItemDeCompra;
import Modelo.CarrinhoDeCompra;
import Modelo.Cliente;
import Modelo.Compra;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "ControleCompra", urlPatterns = {"/ControleCompra"})
public class ControleCompra extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String acao = request.getParameter("acao");             
            HttpSession sessao = request.getSession(); 
            Cliente cliente = (Cliente) sessao.getAttribute("cliente");                       
            BllCompra bll = new BllCompra();
            BllItemDeCompra bllI = new BllItemDeCompra();
             
             if (acao.equals("buscarCompras")){
                 System.out.println("Entrou na Controle Compra");
                 ArrayList<Compra> listaDeCompras = bll.listarCompras(cliente);
                 for(Compra compra : listaDeCompras){ 
                    CarrinhoDeCompra carrinho = bllI.localizarItens(compra);
                    compra.setCarrinho(carrinho);
                }
                 sessao.setAttribute("listaDeCompras", listaDeCompras);
                 response.sendRedirect("lobby.jsp");
             }
            
            } catch (Exception erro) {
            request.setAttribute("erro", erro);
            request.getRequestDispatcher("/erro.jsp").forward(request, response);
        }       
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String acao = request.getParameter("acao");             
            HttpSession sessao = request.getSession(); 
            Cliente cliente = (Cliente) sessao.getAttribute("cliente");
            CarrinhoDeCompra carrinho = (CarrinhoDeCompra) sessao.getAttribute("carrinho");            
            BllCompra bll = new BllCompra();            
            
             if(acao.equals("Finalizar Pedido")){          
                Compra compra = new Compra(cliente,carrinho);
                compra.setDataCompra(new Date());
                compra.setValor(carrinho.getTotal());
                compra.setFormaDePagamento(request.getParameter("forma")); 
                bll.cadastroCompra(compra);                 
                sessao.removeAttribute("carrinho"); 
                sessao.removeAttribute("msgCheck");                
                String msg = "Compra feita com sucesso";                
                sessao.setAttribute("msg", msg);
                response.sendRedirect("index.jsp");                  
             }
             if (acao.equals("buscarCompras")){
                 ArrayList<Compra> listaDeCompras = bll.listarCompras(cliente);
                 sessao.setAttribute("listaDeCompras", listaDeCompras);
                 response.sendRedirect("lobby.jsp");
             }
            
            } catch (Exception erro) {
            request.setAttribute("erro", erro);
            request.getRequestDispatcher("/erro.jsp").forward(request, response);
        }
    }

    

}
