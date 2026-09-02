# PingPongScoreBoard - O que é e para que serve?

Simples placar de ping-pong feito utilizando jetpack compose. a mesma tela é implementada quatro vezes, mudando apenas a maneira de armazenar os pontos: `remember`, ViewModel com `mutableStateOf`, ViewModel com `StateFlow` e ViewModel com `SavedStateHandle`.

O app em si é relativamente simples: dois jogadores, um botão "+1" pra cada um deles e um "reiniciar partida". o interessante é exercitar onde cada abordagem quebra.

## Organização do Código

- `ui/ComponentesPlacar.kt`: painel do jogador e o corpo do placar, os dois sem estado nenhum para que as quatro etapas consiga reaproveitar esse código.
- `ui/PlacarRemember.kt`: etapa 1
- `ui/PlacarMutableState.kt` e `viewmodel/PlacarMutableStateViewModel.kt`: etapa 2
- `ui/PlacarStateFlow.kt` e `viewmodel/PlacarStateFlowViewModel.kt`: etapa 3
- `ui/PlacarSavedState.kt` e `viewmodel/PlacarSavedStateViewModel.kt`: etapa 4

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
| ViewModel + `SavedStateHandle` | sobrevive | sobrevive |

## perguntas

### 1. por que o ViewModel sozinho (etapas 2 e 3) não sobrevive à morte do processo, mesmo sobrevivendo à rotação?

porque as duas situações destroem coisas diferentes:

1. na rotação o que é destruído é a activity, enquanto o processo do app continua vivo. antes da activity ser destruída, ela entrega o `ViewModelStore` pro sistema, que transfere essas informações pra activity nova, sendo capaz de "persistir" os dados nesse caso

2. na morte do processo TUDO é destruído. somente quando o usuário retorna o android cria um processo novo, a activity nasce de novo e o ViewModel é construído do zero, com os dois placares 0x0. o `ViewModelStore` não ajuda aqui porque ele também morava no processo (que foi destruído/apagado).


### 2. qual a diferença prática entre mutableStateOf e StateFlow dentro do ViewModel nesta aplicação? em algum momento essa diferença foi perceptível nos testes?

nos testes, nenhuma. faz sentido considerando que o estado mora no mesmo lugar nas duas: o ViewModel.

a diferença aparece principalmente no código. o `mutableStateOf` é uma api do próprio compose: a propriedade é lida dentro do composable e o compose já registra a leitura e recompõe sozinho quando o valor muda, já o `StateFlow` vem do coroutines e não sabe nada sobre compose, então a tela precisa coletar o fluxo com `collectAsStateWithLifecycle` pra transformar ele num `State` de fato.

isso muda quem consegue usar o ViewModel. com `StateFlow` o ViewModel não depende do compose, sendo capaz de observar o estado num teste unitário puro, em outra camada (ou em vários lugares ao mesmo tempo!), sendo possível encadear operadores tipo `map` e `combine` em cima do fluxo. com `mutableStateOf` isso fica acoplado ao compose.

pra ser franco, pra um caso de uso simples como um placar de dois inteiros, o `mutableStateOf` é mais direto e simples. eu só utilizaria o `StateFlow` se o estado começasse a vir de fora (como um db ou uma api).

### 3. se o placar precisasse ser salvo permanentemente, qual abordagem ainda seria insuficiente e o que faltaria?

todas as quatro, inclusive a etapa 4.

o `SavedStateHandle` parece resolver, mas ele escreve num bundle temporário que o sistema mantém enquanto acha que a tarefa do app ainda existe. se o usuário fechar o app completamente, ou reiniciar o celular, ou simplesmente o sistema descartar a tarefa depois de um tempo, esse bundle vai embora junto. ele foi feito pra recuperar de uma morte não intencional, não pra armazenar dado. ou seja, não serve nem como uma forma de gambiarra de fato.

pra o app dias depois, independente da situação, e ainda achar o placar lá, não tem segredo: precisa gravar em disco. pra dois inteiros o mais simples seria o `DataStore` (preferences), gravando os pontos a cada alteração e lendo no início. se eu quisesse guardar histórico de partidas, aí valeria o `Room`. em qualquer um dos dois o ViewModel continua existindo, mas comunicando com um repositório ao invés de guardar os números em memória, e as escritas passariam a ser assíncronas dentro do `ViewModelScope`.

### 4. qual abordagem você usaria em produção para este placar e por quê?

ViewModel + `SavedStateHandle`, a etapa 4.

o custo em relação às etapas 2 e 3 é quase nenhum: com umas poucas linhas é capaz de resolver o único caso que realmente irritaria qualquer usuário que utilizasse o app: deixar a partida aberta, atender uma ligação/responder uma mensagem, voltar e achar o placar zerado.