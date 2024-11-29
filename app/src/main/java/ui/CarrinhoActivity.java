package ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.greengarden.R;

import java.util.ArrayList;
import java.util.List;

import model.Carrinho;
import model.Cliente;
import model.ItemCarrinho;

import model.Produto;
import ui.adapters.CarrinhoAdapter;

public class CarrinhoActivity extends AppCompatActivity implements CarrinhoAdapter.OnItemClickListener{

    // Componentes da UI
    private RecyclerView recyclerView;
    private TextView total;
    private ImageView navHome, navCarrinho, navPerfil;
    private Cliente cliente;
    private Produto produto;

    // Adapter e dados do carrinho
    private CarrinhoAdapter carrinhoAdapter;
    private List<ItemCarrinho> itensCarrinho;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrinho);
        EdgeToEdge.enable(this);  // Habilita o modo Edge-to-Edge

        // Recupera o cliente passado via Intent
        cliente = (Cliente) getIntent().getSerializableExtra("Cliente");
        Log.d("UsuarioActivity", "onCreate: " + cliente.getEmail());

        // Inicializa os componentes da UI
        inicializarComponentesUI(cliente);

        // Configura os itens do carrinho e o adapter
        configurarCarrinho();

        // Configura os botões de navegação
    }

    /**
     * Método para inicializar os componentes da interface do usuário.
     * Inclui o total do carrinho e os botões de navegação.
     */
    private void inicializarComponentesUI(Cliente cliente) {
        // Inicializa a label que mostra o total do carrinho
        total = findViewById(R.id.totalValor);
        total.setText(String.format("Total: R$ %.2f", Carrinho.getTotal())); // Exibe o total do carrinho

        // Configura o RecyclerView para mostrar os itens do carrinho
        recyclerView = findViewById(R.id.recyclerViewCarrinho);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Inicializa os botões de navegação
        navHome = findViewById(R.id.iconHome);
        navCarrinho = findViewById(R.id.iconCarrinho);
        navPerfil = findViewById(R.id.iconPerfil);

        navHome.setOnClickListener(v -> navegarTela(ListarProdutosActivity.class));
        navPerfil.setOnClickListener(v -> navegarTela(UsuarioActivity.class));
        navCarrinho.setOnClickListener(v -> navegarTela(CarrinhoActivity.class));

        ((Button) findViewById(R.id.botaoFinalizar)).setOnClickListener(v -> {
            Intent intent = new Intent(CarrinhoActivity.this, PedidoActivity.class);
            intent.putExtra("Cliente", cliente);
            startActivity(intent);
        } );

    }

    /**
     * Configura os itens do carrinho e o adapter.
     * Esse método recupera os itens do carrinho e configura o RecyclerView com o Adapter.
     */
    private void configurarCarrinho() {
        // Obtém os itens do carrinho
        itensCarrinho = Carrinho.getItensCarrinho();

        // Cria e configura o Adapter do RecyclerView
        carrinhoAdapter = new CarrinhoAdapter(itensCarrinho, this);
        recyclerView.setAdapter(carrinhoAdapter);  // Define o adapter para o RecyclerView
    }

    /**
     * Atualiza o total na interface do usuário quando o total do carrinho é alterado.
     */
    @Override
    public void onTotalUpdated(double totalValue) {
        // Atualiza o total na UI com o novo valor
        total.setText(String.format("Total: R$ %.2f", totalValue));
    }
    /**
     * Método que configura os botões de navegação.
     * Configura os listeners para os botões de navegação entre as telas.
     */

    // Métodos não utilizados de interface OnItemClickListener (pode ser implementado mais tarde, se necessário)
    @Override
    public void onItemUpdated() {
        // Atualizar o item se necessário (implementação futura)
    }

    @Override
    public void onItemRemoved() {
        // Remover item do carrinho se necessário (implementação futura)
    }
    private void navegarTela(Class<?> classe){
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
}
