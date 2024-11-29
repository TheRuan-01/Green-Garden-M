# Documentação de Requisitos de Funcionalidades - **RegistroActivity**

## Visão Geral

A `RegistroActivity` é a tela de cadastro de um novo cliente no aplicativo. Nela, o usuário insere informações como nome, CPF, telefone, email, senha e endereço. Após preencher os campos, o usuário pode confirmar o cadastro, e o sistema realizará a validação dos dados e enviará para o servidor. Se o cadastro for bem-sucedido, o usuário será redirecionado para a tela principal do aplicativo.

## Funcionalidades

### 1. **Preenchimento do Formulário de Cadastro**
   - **Objetivo**: Permitir que o usuário insira suas informações pessoais e de endereço para realizar o cadastro.
   - **Funcionamento**:
     - O usuário preenche campos como nome, CPF, telefone, email, senha e endereço (rua, número, complemento, CEP, cidade e estado) através de campos de texto.
     - O sistema utiliza `EditText` para capturar os dados inseridos pelo usuário.

### 2. **Confirmação do Cadastro**
   - **Objetivo**: Enviar os dados do formulário para o servidor para realizar o cadastro do cliente.
   - **Funcionamento**:
     - Após preencher todos os campos, o usuário clica no botão "Confirmar Cadastro".
     - Os dados inseridos no formulário são agrupados em dois objetos: `EnderecoDTO` e `ClienteDTO`.
     - O sistema então chama a função `fazerCadastro()`, que envia os dados para o servidor usando a API.

### 3. **Validação de Cadastro**
   - **Objetivo**: Garantir que o cadastro seja realizado com sucesso ou informar ao usuário sobre erros.
   - **Funcionamento**:
     - O sistema verifica se o cadastro foi bem-sucedido com a resposta da API:
       - **Cadastro Bem-sucedido**: O sistema exibe uma mensagem de sucesso ("Registro feito com sucesso!") e redireciona o usuário para a tela principal do aplicativo.
       - **Erro de Cadastro**: Se o email já estiver em uso, o sistema exibe uma mensagem informando o erro ("Email já utilizado, tente novamente").
     - O redirecionamento para a tela principal ocorre por meio de um `Intent`, que limpa a pilha de atividades para que o usuário não possa voltar à tela de cadastro.

### 4. **Tratamento de Erro de Conexão**
   - **Objetivo**: Informar ao usuário sobre problemas de conexão com a internet.
   - **Funcionamento**:
     - Caso haja uma falha de conexão com a internet durante o envio do cadastro, o sistema exibe uma mensagem de erro ("Erro de conexão com a internet, tente novamente").
     - O erro de conexão é tratado pela função `onFailure()` no callback da API.

### 5. **Ajuste de Interface para Diferentes Tamanhos de Tela**
   - **Objetivo**: Garantir que a interface da tela de cadastro seja exibida corretamente, mesmo em dispositivos com diferentes tamanhos de tela.
   - **Funcionamento**:
     - A tela ajusta o espaçamento (padding) para acomodar corretamente as barras de sistema (como a barra de navegação e a barra de status) usando `ViewCompat.setOnApplyWindowInsetsListener()`.

### 6. **Navegação Após Cadastro**
   - **Objetivo**: Após o sucesso no cadastro, redirecionar o usuário para a tela principal do aplicativo.
   - **Funcionamento**:
     - Se o cadastro for bem-sucedido, o usuário é redirecionado para a tela `MainActivity`.
     - A navegação é feita através de um `Intent` que limpa a pilha de atividades para garantir que o usuário não volte à tela de cadastro.

## Requisitos Técnicos

### 1. **Envio de Dados para o Servidor**
   - Os dados inseridos pelo usuário são enviados para a API por meio de uma requisição `POST` utilizando a biblioteca Retrofit. O `ClienteDTO` e o `EnderecoDTO` são enviados como objetos para realizar o cadastro.

### 2. **Resposta da API**
   - A resposta da API é tratada em dois cenários:
     - **Sucesso**: O cadastro é realizado com sucesso e o usuário é redirecionado para a tela principal.
     - **Erro de Cadastro**: Se o email já estiver registrado, o sistema notifica o usuário sobre isso.

### 3. **Tratamento de Falha de Conexão**
   - A conexão com a internet é verificada automaticamente, e em caso de falha (como a falta de conexão), uma mensagem de erro é exibida ao usuário.

### 4. **Interface Responsiva**
   - A interface da tela de cadastro é adaptada para diferentes dispositivos, garantindo que as barras do sistema (status, navegação) não sobreponham os campos de entrada, ajustando os espaçamentos da tela automaticamente.

## Fluxo de Interação

1. **Tela de Cadastro**: O usuário chega à tela de cadastro, onde preenche seus dados pessoais e de endereço.
2. **Clique em Confirmar Cadastro**: O usuário clica no botão "Confirmar Cadastro" para enviar os dados.
3. **Validação e Resposta da API**:
   - Se o cadastro for bem-sucedido, o usuário é redirecionado para a tela principal e recebe uma mensagem de sucesso.
   - Se houver um erro (como email já registrado), o sistema informa o erro e o usuário pode tentar novamente.
4. **Tratamento de Erros de Conexão**: Caso o usuário não tenha conexão com a internet, o sistema exibe uma mensagem pedindo para tentar novamente mais tarde.

---

Essa documentação descreve as funcionalidades da classe `RegistroActivity`, detalhando como os dados do usuário são processados, enviados ao servidor e como as respostas da API são tratadas para garantir uma experiência do usuário sem interrupções.
