package ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.greengarden.R;

import java.util.List;

import model.Carrinho;
import model.ItemCarrinho;

public class PedidoAdapter extends RecyclerView.Adapter<PedidoAdapter.ProdutoViewHolder> {

    private List<ItemCarrinho> produtos;
    private Context context;

    public PedidoAdapter(Context context, List<ItemCarrinho> produtos) {
        this.context = context;
        this.produtos = produtos;
    }

    @NonNull
    @Override
    public ProdutoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflar o layout item_pedido.xml
        View view = LayoutInflater.from(context).inflate(R.layout.item_produto_resumo, parent, false);
        return new ProdutoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProdutoViewHolder holder, int position) {
        // Obter o produto atual
        ItemCarrinho itemCarrinho = produtos.get(position);

        // Definir os valores nos TextViews
        holder.nomeProduto.setText(itemCarrinho.getProduto().getNome());
        holder.quantidadeProduto.setText(String.format("Qtd: %d", itemCarrinho.getQuantidade()));
        holder.subtotalProduto.setText(String.format("Subtotal: R$ %.2f", Carrinho.getTotal()));
    }

    @Override
    public int getItemCount() {
        return produtos.size();
    }

    // ViewHolder para associar os componentes da UI
    public static class ProdutoViewHolder extends RecyclerView.ViewHolder {

        // Definindo as referências das TextViews
        TextView nomeProduto;
        TextView quantidadeProduto;
        TextView subtotalProduto;

        public ProdutoViewHolder(@NonNull View itemView) {
            super(itemView);

            // Inicializando os componentes da interface
            nomeProduto = itemView.findViewById(R.id.tvNomeProduto);
            quantidadeProduto = itemView.findViewById(R.id.tvQuantidade);
            subtotalProduto = itemView.findViewById(R.id.tvSubtotal);
        }
    }
}
