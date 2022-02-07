    
package ModeloDao;

import Bll.BllCliente;
import Modelo.CarrinhoDeCompra;
import Modelo.Cliente;
import Modelo.Compra;
import Util.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class CompraDao {
    private static final String CADASTRAR = "insert into compra (datacompra,horacompra,valor,formadepagamento,idclientefk) values (?,?,?,?,?) ";
    private static final String BUSCAR = "select * from compra where idclientefk = ? and datacompra = ? and horacompra = ? ";
    private static final String BUSCARTODOS = "select * from compra where idclientefk = ? ";
    private static final String RELATORIOMENSAL = "SELECT * FROM compra WHERE datacompra >= (select current_date - integer'30')";
    private static final String RELATORIODATADO = "SELECT * FROM  compra WHERE datacompra >= ? AND datacompra <=  ? ;";
    
    public void cadastrar (Compra compra ){
       Connection conexao = null;
       try{
           conexao = Conexao.getConexao();   
            SimpleDateFormat dia = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat hora = new SimpleDateFormat("HH:mm:ss");                       
            PreparedStatement pstmt = conexao.prepareStatement(CADASTRAR);            
            Date d = dia.parse(dia.format(compra.getDataCompra()));
            Date t = hora.parse(hora.format(compra.getDataCompra()));
            java.sql.Date dataSql = new java.sql.Date(d.getTime());    
            java.sql.Time horaSql = new java.sql.Time(t.getTime());             
            pstmt.setDate(1, dataSql);
            pstmt.setTime(2, horaSql);
            pstmt.setDouble(3, compra.getValor() );
            pstmt.setString(4, compra.getFormaDePagamento());           
            pstmt.setInt(5,compra.getCliente().getIdCliente());           
            pstmt.execute();         
           
       }catch (Exception e ){
           
           throw new RuntimeException(e);
           
       }finally{
           
           try{conexao.close();
           
           }catch (SQLException ex){
               
               throw new RuntimeException (ex);
           }
        }
   }//FIM CADASTRAR
    
    public ArrayList<Compra> listarCompras (Cliente cliente)throws SQLException, ClassNotFoundException {
       ArrayList<Compra> listaDeCompras = new ArrayList<Compra>();
        Connection conexao = null;              
        try{            
            conexao = Conexao.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(BUSCARTODOS);            
            pstmt.setInt(1, cliente.getIdCliente());          
            ResultSet rs = pstmt.executeQuery();
        while (rs.next()){        //a cada loop 
            Compra compraL = new Compra();
            compraL.setIdCompra(rs.getInt("idCompra"));
            SimpleDateFormat dia = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat hora = new SimpleDateFormat("HH:mm:ss");
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date dR = rs.getDate("datacompra");            
            Date tR = rs.getTime("horacompra");            
            String dD = dia.format(dR);
            String tT = hora.format(tR);
            String dataRecebida = dD+" "+tT;
            compraL.setDataCompra(f.parse(dataRecebida));            
            compraL.setValor(rs.getDouble("valor"));
            compraL.setFormaDePagamento(rs.getString("formaDePagamento"));
            compraL.setCliente(cliente);      
            listaDeCompras.add(compraL);
        }
            return listaDeCompras;            
        }catch(Exception e ){
            throw new RuntimeException(e);
        }finally{
            try{
                conexao.close();
            }catch (SQLException ex){
                    throw new RuntimeException(ex);
            
                }            
            } 
    }
    
    public Compra buscarCompra (Cliente cliente, Date data, CarrinhoDeCompra carrinho)throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        Compra compraL = new Compra();      
        try{            
            conexao = Conexao.getConexao();            
            PreparedStatement pstmt = conexao.prepareStatement(BUSCAR);
            SimpleDateFormat dia = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat hora = new SimpleDateFormat("HH:mm:ss");
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date d = dia.parse(dia.format(data));
            Date t = hora.parse(hora.format(data));
            java.sql.Date dataSql = new java.sql.Date(d.getTime());    
            java.sql.Time horaSql = new java.sql.Time(t.getTime());   
            pstmt.setInt(1, cliente.getIdCliente());
            pstmt.setDate(2, dataSql);
            pstmt.setTime(3, horaSql);
            ResultSet rs = pstmt.executeQuery();
        while (rs.next()){        //a cada loop                     
            compraL.setIdCompra(rs.getInt("idCompra"));
            Date dR = rs.getDate("datacompra");            
            Date tR = rs.getTime("horacompra");            
            String dD = dia.format(dR);
            String tT = hora.format(tR);
            String dataRecebida = dD+" "+tT;
            compraL.setDataCompra(f.parse(dataRecebida));
            compraL.setValor(rs.getDouble("valor"));
            compraL.setFormaDePagamento(rs.getString("formaDePagamento"));
            compraL.setCliente(cliente);      
            compraL.setCarrinho(carrinho);
        }
            return compraL;            
        }catch(Exception e ){
            throw new RuntimeException(e);
        }finally{
            try{
                conexao.close();
            }catch (SQLException ex){
                    throw new RuntimeException(ex);

                }            
            } 
    }

    public ArrayList<Compra> relatorioMensal ()throws SQLException, ClassNotFoundException {
       ArrayList<Compra> listaDeCompras = new ArrayList<Compra>();
        Connection conexao = null;              
        try{            
            conexao = Conexao.getConexao();
            SimpleDateFormat dia = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat hora = new SimpleDateFormat("HH:mm:ss");
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            BllCliente bllC = new BllCliente();
            PreparedStatement pstmt = conexao.prepareStatement(RELATORIOMENSAL);              
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){        //a cada loop 
                Compra compraL = new Compra();
                compraL.setIdCompra(rs.getInt("idCompra"));
                Date dR = rs.getDate("datacompra");            
                Date tR = rs.getTime("horacompra");            
                String dD = dia.format(dR);
                String tT = hora.format(tR);
                String dataRecebida = dD+" "+tT;
                compraL.setDataCompra(f.parse(dataRecebida));            
                compraL.setValor(rs.getDouble("valor"));
                compraL.setFormaDePagamento(rs.getString("formaDePagamento"));
                int idCliente = rs.getInt("idclientefk");            
                compraL.setCliente(bllC.buscarPorId(idCliente));      
                listaDeCompras.add(compraL);
            }
            return listaDeCompras;            
        }catch(Exception e ){
            throw new RuntimeException(e);
        }finally{
            try{
                conexao.close();
            }catch (SQLException ex){
                    throw new RuntimeException(ex);
            
                }            
        } 
    }
    
    public ArrayList<Compra> relatorioDatado (Date dataInicial , Date dataFinal)throws SQLException, ClassNotFoundException {
       ArrayList<Compra> listaDeCompras = new ArrayList<Compra>();
        Connection conexao = null;              
        try{            
            conexao = Conexao.getConexao();
            BllCliente bllC = new BllCliente();
            PreparedStatement pstmt = conexao.prepareStatement(RELATORIODATADO); 
            SimpleDateFormat dia = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat hora = new SimpleDateFormat("HH:mm:ss");
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            java.sql.Date dInicial = new java.sql.Date(dataInicial.getTime());
            java.sql.Date dFinal = new java.sql.Date(dataFinal.getTime());
            pstmt.setDate(1, dInicial ); 
            pstmt.setDate(2, dFinal );
            ResultSet rs = pstmt.executeQuery();
        while (rs.next()){        //a cada loop 
            Compra compraL = new Compra();
            compraL.setIdCompra(rs.getInt("idCompra"));       
            Date dR = rs.getDate("datacompra");            
            Date tR = rs.getTime("horacompra");            
            String dD = dia.format(dR);
            String tT = hora.format(tR);
            String dataRecebida = dD+" "+tT;
            compraL.setDataCompra(f.parse(dataRecebida));            
            compraL.setValor(rs.getDouble("valor"));
            compraL.setFormaDePagamento(rs.getString("formaDePagamento"));
            int idCliente = rs.getInt("idclientefk");            
            compraL.setCliente(bllC.buscarPorId(idCliente));      
            listaDeCompras.add(compraL);
        }
            return listaDeCompras;            
        }catch(Exception e ){
            throw new RuntimeException(e);
        }finally{
            try{
                conexao.close();
            }catch (SQLException ex){
                    throw new RuntimeException(ex);
            
                }            
            } 
    }
}
