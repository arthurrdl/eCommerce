package Controle;

import Bll.BllEndereco;
import Modelo.Endereco;
import Modelo.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "ControleEndereco", urlPatterns = {"/ControleEndereco"})
public class ControleEndereco extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ControleEndereco</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControleEndereco at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String acao = request.getParameter("acao");
        BllEndereco bll = new BllEndereco();
        try{
            if(acao.equals("ChecarEndereco")){                
                Usuario usuario = (Usuario) session.getAttribute("usuario");              
                Endereco endereco = bll.localizarPorUsuario(usuario);
                session.setAttribute("endereco", endereco);                
                response.sendRedirect("dadosPessoais.jsp");                
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
            BllEndereco bll = new BllEndereco();
            
        try{
            if(acao.equals("Cadastrar")){    
                Endereco e = new Endereco();
                e.setLogradouro(request.getParameter("logradouro"));
                e.setNumero(Integer.parseInt(request.getParameter("numero")));
                e.setBairro(request.getParameter("bairro"));
                e.setCidade(request.getParameter("cidade"));
                e.setCep(Integer.parseInt(request.getParameter("cep")));
                bll.cadastrarEndereco(e);                
                session.setAttribute("endereco", e);                
                String enderecoMsg = "Endereço cadastrado com sucesso ! ";
                session.setAttribute("enderecoMsg", enderecoMsg);                
                response.sendRedirect("ControleCliente?acao=AddEndereco");
             
            }
            if(acao.equals("Salvar")){         
                Endereco e = new Endereco();
                Endereco endereco = (Endereco) session.getAttribute("endereco");                
                e.setIdEndereco(endereco.getIdEndereco());
                e.setLogradouro(request.getParameter("logradouro"));
                e.setNumero(Integer.parseInt(request.getParameter("numero")));
                e.setBairro(request.getParameter("bairro"));
                e.setCidade(request.getParameter("cidade"));
                e.setCep(Integer.parseInt(request.getParameter("cep")));                
                bll.atualizarEndereco(e);                
                session.setAttribute("endereco", e);
                String enderecoMsg = "Endereço alterado com sucesso ! ";
                session.setAttribute("enderecoMsg", enderecoMsg);
                response.sendRedirect("lobby.jsp");
            }
            
        }catch(Exception e){
                request.setAttribute("erro",e);
                RequestDispatcher rd = request.getRequestDispatcher("erro.jsp");
                rd.forward(request,response);
        }
    }

    
}
