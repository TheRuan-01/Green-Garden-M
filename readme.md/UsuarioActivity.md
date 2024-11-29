# Documentação de Requisitos de Funcionalidades - **UsuarioActivity**

## Visão Geral

A `UsuarioActivity` é a tela que exibe as informações do perfil do usuário no aplicativo, incluindo dados pessoais como nome, e-mail, telefone, CPF e endereço. Nessa tela, o usuário pode visualizar seus dados e tem a opção de editar ou excluir seu perfil.

## Funcionalidades

### 1. **Exibição dos Dados do Usuário**
   - **Objetivo**: Mostrar as informações do usuário (nome, e-mail, telefone, CPF e endereço) na tela.
   - **Funcionamento**:
     - A tela é preenchida com os dados do usuário assim que a atividade é criada.
     - Esses dados são recebidos através de um objeto `Cliente`, que é passado de outra tela por meio de um `Intent`.
     - O nome, e-mail, telefone e CPF são exibidos em campos de texto (`TextView`).
     - O endereço do usuário é obtido por meio de uma requisição para a API, que retorna o endereço completo do cliente. Esse endereço é exibido na tela após o carregamento.

### 2. **Botão de Edição de Perfil**
   - **Objetivo**: Permitir que o usuário edite seu perfil.
   - **Funcionamento**:
     - O botão "Alterar" na tela permite que o usuário edite suas informações pessoais.
     - Ao clicar nesse botão, o usuário é redirecionado para a tela de edição de perfil (`AtualizarActivity`), onde ele pode atualizar suas informações.
     - As informações do usuário são passadas para a próxima tela através de um `Intent`.

### 3. **Botão de Exclusão de Perfil**
   - **Objetivo**: Permitir que o usuário exclua seu perfil.
   - **Funcionamento**:
     - O botão "Deletar" permite que o usuário exclua sua conta.
     - Ao clicar nesse botão, o usuário é redirecionado para a tela de exclusão de perfil (`DeletarActivity`), onde pode confirmar a exclusão de sua conta.
     - As informações do usuário são passadas para a próxima tela através de um `Intent`.

### 4. **Obtenção e Exibição do Endereço**
   - **Objetivo**: Exibir o endereço completo do usuário.
   - **Funcionamento**:
     - O endereço do usuário é recuperado pela aplicação por meio de uma requisição à API, que usa o `enderecoId` do usuário para buscar as informações.
     - Caso a requisição seja bem-sucedida, o endereço é exibido na tela.
     - Se houver um erro na requisição, uma mensagem de erro é exibida para o usuário, informando que ocorreu um problema ao carregar o endereço.

### 5. **Tratamento de Erros**
   - **Objetivo**: Informar ao usuário caso ocorra um erro ao carregar o endereço ou se houver problemas com a conexão.
   - **Funcionamento**:
     - Se houver um erro na requisição para obter o endereço (como problemas de conexão ou dados não encontrados), uma mensagem de erro é exibida.
     - O sistema utiliza `Toast` para notificar o usuário com a mensagem "Ocorreu um erro" quando a requisição falha.

## Fluxo de Interação

1. **Tela de Perfil**: O usuário abre a tela de perfil e suas informações pessoais são carregadas e exibidas.
2. **Exibição de Dados**: O nome, e-mail, telefone, CPF e endereço do usuário são mostrados na tela.
3. **Clique em "Alterar"**: O usuário pode clicar no botão "Alterar" para ser redirecionado à tela de edição de perfil.
4. **Clique em "Deletar"**: O usuário pode clicar no botão "Deletar" para ser redirecionado à tela de exclusão de perfil.
5. **Carregamento do Endereço**: O endereço do usuário é recuperado da API e exibido na tela. Se houver falha ao carregar o endereço, uma mensagem de erro é mostrada.

## Requisitos Técnicos

### 1. **Recepção dos Dados do Usuário**
   - A `UsuarioActivity` recebe um objeto `Cliente` por meio de um `Intent` enviado pela tela anterior. Esse objeto contém as informações pessoais do usuário, incluindo o endereço.

### 2. **Requisição à API para Obter Endereço**
   - A tela realiza uma chamada à API utilizando Retrofit para obter o endereço completo do usuário. O `enderecoId` do cliente é utilizado para buscar os dados do endereço.
   - A resposta da API é tratada com um callback que atualiza a interface do usuário com o endereço, ou exibe uma mensagem de erro caso a requisição falhe.

### 3. **Navegação entre Telas**
   - Quando o usuário clica no botão "Alterar" ou "Deletar", a navegação é realizada com um `Intent`, que envia os dados do cliente para a tela apropriada (`AtualizarActivity` ou `DeletarActivity`).

### 4. **Exibição de Erros**
   - Em caso de falha na requisição da API ou erro na conexão com a internet, o sistema exibe uma mensagem de erro usando `Toast`.

---

Essa documentação descreve as funcionalidades da classe `UsuarioActivity`, destacando como as informações do usuário são carregadas, exibidas e como o usuário pode editar ou excluir seu perfil, com um fluxo claro de navegação e tratamento de erros.
