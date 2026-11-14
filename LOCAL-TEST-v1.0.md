# MT7 Keymapper v1.0

## Novidade
- Aba **Teste local** com controles X/Y para enviar deltas artificiais inseridos pelo usuário.
- Exibe estado do runtime e valores de entrada/saída.
- O evento só é processado quando o runtime está em `READY`.
- Pausar e parar limpam a saída e bloqueiam novos eventos.
- A fonte `LocalTestEventSource` é explícita e isolada para diagnóstico.

## Limites
Esta versão não captura eventos reais de mouse/teclado, não injeta eventos em outros aplicativos e não altera o comportamento do Free Fire. O backend Android da aba de teste é apenas uma autorização local para o laboratório, não uma permissão privilegiada do sistema. Shizuku e Root continuam separados e sem execução de comandos privilegiados.

## Validação
O ZIP contém testes unitários para a fonte local e para o bloqueio de eventos. A compilação Android e a execução dos testes ainda precisam ser realizadas em um ambiente com Android SDK/Gradle configurado.
