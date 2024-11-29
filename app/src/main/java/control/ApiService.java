package control;
import java.util.List;

import model.Cliente;
import model.DTO.ClienteDTO;
import model.DTO.LoginDTO;
import model.DTO.UpdateDTO;
import model.Endereco;
import model.Produto;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiService  {
    @POST("Clientes/login")
    Call<Cliente> fazerLogin(@Body LoginDTO login);

    @POST("Clientes/registrar")
    Call<Cliente> realizarCadastro(@Body ClienteDTO registro);

    @GET("Produto")
    Call<List<Produto>> getProdutos();

    @PUT("Clientes/{email}")
    Call<Cliente> atualizarCliente(@Body UpdateDTO atualizar, @Path("email") String email);

    @GET("Endereco/{id}")
    Call<Endereco> getEndereco(@Path("id") int id);

    @DELETE("Clientes/{id}")
    Call<Void> deleteCliente(@Path("id") int id);


}
