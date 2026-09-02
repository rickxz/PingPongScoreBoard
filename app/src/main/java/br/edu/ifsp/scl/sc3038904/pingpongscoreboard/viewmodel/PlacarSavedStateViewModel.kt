package br.edu.ifsp.scl.sc3038904.pingpongscoreboard.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow

class PlacarSavedStateViewModel(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val pontosJogadorA: StateFlow<Int> = savedStateHandle.getStateFlow(CHAVE_JOGADOR_A, 0)
    val pontosJogadorB: StateFlow<Int> = savedStateHandle.getStateFlow(CHAVE_JOGADOR_B, 0)

    fun marcarPontoJogadorA() {
        savedStateHandle[CHAVE_JOGADOR_A] = pontosJogadorA.value + 1
    }

    fun marcarPontoJogadorB() {
        savedStateHandle[CHAVE_JOGADOR_B] = pontosJogadorB.value + 1
    }

    fun reiniciarPartida() {
        savedStateHandle[CHAVE_JOGADOR_A] = 0
        savedStateHandle[CHAVE_JOGADOR_B] = 0
    }

    private companion object {
        const val CHAVE_JOGADOR_A = "pontosJogadorA"
        const val CHAVE_JOGADOR_B = "pontosJogadorB"
    }
}
