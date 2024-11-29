package model.DTO;


import java.util.ArrayList;
import java.util.List;

import model.ItemCarrinho;

public class CarrinhoRepository {

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
            count += itens.getPrecoUnitario();
        }
        return count;
    }
}
