package br.edu.ifsp.scl.sc3038904.pingpongscoreboard.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import br.edu.ifsp.scl.sc3038904.pingpongscoreboard.R
import br.edu.ifsp.scl.sc3038904.pingpongscoreboard.viewmodel.PlacarMutableStateViewModel

@Composable
fun PlacarMutableState(
    modifier: Modifier = Modifier,
    viewModel: PlacarMutableStateViewModel = viewModel()
) {
    CorpoPlacar(
        descricaoAbordagem = stringResource(R.string.desc_mutable_state),
        pontosJogadorA = viewModel.pontosJogadorA,
        pontosJogadorB = viewModel.pontosJogadorB,
        aoMarcarPontoJogadorA = viewModel::marcarPontoJogadorA,
        aoMarcarPontoJogadorB = viewModel::marcarPontoJogadorB,
        aoReiniciarPartida = viewModel::reiniciarPartida,
        modifier = modifier
    )
}
