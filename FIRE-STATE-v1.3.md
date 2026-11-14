# v1.3: estado local do botão de atirar

Esta versão acrescenta um simulador local de estado para três modos:

- **Toque (TAP):** cada ação produz uma transição pressionado e outra solto, em sequência.
- **Segurar (HOLD):** a primeira ação marca como pressionado; chamadas repetidas não criam novas transições. O estado só volta a solto com a ação explícita **Soltar** ou ao trocar de modo/resetar.
- **Alternar (TOGGLE):** cada ação alterna entre pressionado e solto. A ação Soltar não interfere nesse modo.

O perfil salva o modo selecionado. Perfis existentes que ainda não possuem o campo `fireMode` são carregados com `TAP` por padrão, sem exigir migração manual.

## Limites

O controlador é uma máquina de estados local para teste da interface. Não recebe eventos do mouse/teclado do Android, não injeta eventos em outros aplicativos, não controla o Free Fire e não altera recuo, dispersão ou qualquer mecânica interna do jogo. Não é um recurso de anti-recuo ou automação de disparo.

## Testes incluídos

- TAP gera duas transições e termina solto.
- HOLD permanece pressionado até a liberação explícita e ignora pressionamentos repetidos.
- TOGGLE alterna somente quando ativado.
- Trocar de modo libera um estado HOLD que estava ativo.
