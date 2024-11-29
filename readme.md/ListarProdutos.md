# Documentação de Requisitos de Funcionalidades - **ListarProdutosActivity**

## Visão Geral

A `ListarProdutosActivity` é uma tela do aplicativo que exibe uma lista de produtos disponíveis para o usuário. A lista é apresentada em um formato de grid, permitindo que os produtos sejam visualizados de forma clara e organizada. O usuário pode navegar entre diferentes telas do aplicativo, como a tela de perfil, carrinho e home, através dos ícones de navegação.

## Funcionalidades

### 1. **Exibição de Produtos em Grid**
   - **Objetivo**: O objetivo dessa funcionalidade é mostrar uma lista de produtos disponíveis de maneira organizada em um layout de grid (duas colunas).
   - **Funcionamento**:
     - O aplicativo faz uma requisição para a API para buscar a lista de produtos.
     - Quando os produtos são recebidos com sucesso, eles são exibidos na tela utilizando um `RecyclerView` configurado com um `GridLayoutManager`, que organiza os produtos em duas colunas.
     - Cada item de produto é exibido utilizando um `ProdutoAdapter` que gerencia a exibição dos dados dos produtos.

### 2. **Recuperação de Produtos**
   - **Objetivo**: A função de recuperar os produtos é responsável por buscar os dados da lista de produtos a partir de uma API.
   - **Funcionamento**:
     - A tela chama a API para obter a lista de produtos disponíveis. A requisição é feita de forma assíncrona, ou seja, enquanto a API busca os dados, o aplicativo continua funcionando normalmente.
     - Se a requisição for bem-sucedida, os produtos são carregados na tela.
     - Caso ocorra um erro na requisição (por exemplo, erro de rede ou falha do servidor), o aplicativo exibe uma mensagem de erro ao usuário, informando que não foi possível carregar os produtos.

### 3. **Navegação Entre Telas**
   - **Objetivo**: O aplicativo permite que o usuário navegue entre diferentes telas (Home, Perfil, Carrinho) utilizando ícones de navegação.
   - **Funcionamento**:
     - Três ícones de navegação são exibidos na tela: um ícone de home, um ícone de perfil e um ícone de carrinho.
     - Quando o usuário clica em um desses ícones, o aplicativo navega para a tela correspondente:
       - O ícone de home leva o usuário para a tela de listar produtos.
       - O ícone de perfil leva o usuário para a tela de perfil.
       - O ícone de carrinho leva o usuário para a tela do carrinho.
     - O `Intent` é usado para passar o objeto `Cliente` para as outras telas, garantindo que o usuário esteja autenticado durante a navegação.

### 4. **Tratamento de Erros de Conexão**
   - **Objetivo**: Garantir que o usuário receba feedback adequado caso haja problemas ao carregar os produtos.
   - **Funcionamento**:
     - Se a conexão com a API falhar ou se a resposta da API não for bem-sucedida, o aplicativo exibe uma mensagem de erro ao usuário.
     - O erro pode ser devido a problemas de rede ou a uma falha no servidor da API, e o usuário será notificado com uma mensagem informando sobre o erro de conexão.

### 5. **Ajuste de Layout para Barras do Sistema**
   - **Objetivo**: Ajustar o layout para que ele fique corretamente posicionado mesmo com a presença das barras do sistema (status bar e navigation bar).
   - **Funcionamento**:
     - A tela ajusta o conteúdo para garantir que ele não seja sobreposto pelas barras do sistema, utilizando o método `ViewCompat.setOnApplyWindowInsetsListener`.
     - O conteúdo da tela é redimensionado para que o layout fique visível e funcional em dispositivos com diferentes tamanhos de tela e tipos de barras do sistema.

## Requisitos Técnicos

### 1. **Integração com a API**
   - A classe depende da API para recuperar os produtos, utilizando a biblioteca Retrofit para fazer requisições HTTP.
   - A requisição para obter os produtos é feita via `GET` e a resposta é processada para exibir os dados na tela.

### 2. **Exibição de Produtos com RecyclerView**
   - O `RecyclerView` é utilizado para exibir os produtos, configurado com o layout `GridLayoutManager` para exibir dois itens por linha.
   - O `ProdutoAdapter` é responsável por preencher a visualização de cada item do produto dentro do `RecyclerView`.

### 3. **Navegação Entre Telas**
   - O `Intent` é utilizado para navegar entre as telas e passar o objeto `Cliente` como parte dos dados de navegação.
   - As telas de navegação incluem a `ListarProdutosActivity`, `UsuarioActivity` (perfil) e `CarrinhoActivity`.

### 4. **Gestão de Erros**
   - O aplicativo deve lidar com falhas de rede e falhas na API, exibindo mensagens de erro adequadas para o usuário.
   - Mensagens de erro são exibidas utilizando `Toast`.

## Fluxo de Interação

1. **Tela de Listagem de Produtos**: O usuário acessa a tela onde os produtos são listados em um grid.
2. **Carregamento dos Produtos**: O aplicativo faz uma requisição para a API para carregar a lista de produtos.
3. **Exibição dos Produtos**: Após a resposta da API, os produtos são exibidos na tela em um formato de grid.
4. **Navegação entre Telas**: O usuário pode clicar nos ícones de navegação para ir para a tela inicial, perfil ou carrinho.
5. **Tratamento de Erros**: Se a requisição falhar, o usuário será informado com uma mensagem de erro.

---

Essa documentação fornece uma visão geral sobre a funcionalidade da `ListarProdutosActivity`, explicando como o aplicativo exibe os produtos e interage com o usuário.
