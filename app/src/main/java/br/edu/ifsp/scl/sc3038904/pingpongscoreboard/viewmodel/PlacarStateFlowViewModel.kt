package br.edu.ifsp.scl.sc3038904.pingpongscoreboard.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class PlacarUiState(
    val pontosJogadorA: Int = 0,
    val pontosJogadorB: Int = 0
)

class PlacarStateFlowViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(PlacarUiState())
    val uiState: StateFlow<PlacarUiState> = _uiState.asStateFlow()

    fun marcarPontoJogadorA() {
        _uiState.update { estado -> estado.copy(pontosJogadorA = estado.pontosJogadorA + 1) }
    }

    fun marcarPontoJogadorB() {
        _uiState.update { estado -> estado.copy(pontosJogadorB = estado.pontosJogadorB + 1) }
    }

    fun reiniciarPartida() {
        _uiState.value = PlacarUiState()
    }
}
