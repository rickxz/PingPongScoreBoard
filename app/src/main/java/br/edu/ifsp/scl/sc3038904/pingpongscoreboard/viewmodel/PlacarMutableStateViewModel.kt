package br.edu.ifsp.scl.sc3038904.pingpongscoreboard.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class PlacarMutableStateViewModel : ViewModel() {

    var pontosJogadorA by mutableIntStateOf(0)
        private set

    var pontosJogadorB by mutableIntStateOf(0)
        private set

    fun marcarPontoJogadorA() {
        pontosJogadorA++
    }

    fun marcarPontoJogadorB() {
        pontosJogadorB++
    }

    fun reiniciarPartida() {
        pontosJogadorA = 0
        pontosJogadorB = 0
    }
}
