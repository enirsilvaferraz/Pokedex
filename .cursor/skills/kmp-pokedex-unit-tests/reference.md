# Referência — exemplos no Pokedex

Use estes arquivos como espelho de estilo (não copiar cegamente; adaptar à classe sob teste).

## Repositório (`jvmTest` + MockK)

- `core/data/repositories/src/jvmTest/kotlin/com/eferraz/pokedex/repositories/PokemonSummaryRepositoryImplTest.kt`
- `core/data/repositories/src/jvmTest/kotlin/com/eferraz/pokedex/repositories/PokemonDetailedRepositoryImplTest.kt`
- `core/data/repositories/src/jvmTest/kotlin/com/eferraz/pokedex/repositories/RepositoryTestFixtures.kt`

Padrões: `every { db.get(limit = 151, offset = 0) }`, `coVerify { api.get(...) }`, `flowOf` + `first()`.

## Use cases (`jvmTest` + MockK)

- `core/domain/usecases/src/jvmTest/kotlin/com/eferraz/pokedex/usecases/GetPokemonDetailsUseCaseImplTest.kt`
- `core/domain/usecases/src/jvmTest/kotlin/com/eferraz/pokedex/usecases/ObservePokemonListUseCaseImplTest.kt`
- `core/domain/usecases/src/jvmTest/kotlin/com/eferraz/pokedex/usecases/UseCaseTestFixtures.kt`

`AppUseCase`: testar com `useCase(param)` suspend e `Result`.

## Entidade (`commonTest`, sem MockK)

- `core/domain/entity/src/commonTest/kotlin/com/eferraz/pokedex/entity/summary/PokemonSummaryTest.kt`
- `core/domain/entity/src/commonTest/kotlin/com/eferraz/pokedex/entity/detail/PokemonDetailedTest.kt`

## Comando Gradle

Substituir o projeto conforme `settings.gradle.kts`:

```bash
./gradlew :data:repositories:jvmTest
./gradlew :domain:usecases:jvmTest
./gradlew :domain:entity:jvmTest
```

## Escopo por classe (prompt)

Exemplos para o usuário colar no chat:

- “Crie testes para a classe `UpdatePokemonTypeUseCaseImpl` seguindo a skill kmp-pokedex-unit-tests.”
- “Teste só o arquivo `core/data/network/.../Foo.kt`.”

O agente deve localizar o módulo, escolher `commonTest` vs `jvmTest` e gerar **apenas** o teste dessa unidade (+ fixtures locais se preciso).
