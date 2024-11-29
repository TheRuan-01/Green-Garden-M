package ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.greengarden.R;

import model.Carrinho;
import model.Cliente;
import model.ItemCarrinho;
import model.Produto;

public class ProdutoActivity extends AppCompatActivity {

    // Definindo as views
    private ImageView produtoImagem, iconPerfil, iconHome, iconCarrinho;
    private TextView produtoNome, produtoDescricao, produtoPreco;
    private Button button;
    private Produto produto;
    private Cliente cliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Definindo a interface de tela cheia (se desejado)
        setContentView(R.layout.activity_produto);
        EdgeToEdge.enable(this);

        // Inicializando as views
        produtoNome = findViewById(R.id.produtoNome);
        produtoDescricao = findViewById(R.id.produtoDescricao);
        produtoPreco = findViewById(R.id.produtoPreco);
        produtoImagem = findViewById(R.id.produtoImagem);  // Corrigindo a inicialização da imagem
        button = findViewById(R.id.botaoComprar);
        iconCarrinho = findViewById(R.id.iconCarrinho);
        iconPerfil = findViewById(R.id.iconPerfil);
        iconHome = findViewById(R.id.iconHome);

        // Obtendo os dados passados via Intent
        produto = (Produto) getIntent().getSerializableExtra("Produto");
        cliente = (Cliente) getIntent().getSerializableExtra("Cliente");

        button.setOnClickListener(v -> adicionarProdutoNoCarrinho());

        // Verificando se os dados não são nulos
            mudarInterface(); // Atualiza a interface com os dados do produto
        // Configurando ações para os ícones de navegação
        iconHome.setOnClickListener(v -> navegarTela(ListarProdutosActivity.class));
        // Ações para ícones de perfil e carrinho podem ser implementadas aqui
        iconPerfil.setOnClickListener(v -> navegarTela(UsuarioActivity.class));
        iconCarrinho.setOnClickListener(v -> navegarTela(CarrinhoActivity.class));

    }

    // Método para mudar a interface com as informações do produto
    private void mudarInterface() {
        produtoNome.setText(produto.getNome());
        produtoDescricao.setText(produto.getDescricao());
        produtoPreco.setText(String.format("R$ %.2f", produto.getPreco()));

        // Usando Glide para carregar a imagem do produto
        Glide.with(this)
                .load(produto.getImagem())
                .placeholder(R.drawable.person)  // Imagem de placeholder
                .into(produtoImagem);
    }

    // Método para navegação entre as telas
    private void navegarTela(Class<?> classe) {
        if(cliente != null){
            Intent intent = new Intent(this, classe);
            intent.putExtra("Cliente", cliente);
            startActivity(intent);
        }
        else{
            Log.d("ProdutoActivity", "navegarTela: cliente/produto null");
            Log.d("ProdutoActivity", "navegarTela: " + cliente);
        }
    }

    private void adicionarProdutoNoCarrinho(){
        ItemCarrinho itemCarrinho = new ItemCarrinho(1, produto.getPreco(), produto.getPreco(), "compra", produto.getIdProduto(), produto, 0, null);

        if (Carrinho.getItensCarrinho().contains(itemCarrinho)) {
            int index = Carrinho.getItensCarrinho().indexOf(itemCarrinho);
            ItemCarrinho itemExistente = Carrinho.getItensCarrinho().get(index);
            itemExistente.setQuantidade(itemExistente.getQuantidade() + 1);
        } else {
            Carrinho.adicionarItem(itemCarrinho);
            Toast.makeText(this, "Produto adicionado ao carrinho", Toast.LENGTH_SHORT).show();
        }

    }
}
