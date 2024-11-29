# Documento de Requisitos de Funcionalidades - Classe Retrofit

## **1. Contexto e Objetivo**

A classe `Retrofit` apresentada tem como objetivo facilitar a comunicação entre um aplicativo Android e uma API desenvolvida em ASP.NET.  

Uma **API** (Interface de Programação de Aplicações) é um conjunto de regras e padrões que permite que diferentes sistemas de software se comuniquem.  

Neste caso, a API funciona como um intermediário entre o aplicativo Android e o banco de dados. Por meio dela, o aplicativo pode buscar, criar, atualizar ou deletar dados no banco.

O **Retrofit** é uma biblioteca popular no Android para realizar requisições HTTP (como GET, POST, PUT, DELETE) à API de forma eficiente e simplificada.

---

## **2. Funcionamento Geral da Classe**

A classe `Retrofit` é uma configuração básica para se conectar à API. Ela cria um cliente HTTP configurado para enviar e receber dados no formato JSON (**JavaScript Object Notation**), que é uma das formas mais comuns de comunicação entre sistemas.

---

## **3. Funcionalidades e Responsabilidades da Classe**

1. **Definir o Endereço Base da API**  
   - A variável `URL` contém o endereço do servidor onde a API está hospedada. Neste caso, `http://10.0.2.2:5003/api/` é utilizado para acessar a API localmente em um ambiente de emulador Android.

2. **Configurar o Cliente HTTP (`Retrofit`)**  
   - A classe cria uma instância do Retrofit apenas uma vez (padrão Singleton). Isso economiza recursos e evita múltiplas configurações desnecessárias.
   - `GsonConverterFactory.create()` define que os dados enviados e recebidos serão automaticamente convertidos entre objetos Java e JSON.

3. **Criar o Serviço da API (`ApiService`)**  
   - A classe usa o Retrofit para criar uma instância de `ApiService`, uma interface onde são definidas as operações (como buscar dados ou criar um novo registro). Essa interface deve ser criada separadamente.

4. **Fornecer o Serviço da API**  
   - O método `getApiService()` retorna a instância de `ApiService` para que outras partes do código possam utilizá-la para interagir com a API.

---

## **4. Exemplos de Uso**

### **1. Definir a Interface `ApiService`**  
Antes de usar o Retrofit, você precisa criar uma interface que descreva as chamadas da API que deseja realizar.  

Exemplo:  

```java
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {
    @GET("usuarios") // Busca todos os usuários
    Call<List<Usuario>> getUsuarios();

    @GET("usuarios/{id}") // Busca um usuário por ID
    Call<Usuario> getUsuarioById(@Path("id") int id);
}
```
## **2. Usar a Classe Retrofit**

Com o `ApiService` definido, você pode usar o Retrofit para realizar chamadas à API:

```java
ApiService apiService = Retrofit.getApiService();

// Exemplo: Buscar todos os usuários
apiService.getUsuarios().enqueue(new Callback<List<Usuario>>() {
    @Override
    public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
        if (response.isSuccessful()) {
            List<Usuario> usuarios = response.body();
            // Processa a lista de usuários recebida
        }
    }

    @Override
    public void onFailure(Call<List<Usuario>> call, Throwable t) {
        // Lida com erros de conexão
    }
});
