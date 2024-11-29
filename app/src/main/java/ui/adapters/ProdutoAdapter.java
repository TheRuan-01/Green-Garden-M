package ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.greengarden.R;


import java.util.List;

import model.Cliente;
import model.Produto;
import ui.ProdutoActivity;

public class ProdutoAdapter extends RecyclerView.Adapter<ProdutoAdapter.ProdutoViewHolder> {

    private List<Produto> listaProdutos;
    private Context context; // Contexto para iniciar a nova Activity
    private Cliente cliente;

    public ProdutoAdapter(List<Produto> listaProdutos, Cliente cliente, Context context) {
        this.listaProdutos = listaProdutos;
        this.context = context;
        this.cliente = cliente;
    }

    @Override
    public ProdutoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_produto, parent, false);
        return new ProdutoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProdutoViewHolder holder, int position) {
        Produto produto = listaProdutos.get(position);
        holder.nomeProduto.setText(produto.getNome());
        holder.precoProduto.setText(String.format("R$ %.2f", produto.getPreco()));

        // Usando Glide para carregar a imagem do produto
        Glide.with(holder.imgProduto.getContext())
                .load(produto.getImagem()) // Aqui você pode passar uma URL ou um recurso drawable
                .placeholder(R.drawable.cart) // Imagem de placeholder enquanto a imagem carrega
                .into(holder.imgProduto);

        // Definindo o comportamento do botão de comprar
        holder.btnComprar.setOnClickListener(v -> {
            // Cria a Intent para a nova Activity (DetalhesProdutoActivity)
            Intent intent = new Intent(context, ProdutoActivity.class);
            intent.putExtra("Produto", produto);
            intent.putExtra("Cliente", cliente);
            // Inicia a Activity
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return listaProdutos.size();
    }

    public class ProdutoViewHolder extends RecyclerView.ViewHolder {
        TextView nomeProduto, precoProduto;
        ImageView imgProduto;
        Button btnComprar;

        public ProdutoViewHolder(View itemView) {
            super(itemView);
            nomeProduto = itemView.findViewById(R.id.txtNomeProduto);
            precoProduto = itemView.findViewById(R.id.txtPrecoProduto);
            imgProduto = itemView.findViewById(R.id.imgProduto);
            btnComprar = itemView.findViewById(R.id.btnComprar);
        }
    }
}
