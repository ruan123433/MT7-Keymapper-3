# Gerar o APK do MT7 Keymapper pela nuvem (sem instalar Android Studio no celular)

Este projeto inclui um fluxo do GitHub Actions em `.github/workflows/android-build.yml`. Ele prepara JDK, Android SDK, NDK, CMake e Gradle 9.6.0, executa os testes unitários e tenta gerar o APK de depuração.

## O que você precisa

- Uma conta GitHub com acesso a GitHub Actions.
- Um repositório pessoal para guardar o código. Um repositório privado é suficiente; confira os limites de minutos de Actions da sua conta.
- O conteúdo deste projeto extraído do ZIP.

## Passos

1. No GitHub, crie um repositório chamado `MT7-Keymapper`. Escolha privado caso não queira tornar o código público.
2. Envie para a raiz do repositório o **conteúdo** da pasta `MT7-Keymapper` extraída, incluindo a pasta oculta `.github`. Não envie somente o ZIP como um único arquivo.
3. Depois do primeiro envio para a branch `main`, abra a aba **Actions** do repositório.
4. Abra a execução chamada **Build MT7 Keymapper APK**. Aguarde ela terminar e confira se ficou verde.
5. Na página da execução concluída, localize **Artifacts** e baixe `mt7-keymapper-debug-apk`.
6. Extraia o arquivo baixado. O instalador estará em `app-debug.apk`. Transfira-o para o Android e instale para teste.

## Se a execução falhar

Abra a etapa vermelha da execução e copie a primeira mensagem de erro completa. Erros de dependência, versão de SDK ou compilação precisam ser corrigidos no código antes de o APK ser considerado pronto. Um artefato só aparece quando o APK foi realmente gerado.

## Limites desta versão

O APK é uma compilação de depuração. A v1.8 é um protótipo de interface, diagnóstico de dispositivos e simulação local. Ela ainda não mapeia mouse/teclado dentro do Free Fire e não implementa captura global ou injeção de eventos.
