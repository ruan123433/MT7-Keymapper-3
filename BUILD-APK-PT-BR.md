# Gerar o APK de teste do MT7 Keymapper

Este ZIP contém o projeto-fonte, não um APK pronto.

Há dois caminhos:

- **GitHub Actions (sem Android Studio no celular):** veja [BUILD-APK-GITHUB-PT-BR.md](BUILD-APK-GITHUB-PT-BR.md). O projeto já inclui o fluxo `.github/workflows/android-build.yml`, que executa os testes e tenta gerar um APK de depuração na nuvem.
- **Computador com Android Studio:** siga as instruções abaixo.

## Preparação no computador

1. Instale o Android Studio e um JDK 17 ou superior.
2. Instale o Gradle **9.6.0**. Este projeto usa Android Gradle Plugin 9.4.0, que requer no mínimo Gradle 9.6.0.
3. Extraia o ZIP. No terminal, entre na pasta `MT7-Keymapper` e gere os arquivos do Gradle Wrapper:

   ```bash
   gradle wrapper --gradle-version 9.6.0
   ```

4. Abra a pasta `MT7-Keymapper` no Android Studio.
5. No SDK Manager, instale:
   - Android SDK Platform **37**;
   - Android SDK Build-Tools **36.0.0**;
   - NDK **28.2.13676358**;
   - CMake **3.22.1**.
6. Configure o Gradle JDK para JDK 17 ou superior compatível e sincronize o projeto.

## Gerar o APK

No Android Studio, use **Build > Build Bundle(s) / APK(s) > Build APK(s)**. O APK de depuração deve aparecer em:

`app/build/outputs/apk/debug/app-debug.apk`

Transfira esse APK para o celular de teste e instale-o. Na primeira execução, conceda a permissão de sobreposição somente se for testar a bolinha flutuante.

## O que testar nesta versão

- Abrir as abas Shizuku, Root, Permissões, Teste local e Dispositivos.
- Verificar o inventário de mouse, teclado e controle; usar **Atualizar** depois de conectar um periférico.
- Criar e salvar perfis.
- Ajustar sensibilidade X/Y, joystick e posições na prévia.
- Iniciar, pausar e parar a sessão simulada; testar os modos do botão no laboratório local.
- Conceder sobreposição e verificar a bolinha flutuante e seu menu.

## Limitação essencial

Esta versão é um protótipo Android testável de configuração, diagnóstico e simulação local. Ela ainda não transforma os periféricos em controles jogáveis dentro do Free Fire: não há captura global ou injeção de eventos implementada. Não interprete o estado `READY` como autorização ou ativação de entrada no jogo.
