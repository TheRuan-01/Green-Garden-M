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

import control.Retrofit;
import model.Cliente;
import model.DTO.UpdateDTO;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AtualizarActivity extends AppCompatActivity {

    private EditText nome, email, telefone, cpf, senha;
    private Cliente cliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_atualizar);

        cliente = ((Cliente) getIntent().getSerializableExtra("Cliente"));

        nome = findViewById(R.id.editTextNome);
        email = findViewById(R.id.editTextEmail);
        senha = findViewById(R.id.editTextSenha);
        telefone = findViewById(R.id.editTextTelefone);
        cpf = findViewById(R.id.editTextCpf);

        ((Button) findViewById(R.id.botaoSalvarAlteracoes)).setOnClickListener(v -> {
            String nome_txt = nome.getText().toString();
            String email_txt = email.getText().toString();
            String senha_txt = senha.getText().toString();
            String telefone_txt = telefone.getText().toString();
            String cpf_txt = telefone.getText().toString();

            atualizarConta(nome_txt, email_txt, senha_txt, telefone_txt, cpf_txt);
        });

    }

    private void atualizarConta(String nome, String email, String senha, String telefone, String cpf){
        UpdateDTO dto = new UpdateDTO(nome, cpf, telefone, email, senha);
        Retrofit.getApiService().atualizarCliente(dto, cliente.getEmail()).enqueue(new Callback<Cliente>() {
            @Override
            public void onResponse(Call<Cliente> call, Response<Cliente> response) {
                if(response.isSuccessful()){
                    Toast.makeText(AtualizarActivity.this, "Cliente atualizado com sucesso!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AtualizarActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(AtualizarActivity.this, "Ocorreu um erro ao atualizar os seus dados", Toast.LENGTH_SHORT).show();
                    Log.d("AtualizarActivity", "onResponse: " + response.code() + " - " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Cliente> call, Throwable t) {
                Toast.makeText(AtualizarActivity.this, "Ocorreu um erro ao atualizar os seus dados", Toast.LENGTH_SHORT).show();
                Log.d("AtualizarActivity", "onResponse: " + t);
            }
        });


    }
}