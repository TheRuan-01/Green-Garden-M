package ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.greengarden.R;

import java.util.List;

import model.ItemCarrinho;

public class CarrinhoAdapter extends RecyclerView.Adapter<CarrinhoAdapter.CarrinhoViewHolder> {

    private List<ItemCarrinho> itensCarrinho;  // Lista de itens no carrinho
    private OnItemClickListener onItemClickListener;  // Listener para ações no item

    public CarrinhoAdapter(List<ItemCarrinho> itensCarrinho, OnItemClickListener onItemClickListener) {
        this.itensCarrinho = itensCarrinho;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public CarrinhoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Infla o layout do item do carrinho
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_carrinho, parent, false);
        return new CarrinhoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CarrinhoViewHolder holder, int position) {
        // Preenche os dados do item no ViewHolder
        ItemCarrinho itemCarrinho = itensCarrinho.get(position);
        holder.bind(itemCarrinho);
    }

    @Override
    public int getItemCount() {
        // Retorna a quantidade de itens no carrinho
        return itensCarrinho.size();
    }

    public class CarrinhoViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgProduto;  // Imagem do produto
        private TextView nomeProduto;  // Nome do produto
        private TextView precoProduto;  // Preço do produto
        private TextView quantidadeProduto;  // Quantidade do produto
        private ImageButton btnAumentar;  // Botão para aumentar a quantidade
        private ImageButton btnDiminuir;  // Botão para diminuir a quantidade
        private ImageButton btnRemover;  // Botão para remover o item do carrinho

        public CarrinhoViewHolder(View itemView) {
            super(itemView);
            // Inicializa as views do layout com IDs consistentes
            imgProduto = itemView.findViewById(R.id.itemImagem);
            nomeProduto = itemView.findViewById(R.id.itemNome);
            precoProduto = itemView.findViewById(R.id.itemPreco);
            quantidadeProduto = itemView.findViewById(R.id.tvQuantidade);
            btnRemover = itemView.findViewById(R.id.btnRemover);
            btnAumentar = itemView.findViewById(R.id.btnAdicionar);
            btnDiminuir = itemView.findViewById(R.id.btnDiminuir);
        }

        public void bind(ItemCarrinho itemCarrinho) {
            // Preenche os dados do item no layout
            nomeProduto.setText(itemCarrinho.getProduto().getNome());
            precoProduto.setText("R$ " + itemCarrinho.getPrecoUnitario());
            quantidadeProduto.setText(String.valueOf(itemCarrinho.getQuantidade()));

            // Define a imagem do produto
            Glide.with(itemView.getContext())
                    .load(itemCarrinho.getProduto().getImagem()) // URL ou recurso da imagem
                    .into(imgProduto);

            // Aumenta a quantidade do item
            btnAumentar.setOnClickListener(v -> {
                itemCarrinho.setQuantidade(itemCarrinho.getQuantidade() + 1);
                quantidadeProduto.setText(String.valueOf(itemCarrinho.getQuantidade()));
                atualizarTotal();
                notifyItemChanged(getAdapterPosition());
            });

            // Diminui a quantidade do item
            btnDiminuir.setOnClickListener(v -> {
                if (itemCarrinho.getQuantidade() > 1) {
                    itemCarrinho.setQuantidade(itemCarrinho.getQuantidade() - 1);
                    quantidadeProduto.setText(String.valueOf(itemCarrinho.getQuantidade()));
                    atualizarTotal();
                    notifyItemChanged(getAdapterPosition());
                }
            });

            // Remove o item do carrinho
            btnRemover.setOnClickListener(v -> {
                itensCarrinho.remove(getAdapterPosition());
                notifyItemRemoved(getAdapterPosition());
                atualizarTotal();
            });
        }
    }

    private void atualizarTotal() {
        double total = 0.0;
        for (ItemCarrinho item : itensCarrinho) {
            total += item.getPrecoUnitario() * item.getQuantidade();
        }
        onItemClickListener.onTotalUpdated(total);
    }

    public interface OnItemClickListener {
        void onItemUpdated();

        void onItemRemoved();

        void onTotalUpdated(double total);
    }
}
