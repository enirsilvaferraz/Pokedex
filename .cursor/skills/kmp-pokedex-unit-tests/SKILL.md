---
name: kmp-pokedex-unit-tests
description: >-
  Analisa módulos Kotlin Multiplatform deste projeto Pokedex e cria ou amplia
  testes unitários no padrão GIVEN/WHEN/THEN, com MockK em jvmTest quando
  necessário. Use quando o usuário pedir testes unitários, testes de use case,
  repositório, entidade, ou ao citar uma classe específica para testar neste repo.
---

# Testes unitários KMP (Pokedex)

## Entrada do usuário (escopo)

1. **Se o usuário indicar uma classe** (obrigatório quando ele pedir “só X”):
   - Aceitar: FQN (`com.eferraz.pokedex.usecases.GetPokemonDetailsUseCaseImpl`), caminho de arquivo (`core/domain/usecases/.../Foo.kt`), ou nome curto (`GetPokemonDetailsUseCaseImpl`).
   - Resolver o arquivo com busca no repositório; **limitar** novos testes e alterações àquela unidade (e a um único arquivo de teste alvo, salvo fixtures compartilhados no mesmo módulo).
2. **Se não indicar classe**: sugerir módulo/camada ou perguntar qual classe ou pacote testar antes de espalhar testes.

## Análise do projeto (checklist)

1. **Módulo Gradle**: a partir do caminho do arquivo fonte, identificar o `build.gradle.kts` e o nome do projeto (`settings.gradle.kts`, ex.: `:domain:entity`, `:domain:usecases`, `:data:repositories`).
2. **Plugins**: `foundation.project` define `explicitApi()`, `commonTest` (kotlin-test + coroutines-test) e `jvmTest` com MockK (`kotlin-jvm-test` no convention plugin).
3. **Onde colocar os testes**:
   - **`entity`**, só quando houver **comportamento** (métodos, regras, mapeamentos): **`src/commonTest`**. **Não** testar entidades anêmicas — `data class` só com propriedades ou getters triviais (ex.: `Breeding`, `Stats.total`, URL one-liner).
   - **`usecases`**, **`repositories`**, qualquer coisa com **corrotinas + colaboradores mockáveis**: **`src/jvmTest`** — [MockK](https://github.com/mockk/mockk) é **apenas JVM**; não usar MockK em `commonTest`.
4. **Visibilidade**: implementações `internal` no `commonMain` são testáveis no **mesmo módulo** (`commonTest` ou `jvmTest`). Com `explicitApi()`, usar visibilidade explícita nos fixtures de teste (`internal` ou `public` conforme o compilador exigir entre arquivos do mesmo source set).
5. **Executar**: `./gradlew :<projeto>:jvmTest` ou, para entity também validar metadados comuns, o mesmo task compila `commonTest` para JVM. Ajustar o path do projeto ao `settings.gradle.kts`.

## Padrão de teste (obrigatório neste repo)

- **Nome do método**: backticks com **`GIVEN … WHEN … THEN …`** (inglês, descritivo).
- **Corpo**: comentários `// GIVEN`, `// WHEN`, `// THEN` separando arranjo, ação e asserções.
- **Corrotinas**: `kotlinx.coroutines.test.runTest`; `StandardTestDispatcher` ou `UnconfinedTestDispatcher` quando `withContext`/`flowOn` exigirem controle do scheduler.
- **MockK** (só `jvmTest`):
  - Interfaces: `mockk()`, `every { }` para métodos normais, `coEvery` / `coVerify` para `suspend`.
  - `relaxUnitFun = true` em colaboradores não usados no cenário.
  - `@AfterTest fun tearDown() { clearAllMocks() }` na classe de teste.
- **Fixtures**: arquivo dedicado no mesmo source set (ex.: `UseCaseTestFixtures.kt`, `RepositoryTestFixtures.kt`) com funções `sampleX()` / `testX()`, preferir `internal` se visível entre arquivos do módulo.
- **Quantidade**: essencial — caminho feliz + 1 borda relevante (erro, lista vazia, cache vs fetch, etc.), alinhado ao que já existe no projeto.

## Casos por camada

| Camada | Fonte típica | Source set | Ferramentas |
|--------|----------------|------------|-------------|
| Entidade | `core/domain/entity` | `commonTest` | kotlin.test, sem MockK |
| Use case | `core/domain/usecases` | `jvmTest` | MockK + testar via `invoke()` quando o fluxo passa por `AppUseCase` |
| Repositório | `core/data/repositories` | `jvmTest` | MockK para `*DataSourceApi` / `*DataSourceDatabase` |

## Depois de implementar

1. Rodar o task de teste JVM do módulo afetado.
2. Não adicionar dependências em `build.gradle.kts` se já estiver coberto pelo plugin foundation.
3. Não criar README ou docs extras salvo pedido explícito.

## Referência no repositório

Exemplos concretos deste workspace: [reference.md](reference.md).
