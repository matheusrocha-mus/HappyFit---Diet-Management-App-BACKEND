# HappyFit

**Gustavo Delfino Guimarães, delfinoguimaraes1@gmail.com**

**João Pedro Santana Marques, jpsantanmarques2905@gmail.com**

**Júlia Medeiros Silva, julia.medeiros1159@gmail.com**

**Matheus Caetano Rocha, matheuscaetanorocha@gmail.com**

**Rafael Caetano da Silva, rafael.caeta663@gmail.com**

---

Professores:

**Prof. Cleiton Silva Tavares**

**Prof. Ivan Luiz Vieira de Araújo**


---

_Curso de Engenharia de Software, Unidade Praça da Liberdade_

_Instituto de Informática e Ciências Exatas – Pontifícia Universidade Católica de Minas Gerais (PUC MINAS), Belo Horizonte – MG – Brasil_

---

_**Resumo**._

_Na sociedade moderna, a busca por um estilo de vida saudável e esteticamente agradável está em crescimento. A tecnologia democratizou o acesso à informação sobre nutrição, com plataformas online e aplicativos que facilitam o aprendizado sobre macronutrientes. Foi com o objetivo de ajudar pessoas interessadas em alcançar uma vida e forma mais saudáveis que surgiu a ideia do HappyFit._

_A aplicação tem como principal funcionalidade o controle de dietas, com maior enfoque nos macronutrientes. A interface é voltada para três principais tipos de usuários: o usuário que sabe controlar sua própria alimentação e já tem uma base sobre macronutrientes, o usuário que não tem noção de como controlar sua dieta, e o nutricionista, que irá auxiliar o usuário inexperiente a obter esse controle._

---


## 1. Introdução

_Este documento descreve a proposta de desenvolvimento de um site para elaboração e gerenciamento de dietas com foco no controle de macronutrientes, ideal para pessoas que desejam alcançar seus objetivos estéticos, como hipertrofia muscular, definição corporal ou perda de peso._

### 1.1 Contextualização

_Na sociedade moderna, a busca por um estilo de vida saudável e esteticamente agradável se intensifica cada vez mais. Dados da Sociedade Brasileira de Nutrição (SBN) indicam que 70% da população brasileira busca melhorar seus hábitos alimentares, e 40% desejam alcançar objetivos específicos de composição corporal. Essa crescente preocupação com a saúde e a estética impulsiona a demanda por ferramentas que auxiliem na gestão da dieta, especialmente para aqueles que visam o controle de macronutrientes (macro)._

_Com o advento da internet e das tecnologias digitais, o acesso à informação sobre nutrição e saúde se democratizou. Plataformas online, aplicativos e blogs especializados oferecem uma gama de conteúdos sobre alimentação e nutrição, facilitando o aprendizado sobre macronutrientes e suas implicações na saúde e na estética corporal._

_Em paralelo, a indústria alimentícia se adapta à crescente demanda por produtos nutritivos e práticos, expandindo a oferta de alimentos com diferentes perfis nutricionais, como opções com baixo teor de gordura, alto teor de proteínas e enriquecidas com vitaminas e minerais._

_A prática de atividades físicas também acompanha essa tendência, com o aumento do número de pessoas que frequentam academias, parques e espaços públicos para praticar exercícios físicos. Nesse contexto, softwares e aplicativos de nutrição surgem como ferramentas valiosas para auxiliar na gestão da dieta e no controle de macronutrientes._


### 1.2 Problema

_Apesar da ampla oferta de ferramentas digitais para gestão da dieta, ainda há uma carência de soluções que atendam às necessidades específicas de pessoas que desejam alcançar objetivos estéticos através do controle de macronutrientes. As principais dificuldades enfrentadas por esse público incluem:_

• _Excesso de informações: A quantidade de dados disponíveis sobre nutrição e dietas pode ser desanimadora e gerar dúvidas sobre como aplicá-los de forma eficaz._

