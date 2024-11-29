# Documentação de Requisitos de Funcionalidades - **MainActivity**

## Visão Geral

A `MainActivity` é a tela de login do aplicativo, onde o usuário pode acessar sua conta ou se registrar. Ela permite que o usuário insira suas credenciais (usuário e senha), faça login e, em caso de sucesso, seja redirecionado para a tela de produtos. Caso o usuário ainda não tenha uma conta, ele pode acessar a tela de registro.

## Funcionalidades

### 1. **Login do Usuário**
   - **Objetivo**: Permitir que o usuário faça login no aplicativo utilizando suas credenciais (usuário e senha).
   - **Funcionamento**:
     - O usuário insere seu e-mail e senha nos campos correspondentes.
     - Quando o botão de login é clicado, os dados são enviados para o servidor por meio de uma requisição para a API.
     - A resposta da API é verificada:
       - Se a resposta for bem-sucedida, o usuário é autenticado e um objeto `Cliente` é retornado.
       - O usuário é então redirecionado para a tela de listagem de produtos (`ListarProdutosActivity`).
       - Caso as credenciais estejam incorretas, uma mensagem de erro é exibida informando o problema.
     - Se houver falha na comunicação com o servidor (problemas de rede, por exemplo), o usuário é notificado com uma mensagem de erro.

### 2. **Navegação para Registro**
   - **Objetivo**: Permitir que o usuário navegue para a tela de registro, caso ainda não tenha uma conta.
   - **Funcionamento**:
     - O botão de "Registrar-se" redireciona o usuário para a tela de registro (`RegistroActivity`), onde ele pode criar uma nova conta.
     - A navegação é realizada através de um `Intent`, que abre a nova tela sem perder o estado da aplicação atual.

### 3. **Validação de Campos de Login**
   - **Objetivo**: Garantir que ambos os campos de e-mail e senha sejam preenchidos antes de tentar realizar o login.
   - **Funcionamento**:
     - Antes de enviar os dados para a API, o aplicativo verifica se o usuário preencheu tanto o campo de e-mail quanto o de senha.
     - Se algum dos campos estiver vazio, uma mensagem de aviso é exibida solicitando que o usuário preencha ambos os campos.

### 4. **Ajuste de Layout para Barras do Sistema**
   - **Objetivo**: Ajustar o conteúdo da tela para evitar que ele seja coberto pelas barras do sistema (status bar, navigation bar).
   - **Funcionamento**:
     - A tela faz ajustes de layout para garantir que o conteúdo seja visível, independentemente do dispositivo ou da configuração de barras do sistema.
     - Isso é feito utilizando o método `ViewCompat.setOnApplyWindowInsetsListener`, que ajusta o preenchimento da tela para se adaptar às barras do sistema.

## Requisitos Técnicos

### 1. **Integração com a API**
   - O aplicativo se comunica com o servidor por meio de requisições HTTP usando a biblioteca Retrofit.
   - A requisição de login é realizada utilizando um objeto `LoginDTO`, que contém o e-mail e a senha do usuário.
   - A resposta da API retorna um objeto `Cliente`, que é utilizado para autenticar o usuário.

### 2. **Manipulação de Dados do Cliente**
   - Após um login bem-sucedido, o aplicativo cria um objeto `Cliente` a partir da resposta da API.
   - Este objeto `Cliente` é passado para a próxima tela (ListarProdutosActivity) através de um `Intent`, garantindo que o estado do usuário seja mantido ao navegar para a tela de produtos.

### 3. **Mensagens de Feedback ao Usuário**
   - O aplicativo utiliza o `Toast` para exibir mensagens de sucesso ou erro para o usuário:
     - Mensagens de sucesso são exibidas quando o login é bem-sucedido.
     - Mensagens de erro são exibidas quando o login falha devido a credenciais incorretas ou problemas de conexão.

### 4. **Navegação Entre Telas**
   - O aplicativo utiliza `Intent` para navegar entre as telas de login, registro e listagem de produtos.
   - A navegação para a tela de produtos é feita com flags para limpar a pilha de atividades anteriores e garantir que o usuário não possa voltar à tela de login após fazer o login.

## Fluxo de Interação

1. **Tela de Login**: O usuário acessa a tela de login onde insere suas credenciais (usuário e senha).
2. **Verificação de Campos**: O aplicativo verifica se os campos de login estão preenchidos.
3. **Envio de Dados para API**: O aplicativo envia as credenciais para a API.
4. **Resposta da API**:
   - Se o login for bem-sucedido, o usuário é autenticado e redirecionado para a tela de produtos.
   - Se as credenciais estiverem erradas, uma mensagem de erro é exibida.
   - Se houver falha na comunicação, uma mensagem de erro é exibida.
5. **Acesso ao Registro**: Caso o usuário não tenha uma conta, ele pode clicar no botão de registro e ser redirecionado para a tela de cadastro.

---

Essa documentação descreve as funcionalidades da tela de login (`MainActivity`), explicando como ela permite que o usuário faça login, se registre e como o aplicativo lida com a comunicação com a API, validação de campos e navegação entre as telas.
