package ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.greengarden.R;

import java.util.List;

import control.Retrofit;
import model.Cliente;
import model.Produto;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ui.adapters.ProdutoAdapter;

public class ListarProdutosActivity extends AppCompatActivity {
    private List<Produto> produtos;
    private RecyclerView recyclerView;
    private Cliente cliente;

    ImageView iconPerfil, iconHome, iconCarrinho;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_produtos);
        EdgeToEdge.enable(this);

        // Ajuste para as barras do sistema (status bar, navigation bar, etc)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Recuperando o cliente passado via Intent
        cliente = (Cliente) getIntent().getSerializableExtra("Cliente");
        Log.d("UsuarioActivity", "onCreate: " + cliente.getEmail());

        iconCarrinho = ((ImageView) findViewById(R.id.iconCarrinho));
        iconPerfil = ((ImageView) findViewById(R.id.iconPerfil));
        iconHome = ((ImageView) findViewById(R.id.iconHome));

        // Chamando o método para listar os produtos
        listarProdutos();

        iconHome.setOnClickListener(v -> navegarTela(ListarProdutosActivity.class));
        iconPerfil.setOnClickListener(v -> navegarTela(UsuarioActivity.class));
        iconCarrinho.setOnClickListener(v -> navegarTela(CarrinhoActivity.class));
    }

    private void listarProdutos() {
        Log.d("ListarProdutos", "Chamando a API para listar produtos...");

        // Inicializando RecyclerView e definindo o layout (grid com 2 colunas)
        recyclerView = findViewById(R.id.recyclerViewProdutos);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2)); // 2 colunas

        // Chamando a API para obter a lista de produtos
        Retrofit.getApiService().getProdutos().enqueue(new Callback<List<Produto>>() {
            @Override
            public void onResponse(Call<List<Produto>> call, Response<List<Produto>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    produtos = response.body();

                    Log.d("ListarProdutos", "Produtos recebidos com sucesso. Inicializando o Adapter...");
                    ProdutoAdapter adapter = new ProdutoAdapter(produtos, cliente, ListarProdutosActivity.this);
                    recyclerView.setAdapter(adapter);
                } else {
                    // Erro caso a resposta não seja bem-sucedida
                    Toast.makeText(ListarProdutosActivity.this, "Não foi possível carregar os produtos", Toast.LENGTH_SHORT).show();
                    Log.e("ListarProdutos", "Erro ao obter produtos: " + response.code() + " - " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Produto>> call, Throwable t) {
                // Erro caso a requisição falhe
                Toast.makeText(ListarProdutosActivity.this, "Erro de conexão com a internet, tente novamente", Toast.LENGTH_SHORT).show();
                Log.e("ListarProdutos", "Falha ao fazer requisição: " + t.getMessage(), t);
            }
        });
    }
    private void navegarTela(Class<?> classe){
        if(cliente != null){
            Intent intent = new Intent(this, classe);
            intent.putExtra("Cliente", cliente);
            startActivity(intent);
        }
        else{
            Log.d("ListarProdutos", "navegarTela: cliente null");
        }
    }

}