• _Falta de foco em macros: A maioria das ferramentas de dieta não oferece recursos específicos para o controle preciso de macronutrientes, dificultando o acompanhamento e a otimização da dieta para objetivos estéticos._

• _Dificuldade em montar e gerenciar dietas: O processo de criação, ajuste e acompanhamento de uma dieta personalizada pode ser trabalhoso e demandar tempo, especialmente para quem busca otimizar os resultados._

• _Falta de praticidade: As ferramentas disponíveis geralmente não oferecem uma experiência amigável e intuitiva, tornando o processo de gestão da dieta menos prático e motivador._

### 1.3 Objetivo geral

_Desenvolver um site intuitivo e prático para a elaboração e gerenciamento de dietas com foco no controle de macronutrientes, auxiliando especificamente profissionais de nutrição e pessoas que desejam alcançar seus objetivos estéticos de forma eficiente e motivadora._

#### 1.3.1 Objetivos específicos

_• Permitir a criação e o gerenciamento rápidos e intuitivos de dietas, refeições e alimentos._

_• Fornecer feedback em tempo real sobre a adequação da dieta aos objetivos do usuário, incluindo se ela é saudável e se está em linha com suas metas de calorias e macronutrientes._

_• Exibir em tempo real o total de calorias e macronutrientes das refeições e da dieta, atualizando automaticamente a cada alteração feita pelo usuário._

### 1.4 Justificativas

_• Atender à demanda por uma solução específica para o controle de macronutrientes: O site fornecerá ferramentas direcionadas para as necessidades de pessoas que buscam objetivos estéticos, preenchendo uma lacuna no mercado de ferramentas de nutrição._

_• Facilitar o processo de criação e gerenciamento de dietas: O site automatizará tarefas repetitivas e oferecerá recursos para otimizar o tempo e o esforço dos usuários, tornando a gestão da dieta mais prática e acessível._

_• Promover a educação alimentar: O site fornecerá informações confiáveis e relevantes sobre nutrição e macronutrientes, empoderando os usuários para tomar decisões alimentares saudáveis e alcançar seus objetivos de forma sustentável._

_• Melhorar a qualidade de vida dos usuários: O site auxiliará os usuários a alcançar seus objetivos estéticos de forma saudável e segura, impactando positivamente sua autoestima, confiança e bem-estar geral._

## 2. Participantes do processo

_Principais participantes:_

_• Usuários finais: Os usuários finais do site serão pessoas com idade a partir de 18 anos, de ambos os sexos, que buscam alcançar objetivos estéticos através do controle de macronutrientes. Esse público tende a ter um nível de conhecimento básico sobre nutrição e está disposto a investir tempo e esforço para alcançar seus objetivos._

**Nome:** Ana Clara

**Idade:** 28 anos

**Profissão:** Designer Gráfica

**Objetivo:** Emagrecer 5kg e definir o corpo para o verão

**Nível de conhecimento em nutrição:** Básico (já fez algumas dietas no passado, mas não obteve resultados duradouros)

**Desafios:**

_•Dificuldade em controlar a vontade de comer doces e alimentos processados_

_•Falta de tempo para cozinhar durante a semana_

_•Dificuldade em manter a disciplina e seguir um plano alimentar por longo tempo_


**Necessidades:**

_•Um plano alimentar personalizado e fácil de seguir_

_•Ferramentas para acompanhar o progresso e se manter motivada_

_•Suporte e orientação de profissionais de nutrição_


_• Profissionais de nutrição: Nutricionistas e outros profissionais de saúde poderão utilizar o site para auxiliar na elaboração e acompanhamento de planos alimentares personalizados para seus clientes. O site fornecerá ferramentas para otimizar o trabalho dos profissionais e oferecer um serviço mais completo e eficiente aos seus clientes._

**Nome:** Dr. Carlos Silva

**Especialidade:** Nutrição Clínica e Esportiva

**Experiência:** 10 anos

