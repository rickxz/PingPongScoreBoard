package br.edu.ifsp.scl.sc3038904.pingpongscoreboard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FilterChip
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import br.edu.ifsp.scl.sc3038904.pingpongscoreboard.ui.PlacarMutableState
import br.edu.ifsp.scl.sc3038904.pingpongscoreboard.ui.PlacarRemember
import br.edu.ifsp.scl.sc3038904.pingpongscoreboard.ui.PlacarStateFlow

enum class Etapa(@StringRes val rotuloRes: Int) {
    REMEMBER(R.string.etapa_remember),
    MUTABLE_STATE(R.string.etapa_mutable_state),
    STATE_FLOW(R.string.etapa_state_flow)
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TelaPrincipal(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TelaPrincipal(modifier: Modifier = Modifier) {
    var etapaSelecionada by rememberSaveable { mutableStateOf(Etapa.REMEMBER) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = stringResource(R.string.titulo_app),
            style = MaterialTheme.typography.headlineMedium
        )

        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Etapa.entries.forEach { etapa ->
                FilterChip(
                    selected = etapa == etapaSelecionada,
                    onClick = { etapaSelecionada = etapa },
                    label = { Text(text = stringResource(etapa.rotuloRes)) }
                )
            }
        }

        HorizontalDivider()

        when (etapaSelecionada) {
            Etapa.REMEMBER -> PlacarRemember()
            Etapa.MUTABLE_STATE -> PlacarMutableState()
            Etapa.STATE_FLOW -> PlacarStateFlow()
        }
    }
}
