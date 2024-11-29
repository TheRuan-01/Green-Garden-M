package ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.greengarden.R;

import control.Retrofit;
import model.Cliente;
import model.Endereco;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsuarioActivity extends AppCompatActivity {
    TextView nomeTextView, emailTextView, telefoneTextView, cpfTextView, enderecoTextView;
    Cliente cliente;
    private ImageView navHome, navCarrinho, navPerfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);
        EdgeToEdge.enable(this);

        // Receber o objeto através do Intent
         cliente = ((Cliente) getIntent().getSerializableExtra("Cliente"));
        Log.d("UsuarioActivity", "onCreate: " + cliente.getEmail());

        // Verificar se o objeto foi recebido corretamente
            // Inicializar os componentes da UI com os dados do objeto 'usuario'
            nomeTextView = (TextView) findViewById(R.id.tvNome);
            emailTextView = (TextView) findViewById(R.id.tvEmail);
            telefoneTextView = (TextView) findViewById(R.id.tvTelefone);
            cpfTextView = (TextView) findViewById(R.id.tvCpf);
            enderecoTextView = (TextView) findViewById(R.id.tvEndereco);

            Button editarButton = findViewById(R.id.btnAlterar);
            Button excluirButton = findViewById(R.id.btnDeletar);

            inicializarDados();

            editarButton.setOnClickListener(v -> editarPerfil());
            excluirButton.setOnClickListener(v -> excluirPerfil());

        // Inicializa os botões de navegação
            navHome = findViewById(R.id.iconHome);
            navCarrinho = findViewById(R.id.iconCarrinho);
            navPerfil = findViewById(R.id.iconPerfil);

            navHome.setOnClickListener(v -> navegarTela(ListarProdutosActivity.class));
            navPerfil.setOnClickListener(v -> navegarTela(UsuarioActivity.class));
            navCarrinho.setOnClickListener(v -> navegarTela(CarrinhoActivity.class));

    }

    private void editarPerfil(){
        Intent intent = new Intent(this, AtualizarActivity.class);
        intent.putExtra("Cliente", cliente);
        startActivity(intent);
    }

    private void excluirPerfil(){
        Intent intent = new Intent(this, DeletarActivity.class);
        intent.putExtra("Cliente", cliente);
        startActivity(intent);
    }

    private void inicializarDados(){
        emailTextView.setText(cliente.getEmail());
        nomeTextView.setText(cliente.getNome());
        telefoneTextView.setText(cliente.getTelefone());
        cpfTextView.setText(cliente.getCpf());

        Log.d("UsuarioActivity", cliente.getEmail() );

        Retrofit.getApiService().getEndereco(cliente.getEnderecoId()).enqueue(new Callback<Endereco>() {
            @Override
            public void onResponse(Call<Endereco> call, Response<Endereco> response) {
                Log.d("UsuarioActivity", "onResponse: Chamada de API" );
                cliente.setEndereco(response.body());
                if(response.isSuccessful() && response.body() != null){
                    Log.d("UsuarioActivity", "onResponse: " +  response.body());
                    enderecoTextView.setText(cliente.getEndereco().toString());
                }
                else {
                    Log.d("UsuarioActivity", "onResponse: " +  response.code() + ": " + response.message());
                    Toast.makeText(UsuarioActivity.this, "Ocorreu um erro", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Endereco> call, Throwable t) {
                Log.d("UsuarioActivity", "onResponse: " + t);
                Toast.makeText(UsuarioActivity.this, "Ocorreu um erro", Toast.LENGTH_SHORT).show();
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
            Log.d("ProdutoActivity", "navegarTela: cliente/produto null");
            Log.d("ProdutoActivity", "navegarTela: " + cliente);
        }
    }
}
