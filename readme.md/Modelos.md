# Documento de Requisitos do Sistema de Carrinho de Compras - Projeto GreenGarden

## 1. Introdução

Este documento tem como objetivo definir os requisitos de funcionalidades do sistema de carrinho de compras do projeto GreenGarden, uma aplicação mobile que visa otimizar o processo de compra e entrega de alimentos, permitindo aos usuários gerenciar seus carrinhos de compras, seus produtos e seus endereços de entrega. A seguir, serão detalhados os requisitos para as classes que representam os componentes essenciais do sistema.

## 2. Visão Geral do Sistema

O sistema do carrinho de compras deve ser capaz de armazenar e gerenciar as informações do cliente, o carrinho de compras, os itens dentro do carrinho, os produtos, os pagamentos e os endereços de entrega. O usuário deverá poder visualizar os produtos no carrinho, modificar suas quantidades, adicionar ou remover produtos, e finalizar o pedido. Além disso, o sistema deve permitir a personalização do endereço de entrega, conforme a escolha do cliente.

## 3. Requisitos Funcionais

### 3.1. Classe `Cliente`

**Descrição**: A classe `Cliente` é responsável por representar um usuário no sistema. Cada cliente possui informações pessoais e dados de contato, e é associado a um ou mais carrinhos.

**Requisitos**:

- O cliente deve ter um identificador único `idCliente`.
- O cliente deve ter nome completo, CPF, telefone, e-mail e senha.
- O sistema deve permitir que o cliente altere suas informações pessoais, com exceção do CPF.
- O cliente pode ter múltiplos carrinhos, mas cada carrinho é associado a um único cliente.
- O cliente também pode ter múltiplos endereços, mas no momento de realizar a compra, deve ser possível escolher um único endereço de entrega.

### 3.2. Classe `Endereco`

**Descrição**: A classe `Endereco` armazena os dados de endereço de um cliente, sendo utilizado no processo de entrega dos produtos comprados.

**Requisitos**:

- O cliente pode ter um ou mais endereços.
- Cada endereço deve ter as informações: rua, número, complemento (opcional), cidade, estado e CEP.
- O cliente poderá selecionar um endereço para a entrega de seus pedidos.
- O sistema deve validar o endereço para garantir que ele esteja completo e correto, caso contrário, o cliente será solicitado a corrigir.

### 3.3. Classe `Carrinho`

**Descrição**: A classe `Carrinho` armazena os itens selecionados pelo cliente para compra. Cada carrinho está associado a um cliente e contém informações sobre os itens, suas quantidades e o status da compra.

**Requisitos**:

- Cada carrinho deve ser identificado por um `idCarrinho` único.
- O carrinho deve conter informações sobre o cliente (idCliente).
- O carrinho deve armazenar uma lista de itens (`ItemCarrinho`), onde cada item contém o nome do produto, quantidade e preço.
- O sistema deve permitir que o cliente adicione ou remova itens do carrinho.
- O carrinho deve ter um status, que pode ser "em andamento", "finalizado" ou "cancelado".
- O sistema deve calcular automaticamente o preço total do carrinho com base nos itens e suas quantidades.

### 3.4. Classe `ItemCarrinho`

**Descrição**: A classe `ItemCarrinho` representa um item dentro do carrinho de compras, associando um produto a uma quantidade e calculando o valor total do item.

**Requisitos**:

- Cada item no carrinho deve ter um identificador único `idItemCarrinho`.
- O item deve conter informações sobre o produto, como nome, preço unitário e quantidade.
- O sistema deve calcular automaticamente o preço total do item multiplicando a quantidade pelo preço unitário.
- O sistema deve permitir que o cliente altere a quantidade de um item ou remova o item do carrinho.

### 3.5. Classe `Produto`

**Descrição**: A classe `Produto` representa um produto disponível para compra no sistema. Cada produto possui informações sobre seu nome, preço, descrição e estoque.

**Requisitos**:

- O produto deve ter um identificador único `idProduto`.
- O produto deve ter um nome, descrição e preço.
- O sistema deve permitir que o produto tenha um estoque definido e atualizado sempre que for comprado.
- O preço do produto pode ser alterado, mas o histórico de alterações deve ser mantido.
- O produto pode ter categorias (ex: bebidas, lanches, sobremesas, etc.).

