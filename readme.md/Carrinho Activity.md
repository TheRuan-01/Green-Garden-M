# **Documentação de Requisitos - Classe CarrinhoActivity**

## **Objetivo**
A classe `CarrinhoActivity` é responsável por exibir a tela do carrinho de compras no aplicativo **GreenGarden**, permitindo ao usuário visualizar os itens adicionados, o total da compra e navegar entre as telas de produto, perfil e pedido.

---

## **Funcionalidades**

### **1. Exibir Itens no Carrinho**
- A tela exibe os itens que o usuário adicionou ao carrinho de compras, incluindo o nome, quantidade e preço de cada produto.
- A lista de itens é exibida utilizando um **RecyclerView**, que é uma lista dinâmica, facilitando o gerenciamento e visualização de diversos itens.

---

### **2. Exibir Total do Carrinho**
- O total da compra é calculado automaticamente e mostrado ao usuário. Esse valor é atualizado sempre que há uma alteração no carrinho (como adicionar ou remover itens).
- O total é exibido em formato monetário (exemplo: "Total: R$ 50,00").

---

### **3. Navegação entre Telas**
A classe configura três botões de navegação:
- **Ícone Home**: Redireciona o usuário para a tela de listagem de produtos (`ListarProdutosActivity`).
- **Ícone Carrinho**: Redireciona o usuário de volta para a tela do carrinho (`CarrinhoActivity`).
- **Ícone Perfil**: Redireciona o usuário para a tela de perfil (`UsuarioActivity`).

Além disso, há um botão de "Finalizar Compra" que redireciona o usuário para a tela de finalização do pedido (`PedidoActivity`), passando as informações do cliente.

---

### **4. Atualização de Total**
- Quando o total do carrinho é atualizado (por exemplo, se um item for removido ou adicionado), o valor exibido na interface do usuário é alterado automaticamente para refletir o novo total.

---

### **5. Configuração do Carrinho**
- A classe recupera a lista de itens no carrinho a partir de uma classe `Carrinho`, e configura um **Adapter** para gerenciar a exibição dos itens na interface (RecyclerView).
- O adapter (`CarrinhoAdapter`) é responsável por gerar as visualizações de cada item de acordo com a lista de itens do carrinho.

---

### **6. Comunicação com o Cliente**
- A classe `CarrinhoActivity` recebe um objeto `Cliente` através de uma `Intent` que é passada da tela anterior.
- O cliente contém informações como nome, e-mail e outros dados, os quais podem ser usados em outras telas do aplicativo, como a tela de perfil ou a de finalização de pedido.

---

## **Requisitos**

### **Requisitos Funcionais**
1. **Exibição dos Itens do Carrinho**: A tela deve mostrar todos os itens do carrinho, com informações detalhadas de cada produto (nome, preço, quantidade).
2. **Exibição e Atualização do Total**: O total do carrinho deve ser mostrado e atualizado sempre que o carrinho sofrer alterações.
3. **Navegação entre Telas**: O usuário deve ser capaz de navegar entre as telas de **Home**, **Carrinho**, **Perfil** e **Pedido** utilizando os botões de navegação.
4. **Finalizar Compra**: O botão de "Finalizar Compra" deve redirecionar o usuário para a tela de finalização do pedido.
5. **Recebimento de Dados do Cliente**: O cliente deve ser passado via `Intent` de uma tela anterior e utilizado para navegação nas telas.

### **Requisitos Não Funcionais**
1. **Desempenho**: A tela deve ser capaz de exibir grandes quantidades de itens no carrinho sem causar lentidão ou travamentos.
2. **Interatividade**: A interface deve ser intuitiva e responder rapidamente às interações do usuário (como navegação entre telas e atualização do total).

---

## **Tecnologias Utilizadas**
- **Android SDK**: Para criar a interface do usuário e gerenciar os componentes como RecyclerView e Buttons.
- **Intent**: Para passar dados entre telas, como o objeto `Cliente`.
- **RecyclerView**: Para exibir os itens do carrinho de forma eficiente e dinâmica.
- **EdgeToEdge**: Para permitir que a interface ocupe toda a tela do dispositivo.

---

## **Fluxo de Navegação**

1. O usuário acessa a tela do carrinho de compras, onde são exibidos todos os itens que ele adicionou.
2. O total do carrinho é exibido no topo da tela e é atualizado automaticamente conforme os itens são alterados.
3. O usuário pode navegar para as telas de **Home**, **Carrinho** ou **Perfil** clicando nos ícones correspondentes.
4. Quando o usuário clica em **Finalizar Compra**, ele é redirecionado para a tela de **Pedido**, onde pode concluir a compra.

---

## **Próximos Passos**

- **Implementação de Ações no Carrinho**: A classe atualmente implementa métodos para atualizar ou remover itens do carrinho, mas esses métodos estão configurados para implementação futura.
