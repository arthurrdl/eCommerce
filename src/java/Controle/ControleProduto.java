
package Controle;

import Bll.BllCategoria;
import Bll.BllProduto;
import Bll.BllPromocao;
import Modelo.Categoria;
import Modelo.Produto;
import Modelo.Usuario;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.http.Part;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ControleProduto", urlPatterns = {"/ControleProduto"})
@MultipartConfig(fileSizeThreshold   = 1024 * 1024 * 100,  // 1 MB
        maxFileSize         = 1024 * 1024 * 100, // 10 MB
        maxRequestSize      = 1024 * 1024 * 150 )
public class ControleProduto extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String acao = request.getParameter("acao");
            HttpSession session = request.getSession(); 
            BllProduto bll = new BllProduto();
            BllPromocao bllPromo = new BllPromocao();
            BllCategoria bllC = new BllCategoria();
            
            if(acao.equals("relatorioMensal")){   
                Usuario usuario = (Usuario)session.getAttribute("usuario");                
                bll.gerarRelatorioMensal(usuario.getEmail());
                String msg = "Relatorio Mensal enviado com sucesso";
                session.setAttribute("msg", msg);                
                response.sendRedirect("relatorio.jsp");
            }
            if(acao.equals("Remover")){
                bllPromo.removerPromocao();
                String msg = "Promoção removida com sucesso !";
                session.setAttribute("msg", msg);                
                response.sendRedirect("relatorio.jsp");
            }
            if(acao.equals("Resetar")){
                session.removeAttribute("listaProdutosAtualizar");
                response.sendRedirect("atualizarProduto.jsp");
            }
            if(acao.equals("LocalizarProduto")){
                int id = Integer.parseInt(request.getParameter("idProduto"));
                Produto prod = bll.buscarPorId(id);
                session.setAttribute("produtoAtualizar", prod);
                response.sendRedirect("atualizarProduto.jsp");
            }
            if(acao.equals("FiltrarPorCategoria")){
                int idCategoria = Integer.parseInt(request.getParameter("idCategoria"));
                Categoria categoria = bllC.buscarPorId(idCategoria);
                ArrayList<Produto> listaConsulta = bll.filtrarPorCategoria(categoria);
                session.setAttribute("produtosConsulta", listaConsulta);
                response.sendRedirect("indexBusca.jsp");
            }
                    
            } catch (Exception erro) {
            request.setAttribute("erro", erro);
            request.getRequestDispatcher("erro.jsp").forward(request, response);
            
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
            BllProduto bll = new BllProduto();
            HttpSession session = request.getSession();
            BllPromocao bllPromo = new BllPromocao();
            BllCategoria bllCat = new BllCategoria();
            if(acao.equals("Buscar")){     
                String busca = request.getParameter("consulta");                 
                ArrayList<Produto> listaConsulta = bll.filtrarPorNome(busca);                
                session.setAttribute("produtosConsulta", listaConsulta);
                response.sendRedirect("indexBusca.jsp");
            }            
            if(acao.equals("Emitir")){   
                Usuario usuario = (Usuario)session.getAttribute("usuario");
                String di = request.getParameter("datainicial");
                String df = request.getParameter("datafinal");
                bll.gerarRelatorioDatado(di, df, usuario.getEmail());
                String msg = "Relatório Datado enviado com sucesso";
                session.setAttribute("msg", msg);                
                response.sendRedirect("relatorio.jsp");
            }
            if(acao.equals("Atualizar")){                
                double porcento = Double.parseDouble(request.getParameter("porcentagem"));
                bllPromo.cadastrar(porcento);
                String msg = "Promoção gerada com sucesso !";
                session.setAttribute("msg", msg);                
                response.sendRedirect("relatorio.jsp");
            }
            if(acao.equals("Cadastrar")){                        
                Part img = (Part) request.getPart("foto");
                Categoria cat = bllCat.buscarPorNome(request.getParameter("categoria"));
                Produto produto = new Produto();
                produto.setCategoria(cat);
                produto.setNome(request.getParameter("nome"));
                produto.setDescricao(request.getParameter("descricao"));
                String valor = request.getParameter("valor");
                produto.setValorCompra(Double.parseDouble(valor.replace(",", ".")));
                produto.setImagem(img.getSubmittedFileName());                
                bll.cadastrar(produto, img);            
                String msg = " Produto Cadastrado Com Sucesso ! ";
                session.setAttribute("msg", msg);
                response.sendRedirect("cadastroProduto.jsp");
            }
            if(acao.equals("Localizar")){
                String busca = request.getParameter("consulta");                 
                ArrayList<Produto> listaConsulta = bll.filtrarPorNome(busca);
                session.setAttribute("listaProdutosAtualizar", listaConsulta);
                response.sendRedirect("atualizarProduto.jsp");
            }
            if(acao.equals("Salvar")){
                Part img = (Part) request.getPart("foto");
                Categoria cat = bllCat.buscarPorNome(request.getParameter("categoria"));
                Produto produto = new Produto();
                produto.setIdProduto(Integer.parseInt(request.getParameter("idProduto")));
                produto.setCategoria(cat);
                produto.setNome(request.getParameter("nome"));
                produto.setDescricao(request.getParameter("descricao"));
                String valor = request.getParameter("valor");
                produto.setValorVenda(Double.parseDouble(valor.replace(",", ".")));
                produto.setImagem(img.getSubmittedFileName());            
                bll.atualizar(produto, img);            
                String msg = " Produto Alterado Com Sucesso ! ";
                session.setAttribute("msg", msg);
                session.removeAttribute("produtoAtualizar");
                response.sendRedirect("atualizarProduto.jsp");
            }
            
            
            } catch (Exception erro) {
            request.setAttribute("erro", erro);
            request.getRequestDispatcher("erro.jsp").forward(request, response);
            
            }
    }

    
}