### 3.6. Classe `Pagamento`

**Descrição**: A classe `Pagamento` representa o processo de pagamento de um pedido, incluindo o método de pagamento escolhido pelo cliente e o status do pagamento.

**Requisitos**:

- O pagamento deve ser associado a um carrinho específico (`idCarrinho`).
- O cliente pode escolher entre diferentes métodos de pagamento, como cartão de crédito, débito ou transferência bancária.
- O sistema deve calcular automaticamente o valor total a ser pago, considerando o valor dos produtos, descontos e taxas de entrega.
- O pagamento deve ter um status, que pode ser "pendente", "autorizado" ou "recusado".
- O sistema deve enviar uma confirmação de pagamento após a finalização da transação.

### 3.7. Classe `Pedido`

**Descrição**: A classe `Pedido` representa um pedido realizado pelo cliente. Ela contém informações sobre o carrinho, o pagamento e o endereço de entrega.

**Requisitos**:

- O pedido deve ser associado a um único cliente e a um único carrinho.
- O sistema deve registrar o status do pedido, que pode ser "em andamento", "enviado", "entregue" ou "cancelado".
- O pedido deve ter um número de identificação único.
- O pedido deve conter informações sobre o endereço de entrega e o pagamento associado.

## 4. Requisitos Não Funcionais

### 4.1. Desempenho

- O sistema deve suportar até 10.000 carrinhos simultâneos.
- O tempo de resposta para a visualização e modificação de um carrinho não pode ultrapassar 2 segundos.

### 4.2. Segurança

- A senha do cliente deve ser armazenada de forma segura, utilizando criptografia.
- O sistema deve garantir que as informações pessoais do cliente sejam protegidas, cumprindo as regulamentações de segurança de dados, como LGPD.

### 4.3. Usabilidade

- O sistema deve ser simples e fácil de usar, com uma interface amigável para navegação pelo carrinho de compras.
- As funcionalidades de adicionar, remover e editar itens no carrinho devem ser intuitivas e rápidas.

### 4.4. Escalabilidade

- O sistema deve ser capaz de escalar horizontalmente, permitindo o aumento de servidores para suportar mais usuários simultâneos sem perder performance.

### 4.5. Integridade de Dados

- O sistema deve garantir a consistência dos dados do carrinho e dos produtos durante todas as operações de adição, remoção ou modificação de itens.
- O sistema deve ter um mecanismo de backup para evitar perda de dados críticos.

## 5. Fluxos de Uso

### 5.1. Cadastro de Cliente

1. O cliente se registra fornecendo nome, e-mail, CPF, telefone e senha.
2. O sistema valida os dados e cria um perfil para o cliente.
3. O cliente pode acessar sua conta utilizando o e-mail e a senha cadastrados.

### 5.2. Criação de Carrinho

1. O cliente inicia a criação de um carrinho ao adicionar produtos ao seu carrinho.
2. O sistema exibe os produtos, suas quantidades e o preço total do carrinho.
3. O cliente pode adicionar ou remover produtos do carrinho até que decida finalizar a compra.

### 5.3. Finalização de Compra

1. O cliente escolhe um endereço de entrega dentre os cadastrados.
2. O sistema calcula o preço final do pedido, incluindo frete e descontos.
3. O cliente confirma a compra e é redirecionado para a tela de pagamento.

### 5.4. Pagamento do Pedido

1. O cliente escolhe um método de pagamento.
2. O sistema processa o pagamento e atualiza o status do pedido.
3. O cliente recebe a confirmação de pagamento, e o pedido é processado para envio.

### 5.5. Histórico de Pedidos

1. O cliente pode visualizar todos os pedidos anteriores, com detalhes dos itens comprados, valores e status do pedido.

## 6. Conclusão

Este documento apresenta os requisitos essenciais para a implementação do sistema de carrinho de compras do projeto GreenGarden. As funcionalidades descritas visam fornecer uma experiência de compra simples e segura para os usuários, ao mesmo tempo que garantem o bom desempenho e escalabilidade do sistema.
