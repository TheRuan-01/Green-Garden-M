package ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.greengarden.R;

import control.Retrofit;
import model.Cliente;
import model.DTO.ClienteDTO;
import model.DTO.EnderecoDTO;
import model.DTO.LoginDTO;
import model.EdgeToEdge;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistroActivity extends AppCompatActivity {
    private EditText txtNome, txtEmail, txtSenha, txtTelefone, txtRua, txtNumero, txtComplemento,
    txtCep, txtCidade, txtEstado, txtCpf;
    private Button button;
    private EnderecoDTO enderecoDTO;
    private ClienteDTO clienteDTO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registro);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        instanciarViews();
        button.setOnClickListener((a) -> {
            EnderecoDTO enderecoDTO = new EnderecoDTO(
                    txtRua.getText().toString(),
                    txtNumero.getText().toString(),
                    txtComplemento.getText().toString(),
                    txtCep.getText().toString(),
                    txtCidade.getText().toString(),
                    txtEstado.getText().toString()
            );
            ClienteDTO clienteDTO = new ClienteDTO(
                    txtNome.getText().toString(),
                    txtCpf.getText().toString(),
                    txtTelefone.getText().toString(),
                    txtEmail.getText().toString(),
                    txtSenha.getText().toString(),
                    enderecoDTO
            );
            fazerCadastro(clienteDTO);
        });

    }

    private void instanciarViews(){
        button = ((Button)findViewById(R.id.btnConfirmarCadastro));
        txtNome = ((EditText) findViewById(R.id.textNome));
        txtEmail = ((EditText) findViewById(R.id.textEmail));
        txtSenha = ((EditText) findViewById(R.id.textSenha));
        txtTelefone = ((EditText) findViewById(R.id.textTelefone));
        txtCpf = ((EditText) findViewById(R.id.textCPF));
        txtRua = ((EditText) findViewById(R.id.textRua));
        txtNumero = ((EditText) findViewById(R.id.textNumero));
        txtComplemento = ((EditText) findViewById(R.id.textComplemento));
        txtCep = ((EditText) findViewById(R.id.textCep));
        txtCidade = ((EditText) findViewById(R.id.textCidade));
        txtEstado = ((EditText) findViewById(R.id.textEstado));

    }

    private void fazerCadastro(ClienteDTO dto){

        Log.d("Registro Activity", "Chamando api...");
        Retrofit.getApiService().realizarCadastro(dto).enqueue(new Callback<Cliente>() {
            @Override
            public void onResponse(Call<Cliente> call, Response<Cliente> response) {
                if(response.isSuccessful() && response.body() != null){
                    Toast.makeText(RegistroActivity.this, "Registro feito com sucesso!", Toast.LENGTH_SHORT).show();
                    Log.d("Registro Activity", "API Response: " + response.code() + " - " + response.message());
                    Intent intent = new Intent(RegistroActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(RegistroActivity.this, "Email já utilizado, tente novamente", Toast.LENGTH_SHORT).show();
                    Log.d("Registro Activity", "API Response: " + response.code() + " - " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Cliente> call, Throwable t) {
                Toast.makeText(RegistroActivity.this, "Erro de conexão com a internet, tente novamente", Toast.LENGTH_SHORT).show();
                Log.d("Registro Activity", "API Response: " + t);
            }
        });
    }

}

