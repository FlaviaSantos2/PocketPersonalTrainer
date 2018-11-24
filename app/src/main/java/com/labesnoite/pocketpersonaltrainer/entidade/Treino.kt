package com.labesnoite.pocketpersonaltrainer.entidade

class Treino() {

    private val id: Int
        get() = this.id

    private var nome: String
        get() = this.nome
        set(value: String) {
            this.nome = value
        }

    private var descricao: String
        get() = this.descricao
        set(value: String) {
            this.descricao = value
        }

}