
package Controle;

import Bll.BllEndereco;
import Bll.BllFuncionario;
import Bll.BllUsuario;
import Modelo.Endereco;
import Modelo.Funcionario;
import Modelo.Usuario;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "ControleFuncionario", urlPatterns = {"/ControleFuncionario"})
public class ControleFuncionario extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
    }      
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String acao = request.getParameter("acao");  
        BllFuncionario bllF = new BllFuncionario();
        try{            
            if(acao.equals("LocalizarFuncionario")){               
               int idfuncionario = Integer.parseInt(request.getParameter("idFuncionario"));            
               Funcionario func = bllF.localizarFuncionarioPorId(idfuncionario);
               session.setAttribute("funcionario", func);
               response.sendRedirect("atualizarFuncionario.jsp");
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
        BllUsuario bllU = new BllUsuario();
        BllEndereco bllE = new BllEndereco();
        BllFuncionario bllF = new BllFuncionario();
        try{            
            if(acao.equals("Cadastrar Funcionario")){
                Usuario u = new Usuario();
                Funcionario f = new Funcionario();
                Endereco e = new Endereco();
                
                u.setCredencial("Funcionario");
                u.setUsuario(request.getParameter("nome"));
                u.setEmail(request.getParameter("email"));
                u.setSenha("123");
                u.setAtivo(true); 
                bllU.CadastrarUsuarioFuncionario(u);
                u = bllU.buscarUsuario(u);
                
                e.setLogradouro(request.getParameter("rua"));
                e.setNumero(Integer.parseInt(request.getParameter("numero")));
                e.setCep(Integer.parseInt(request.getParameter("cep")));
                e.setBairro(request.getParameter("bairro"));
                e.setCidade(request.getParameter("cidade"));                
                bllE.cadastrarEndereco(e);
                e = bllE.localizarEndereco(e);
                
                f.setNome(request.getParameter("nome"));
                f.setRg(request.getParameter("rg"));
                f.setCpf(request.getParameter("cpf"));
                f.setTelefone(request.getParameter("telefone"));
                f.setCelular(request.getParameter("celular"));
                f.setUsuario(u);
                f.setEndereco(e);         
                bllF.cadastrarFuncionario(f); 
                
                String usuarioMsg = " Cadastrado com sucesso !";
                session.setAttribute("msgAdm", usuarioMsg);
                response.sendRedirect("administrador.jsp"); 
            }
            if(acao.equals("Salvar")){
                Usuario u = new Usuario();
                Funcionario f = new Funcionario();
                Endereco e = new Endereco();
                
                f.setIdFuncionario(Integer.parseInt(request.getParameter("idFuncionario")));
                f.setNome(request.getParameter("nome"));
                f.setRg(request.getParameter("rg"));
                f.setCpf(request.getParameter("cpf"));
                f.setTelefone(request.getParameter("telefone"));
                f.setCelular(request.getParameter("celular"));  
                
                e.setIdEndereco(Integer.parseInt(request.getParameter("idEndereco")));
                e.setLogradouro(request.getParameter("rua"));
                e.setNumero(Integer.parseInt(request.getParameter("numero")));
                e.setCep(Integer.parseInt(request.getParameter("cep")));
                e.setBairro(request.getParameter("bairro"));
                e.setCidade(request.getParameter("cidade"));
                bllE.atualizarEndereco(e);
                e = bllE.localizarPorId(e.getIdEndereco());
                f.setEndereco(e);       
                
                
                u.setIdUsuario(Integer.parseInt(request.getParameter("idUsuario")));
                u.setEmail(request.getParameter("email"));               
                bllU.atualizarEmail(u);
                u = bllU.buscarPorId(u.getIdUsuario());                
                f.setUsuario(u);
                bllF.atualizarFuncionario(f);
                session.removeAttribute("funcionario");
                String usuarioMsg = "Alterado com sucesso";
                session.setAttribute("msgAdm", usuarioMsg);
                response.sendRedirect("administrador.jsp"); 
            }
            
            
        }catch(Exception e){
            request.setAttribute("erro",e);
            RequestDispatcher rd = request.getRequestDispatcher("erro.jsp");
            rd.forward(request,response);
        }
    }

}
