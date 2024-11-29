# Documentação de Requisitos de Funcionalidades - **ProdutoActivity**

## Visão Geral

A `ProdutoActivity` é a tela onde o usuário pode visualizar os detalhes de um produto e adicioná-lo ao seu carrinho de compras. A tela exibe informações como o nome, descrição, preço e imagem do produto. Além disso, o usuário pode navegar para outras telas do aplicativo, como a tela inicial de produtos, perfil ou carrinho, e adicionar o produto ao carrinho de compras.

## Funcionalidades

### 1. **Exibição de Detalhes do Produto**
   - **Objetivo**: Mostrar as informações detalhadas de um produto selecionado.
   - **Funcionamento**:
     - A tela exibe o nome, descrição e preço do produto.
     - A imagem do produto é carregada dinamicamente utilizando a biblioteca `Glide`, que exibe a imagem em alta qualidade e com um placeholder enquanto a imagem real é carregada.
     - As informações são obtidas a partir de um objeto `Produto`, que é passado para a tela via `Intent`.

### 2. **Adição do Produto ao Carrinho**
   - **Objetivo**: Permitir que o usuário adicione o produto ao carrinho de compras.
   - **Funcionamento**:
     - O usuário pode adicionar o produto ao carrinho clicando no botão "Comprar".
     - O produto é adicionado ao carrinho como um item, com quantidade inicial de 1.
     - Se o produto já estiver no carrinho, a quantidade do item é incrementada em 1.
     - Uma mensagem de confirmação ("Produto adicionado ao carrinho") é exibida para o usuário ao adicionar o produto.

### 3. **Navegação entre Telas**
   - **Objetivo**: Permitir que o usuário navegue para outras telas do aplicativo.
   - **Funcionamento**:
     - O usuário pode navegar para a tela inicial de produtos (tela de listagem de produtos) ao clicar no ícone "Home".
     - O usuário pode navegar para a tela de perfil ao clicar no ícone "Perfil".
     - O usuário pode navegar para a tela do carrinho ao clicar no ícone "Carrinho".
     - Para todas as navegações, o objeto `Cliente` é passado para a próxima tela, garantindo que os dados do cliente (como o estado do carrinho e perfil) sejam mantidos.

### 4. **Atualização da Interface com os Detalhes do Produto**
   - **Objetivo**: Exibir os dados do produto de forma clara e organizada.
   - **Funcionamento**:
     - Os campos de texto (nome, descrição e preço) são preenchidos com as informações do produto.
     - A imagem do produto é carregada na interface, proporcionando uma experiência visual rica ao usuário.

### 5. **Feedback ao Usuário**
   - **Objetivo**: Informar o usuário sobre ações realizadas na tela.
   - **Funcionamento**:
     - Quando o produto é adicionado ao carrinho, o usuário recebe um feedback visual através de uma mensagem de `Toast` que diz "Produto adicionado ao carrinho".

## Requisitos Técnicos

### 1. **Exibição Dinâmica de Dados**
   - As informações do produto (nome, descrição, preço e imagem) são carregadas dinamicamente a partir de um objeto `Produto`, que é passado via `Intent` quando o usuário chega à tela.

### 2. **Utilização da Biblioteca Glide**
   - A imagem do produto é carregada de forma eficiente e dinâmica utilizando a biblioteca `Glide`, garantindo que a imagem seja exibida corretamente e com um placeholder enquanto o carregamento está em andamento.

### 3. **Navegação entre Telas com Intent**
   - A navegação entre telas é realizada utilizando `Intent` para passar o objeto `Cliente` de uma tela para outra.
   - Isso garante que o estado do cliente, incluindo dados do carrinho, seja mantido durante a navegação.

### 4. **Gerenciamento de Itens no Carrinho**
   - O gerenciamento dos itens no carrinho é feito pela classe `Carrinho`, que possui métodos para adicionar e verificar a presença de itens.
   - Quando o produto é adicionado ao carrinho, a aplicação verifica se ele já existe e, em caso afirmativo, aumenta a quantidade desse item.

## Fluxo de Interação

1. **Tela do Produto**: O usuário chega à tela do produto, onde pode visualizar as informações do produto, como nome, descrição, preço e imagem.
2. **Adição ao Carrinho**: O usuário clica no botão "Comprar" para adicionar o produto ao carrinho. Se o produto já estiver no carrinho, a quantidade é aumentada em 1.
3. **Feedback Visual**: O usuário recebe um feedback visual indicando que o produto foi adicionado ao carrinho.
4. **Navegação para Outras Telas**: O usuário pode navegar para as telas de "Produtos", "Perfil" e "Carrinho" ao clicar nos ícones correspondentes.

---

Essa documentação descreve as funcionalidades da classe `ProdutoActivity`, detalhando as interações do usuário com a interface e o comportamento do aplicativo em cada etapa da visualização e adição de um produto ao carrinho.
