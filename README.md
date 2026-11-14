# MT7 Keymapper

Projeto Android nativo em Kotlin + Jetpack Compose + C++.

## Configuração do projeto
- Android mínimo: API 29 (Android 10)
- `compileSdk`: 37
- `targetSdk`: 36
- Kotlin + Compose
- C++ via NDK/CMake
- Shizuku API: 13.1.5

## Abrir
Abra a pasta `MT7-Keymapper` no Android Studio e sincronize o Gradle. Instale Android SDK Platform 37, NDK e CMake conforme solicitado pelo Android Studio. O wrapper Gradle executável não está incluído neste pacote.

## Implementado nesta versão
- Central de permissões com verificação de sobreposição e estado de notificações.
- Atalhos para as configurações especiais do Android.
- Serviço Android em primeiro plano com notificação persistente e `stopWithTask=false`.
- Cartões informativos para Shizuku e Root (a integração privilegiada ainda não executa operações).
- Estrutura inicial Kotlin/C++ e transformação local de deltas X/Y.

## Limitações importantes
- O serviço é somente um esqueleto de ciclo de vida. Ainda não captura nem injeta eventos de mouse/teclado em outros aplicativos.
- A bolinha flutuante ainda não foi implementada.
- O botão "Iniciar serviço MT7" verifica a permissão de sobreposição antes de iniciar o serviço.
- A permissão de notificação é mostrada como estado, mas o pedido runtime ainda precisa ser conectado ao fluxo de UI.
- Shizuku é apenas inspecionado; Root permanece não implementado.
- `specialUse` para foreground service exige declarar o uso de forma adequada e cumprir as políticas da distribuição Android/Google Play aplicáveis. A disponibilidade depende da versão e das políticas do dispositivo.

## Próximas etapas
1. Conectar solicitação runtime de notificações (Android 13+).
2. Implementar a bolinha com overlay e controles de recolher/suspender.
3. Implementar persistência dos perfis e parâmetros decimais X/Y.
4. Implementar e testar backends de entrada compatíveis, sem contornar proteções de jogos ou do Android.

## v0.4 - bolinha flutuante (estrutura inicial)

- Dois toques na bolinha MT7 abrem/fecham o menu.
- A bolinha pode ser arrastada pela tela.
- O menu oferece ações de suspender/reativar visualmente, recolher e parar o serviço.
- O serviço de primeiro plano mantém a sobreposição enquanto o Android permitir.

**Limitação importante:** esta versão ainda não injeta eventos de mouse/teclado em outros aplicativos. O botão de suspensão altera o estado visual do protótipo; ele será ligado ao motor de entrada quando o backend autorizado estiver implementado. O item Visão/Sensibilidade é apenas um ponto de entrada para a próxima etapa.

Para testar, conceda a permissão "Sobrepor a outros apps" nas configurações do Android e toque em "Iniciar serviço MT7" na tela principal. Para parar, use "PARAR SERVIÇO" no menu ou encerre o serviço pela notificação/sistema.

## v0.5 - Painel de visão (configuração local)
- Sensibilidade X/Y independente, com passo de 0,01 e faixa de 0,05 a 10,00.
- Frequência selecionável de 125, 250, 500 ou 1000 Hz; padrão 250 Hz.
- Preferências salvas localmente em SharedPreferences.
- Estes controles armazenam configuração; não alteram a taxa física do mouse nem implementam injeção de eventos. A frequência efetiva depende do dispositivo, periférico e backend.


## v0.6 - Vision transform engine

- Added a deterministic linear X/Y pointer-delta transform in Kotlin.
- Updated the JNI/C++ bridge to apply independent X/Y sensitivity and reject
  non-finite values or out-of-range sensitivity values.
- Added tests for decimal sensitivity values (0.1 / 0.2), independent axes,
  and linear gain.
- The 250 Hz selection remains a configuration value only. This release does
  not change the physical mouse polling rate or implement Android input capture
  or injection. Device-level input integration remains a separate backend task.
- Acceleration protection means the MT7 transform does not add speed-dependent
  gain. It cannot disable game-side recoil, spread, aim behavior, or anti-cheat.
- Build and device validation have not yet been performed.


## v0.7 - Abas separadas de ativação

- Criadas abas independentes para Shizuku, Root e permissões Android.
- Shizuku mostra o estado do Binder e permite solicitar autorização ao usuário.
- Root aparece como backend separado, explicitamente pendente de implementação.
- Botão para iniciar apenas o serviço local do MT7, após conceder sobreposição.
- Nenhuma injeção de entrada em aplicativos terceiros é implementada nesta versão.
- A aba Root não executa comandos privilegiados nem solicita superusuário ainda.
- Compilação e testes em dispositivo físico ainda pendentes.


## v0.8 - Backends de ativação separados

