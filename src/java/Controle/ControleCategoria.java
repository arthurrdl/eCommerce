
package Controle;

import Bll.BllCategoria;
import Modelo.Categoria;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "ControleCategoria", urlPatterns = {"/ControleCategoria"})
public class ControleCategoria extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            HttpSession session = request.getSession();
            String acao = request.getParameter("acao");
            BllCategoria bll = new BllCategoria();
           
        try{
            if(acao.equals("LocalizarCategoria")){
                int idCategoria = Integer.parseInt(request.getParameter("idCategoria"));
                Categoria cat = bll.buscarPorId(idCategoria);
                session.setAttribute("categoria", cat);
                response.sendRedirect("cadastroCategoria.jsp");
            }           
        }catch(Exception e){
                request.setAttribute("erro",e);
                RequestDispatcher rd = request.getRequestDispatcher("erro.jsp");
                rd.forward(request,response);
        }
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            HttpSession session = request.getSession();
            String acao = request.getParameter("acao");
            BllCategoria bll = new BllCategoria();
        try{
            if(acao.equals("Cadastrar Categoria")){
                Categoria cat = new Categoria();
                cat.setNome(request.getParameter("nome"));
                bll.cadastrar(cat);
                response.sendRedirect("cadastroCategoria.jsp");
            }
            if(acao.equals("Salvar")){
                Categoria cat = new Categoria();
                cat.setIdCategoria(Integer.parseInt(request.getParameter("idCategoria")));
                cat.setNome(request.getParameter("nome"));
                bll.atualizar(cat);
                session.removeAttribute("categoria");
                String msg = " Alterado com sucesso !";
                session.setAttribute("msgAdm", msg);
                response.sendRedirect("administrador.jsp");
            }
                       
        }catch(Exception e){
                request.setAttribute("erro",e);
                RequestDispatcher rd = request.getRequestDispatcher("erro.jsp");
                rd.forward(request,response);
        }
    }

    

}
