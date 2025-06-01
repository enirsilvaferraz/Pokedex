# Implementando uma Pokédex com Kotlin e Jetpack Compose Multiplatform (Android e iOS)

Fala pessoal! Tudo bem?

Com uma trajetória de mais de oito anos no desenvolvimento Android, dos quais os últimos cinco foram intensamente dedicados ao Jetpack Compose, 
pude testemunhar em primeira mão a evolução e o impacto transformador desta tecnologia na criação de interfaces de usuário. A transição para 
uma abordagem declarativa com o Compose não apenas otimizou o fluxo de trabalho, mas também elevou a qualidade e a eficiência no desenvolvimento 
de UIs para a plataforma Android.

O recente anúncio da versão estável do Compose Multiplatform para iOS representou um marco particularmente interessante. A perspectiva de estender 
essa mesma metodologia de desenvolvimento de UI, com a qual possuo profunda familiaridade no Android, ao ecossistema iOS, apresentou-se como uma 
oportunidade de exploração técnica que me sinto compelido a investigar. Para tal, propus-me a um projeto prático: **o desenvolvimento de uma Pokédex funcional, 
utilizando Kotlin Multiplatform (KMP) para a lógica de negócios compartilhada e Jetpack Compose para a construção de interfaces nativas em Android e, agora, em iOS.**

Este artigo inaugura uma série na qual documentarei o processo, os aprendizados e os desafios inerentes a esta iniciativa. **O objetivo central desta série é avaliar 
a viabilidade e a experiência de utilizar o Compose Multiplatform para o desenvolvimento de interfaces de usuário nativas e ricas no iOS, levando em consideração a experiência 
consolidada com Jetpack Compose no ambiente Android.**

## Proposta de Projeto: Desenvolver uma Pokedex em KMP

A seleção de uma Pokédex como tema para este projeto de exploração multiplataforma é motivada por diversos fatores práticos. **O principal deles é a existência de uma API pública 
robusta e completa (como a [PokéAPI](https://pokeapi.co/)) que fornece uma fonte de dados rica e pronta para uso, permitindo focar nos desafios do desenvolvimento KMP e Compose 
Multiplatform sem a necessidade de construir um backend complexo.**

Não espere nada muito elaborado, a ideia aqui é experimentar as tecnologias.

## Objetivos Técnicos do Projeto

*   Aprofundamento em Kotlin Multiplatform (KMP): Compreensão prática do compartilhamento de lógica de negócios, acesso a dados e outras funcionalidades entre Android, iOS e potencialmente outras plataformas.
*   Validação do Compose Multiplatform no iOS: Análise de desempenho, maturidade e paridade de recursos em comparação com as abordagens nativas de UI para iOS.
*   Validação do Ecossistema de Desenvolvimento: Avaliação das Principais Bibliotecas KMP como Ktor, Koin, ROOM, etc. 
*   Aplicação de Estruturas e Boas Práticas: Utilização de conceitos de Arquitetura Limpa (Clean Architecture) e garantia da qualidade do código através de testes unitários no contexto multiplataforma.
*   Avaliação da Experiência Multiplataforma Completa: Análise ponderada das vantagens, desvantagens, desafios encontrados e benefícios da adoção da abordagem KMP combinada com Compose.

## Objetivos Específicos

*   Implementar a listagem e visualização detalhada dos 151 Pokémon originais da região de Kanto.
*   Consumir uma API pública (como a [PokéAPI](https://pokeapi.co/)) para obter dados dos Pokémon.
*   Estruturar o código de forma modular, compartilhando lógica de negócios e interface de usuário entre as plataformas.
*   Aplicar boas práticas de desenvolvimento, incluindo arquitetura limpa (Clean Architecture) e testes unitários.
*   Avaliar os desafios e benefícios do desenvolvimento multiplataforma com Kotlin e Compose.

## Stack Tecnológico Principal

As ferramentas e bibliotecas centrais planejadas para este projeto incluem (versões iniciais como referência):

*   **Kotlin:** `2.1.21`
*   **Compose Multiplatform (UI KMP):** `1.8.1`
*   **Jetpack Compose (Android UI & Compiler):** Alinhado com Kotlin `2.1.21` e AndroidX mais recentes (ex: Activity `1.10.1`, Lifecycle `2.9.0`)
*   **Ktor (Networking KMP):** `~2.3.12`
*   **SQLDelight (Persistência KMP):** `~2.0.2`
*   **Kotlinx Coroutines (Concorrência KMP):** `~1.8.1`
*   **Koin (Injeção de Dependência KMP):** `~3.5.6`

*As versões exatas podem ser atualizadas para as últimas estáveis durante o desenvolvimento.*

## Próximos Passos

Esta série de artigos continuará acompanhando o desenvolvimento da Pokédex multiplataforma. Os próximos tópicos planejados incluem:

1.  **Construindo a Interface do Usuário com Jetpack Compose:** Detalharemos a criação dos componentes visuais da Pokédex, focando na implementação com Compose Multiplatform para garantir uma experiência consistente em Android e iOS. Exploraremos layouts, navegação e a adaptação de componentes para ambas as plataformas.
2.  **Consumindo a PokéAPI com Ktor:** Abordaremos a implementação da camada de rede utilizando Ktor. Isso incluirá a configuração do cliente HTTP, a definição de requisições para a PokéAPI e o tratamento das respostas e possíveis erros, tudo dentro do módulo compartilhado KMP.
3.  **Persistência de Dados e Cache com SQLDelight (ou Room para Android):** Discutiremos estratégias para persistir os dados dos Pokémon localmente, permitindo o uso offline e melhorando o desempenho através de caching. Exploraremos o uso do SQLDelight para uma solução KMP ou, alternativamente, o Room para a plataforma Android, considerando como integrar essa camada com a lógica compartilhada.

## Convite à Discussão

Este projeto representa uma jornada de aprendizado e uma oportunidade para compartilhar conhecimento com a comunidade, especialmente com aqueles interessados no potencial do Compose Multiplatform e do Kotlin Multiplatform.

Caso possua interesse em desenvolvimento mobile, Kotlin, Jetpack Compose, ou esteja acompanhando a evolução das UIs e arquiteturas multiplataforma, convido-o a acompanhar esta série.

**Qual sua perspectiva sobre o uso de Jetpack Compose? Já explorou o Compose Multiplatform ou o Kotlin Multiplatform para desenvolvimento iOS ou outras plataformas? Suas observações e questionamentos são bem-vindos nos comentários.**

Agradeço a atenção e antecipo as discussões futuras.