- Criado `ShizukuBackend`, com inspeção do Binder e autorização explícita do MT7.
- Criado `RootBackend`, separado, que apenas procura binários `su` em locais comuns.
- A verificação Root não executa `su`, não solicita superusuário e não executa comandos privilegiados.
- A tela Shizuku recebe o resultado do pedido de autorização e permite atualizar o estado.
- A tela Root diferencia "binário encontrado" de "acesso Root autorizado"; esta última etapa ainda não existe.
- Não há captura/injeção de mouse ou teclado em jogos ou outros apps nesta versão.
- Não há tentativa de contornar anti-cheat ou restrições de segurança do Android.
- Compilação e teste em dispositivo físico ainda pendentes.


## v0.9: ciclo de vida do motor local
Consulte `docs/RUNTIME-v0.9.md`. Esta versão adiciona uma máquina de estados e processamento local de deltas fornecidos pelo próprio código, sem captura ou injeção de entrada em outros aplicativos. Os testes foram adicionados, mas ainda não foram executados neste ambiente.


## v1.0
Consulte `docs/LOCAL-TEST-v1.0.md` para o laboratório de eventos locais e seus limites.


## v1.1
Perfis locais com sensibilidade X/Y editável e persistência. Consulte `docs/PROFILES-v1.1.md`.


## v1.2
Configuração e simulação local do joystick, com zona morta e sensibilidade X/Y persistidas nos perfis. Consulte `docs/JOYSTICK-v1.2.md`.


## v1.3
Consulte `docs/FIRE-STATE-v1.3.md`. O laboratório local agora simula o botão de atirar nos modos Toque, Segurar e Alternar. O modo é salvo em cada perfil; perfis antigos assumem Toque. Esta versão não captura nem injeta entradas em outros aplicativos ou jogos.


## v1.4 - posições locais

A v1.4 adiciona configuração e prévia proporcional das posições X/Y do joystick, área de visão e botão de atirar, persistidas por perfil. Consulte `docs/CONTROL-POSITIONS-v1.4.md`.


## v1.5: editor de posições por arraste

A prévia proporcional agora permite selecionar e arrastar diretamente os marcadores de Joystick, Visão e Atirar. As coordenadas são normalizadas entre 0.0 e 1.0 e continuam sendo gravadas pelo fluxo de salvamento de perfil já existente. Os controles deslizantes X/Y permanecem disponíveis para ajuste fino. A interação altera somente a configuração local da prévia; não captura nem injeta eventos em outros aplicativos.


## v1.6 - validação do editor

A geometria de seleção e normalização dos marcadores foi extraída para `ControlPositionMath`, com testes para hit radius, seleção do marcador mais próximo, clamp e limites de tamanho zero. Consulte `docs/VALIDATION-v1.6.md` para o resultado e as limitações da validação disponível.


## v1.7.0 — sessão local integrada

- `LocalMappingSession` coordena o ciclo de vida local, o processamento de movimento relativo, a transformação do joystick e o estado do botão de atirar.
- Eventos de ponteiro e joystick só são processados quando a sessão está em `READY`.
- Pausar ou parar limpa o estado pressionado do botão de teste, evitando que o simulador fique preso em `HOLD`.
- A tela Laboratório usa a sessão integrada para os testes de ponteiro, joystick e botão.
- O backend Android usado no botão Iniciar é explicitamente um backend local de diagnóstico, não uma autorização de injeção.

**Limites:** esta sessão recebe somente valores gerados pelos controles do próprio aplicativo. Não lê dispositivos, não captura eventos globais, não injeta entrada em outros aplicativos e não interage com Free Fire. A indicação `READY` significa apenas que o simulador local está pronto.

## v1.8.0 - diagnóstico de dispositivos

- Nova aba de diagnóstico para dispositivos de entrada que o Android expõe ao aplicativo.
- Identifica fontes como mouse, teclado, gamepad, joystick, direcional e tela touch, além de alguns metadados do dispositivo.
- Inclui atualização manual e testes unitários da classificação.
- Corrige a área do laboratório local para permitir rolagem em telas menores.
- A leitura é somente de inventário: não captura eventos, não injeta entrada em jogos e não altera o comportamento do Free Fire.

## Preparar um APK de teste

Este pacote contém o projeto-fonte, não um APK pronto. Para gerar o APK, abra a pasta `MT7-Keymapper` no Android Studio em um computador, instale Android SDK Platform 37, NDK e CMake 3.22.1 pelo SDK Manager e sincronize as dependências Gradle. Depois use **Build > Build Bundle(s) / APK(s) > Build APK(s)**. O APK de debug normalmente é criado em `app/build/outputs/apk/debug/app-debug.apk`.

A compilação Android completa ainda não foi executada neste ambiente porque não há Android SDK/Gradle instalados e a conexão de rede para baixar essas ferramentas está indisponível. O ZIP também não inclui os scripts/JAR do Gradle Wrapper; gere-os conforme `BUILD-APK-PT-BR.md`. Portanto, este pacote não deve ser tratado como APK já compilado ou validado em aparelho.
