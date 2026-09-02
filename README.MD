# PingPongScoreBoard - O que é e para que serve?

Simples placar de ping-pong feito utilizando jetpack compose. a mesma tela é implementada quatro vezes, mudando apenas a maneira de armazenar os pontos: `remember`, ViewModel com `mutableStateOf`, ViewModel com `StateFlow` e ViewModel com `SavedStateHandle`.

O app em si é relativamente simples: dois jogadores, um botão "+1" pra cada um deles e um "reiniciar partida". o interessante é exercitar onde cada abordagem quebra.

## Organização do Código

- `ui/ComponentesPlacar.kt`: painel do jogador e o corpo do placar, os dois sem estado nenhum para que as quatro etapas consiga reaproveitar esse código.
- `ui/PlacarRemember.kt`: etapa 1
- `ui/PlacarMutableState.kt` e `viewmodel/PlacarMutableStateViewModel.kt`: etapa 2
- `ui/PlacarStateFlow.kt` e `viewmodel/PlacarStateFlowViewModel.kt`: etapa 3

## Testes

em toda etapa marquei pontos para os jogadores e fiz os seguintes testes:

1. rotação de tela, girando o emulador de retrato pra paisagem.
2. morte do processo, com "não manter atividades" ligado nas opções de desenvolvedor: marco os pontos, saio do app, volto. também é possível matar o processo na mão pelo adb:

```
adb shell am kill br.edu.ifsp.scl.sc3038904.pingpongscoreboard
```

## quadro comparativo

| abordagem | rotação de tela | morte do processo |
| --- | --- | --- |
| `remember` | não sobrevive | não sobrevive |
| ViewModel + `mutableStateOf` | sobrevive | não sobrevive |
| ViewModel + `StateFlow` | sobrevive | não sobrevive |