**Objetivo:** Auxiliar seus clientes a alcançar seus objetivos de forma saudável e sustentável

**Desafios:**

_•Falta de tempo para elaborar planos alimentares personalizados para cada cliente_

_•Dificuldade em acompanhar o progresso dos clientes e fornecer feedback individualizado_

_•Necessidade de se manter atualizado com as últimas pesquisas em nutrição_

**Necessidades:**

_•Ferramentas para otimizar o trabalho e oferecer um serviço mais completo e eficiente aos clientes_

_•Plataforma para se conectar com novos clientes e divulgar seu trabalho_

_•Acesso a recursos e informações atualizadas sobre nutrição_


_Possíveis outros participantes:_

_• Empresas de alimentos: Empresas de alimentos saudáveis e suplementos alimentares podem se beneficiar do site ao promover seus produtos para um público segmentado e engajado em uma alimentação saudável._

_• Pesquisadores: O site pode ser utilizado por pesquisadores para coletar dados sobre hábitos alimentares e comportamentos de pessoas que buscam alcançar objetivos estéticos._

## 3. Modelagem do processo de negócio

### 3.1. Análise da situação atual

Na atualidade, existem alguns aplicativos voltados para o controle de dietas, como o *[Yazio](https://www.yazio.com/pt)* e o *[Growth Dietas e Treinos](https://www.gsuplementos.com.br/app?gad_source=1&gclid=CjwKCAjwzN-vBhAkEiwAYiO7oEYJd6V6KzpCk-EeS1c_pjNOUiyF1BKtR0VlWm9udEPvhd2uz2uRWhoCbboQAvD_BwE
)*. 
Esses aplicativos contabilizam os macronutrientes de cada refeição do usuário conforme os alimentos cadastrados pelo usuário em cada refeição (café, almoço, jantar e lanches). 

Ao início do uso, esses sistemas geralmente fazem uma pesquisa controlando a sua dieta.

Na pesquisa, eles levam em consideração basicamente a sua idade, peso, altura, nível de condicionamento físico e qual o seu objetivo com a dieta (emagrecer, ganhar massa ou manter o peso). Grande parte dos aplicativos é paga e tem muitas informações sobre os alimentos e suas calorias. 

Além disso, algumas aplicações permitem integração com os smartwatches e, além de fazer o controle da sua dieta, elas colhem dados como quantidade de passos diários, duração de sono, oximetria e análise cardíaca, permitindo uma análise detalhada de sua rotina por parte do usuário. Conseguimos verificar também que alguns aplicativos possuem dicas de receitas e têm um cronômetro de jejum intermitente para quem segue esse tipo de dieta, possibilitando colher dados de tempo em jejum, entre outros. 

Visto que são aplicativos voltados para a saúde, concluímos que existem aplicações que priorizam as dietas e outras com foco maior em exercícios físicos, deixando a parte de controle alimentar apenas como um “extra”.

No geral, cada aplicativo tem funcionalidades únicas, mas o que podemos notar é que todos têm controle de alimentação diária e pré-cadastro com as informações necessárias.

### 3.2. Descrição geral da proposta

É proposto o desenvolvimento de um site para elaboração e gerenciamento de dietas com foco no controle de macronutrientes, ideal para pessoas que desejam alcançar seus objetivos estéticos, como hipertrofia muscular, definição corporal ou perda de peso. 

O site permitirá a criação e o gerenciamento rápidos e intuitivos de dietas, refeições e alimentos. O usuário cadastrará os alimentos consumidos ao longo das refeições e a aplicação fornecerá um feedback em tempo real sobre a adequação da dieta aos objetivos do usuário, levando em conta as metas de calorias e macronutrientes estabelecidas.


### 3.3. Modelagem dos processos

[PROCESSO 1 - Login de Usuário](processes/processo-1-realizar-login.md "Detalhamento do Processo 1.")

[PROCESSO 2 - Cadastro de Perfil Alimentar](processes/processo-2-cadastro-de-perfil-alimentar.md "Detalhamento do Processo 2.")

