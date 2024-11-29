# Documentação de Requisitos de Funcionalidades - **CarrinhoAdapter**

## Visão Geral

A classe `CarrinhoAdapter` é responsável por exibir os itens no carrinho de compras na tela do aplicativo. Ela utiliza um `RecyclerView` para listar os produtos no carrinho, mostrando suas imagens, nome, preço, quantidade e permitindo que o usuário possa interagir com esses itens para alterar a quantidade, remover o item ou visualizar o valor total do carrinho.

## Funcionalidades

### 1. **Exibição dos Itens no Carrinho**
   - **Objetivo**: Mostrar os produtos que foram adicionados ao carrinho, com as informações de nome, preço, quantidade e imagem.
   - **Funcionamento**:
     - A lista de itens do carrinho é recebida pelo adaptador e, para cada item, o layout é preenchido com as informações do produto, como:
       - Nome do produto
       - Preço unitário do produto
       - Quantidade do produto
       - Imagem do produto (usando o Glide para carregar a imagem)
     - O adaptador utiliza o layout `item_carrinho` para exibir cada item.

### 2. **Alteração da Quantidade de um Item**
   - **Objetivo**: Permitir que o usuário aumente ou diminua a quantidade de um item no carrinho.
   - **Funcionamento**:
     - Cada item possui dois botões: um para aumentar a quantidade e outro para diminuir.
     - Quando o botão de aumentar é pressionado, a quantidade do item é incrementada em 1 e a interface é atualizada.
     - Quando o botão de diminuir é pressionado, a quantidade do item é decrementada em 1, mas a quantidade não pode ser menor que 1.
     - O total do carrinho é atualizado sempre que a quantidade de um item é alterada.

### 3. **Remoção de um Item do Carrinho**
   - **Objetivo**: Permitir que o usuário remova um item do carrinho de compras.
   - **Funcionamento**:
     - Cada item possui um botão de "remover" que, quando pressionado, remove o item da lista do carrinho.
     - Após a remoção, a interface é atualizada e o total do carrinho também é recalculado.

### 4. **Atualização do Total do Carrinho**
   - **Objetivo**: Manter o valor total do carrinho atualizado quando a quantidade de itens for alterada ou quando um item for removido.
   - **Funcionamento**:
     - Sempre que a quantidade de um item é alterada ou um item é removido, o valor total do carrinho é recalculado.
     - O método `atualizarTotal` percorre todos os itens no carrinho e calcula o valor total com base no preço e na quantidade de cada item.
     - O total atualizado é enviado para o `OnItemClickListener`, que pode usar esse valor para atualizar a interface do usuário.

### 5. **Uso de Imagens para os Produtos**
   - **Objetivo**: Exibir a imagem de cada produto no carrinho.
   - **Funcionamento**:
     - A imagem de cada item é carregada usando a biblioteca Glide, que permite carregar imagens de URLs ou recursos locais de maneira eficiente.
     - A imagem do produto é exibida ao lado de suas informações, como nome, preço e quantidade.

### 6. **Interação com o Usuário**
   - **Objetivo**: Permitir que o usuário interaja facilmente com os itens do carrinho.
   - **Funcionamento**:
     - Os botões de aumento, diminuição e remoção possuem comportamentos claros e intuitivos.
     - O usuário pode alterar a quantidade ou remover itens do carrinho com simples cliques, e as mudanças são refletidas imediatamente na interface.

## Fluxo de Interação

1. **Exibição dos Itens**: O usuário abre o carrinho e os itens que foram adicionados são exibidos, incluindo as informações como nome, preço, quantidade e imagem.
2. **Alterar Quantidade**: O usuário pode clicar no botão de aumentar ou diminuir para modificar a quantidade de um item. O total do carrinho é atualizado após cada modificação.
3. **Remover Item**: O usuário pode clicar no botão de "remover" para excluir um item do carrinho. O item é removido da lista e o total do carrinho é recalculado.
4. **Atualização do Total**: Sempre que um item é modificado ou removido, o total do carrinho é atualizado e refletido na interface.

## Requisitos Técnicos

### 1. **Integração com a Interface do Usuário**
   - A classe usa um `RecyclerView` para exibir a lista de itens, e cada item é representado por um `ViewHolder` que carrega os dados do produto.
   - O layout de cada item é inflado com o método `onCreateViewHolder` e os dados são preenchidos com o método `onBindViewHolder`.

### 2. **Escuta de Ações do Usuário**
   - A classe possui botões para aumentar, diminuir ou remover itens. As ações do usuário são tratadas dentro do `ViewHolder`, e cada ação aciona a atualização da interface e o cálculo do total.

### 3. **Interface de Comunicação com o Activity**
   - A interface `OnItemClickListener` é usada para comunicar os eventos do carrinho para a Activity. Ela permite que o valor total do carrinho seja atualizado sempre que necessário.

### 4. **Biblioteca Glide**
   - A biblioteca Glide é utilizada para carregar imagens de produtos de maneira eficiente e sem bloquear a interface do usuário. As imagens podem ser de URLs ou recursos locais.

---

Essa documentação descreve as funcionalidades da classe `CarrinhoAdapter`, explicando como o adaptador exibe os itens no carrinho, como o usuário pode interagir com os itens e como o total do carrinho é mantido atualizado. O fluxo de interação e os requisitos técnicos também são abordados para fornecer uma visão clara do funcionamento da classe.
