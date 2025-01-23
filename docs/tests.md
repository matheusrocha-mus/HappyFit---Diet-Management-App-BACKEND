## 7. Testes do software

Nesta sessão são apresentados os testes realizados no software implementado:

_Deve ser utilizada a abordadem caixa preta, que tem por objetivo verificar a conformidade do software com os requisitos funcionais e não-funcionais do sistema._

_Se quiser conhecer um pouco mais sobre os tipos de teste de software, leia o documento [Teste de Software: Conceitos e tipos de testes](https://blog.onedaytesting.com.br/teste-de-software/)._

_Nesta seção o grupo deverá documentar os testes de software que verificam a correta implementação dos requisitos funcionais e não-funcionais do software. Preencha a tabela com o plano de testes. Para cada Caso de Teste (CT), associe quais testes são responsáveis por verificar a conformidade do caso de teste. Veja a tabela de exemplo._


**Caso de Teste** | **CT01 - Cadastrar usuário**
 :--------------: | ------------
**Procedimento**  | Cadastrar novo usuário. |
**Dados de entrada** | Inserção de dados válidos no formulário de cadastro. |
**Resultado esperado**| Dado cadastrado com sucesso|
**Resultado obtido** | Dado cadastrado com sucesso. |


**Caso de Teste** | **CT02 - Cadastrar usuário já existente**
 :--------------: | ------------
**Procedimento**  | Cadastrar usuário já existente.
**Dados de entrada** | Inserção de dados válidos com nome de usuário já existente no banco.
**Resultado esperado**| Erro ao fazer cadastro
**Resultado obtido** | Erro ao fazer cadastro

**Caso de Teste** | **CT03 - Não preencher campos no cadastro de perfil alimentar**
 :--------------: | ------------
**Procedimento**  | Cadastrar usuário já existente.Não preencher campos no cadastro de perfil alimentar
**Dados de entrada** | Em branco no campo idade e peso
**Resultado esperado**| Não permissão de dados em branco
**Resultado obtido** | Formulário não permite seguir para a próxima etapa

**Caso de Teste** | **CT04 - Deletar alimento do diário**
 :--------------: | ------------
**Procedimento**  | Deletar alimento do diário
**Dados de entrada** | Exclusão de um alimento já adicionado em uma refeição do diário
**Resultado esperado**| Atualização dos macronutrientes referente ao alimento excluído
**Resultado obtido** | Macronutrientes atualizados

**Caso de Teste** | **CT05 - Adicionar alimento a uma refeição do diário**
 :--------------: | ------------
**Procedimento**  |  Adicionar alimento a uma refeição do diário
**Dados de entrada** | Adicionar um alimento em uma refeição do diário
**Resultado esperado**| Atualização dos macronutrientes referente ao alimento Adicionado
**Resultado obtido** | Macronutrientes atualizados

**Caso de Teste** | **CT06 - Adicionar refeição a Dieta**
 :--------------: | ------------
**Procedimento**  | Adicionar refeição a dieta e adicionar seus alimentos
**Dados de entrada** | Adicionar uma refeiçã, nomea-la e adicionar alimentos dessa refeição
**Resultado esperado**| Atualização dos macronutrientes da dieta referente a refeição adicionada
**Resultado obtido** | Atualização dos macronutrientes da Dieta 

**Caso de Teste** | **CT07 - Deletar refeição da dieta**
 :--------------: | ------------
**Procedimento**  |  Deletar uma refeição da Dieta
**Dados de entrada** | Deletar uma refeição da dieta
**Resultado esperado**| Atualização dos macronutrientes da dieta referente a refeição deletada
**Resultado obtido** | Macronutrientes atualizados

**Caso de Teste** | **CT08 - Visualização do diário do Usuário pelo nutricionista**
 :--------------: | ------------
**Procedimento**  | Verificar se o nutricionista consegue visualizar o diário atualizado do seu cliente após o cliente preencher o diário
**Dados de entrada** | Preenchimento do diário por parte do usuário acompanhado por nutricionista
**Resultado esperado**| Visualização do nutricionista dos dados inseridos no diário
**Resultado obtido** | Nutricionista consegue visualizar os dados inseridos no diário

**Caso de Teste** | **CT09 - Visualização do Perfil Alimentar por parte do usuário que recebe acompanhamento inserido pelo nutricionista**
 :--------------: | ------------
**Procedimento**  | Verificar se o usuário consegue visualizar o Perfil Alimentar inserido pelo seu nutricionista
**Dados de entrada** | Preenchimento do perfil alimentar por parte do nutricionista para o seu usuário
**Resultado esperado**| Visualização do perfil alimentar no seu login
**Resultado obtido** | Usuário consegue visualizar o seu perfil com seu login

## Avaliação dos Testes de Software

A partir dos casos de teste elencados acima podemos observar que as principais funcionalidades propostas pelo grupo estão funcionando da maneira esperada. Ao londo da implementação foram feitos diversos testes que não foram documentados, mas apenas com esses testes foi possível o grupo chegar a implementaçaõ do jeito que o grupo gostaria.
