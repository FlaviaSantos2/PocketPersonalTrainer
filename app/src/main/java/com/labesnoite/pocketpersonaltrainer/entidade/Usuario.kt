package com.labesnoite.pocketpersonaltrainer.entidade

import com.google.gson.annotations.SerializedName
import java.util.*

// entidade usuario: referente a tabela cliente no banco de dados
class Usuario {

    private var id: Int = 0
    private var nome: String = ""
    private var email: String = ""
    private var peso: Double = 00.00
    private var altura: Double = 00.00
    private var senha: String = ""
    private var telefone: String = ""
    private var status: Int = 0
    private var valorMensalidade: Double = 00.00
    private var dtVencimentoMensalidade: Date = Date()


    constructor()


    constructor(id: Int, nome: String, email: String, senha: String, peso: Double, altura: Double, telefone: String,
                status: Int, valorMensalidade: Double, dtVencimentoMensalidade: Date) {
        this.id = id
        this.nome = nome
        this.email = email
        this.senha = senha
        this.peso = peso
        this.altura = altura
        this.telefone = telefone
        this.status = status
        this.valorMensalidade = valorMensalidade
        this.dtVencimentoMensalidade = dtVencimentoMensalidade
    }

    constructor(nome: String, email: String, senha: String, peso: Double, altura: Double, telefone: String,
                status: Int, valorMensalidade: Double, dtVencimentoMensalidade: Date) {
        this.nome = nome
        this.email = email
        this.senha = senha
        this.peso = peso
        this.altura = altura
        this.telefone = telefone
        this.status = status
        this.valorMensalidade = valorMensalidade
        this.dtVencimentoMensalidade = dtVencimentoMensalidade
    }
}