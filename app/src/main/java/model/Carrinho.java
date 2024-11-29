package model;


import java.util.ArrayList;
import java.util.List;

public class Carrinho {

    private static final List<ItemCarrinho> itensCarrinho = new ArrayList<>();

    public static List<ItemCarrinho> getItensCarrinho() {
        return itensCarrinho;
    }

    public static void adicionarItem(ItemCarrinho itemCarrinho) {
        itensCarrinho.add(itemCarrinho);
    }

    public static void limparCarrinho() {
        itensCarrinho.clear();
    }

    public static double getTotal(){
        double count = 0;
        for(ItemCarrinho itens : itensCarrinho){
            count += itens.getPrecoTotal();
        }
        return count;
    }
}
