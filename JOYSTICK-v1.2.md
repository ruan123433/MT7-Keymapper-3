# MT7 Keymapper v1.2

## Joystick local
- Ajustes independentes de sensibilidade X/Y (0,1 a 2,0).
- Zona morta radial ajustável (0 a 0,5).
- Simulador de vetor normalizado entre -1 e 1.
- Saída limitada ao intervalo -1 a 1.
- Ajustes de joystick passam a ser persistidos nos perfis existentes. Perfis antigos recebem valores padrão automaticamente.

## Segurança e escopo
O joystick é um simulador de transformação local. Não cria um joystick virtual do sistema, não captura controles físicos, não injeta eventos em Free Fire ou outros aplicativos e não altera proteções do jogo.

## Testes
Incluídos testes unitários para zona morta, limite de saída e sensibilidade independente. Compilação e execução ainda precisam ser verificadas no Android Studio.
