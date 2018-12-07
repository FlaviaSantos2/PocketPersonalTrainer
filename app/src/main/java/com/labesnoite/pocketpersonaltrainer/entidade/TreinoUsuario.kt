package com.labesnoite.pocketpersonaltrainer.entidade

import java.util.*

class TreinoUsuario {

    private var dtAgedado: Date
    private var usuario: Usuario
    private var treino: Treino
    private var pesoEscolhido: Double
    private var repeticoes: Int
    private var series: Int

    init {
        this.dtAgedado = Date()
        this.usuario = Usuario()
        this.treino = Treino()
        this.pesoEscolhido = 00.00
        this.repeticoes = 0
        this.series = 0
    }

    constructor() {}

    constructor(dtAgendado: Date, usuario: Usuario, treino: Treino, peso: Double, repeticoes: Int, series: Int) {
        this.dtAgedado = dtAgendado
        this.usuario = usuario
        this.treino = treino
        this.pesoEscolhido = peso
        this.repeticoes = repeticoes
        this.series = series
    }

    constructor(dtAgendado: Date, treino: Treino, peso: Double, repeticoes: Int, series: Int) {
        this.dtAgedado = dtAgendado
        this.treino = treino
        this.pesoEscolhido = peso
        this.repeticoes = repeticoes
        this.series = series
    }

}