[PROCESSO 3 - Gerenciamento de Dieta](processes/processo-3-gerenciamento-de-dieta.md "Detalhamento do Processo 3.")

[PROCESSO 4 - Diário Alimentar](processes/processo-4-diario-alimentar.md "Detalhamento do Processo 4.")

[PROCESSO 5 - Comparações de Calorias e Macros](processes/processo-5-comparações-de-calorias-e-macros.md "Detalhamento do Processo 5.")

## 4. Projeto da solução

O documento a seguir apresenta o detalhamento do projeto da solução. São apresentadas duas seções que descrevem, respectivamente: modelo relacional e tecnologias.

[Projeto da solução](solution-design.md "Detalhamento do projeto da solução: modelo relacional e tecnologias.")


## 5. Indicadores de desempenho

O documento a seguir apresenta os indicadores de desempenho dos processos.

[Indicadores de desempenho dos processos](performance-indicators.md)


## 6. Interface do sistema

A sessão a seguir apresenta a descrição do produto de software desenvolvido. 

[Documentação da interface do sistema](interface.md)

## 7. Conclusão

_Com o término das sprints e, consequentemente, do desenvolvimento do software, podemos concluir que a ideia inicial de criar um sistema para controle de alimentação com foco em macronutrientes foi um grande sucesso. Este software é capaz de auxiliar diversas pessoas com metas e necessidades nutricionais variadas. A implementação bem-sucedida de todos os processos propostos no início do projeto garante que os usuários desfrutem de uma experiência completa e positiva._

_O HappyFit é um software eficiente para controle de dietas focadas em macronutrientes. Ele permite a criação de dietas personalizadas e monitoramento de refeições. Com uma interface intuitiva e ferramentas visuais, facilita a compreensão do progresso. Suporta a interação entre usuários e nutricionistas, incentivando engajamento e compartilhamento. O design user-friendly e as funcionalidades robustas tornam o HappyFit uma excelente ferramenta tanto para amadores quanto para profissionais de nutrição._

_Durante a execução do projeto de software, todos os membros da equipe puderam aplicar os conhecimentos adquiridos nos dois primeiros períodos do curso de Engenharia de Software da PUC-MG. A utilização de um banco de dados pela primeira vez, especialmente com o uso do Spring Boot, foi um grande desafio. No entanto, com bastante auxílio dos professores e trabalho em equipe, conseguimos superar os principais obstáculos. Analisando o desempenho individual, concluímos que o sucesso do projeto se deve ao trabalho colaborativo, discussões contínuas, sugestões de ideias e boa convivência, resultando na alta qualidade do software desenvolvido._

_Além disso listamos alguns campos que podem ser explorados para a extensibilidade  e melhora do sistema:_
- _Integração com Dispositivos de Saúde: A integração com dispositivos de monitoramento de saúde, como smartwatches e balanças inteligentes, pode fornecer dados adicionais e melhorar o acompanhamento nutricional._
- _Expansão de Funcionalidades: Desenvolver funcionalidades adicionais, como planos de exercícios físicos personalizados e acompanhamento de saúde mental, pode proporcionar uma abordagem mais holística à saúde dos usuários._
- _Análise de Dados: Utilizar ferramentas avançadas de análise de dados para oferecer insights sobre tendências alimentares e comportamentais dos usuários, ajudando nutricionistas a tomar decisões mais informadas._


# APÊNDICES


_Atualizar os links e adicionar novos links para que a estrutura do código esteja corretamente documentada._


## Apêndice A - Código fonte

[Código do front-end](../src/front) -- repositório do código do front-end

[Código do back-end](../src/back)  -- repositório do código do back-end


## Apêndice B - Apresentação final


[Slides da apresentação final](slides/HappyFit%20-%20Apresentação%20Final.pdf)


[Vídeo da apresentação final](../divulge/video/HappyFit%20Pitch.mp4)






