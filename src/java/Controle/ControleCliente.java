
package Controle;

import Bll.BllCliente;
import Bll.BllEndereco;
import Modelo.Cliente;
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

@WebServlet(name = "ControleCliente", urlPatterns = {"/ControleCliente"})
public class ControleCliente extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ControleCliente</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControleCliente at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            HttpSession session = request.getSession();
            String acao = request.getParameter("acao");
            BllCliente bll = new BllCliente();
        try{
            if(acao.equals("ChecarCliente")){                             
                Usuario usuairo = (Usuario)session.getAttribute("usuario");            
                Cliente cliente = bll.buscarPorIdUsuario(usuairo);                
                session.setAttribute("cliente", cliente);
                RequestDispatcher rd = request.getRequestDispatcher("ControleEndereco?acao=ChecarEndereco");
                rd.forward(request,response);
                
            }
            if(acao.equals("AddEndereco")){             
                Endereco endereco = (Endereco) session.getAttribute("endereco");
                BllEndereco bllE = new BllEndereco();     
                endereco = bllE.localizarEndereco(endereco);          
                Cliente cliente = (Cliente) session.getAttribute("cliente");
                cliente.setEndereco(endereco);
                bll.addEndereco(cliente);
                session.setAttribute("endereco", endereco);
                session.setAttribute("cliente", cliente);
                response.sendRedirect("lobby.jsp");
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
            BllCliente bll = new BllCliente();
        try{
            if(acao.equals("Cadastrar")){  
                Usuario usuario = (Usuario) session.getAttribute("usuario");
                Cliente c = new Cliente();                
                c.setNome(request.getParameter("nome"));
                c.setRg(request.getParameter("rg"));
                c.setCpf(request.getParameter("cpf"));
                c.setTelefone(request.getParameter("telefone"));
                c.setCelular(request.getParameter("celular"));
                c.setUsuario(usuario);                             
                bll.cadastrar(c);
                String clienteMsg = " Dados cadastrados com sucesso !";
                session.setAttribute("clienteMsg", clienteMsg); 
                c = bll.buscarPorIdUsuario(usuario);
                session.setAttribute("cliente", c);
                response.sendRedirect("dadosPessoais.jsp");          
            }
            if(acao.equals("Salvar")){ 
                System.out.println("Entrou no Salvar");
                Cliente cliente = (Cliente)session.getAttribute("cliente");
                Cliente c = new Cliente();
                c.setIdCliente(cliente.getIdCliente());
                c.setNome(request.getParameter("nome"));
                c.setRg(request.getParameter("rg"));
                c.setCpf(request.getParameter("cpf"));
                c.setTelefone(request.getParameter("telefone"));
                c.setCelular(request.getParameter("celular"));
                bll.atualizar(c);               
                session.setAttribute("cliente", c);
                String clienteMsg = " Dados alterados com sucesso !";
                session.setAttribute("clienteMsg", clienteMsg);
                response.sendRedirect("lobby.jsp");
            }
            
        }catch(Exception e){
                request.setAttribute("erro",e);
                RequestDispatcher rd = request.getRequestDispatcher("erro.jsp");
                rd.forward(request,response);
        }
    }

   
}
