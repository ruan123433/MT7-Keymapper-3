# MT7 Mapping Runtime v0.9

## O que entrou
- Máquina de estados local: parado, aguardando autorização, pronto e suspenso.
- Seleção independente do backend informado (Android, Shizuku ou Root).
- O processamento só aceita deltas quando o serviço lógico está ativo e o backend selecionado está autorizado.
- Pausar e parar bloqueiam imediatamente o processamento local.
- Testes unitários para autorização, transformação X/Y, pausa, parada e atualização de autorização.

## Limites desta etapa
- Não captura mouse/teclado de outros aplicativos.
- Não injeta eventos no Android ou no Free Fire.
- Não executa comandos Root nem comandos Shizuku.
- O status "autorizado" é uma condição da camada lógica, não uma prova de que existe uma API de entrada compatível.
- A taxa de 250 Hz continua sendo uma preferência armazenada, não uma alteração da taxa física do mouse.

## Próximo passo
Integrar uma fonte de eventos de teste controlada pelo próprio MT7 e exibir o estado do runtime na interface. Qualquer integração de entrada do sistema deverá usar APIs documentadas e autorização explícita, sem contornar proteções de jogos ou do Android.
