
package Bll;

import Modelo.Cliente;
import Modelo.Usuario;
import ModeloDao.ClienteDao;
import java.sql.SQLException;

public class BllCliente {
    ClienteDao cdao = new ClienteDao();
    
    public Cliente buscarPorId(int id) throws SQLException, ClassNotFoundException{
        Cliente cliente = cdao.localizarId(id);
        return cliente;
    }
    
    public Cliente buscarPorIdUsuario (Usuario usuario) throws SQLException, ClassNotFoundException{
        Cliente c = cdao.localizarIdUsuario(usuario);
        return c;
    }
    
    public void addEndereco(Cliente cliente) throws SQLException, ClassNotFoundException{
        cdao.addEndereco(cliente);
    }
    
    public void cadastrar(Cliente cliente){
        cdao.cadastrar(cliente);
    }
    
    public void atualizar(Cliente cliente){
        cdao.atualizar(cliente);
    }
    
}
