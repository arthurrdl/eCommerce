
package Modelo;

public class Usuario {
    private int idUsuario;
    private String credencial;
    private String usuario;
    private String senha;
    private String email;
    private boolean ativo;

    public int getIdUsuario() {
        return idUsuario;
    }
    
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getCredencial() {
        return credencial;
    }

    public void setCredencial(String credencial) {
        this.credencial = credencial;
    }         
                
    public String getUsuario() {
        return usuario;
    }  
    
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }    

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }    
    
    
}
