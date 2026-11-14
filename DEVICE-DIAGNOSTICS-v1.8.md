# MT7 Keymapper v1.8.0 - diagnóstico de dispositivos

## O que entrou

- Nova aba **Dispositivos**.
- Lista os dispositivos de entrada que o Android disponibiliza à API pública para o aplicativo.
- Exibe nome, tipo aproximado, fontes de entrada, ID Android, indicação de dispositivo virtual e Vendor/Product ID quando informados pelo sistema.
- Botão **Atualizar** para refazer a leitura da lista.
- Testes unitários para classificação de fontes de entrada.
- A tela do laboratório local agora ocupa a área disponível e permite rolagem vertical, evitando que os controles inferiores fiquem inacessíveis em telas menores.

## Limites desta etapa

A enumeração é somente de leitura. Ela não captura eventos globais, não lê teclas em segundo plano, não injeta eventos em outros aplicativos e não conversa com o Free Fire. A lista depende do que a versão do Android e o fabricante expõem ao aplicativo; portanto, um periférico conectado pode não aparecer aqui.

A aba Root continua sendo apenas uma verificação informativa. A aba Shizuku consulta conexão e concessão do próprio aplicativo, mas ainda não executa comandos privilegiados nem injeta entrada.

## Validação

Os testes de classificação foram adicionados ao conjunto JUnit. A compilação Android completa e a instalação em aparelho físico ainda precisam ser executadas no Android Studio, com Android SDK Platform 37, NDK e CMake 3.22.1 instalados.
