package Modelo;
public class ItemDeCompra {
    private int idItemDeCompra;
    private Produto produto;
    private int quantidade;
    private double total;

    public int getIdItemDeCompra() {
        return idItemDeCompra;
    }

    public void setIdItemDeCompra(int idItemDeCompra) {
        this.idItemDeCompra = idItemDeCompra;
    }
    
    public Produto getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    
    public double getTotal() {
        this.total = this.quantidade*this.produto.getValorVenda();        
        return total;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
    
    
}
