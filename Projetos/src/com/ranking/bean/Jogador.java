package com.ranking.bean;

import java.util.List;

public class Jogador {

	private String nomeJogador;//nome do jogador
	private int idadeJogador;//idade do jogador
	private List <Arma> armasJogador;//lista de armas do jogador na partida
	private Arma armaUsada;
	private int vitoriasJogador;//numero de vitorias do jogador
	private int derrotasJogador;//numero de derrotas do jogador
	private Arma armaFavorita;//arma mais utilizada pelo jogador
	
	public String getNomeJogador() {
		return nomeJogador;
	}
	public void setNomeJogador(String nomeJogador) {
		this.nomeJogador = nomeJogador;
	}
	public int getIdadeJogador() {
		return idadeJogador;
	}
	public void setIdadeJogador(int idadeJogador) {
		this.idadeJogador = idadeJogador;
	}
	public List<Arma> getArmasJogador() {
		return armasJogador;
	}
	public void setArmasJogador(List<Arma> armasJogador) {
		this.armasJogador = armasJogador;
	}
	public Arma getArmaUsada() {
		return armaUsada;
	}
	public void setArmaUsada(Arma armaUsada) {
		this.armaUsada = armaUsada;
	}
	public int getVitoriasJogador() {
		return vitoriasJogador;
	}
	public void setVitoriasJogador(int vitoriasJogador) {
		this.vitoriasJogador = vitoriasJogador;
	}
	public int getDerrotasJogador() {
		return derrotasJogador;
	}
	public void setDerrotasJogador(int derrotasJogador) {
		this.derrotasJogador = derrotasJogador;
	}
	public Arma getArmaFavorita() {
		return armaFavorita;
	}
	public void setArmaFavorita(Arma armaFavorita) {
		this.armaFavorita = armaFavorita;
	}
}
