
package Bll;

import Modelo.Categoria;
import ModeloDao.CategoriaDao;
import java.sql.SQLException;
import java.util.ArrayList;

public class BllCategoria {
    CategoriaDao cdao = new CategoriaDao();
    
    public void cadastrar(Categoria categoria ){
        cdao.cadastrar(categoria);
    }
    
    public void atualizar(Categoria categoria ){
        cdao.atualizar(categoria);
    }
    
    public ArrayList<Categoria> listar() throws SQLException, ClassNotFoundException{
        ArrayList<Categoria> lista = cdao.listar();
        return lista;
    }
    
    public  Categoria buscarPorId(int id) throws SQLException, ClassNotFoundException{
        Categoria cat = cdao.buscarPorId(id);
        return cat;
    }
    
    public Categoria buscarPorNome(String nome)throws SQLException, ClassNotFoundException{
        Categoria cat = cdao.buscarPorNome(nome);
        return cat;
    }
    
}
