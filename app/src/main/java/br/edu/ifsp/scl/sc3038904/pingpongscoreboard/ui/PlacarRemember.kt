package br.edu.ifsp.scl.sc3038904.pingpongscoreboard.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import br.edu.ifsp.scl.sc3038904.pingpongscoreboard.R

@Composable
fun PlacarRemember(modifier: Modifier = Modifier) {
    var pontosJogadorA by remember { mutableIntStateOf(0) }
    var pontosJogadorB by remember { mutableIntStateOf(0) }

    CorpoPlacar(
        descricaoAbordagem = stringResource(R.string.desc_remember),
        pontosJogadorA = pontosJogadorA,
        pontosJogadorB = pontosJogadorB,
        aoMarcarPontoJogadorA = { pontosJogadorA++ },
        aoMarcarPontoJogadorB = { pontosJogadorB++ },
        aoReiniciarPartida = {
            pontosJogadorA = 0
            pontosJogadorB = 0
        },
        modifier = modifier
    )
}
