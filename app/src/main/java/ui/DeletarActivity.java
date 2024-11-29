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

import control.ApiService;
import control.Retrofit;
import model.Cliente;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ui.adapters.ProdutoAdapter;

public class DeletarActivity extends AppCompatActivity {

    private EditText deleteText;
    private Cliente cliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_deletar);

        deleteText = findViewById(R.id.editTextSenhaDelecao);
        cliente = ((Cliente) getIntent().getSerializableExtra("Cliente"));


        ((Button) findViewById(R.id.botaoConfirmarDelecao)).setOnClickListener(view ->{

            String senha = deleteText.getText().toString();
            if(senha.equals(cliente.getSenha())){
                deletarConta(senha, cliente.getIdCliente());
                Log.d("DeletarActvity", "onCreate: " + cliente.getSenha() + ": " + senha);
            }
            else{
                Toast.makeText(this, "Senha incorreta, tente novamente.", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void deletarConta(String senha, int id){
        Retrofit.getApiService().deleteCliente(id).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Log.d("DeletarActivity", "Conta deletada!");
                    Toast.makeText(DeletarActivity.this, "Sua conta foi deletada", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(DeletarActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);

                } else {
                    // Erro caso a resposta não seja bem-sucedida
                    Toast.makeText(DeletarActivity.this, "Não foi possível deletar a conta", Toast.LENGTH_SHORT).show();
                    Log.e("ListarProdutos", "Erro ao obter produtos: " + response.code() + " - " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("Deletar Activity", "API RESPONSE: " + t);
                Toast.makeText(DeletarActivity.this, "Não foi possível deletar a conta", Toast.LENGTH_SHORT).show();
            }
        });

    }
}