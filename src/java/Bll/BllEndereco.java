package Bll;

import Modelo.Endereco;
import Modelo.Usuario;
import ModeloDao.EnderecoDao;
import java.sql.SQLException;

public class BllEndereco {
   EnderecoDao edao = new EnderecoDao();
   
   public void cadastrarEndereco(Endereco end) throws SQLException, ClassNotFoundException{
       edao.cadastrar(end);       
   }
   
   public Endereco localizarEndereco(Endereco end) throws SQLException, ClassNotFoundException{
       Endereco e = edao.localizarPorEndereco(end); 
       return e;
   }
   
   public Endereco localizarPorId(int id) throws SQLException, ClassNotFoundException{
       Endereco e = edao.localizarPorId(id);
       return e;
   }
   
   public void atualizarEndereco(Endereco end) throws SQLException, ClassNotFoundException{
       edao.atualizar(end);
   }
   
   public Endereco localizarPorUsuario(Usuario usuario)throws SQLException, ClassNotFoundException{
       Endereco end = edao.localizarPorUsuario(usuario);
       return end;
   }
   
}
