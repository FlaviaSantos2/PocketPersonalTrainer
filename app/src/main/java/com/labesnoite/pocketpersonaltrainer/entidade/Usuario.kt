package com.labesnoite.pocketpersonaltrainer.entidade

import android.net.Uri
import java.io.Serializable
import java.util.*

// entidade usuario: referente a tabela cliente no banco de dados
class Usuario : Serializable {

    private var id: Int
    private var nome: String
    private var email: String
    private var peso: Double
    private var altura: Double
    private var senha: String
    private var telefone: String
    private var status: Int
    private var valorMensalidade: Double
    private var dtVencimentoMensalidade: Date
    private var URIfotoPerfil: Uri

    init {
        this.id = 0
        this.nome = ""
        this.email = ""
        this.peso = 00.00
        this.altura = 00.00
        this.senha = ""
        this.telefone = ""
        this.status = 0
        this.valorMensalidade = 00.00
        this.dtVencimentoMensalidade = Date()
        this.URIfotoPerfil = Uri.EMPTY
    }


    constructor()


    constructor(id: Int, nome: String, email: String, senha: String, peso: Double, altura: Double, telefone: String,
                status: Int, valorMensalidade: Double, dtVencimentoMensalidade: Date, uriFotoPerfil: Uri) {
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
        this.URIfotoPerfil = uriFotoPerfil
    }

    //COM FOTO DE PERFIL E SEM ID, POIS SERÁ GERADO ALTOMATICAMENTO PELA API
    constructor(nome: String, email: String, senha: String, peso: Double, altura: Double, telefone: String,
                status: Int, valorMensalidade: Double, dtVencimentoMensalidade: Date, uriFotoPerfil: Uri) {
        this.nome = nome
        this.email = email
        this.senha = senha
        this.peso = peso
        this.altura = altura
        this.telefone = telefone
        this.status = status
        this.valorMensalidade = valorMensalidade
        this.dtVencimentoMensalidade = dtVencimentoMensalidade
        this.URIfotoPerfil = uriFotoPerfil
    }

    //Sem foto de perfil
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

    fun getId(): Int {
        return this.id
    }

    fun getNome(): String {
        return this.nome
    }

    fun setNome(value: String) {
        nome = value
    }

    fun getEmail(): String {
        return this.email
    }

    fun setEmail(value: String) {
        email = value
    }

    fun getSenha(): String {
        return this.senha
    }

    fun setSenha(value: String) {
        senha = value
    }

    fun getTelefone(): String {
        return this.telefone
    }

    fun setTelefone(value: String) {
        telefone = value
    }

    fun getUriFotoPerfil(): Uri {
        return this.URIfotoPerfil
    }

    fun setUriFototPerfil(value: Uri) {
        URIfotoPerfil = value
    }

    fun getStatus(): Int {
        return this.status
    }

    fun setStatus(value: Int) {
        status = value
    }

    fun getPeso(): Double {
        return this.peso
    }

    fun setPeso(value: Double) {
        peso = value
    }

    fun getAltura(): Double {
        return this.altura
    }

    fun setAltura(value: Double) {
        peso = value
    }

    fun getValorMensalidade(): Double {
        return this.valorMensalidade
    }

    fun setValorMensalidade(value: Double) {
        valorMensalidade = value
    }

    fun getDtVencimentoMensalidade(): Date {
        return this.dtVencimentoMensalidade
    }

    fun setDtVencimentoMensaldiade(value: Date) {
        dtVencimentoMensalidade = value
    }
}