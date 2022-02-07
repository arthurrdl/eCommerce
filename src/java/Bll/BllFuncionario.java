package Bll;

import Modelo.Funcionario;
import ModeloDao.FuncionarioDao;
import java.sql.SQLException;
import java.util.ArrayList;

public class BllFuncionario {
    
   FuncionarioDao fdao = new FuncionarioDao();
   
   public ArrayList<Funcionario> listarFuncionarios() throws SQLException, ClassNotFoundException{
       ArrayList<Funcionario> lista = fdao.listar();
       return lista;
   }
   
   public void cadastrarFuncionario(Funcionario func)throws SQLException, ClassNotFoundException{
       fdao.cadastrar(func);
   }
   
   public void atualizarFuncionario(Funcionario func)throws SQLException, ClassNotFoundException{
       fdao.atualizar(func);
   }
   
   public Funcionario localizarFuncionarioPorId(int id) throws SQLException, ClassNotFoundException{
       Funcionario f = fdao.buscarPorId(id);
       return f;
   }
   
}
