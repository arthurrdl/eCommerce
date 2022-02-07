
package Bll;

import Modelo.Email;
import Modelo.Usuario;
import ModeloDao.UsuarioDao;
import java.sql.SQLException;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BllUsuario {    
    UsuarioDao udao = new UsuarioDao();  
    
    public String Cadastrar(Usuario usuario) throws SQLException, ClassNotFoundException, MessagingException, AddressException, InterruptedException, Exception{
        String usuarioMsg = "";
        if (udao.emailExiste(usuario.getEmail())==true){
                     usuarioMsg="E-mail existente, favor cadastrar com outro e-mail";                
        }else{                
            udao.cadastrar(usuario);
            usuario = udao.logar(usuario.getUsuario(),usuario.getSenha());        
            Email email = new Email();              
            email.enviaEmail(usuario.getEmail());
            usuarioMsg = "Usuario cadastrado com sucesso. Um e-mail para ativação da conta foi enviado!";                 
        }
        return usuarioMsg;
    }
    
    public void CadastrarUsuarioFuncionario(Usuario usuario){
        udao.cadastrar(usuario);
    }
    
    public String pagina(Usuario usuario) throws SQLException, ClassNotFoundException{ 
        
        if (usuario == null || usuario.getUsuario() == null){            
            return "index.jsp";
        }else{
            if (usuario.getCredencial().equals("Administrador")){            
                return "administrador.jsp";              
            }  
            if (usuario.getCredencial().equals("Funcionario")){            
                return "administrador.jsp";              
            } 
        }
        return "index.jsp";
    }

    public String mensagem(Usuario usuario) throws SQLException, ClassNotFoundException{
        String msg; 
        if(usuario.getUsuario()==null){
            msg="Usuario ou senha incorretos .";
        }else{
            if(usuario.getAtivo()==false){
               msg="Para acessar, verifique seu e-mail para validar seu cadastro .";
            }else{
                msg = " Bem vindo, "+ usuario.getUsuario(); 
            }
        }       
        return msg;
    }
    
    public Usuario buscarUsuario(Usuario usuario) throws SQLException, ClassNotFoundException{
        Usuario usuarioLog = udao.logar(usuario.getUsuario(),usuario.getSenha());
        return usuarioLog;
    }
    
    public Usuario buscarPorId(int idusuario) throws SQLException, ClassNotFoundException{
        Usuario usuario = udao.buscarPorId(idusuario);
        return usuario;
    }
    
    public void atualizarEmail(Usuario usuario) throws SQLException, ClassNotFoundException{
        udao.atualizarEmail(usuario);        
    }

    public String atualizarSenha(Usuario usuarioLogado , String senhaAtual , String novaSenha , String usuario) throws SQLException, ClassNotFoundException{
        String msg = "Senha ou Usuário incorreto !";
        if (usuarioLogado.getUsuario().equals(usuario) && usuarioLogado.getSenha().equals(senhaAtual)){
            Usuario u = udao.logar(usuario, senhaAtual);
            u.setSenha(novaSenha);
            udao.alterarSenha(u);
            msg = " Senha Alterada com sucesso !";
            
        }      
        return msg;
    }
    
    public Usuario logar(String usuario ,String senha) throws SQLException, ClassNotFoundException{
        boolean flag = udao.usuarioExiste(usuario,senha);
        Usuario u = new Usuario();
        if (flag == true ){
            u = udao.logar(usuario, senha);
        }
        return u ;
    }
    
    public void ativarUsuario(String email){
        udao.ativarUsuario(email);
    }
    
}
