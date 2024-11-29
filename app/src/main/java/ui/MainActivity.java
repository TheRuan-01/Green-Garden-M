package ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.greengarden.R;
import com.google.android.material.internal.EdgeToEdgeUtils;

import control.Retrofit;
import model.Cliente;
import model.DTO.LoginDTO;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText txtUsuario, txtSenha;
    private Button btnLogin, btnRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        EdgeToEdge.enable(this);

        txtUsuario = findViewById(R.id.txtUsuario);
        txtSenha = findViewById(R.id.txtSenha);

        btnLogin = findViewById(R.id.bnt_login);
        btnRegistro = findViewById(R.id.bnt_registro);

        // Configurando os listeners dos botões
        btnLogin.setOnClickListener(x -> realizarLogin());
        btnRegistro.setOnClickListener(x -> realizarCadastro());
    }

    private void realizarLogin() {
        // Capturar os dados de login no momento de clicar
        String usuario = txtUsuario.getText().toString();
        String senha = txtSenha.getText().toString();

        // Verificar se os campos estão preenchidos
        if (usuario.isEmpty() || senha.isEmpty()) {
            Toast.makeText(MainActivity.this, "Por favor, preencha ambos os campos", Toast.LENGTH_SHORT).show();
            return;
        }

        LoginDTO dto = new LoginDTO(usuario, senha);  // Criar o LoginDTO com os dados inseridos

        Log.d("LoginActivity", "Chamando api...");
        Retrofit.getApiService().fazerLogin(dto).enqueue(new Callback<Cliente>() {
            @Override
            public void onResponse(Call<Cliente> call, Response<Cliente> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Exibir mensagem de sucesso
                    Toast.makeText(MainActivity.this, "Login feito com sucesso!", Toast.LENGTH_SHORT).show();
                    Cliente cliente = response.body();
                    Log.d("LoginActivity", "Cliente retornado: " + cliente.getEmail());
                    irAoMenu(cliente);
                } else {
                    // Caso a resposta da API seja inválida ou o corpo esteja vazio
                    Toast.makeText(MainActivity.this, "Email ou senha incorretos", Toast.LENGTH_SHORT).show();
                    Log.d("LoginActivity", "Erro na resposta da API: " + response.code() + " - " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Cliente> call, Throwable t) {
                // Caso haja erro de rede ou outro problema na comunicação com a API
                Toast.makeText(MainActivity.this, "Erro de conexão com a internet, tente novamente", Toast.LENGTH_SHORT).show();
                Log.d("LoginActivity", "Falha na requisição: " + t);
            }
        });
    }

    private void realizarCadastro() {
        // Navegar para a tela de registro
        Intent intent = new Intent(this, RegistroActivity.class);
        startActivity(intent);
    }

    private void irAoMenu(Cliente cliente) {
        // Caso o login seja bem-sucedido, passar o cliente para a próxima activity
        Intent intent = new Intent(this, ListarProdutosActivity.class);
        if (cliente != null) {
            intent.putExtra("Cliente", cliente);
            Log.d("LoginActivity", "Cliente passado para a próxima Activity: " + cliente.getEmail());
        }
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
