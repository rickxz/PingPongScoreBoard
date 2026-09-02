package br.edu.ifsp.scl.sc3038904.pingpongscoreboard.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import br.edu.ifsp.scl.sc3038904.pingpongscoreboard.R
import br.edu.ifsp.scl.sc3038904.pingpongscoreboard.viewmodel.PlacarSavedStateViewModel

@Composable
fun PlacarSavedState(
    modifier: Modifier = Modifier,
    viewModel: PlacarSavedStateViewModel = viewModel()
) {
    val pontosJogadorA by viewModel.pontosJogadorA.collectAsStateWithLifecycle()
    val pontosJogadorB by viewModel.pontosJogadorB.collectAsStateWithLifecycle()

    CorpoPlacar(
        descricaoAbordagem = stringResource(R.string.desc_saved_state),
        pontosJogadorA = pontosJogadorA,
        pontosJogadorB = pontosJogadorB,
        aoMarcarPontoJogadorA = viewModel::marcarPontoJogadorA,
        aoMarcarPontoJogadorB = viewModel::marcarPontoJogadorB,
        aoReiniciarPartida = viewModel::reiniciarPartida,
        modifier = modifier
    )
}
