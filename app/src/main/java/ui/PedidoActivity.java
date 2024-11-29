package ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.greengarden.R;

import control.Retrofit;
import model.Carrinho;
import model.Cliente;
import model.Endereco;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ui.adapters.PedidoAdapter;
import model.ItemCarrinho;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PedidoActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PedidoAdapter pedidoAdapter;
    private List<ItemCarrinho> listaProdutos;
    private TextView totalTextoResumo;
    private TextView totalValorResumo;

    private Cliente cliente;
    private ImageView navHome, navCarrinho, navPerfil;

    // Adicionando os Spinners
    private Spinner spinnerMetodoPagamento;
    private Spinner spinnerEndereco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pedido);

        // Inicializando os componentes da interface
        recyclerView = findViewById(R.id.recyclerViewResumoCarrinho);
        totalTextoResumo = findViewById(R.id.totalTextoResumo);
        totalValorResumo = findViewById(R.id.totalValorResumo);
        spinnerMetodoPagamento = findViewById(R.id.spinnerMetodoPagamento);
        spinnerEndereco = findViewById(R.id.spinnerEndereco);

        // Dados fictícios
        listaProdutos = Carrinho.getItensCarrinho();

        // Calculando o total
        totalValorResumo.setText(String.format("R$ %.2f", Carrinho.getTotal()));

        // Configura o RecyclerView com o adaptador
        pedidoAdapter = new PedidoAdapter(this, listaProdutos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(pedidoAdapter);

        // Configurando os Spinners de Método de Pagamento e Endereço
        configurarSpinnerMetodoPagamento();
        configurarSpinnerEndereco();

        Log.d("UsuarioActivity", "onCreate: " + cliente.getEmail());

        navCarrinho = ((ImageView) findViewById(R.id.iconCarrinho));
        navPerfil = ((ImageView) findViewById(R.id.iconPerfil));
        navHome = ((ImageView) findViewById(R.id.iconHome));

        navHome.setOnClickListener(v -> navegarTela(ListarProdutosActivity.class));
        navPerfil.setOnClickListener(v -> navegarTela(UsuarioActivity.class));
        navCarrinho.setOnClickListener(v -> navegarTela(CarrinhoActivity.class));
    }
    /**
     * Configura o Spinner para seleção de Método de Pagamento.
     */
    private void configurarSpinnerMetodoPagamento() {
        // Lista de opções de métodos de pagamento
        String[] metodosPagamento = {"Cartão de Crédito", "Boleto Bancário", "Pix"};

        // Criação do adaptador para o Spinner
        ArrayAdapter<String> adapterMetodoPagamento = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, metodosPagamento);
        adapterMetodoPagamento.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Configura o adaptador no Spinner
        spinnerMetodoPagamento.setAdapter(adapterMetodoPagamento);
        ((Button) findViewById(R.id.botaoFinalizarCompraResumo)).setOnClickListener(v -> Toast.makeText(this, "Compra finalizada!", Toast.LENGTH_SHORT).show());

        // Adicionando um listener para capturar a seleção do método de pagamento
        spinnerMetodoPagamento.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, android.view.View selectedItemView, int position, long id) {
                // Aqui, você pode fazer algo com a seleção, como armazenar o método de pagamento selecionado
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Caso nenhuma opção seja selecionada
            }
        });
    }

    /**
     * Configura o Spinner para seleção de Endereço.
     */
    private void configurarSpinnerEndereco() {
        // Lista de opções de endereços

        cliente = (Cliente) getIntent().getSerializableExtra("Cliente");
        Retrofit.getApiService().getEndereco(cliente.getEnderecoId()).enqueue(new Callback<Endereco>() {
            @Override
            public void onResponse(Call<Endereco> call, Response<Endereco> response) {
                if(response.isSuccessful() && response.body() != null){
                    // Criação do adaptador para o Spinner
                    ArrayAdapter<Endereco> adapterEndereco = new ArrayAdapter<>(PedidoActivity.this,android.R.layout.simple_spinner_item, Collections.singletonList(response.body()));
                    adapterEndereco.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    // Configura o adaptador no Spinner
                    spinnerEndereco.setAdapter(adapterEndereco);

                    // Adicionando um listener para capturar a seleção do endereço
                    spinnerEndereco.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parentView, android.view.View selectedItemView, int position, long id) {
                            // Aqui, você pode fazer algo com a seleção, como armazenar o endereço selecionado
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parentView) {
                            // Caso nenhuma opção seja selecionada
                        }
                    });
                }

            }
            @Override
            public void onFailure(Call<Endereco> call, Throwable t) {

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

