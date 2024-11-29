# **Documentação de Requisitos - Classe AtualizarActivity**

## **Objetivo**
A classe `AtualizarActivity` é responsável por permitir que os usuários atualizem seus dados pessoais no aplicativo **GreenGarden**. Essa tela é acessada quando o cliente deseja alterar informações como nome, e-mail, senha, telefone ou CPF.

---

## **Funcionalidades**

### **1. Exibir Dados Existentes**
- Quando o usuário acessa a tela de atualização, os campos (nome, e-mail, telefone, CPF e senha) são preenchidos automaticamente com as informações já cadastradas no sistema.
- Isso facilita a edição, pois o usuário não precisa lembrar ou digitar novamente os dados que não deseja modificar.

---

### **2. Permitir Alteração dos Dados**
- O usuário pode alterar qualquer um dos seguintes campos:
  - Nome
  - E-mail
  - Senha
  - Telefone
  - CPF
- Após preencher ou editar os campos, o usuário pode clicar no botão "Salvar Alterações" para salvar as modificações.

---

### **3. Validar Campos**
- O aplicativo não possui uma validação explícita dos campos na interface. No entanto, espera-se que o Retrofit envie os dados para o servidor, e o sistema de backend (API) será responsável por validar os campos, como verificar se o e-mail é válido ou se o CPF está no formato correto.

---

### **4. Atualização no Banco de Dados**
- Ao clicar em "Salvar Alterações", os dados inseridos são enviados para o servidor por meio da API do Retrofit.
- O sistema usa a classe `UpdateDTO` para formatar os dados antes de enviá-los ao backend.
- O Retrofit faz uma chamada assíncrona para o servidor, e a resposta é tratada para exibir uma mensagem de sucesso ou erro ao usuário.

---

### **5. Exibição de Mensagens**
- **Sucesso**: Se a atualização for bem-sucedida, uma mensagem de "Cliente atualizado com sucesso!" será exibida ao usuário. Em seguida, o usuário é redirecionado para a tela principal do aplicativo (`MainActivity`).
- **Erro**: Se ocorrer algum erro durante o processo de atualização (como problemas de conexão ou erro no servidor), uma mensagem de erro será exibida ao usuário, informando sobre a falha.

---

### **6. Navegação Após Atualização**
- Após a atualização bem-sucedida, o usuário é redirecionado para a tela principal (`MainActivity`), e a pilha de atividades é limpa para garantir que o usuário não possa voltar para a tela de atualização usando o botão de voltar.

---

## **Requisitos**

### **Requisitos Funcionais**
1. **Preenchimento Automático**: A tela deve preencher automaticamente os campos com as informações já cadastradas do cliente.
2. **Edição de Dados**: O usuário deve ser capaz de editar os campos de nome, e-mail, senha, telefone e CPF.
3. **Envio de Dados para o Servidor**: O Retrofit deve enviar os dados editados para o servidor, onde a API irá processar e atualizar os dados no banco de dados.
4. **Redirecionamento após Atualização**: Após a atualização bem-sucedida, o usuário deve ser redirecionado para a tela principal.
5. **Mensagens de Feedback**: O aplicativo deve exibir mensagens de sucesso ou erro após o envio da atualização.

### **Requisitos Não Funcionais**
1. **Desempenho**: As requisições à API devem ser realizadas de forma assíncrona para evitar que a interface do usuário trave durante a atualização dos dados.
2. **Erros de Conexão**: Em caso de falha na comunicação com o servidor, o sistema deve exibir uma mensagem adequada para o usuário.

---

## **Tecnologias Utilizadas**
- **Android SDK**: Para criar a interface do usuário e gerenciar os componentes.
- **Retrofit**: Para fazer chamadas HTTP à API do servidor.
- **Toast**: Para exibir mensagens de feedback ao usuário.

---

## **Fluxo de Navegação**

1. O usuário acessa a tela `AtualizarActivity`.
2. Os dados existentes são exibidos nos campos de entrada.
3. O usuário edita os campos e clica no botão "Salvar Alterações".
4. A aplicação envia os dados para a API através do Retrofit.
5. A resposta da API é processada, e uma mensagem de sucesso ou erro é exibida.
6. Se bem-sucedido, o usuário é redirecionado para a tela principal (`MainActivity`).
