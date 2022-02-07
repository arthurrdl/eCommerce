package Modelo;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;


public class CarrinhoDeCompra {
    private int id;
    private List<ItemDeCompra> itens;
    private double total;
    private int idlistaDecompra;

    public int getIdlistaDecompra() {
        return idlistaDecompra;
    }

    public void setIdlistaDecompra(int idlistaDecompra) {
        this.idlistaDecompra = idlistaDecompra;
    }
    
    public void addNovoItem(ItemDeCompra item){
        if(this.itens == null){
            this.itens = new ArrayList<ItemDeCompra>();
        }
        this.itens.add(item);
    }
    
    public void removerItem (ItemDeCompra itemRemove){
        for(Iterator i = itens.iterator();i.hasNext();){
            ItemDeCompra item = (ItemDeCompra)i.next();
            if(item.getProduto().getIdProduto() == itemRemove.getProduto().getIdProduto()){
                i.remove();
            }
        }
    }
    
    public double calculaTotal(){
        double vtotal=0;
        for(ItemDeCompra item : this.itens){
            vtotal+=item.getTotal(); 
        }
        this.total  = vtotal;
        return total;
    }

    public int getId() {
        return id;
    }

    public List<ItemDeCompra> getItens() {
        return itens;
    }

    public double getTotal() {
        return total;
    }
    
    
    
    
}//FIM CARRINHO
