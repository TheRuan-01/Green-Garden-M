# Documentação de Requisitos de Funcionalidades - **PedidoActivity**

## Visão Geral

A `PedidoActivity` é a tela onde o usuário pode revisar o conteúdo do seu carrinho de compras antes de finalizar a compra. Ela exibe a lista de itens no carrinho, calcula o total da compra, e permite que o usuário selecione o método de pagamento e o endereço para entrega. Além disso, o usuário pode navegar para outras telas do aplicativo, como a tela de produtos, perfil ou carrinho.

## Funcionalidades

### 1. **Exibição dos Itens no Carrinho**
   - **Objetivo**: Mostrar todos os itens que o usuário adicionou ao carrinho.
   - **Funcionamento**:
     - A tela exibe a lista de produtos no carrinho utilizando um componente `RecyclerView` para mostrar cada item, incluindo o nome, quantidade e preço.
     - A lista de produtos é obtida do objeto `Carrinho` e é gerenciada por um `PedidoAdapter` que organiza os dados para exibição.

### 2. **Cálculo do Total da Compra**
   - **Objetivo**: Exibir o valor total dos itens no carrinho.
   - **Funcionamento**:
     - O total da compra é calculado com base nos itens presentes no carrinho e é exibido na tela no formato monetário (R$).
     - O total é atualizado automaticamente ao carregar a tela.

### 3. **Seleção do Método de Pagamento**
   - **Objetivo**: Permitir que o usuário escolha como deseja pagar pela compra.
   - **Funcionamento**:
     - O usuário pode selecionar entre três métodos de pagamento: "Cartão de Crédito", "Boleto Bancário" e "Pix".
     - O método de pagamento é selecionado através de um `Spinner`, que exibe as opções para o usuário escolher.
     - Após a seleção, a aplicação pode utilizar o método escolhido para processar o pagamento, embora neste momento só haja um feedback de finalização da compra.

### 4. **Seleção do Endereço para Entrega**
   - **Objetivo**: Permitir que o usuário selecione o endereço onde a compra será entregue.
   - **Funcionamento**:
     - O endereço do cliente é recuperado a partir do servidor usando a API e exibido em um `Spinner`, permitindo que o usuário escolha um endereço específico para a entrega.
     - A seleção do endereço é feita pelo usuário ao escolher uma das opções disponibilizadas no `Spinner`.

### 5. **Botão de Finalização de Compra**
   - **Objetivo**: Permitir que o usuário finalize sua compra após revisar os itens no carrinho e selecionar os métodos de pagamento e endereço.
   - **Funcionamento**:
     - Após o usuário selecionar as opções de pagamento e endereço, ele pode clicar no botão "Finalizar Compra", o qual exibe uma mensagem de confirmação de que a compra foi finalizada com sucesso.
     - A funcionalidade de finalização de compra ainda não interage com um sistema de pagamento real, apenas mostra a mensagem.

### 6. **Navegação entre Telas**
   - **Objetivo**: Permitir que o usuário navegue para outras telas do aplicativo.
   - **Funcionamento**:
     - O usuário pode navegar para a tela inicial de produtos, para a tela de perfil ou para a tela do carrinho de compras.
     - A navegação é realizada ao clicar nos ícones correspondentes, que são configurados para abrir as telas apropriadas com base no tipo de ícone clicado (home, perfil, carrinho).

### 7. **Configuração dos Spinners**
   - **Objetivo**: Configurar os spinners para permitir que o usuário selecione as opções de pagamento e endereço.
   - **Funcionamento**:
     - O `Spinner` para método de pagamento exibe uma lista de opções de pagamento (Cartão de Crédito, Boleto Bancário, Pix).
     - O `Spinner` para endereço exibe um ou mais endereços do cliente, que são recuperados a partir do servidor.
     - Ambos os spinners são configurados com adaptadores que definem como as opções são exibidas e como o aplicativo reage quando o usuário seleciona uma opção.

## Requisitos Técnicos

### 1. **Integração com a API**
   - O aplicativo se comunica com a API para obter o endereço do cliente a partir do servidor.
   - A resposta da API é tratada e o endereço retornado é exibido no `Spinner` de endereço.

### 2. **Cálculo de Total de Compra**
   - O total da compra é calculado utilizando o valor dos itens presentes no carrinho e exibido na interface do usuário.

### 3. **Feedback ao Usuário**
   - O aplicativo exibe mensagens de feedback ao usuário:
     - "Compra finalizada!" quando o usuário clica no botão para finalizar a compra.
     - Mensagens de navegação e log, caso haja falhas ou ações importantes a serem registradas.

### 4. **Navegação com `Intent`**
   - O aplicativo usa `Intent` para navegar entre diferentes telas:
     - `ListarProdutosActivity` (tela inicial de produtos).
     - `UsuarioActivity` (perfil do usuário).
     - `CarrinhoActivity` (tela de carrinho).

### 5. **Gerenciamento de Dados do Cliente**
   - O cliente é passado de uma tela para outra através de `Intent` usando o método `putExtra`, o que garante que os dados do cliente (como endereço) possam ser acessados nas diferentes telas.

## Fluxo de Interação

1. **Tela de Pedido**: O usuário chega à tela de pedidos após adicionar itens ao carrinho.
2. **Exibição do Carrinho**: Os itens do carrinho são exibidos em um `RecyclerView`.
3. **Seleção de Pagamento e Endereço**: O usuário escolhe seu método de pagamento e o endereço para entrega.
4. **Finalização da Compra**: O usuário clica em "Finalizar Compra", recebendo uma mensagem de confirmação.
5. **Navegação**: O usuário pode navegar para a tela inicial, perfil ou carrinho utilizando os ícones de navegação.

---

Essa documentação descreve as funcionalidades da tela `PedidoActivity`, detalhando as interações do usuário com a interface e o comportamento do aplicativo em cada etapa da finalização do pedido.
