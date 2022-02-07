package Modelo;
// Locacao Versao 2 
import java.net.MalformedURLException;
import java.util.Date;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email {
    private static Session session;
    
    public void enviaEmail(String email)throws  MalformedURLException, AddressException, MessagingException, InterruptedException, Exception{
        Properties props = new Properties();
            /** Parâmetros de conexão com servidor Gmail */
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.socketFactory.port", "587");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.ssl", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.port", "587");    
            
            try{
                Authenticator auth = new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("enviaemailsempre@gmail.com", "enviaemail");
                    }
                };            
                session = Session.getDefaultInstance(props,auth);
            
            } catch (Exception ex) {
                Thread.sleep(1000);
                System.out.println("Erro ao criar a sessão");
                System.out.println("__________________________________________________");
                throw new Exception("Erro ao criar a sessão");
                
            }                    
            try{
                  Message message = new MimeMessage(session);
                  message.setFrom(new InternetAddress("enviaemailsempre@gmail.com")); //Remetente
                  //Destinatário(s)
                  Address[] toUser = InternetAddress.parse(email);  
 
                  message.setRecipients(Message.RecipientType.TO, toUser);
                  message.setSubject("Ativação de Cadastro : Festa&Cia E-Shooper");//Assunto
                  message.setText("\n\n Bem Vindo! \n\n\n\n"
                    +"Seu cadastro no sistema do nosso site de compras Festa&Cia E-Shooper \n"+
                    " foi realizado com sucesso, mas para conclusão de \n"+
                    "seu cadastro será  necessario ativação da conta e para realiza-la\n"+
                    " basta clicar neste link aqui :  http://localhost:8080/eCommerce/ControleUsuario?acao=emailAtivacao&emailCliente="+email);
                  /**Método para enviar a mensagem criada*/             
                  
                  Transport.send(message);          
                  
                  System.out.println("Enviado com sucesso !" );
                
                  
            }catch(Exception ex){
                    System.out.println("Erro ao abrir a conexão");
                    System.out.println("Erro: " + ex.getMessage());
                    System.out.println("__________________________________________________");
                   
            }
           
    }
    
    public void enviaRelatorioDatadoPorEmail(String email,String dataInicail, String dataFinal , String relatorio)throws  MalformedURLException, AddressException, MessagingException, InterruptedException, Exception{
        Properties props = new Properties();
            /** Parâmetros de conexão com servidor Gmail */
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.socketFactory.port", "587");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.ssl", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.port", "587");    
            
            try{
                Authenticator auth = new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("enviaemailsempre@gmail.com", "enviaemail");
                    }
                };            
                session = Session.getDefaultInstance(props,auth);
            
            } catch (Exception ex) {
                Thread.sleep(1000);
                System.out.println("Erro ao criar a sessão");
                System.out.println("__________________________________________________");
                throw new Exception("Erro ao criar a sessão");
                
            }                    
            try{
                  Message message = new MimeMessage(session);
                  message.setFrom(new InternetAddress("enviaemailsempre@gmail.com")); //Remetente
                  //Destinatário(s)
                  Address[] toUser = InternetAddress.parse(email);  
 
                  message.setRecipients(Message.RecipientType.TO, toUser);
                  message.setSubject("Relatório Datado : Festa&Cia E-Shooper");//Assunto
                  message.setText("\n\n Bem Vindo! \n\n"+
                          "\n\n\n\n Segue o Relatorio entre as datas "+dataInicail+" e "+dataFinal+"\n\n\n\n"+relatorio);
                  /**Método para enviar a mensagem criada*/             
                  
                  Transport.send(message);          
                  
                  System.out.println("Enviado com sucesso !" );
                
                  
            }catch(Exception ex){
                    System.out.println("Erro ao abrir a conexão");
                    System.out.println("Erro: " + ex.getMessage());
                    System.out.println("__________________________________________________");
                   
            }
           
    }
    
    public void enviaRelatorioMensal(String email, String relatorio)throws  MalformedURLException, AddressException, MessagingException, InterruptedException, Exception{
        Properties props = new Properties();
            /** Parâmetros de conexão com servidor Gmail */
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.socketFactory.port", "587");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.ssl", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.port", "587");    
            
            try{
                Authenticator auth = new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("enviaemailsempre@gmail.com", "enviaemail");
                    }
                };            
                session = Session.getDefaultInstance(props,auth);
            
            } catch (Exception ex) {
                Thread.sleep(1000);
                System.out.println("Erro ao criar a sessão");
                System.out.println("__________________________________________________");
                throw new Exception("Erro ao criar a sessão");
                
            }                    
            try{
                  Message message = new MimeMessage(session);
                  message.setFrom(new InternetAddress("enviaemailsempre@gmail.com")); //Remetente
                  //Destinatário(s)
                  Address[] toUser = InternetAddress.parse(email);  
 
                  message.setRecipients(Message.RecipientType.TO, toUser);
                  message.setSubject("Relatório Mensal : Festa&Cia E-Shooper");//Assunto
                  message.setText("\n\n Bem Vindo! \n\n"+
                          "\n\n\n\n Segue o Relatorio dos últimos 30 dias "+"\n\n\n\n"+relatorio);
                  /**Método para enviar a mensagem criada*/             
                  
                  Transport.send(message);          
                  
                  System.out.println("Enviado com sucesso !" );
                
                  
            }catch(Exception ex){
                    System.out.println("Erro ao abrir a conexão");
                    System.out.println("Erro: " + ex.getMessage());
                    System.out.println("__________________________________________________");
                   
            }
           
    }
    
            
}

    


