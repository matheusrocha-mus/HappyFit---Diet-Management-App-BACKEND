### 3.3.1 Processo 1 – Realizar Login

Processo dedicado a realizar o login do usuário, caso não tenha é necessário realizar o cadastro de usuário.

![PROCESSO 1](../images/login-final.png "Modelo BPMN do Processo 1.")

#### Detalhamento das atividades


### Coletar informações básicas do usuário:

_Informações como nome,sobrenome,e-mail,senha,telefone,tipo de usuário._

### Verificar se o usuário já possui cadastro:


_Se sim, direcionar o usuário para a tela de login._

_Se não, direcionar o usuário para a tela de cadastro do perfil alimentar._


### Tela de login:


_Solicita ao usuário que digite seu e-mail e senha._

_Verifica se as informações digitadas estão corretas._

_Se as informações estiverem corretas, direciona o usuário para a tela principal do sistema._

_Se as informações estiverem incorretas, exibe uma mensagem de erro e solicita ao usuário que tente novamente._

**Atividade 1: Inserir informações de login**

| **Campo**               | **Tipo**        | **Restrições**    | **Valor default** |
| ---                     | ---             | ---               | ---               |
| Email                   | Caixa de texto  |                   |                   |
| Senha                   | Caixa de texto  |                   |                   |

| **Comandos**         |  **Destino**                       | **Tipo**  |
| ---                  | ---                                | ---       |
| Entrar               | Entra com o seu perfil             | (default) |
| Fazer Cadastro       | Começa o processo de cadastro      | (default) |

**Atividade 2: Preencher informações básicas de cadastro**

| **Campo**       | **Tipo**                                | **Restrições**                                                                               | **Valor default** |
| ---             | ---                                     | ---                                                                                          | ---               |
| Nome            | Caixa de texto                          | Somente letras e espaço                                                                      |                   |
| Sobrenome       | Caixa de Texto                          | Somente letras e espaço                                                                      |                   |
| Tipo de Usuário | Seleção Única (Usuário / nutricionista) |                                                                                              |                   |
| Email           | Caixa de texto                          |                                                                                              |                   |
| Telefone        | Caixa de texto                          |                                                                                              |                   |
| Senha           | Caixa de texto                          | Mínimo de 8 caracteres e ao menos uma letra maiúscula, uma minúscula e um carcatere especial |                   |
| Confirmar Senha | Caixa de texto                          | Mínimo de 8 caracteres e ao menos uma letra maiúscula, uma minúscula e um carcatere especial |                   |

| **Comandos**         |  **Destino**          | **Tipo**  |
| ---                  | ---                   | ---       |
| Cadastrar            | Página de login       | (default) |
| Fazer login aqui     | Página de login       | (default) |
