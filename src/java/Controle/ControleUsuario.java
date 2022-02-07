package Controle;


import Bll.BllCliente;
import Bll.BllEndereco;
import Bll.BllUsuario;
import Modelo.Email;
import Modelo.Usuario;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "ControleUsuario", urlPatterns = {"/ControleUsuario"})
public class ControleUsuario extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String acao = request.getParameter("acao");
        HttpSession session = request.getSession();
        BllUsuario bll = new BllUsuario();
        try {
           if(acao.equals("emailAtivacao")){                
                String email = request.getParameter("emailCliente");
                bll.ativarUsuario(email);
                response.sendRedirect("index.jsp");
            } 
           if(acao.equals("sair")){               
               session.invalidate();
               response.sendRedirect("index.jsp");
           }
            if (acao.equals("Reenviar E-mail")){            
                Usuario usuario =(Usuario) session.getAttribute("usuario");
                Email email = new Email();   
                String emailtxt = usuario.getEmail();
                email.enviaEmail(emailtxt);                  
                String msg = "O email foi enviado com sucesso !";
                session.setAttribute("msg", msg);
                response.sendRedirect("index.jsp");              
            }
            if(acao.equals("VerificarUsuario")){                
                Usuario u = (Usuario) session.getAttribute("usuario");
                if(u.getCredencial() == "Administrador" || u.getCredencial()== "Funcionario"){
                    response.sendRedirect("adminsitrador.jsp");
                }else {
                    response.sendRedirect("index.jsp");
                }
            }
        }catch(Exception e){
            request.setAttribute("erro",e);
            RequestDispatcher rd = request.getRequestDispatcher("erro.jsp");
            rd.forward(request,response);
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
        HttpSession session = request.getSession();
        String acao = request.getParameter("acao");        
        BllUsuario bll = new BllUsuario();
        BllCliente bllC = new BllCliente();
        BllEndereco bllE = new BllEndereco();
        
        try{
            if (acao.equals("Entrar")){                 
                String usuarioTxt = request.getParameter("usuario");
                String senhaTxt = request.getParameter("senha");
                Usuario usuarioLogado = bll.logar(usuarioTxt,senhaTxt);            
                String msg = bll.mensagem(usuarioLogado);               
                String page = bll.pagina(usuarioLogado);   
                session.setAttribute("cliente", bllC.buscarPorIdUsuario(usuarioLogado));
                session.setAttribute("endereco", bllE.localizarPorUsuario(usuarioLogado));
                session.setAttribute("usuario", usuarioLogado);
                session.setAttribute("msg", msg); 
                response.sendRedirect(page);    
                
            }
            
            if(acao.equals("Cadastrar")){
                Usuario usuario = new Usuario();
                String usuariotxt = request.getParameter("usuario");
                String senhatxt = request.getParameter("senha");
                String emailtxt = request.getParameter("email");              
                usuario.setUsuario(usuariotxt);
                usuario.setSenha(senhatxt);
                usuario.setEmail(emailtxt);
                usuario.setAtivo(false);                
                usuario.setCredencial("Cliente");
                String usuarioMsg =bll.Cadastrar(usuario);          
                session.setAttribute("usuarioMsg", usuarioMsg);
                response.sendRedirect("cadastro.jsp"); 
            }
            
           
            
            if (acao.equals("Alterar Senha")){
                Usuario u = (Usuario)session.getAttribute("usuario");
                String senhaAtual = request.getParameter("senhaAtual");
                String novaSenha = request.getParameter("novaSenha");
                String usuario = request.getParameter("usuario");
                String msg = bll.atualizarSenha(u,senhaAtual,novaSenha,usuario);
                session.setAttribute("msg", msg);
                response.sendRedirect("index.jsp");                
            }
            
            if (acao.equals("Trocar Senha")){
                Usuario u = (Usuario)session.getAttribute("usuario");
                String senhaAtual = request.getParameter("senhaAtual");
                String novaSenha = request.getParameter("novaSenha");
                String usuario = request.getParameter("usuario");
                String msg = bll.atualizarSenha(u,senhaAtual,novaSenha,usuario);
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
