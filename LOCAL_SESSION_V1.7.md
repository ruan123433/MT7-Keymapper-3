# Sessão local v1.7

`LocalMappingSession` une os componentes já existentes do laboratório em uma única API:

- `start(BackendStatus)`, `pause()`, `resume()` e `stop()` controlam o ciclo de vida;
- `processPointer(...)` aplica a sensibilidade X/Y apenas em `READY`;
- `processJoystick(...)` aplica zona morta radial e sensibilidade por eixo apenas em `READY`;
- `setFireMode(...)`, `activateFire()` e `releaseFire()` controlam somente o estado simulado do botão;
- pausar/parar redefine o botão para solto.

O backend Android passado pela tela é um estado local de diagnóstico. Não representa permissão para controlar outros aplicativos. A sessão não possui APIs de captura ou injeção.

## Testes

`LocalMappingSessionTest` cobre bloqueio antes da autorização, processamento em estado pronto, bloqueio durante pausa/parada, reset do botão e alternância do modo de disparo.
