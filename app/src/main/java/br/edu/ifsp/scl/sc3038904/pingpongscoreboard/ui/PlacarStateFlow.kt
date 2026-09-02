package br.edu.ifsp.scl.sc3038904.pingpongscoreboard.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import br.edu.ifsp.scl.sc3038904.pingpongscoreboard.R
import br.edu.ifsp.scl.sc3038904.pingpongscoreboard.viewmodel.PlacarStateFlowViewModel

@Composable
fun PlacarStateFlow(
    modifier: Modifier = Modifier,
    viewModel: PlacarStateFlowViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    CorpoPlacar(
        descricaoAbordagem = stringResource(R.string.desc_state_flow),
        pontosJogadorA = uiState.pontosJogadorA,
        pontosJogadorB = uiState.pontosJogadorB,
        aoMarcarPontoJogadorA = viewModel::marcarPontoJogadorA,
        aoMarcarPontoJogadorB = viewModel::marcarPontoJogadorB,
        aoReiniciarPartida = viewModel::reiniciarPartida,
        modifier = modifier
    )
}
