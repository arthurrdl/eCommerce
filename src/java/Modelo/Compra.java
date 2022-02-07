package Modelo;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

public class Compra {
    private int idCompra;
    private Date dataCompra;
    private double valor;
    private String formaDePagamento;
    private Cliente cliente;
    private CarrinhoDeCompra carrinho;
    
    public Compra(){
    
    }
            
    public Compra (Cliente cliente , CarrinhoDeCompra carrinho){
        this.carrinho = carrinho;
        this.cliente = cliente;
    }
    
    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    public Date getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(Date dataCompra) {
        this.dataCompra = dataCompra;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getFormaDePagamento() {
        return formaDePagamento;
    }

    public void setFormaDePagamento(String formaDePagamento) {
        this.formaDePagamento = formaDePagamento;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }  

    public CarrinhoDeCompra getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(CarrinhoDeCompra carrinho) {
        this.carrinho = carrinho;
    }
    
    
}
