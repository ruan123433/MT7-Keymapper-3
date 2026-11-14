# v1.6 - validação do editor local

- Extraída a geometria de seleção e normalização para `ControlPositionMath`, sem dependência de Android ou Compose.
- O editor seleciona o marcador mais próximo somente quando o início do gesto está dentro do raio de toque de 52 px.
- Coordenadas arrastadas são normalizadas e limitadas ao intervalo [0, 1]. Limites com tamanho zero são tratados de forma segura.
- Foram adicionados testes unitários para seleção, rejeição fora do raio, normalização, clamp e dimensão zero.

## Verificação executada

O ZIP de entrada foi testado com `unzip -t`. A classe pura `ControlPositionMath.kt` foi compilada com o compilador Kotlin disponível e exercitada por verificações locais. A compilação Android completa e a suíte JUnit/Gradle não foram executadas: este ambiente não possui Gradle instalado, o pacote não inclui `gradle-wrapper.jar` e não há Android SDK configurado.

## Limites

O editor altera somente coordenadas normalizadas guardadas no perfil e mostradas na prévia do MT7. Não cria uma sobreposição interativa dentro do Free Fire, não captura eventos do jogo e não injeta entradas em outros aplicativos.
