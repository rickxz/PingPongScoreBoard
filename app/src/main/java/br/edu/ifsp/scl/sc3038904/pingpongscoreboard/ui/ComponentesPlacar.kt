package br.edu.ifsp.scl.sc3038904.pingpongscoreboard.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import br.edu.ifsp.scl.sc3038904.pingpongscoreboard.R

@Composable
fun PainelJogador(
    nomeJogador: String,
    pontos: Int,
    aoMarcarPonto: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = nomeJogador,
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = stringResource(R.string.pontos, pontos),
            style = MaterialTheme.typography.headlineSmall
        )
        Button(onClick = aoMarcarPonto) {
            Text(text = stringResource(R.string.mais_um))
        }
    }
}

@Composable
fun CorpoPlacar(
    descricaoAbordagem: String,
    pontosJogadorA: Int,
    pontosJogadorB: Int,
    aoMarcarPontoJogadorA: () -> Unit,
    aoMarcarPontoJogadorB: () -> Unit,
    aoReiniciarPartida: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = descricaoAbordagem,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center
        )

        PainelJogador(
            nomeJogador = stringResource(R.string.jogador_a),
            pontos = pontosJogadorA,
            aoMarcarPonto = aoMarcarPontoJogadorA
        )

        PainelJogador(
            nomeJogador = stringResource(R.string.jogador_b),
            pontos = pontosJogadorB,
            aoMarcarPonto = aoMarcarPontoJogadorB
        )

        OutlinedButton(
            onClick = aoReiniciarPartida,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = stringResource(R.string.reiniciar_partida))
        }
    }
}
