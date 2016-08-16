package com.ranking.bean;


import java.util.List;

public class Partida {
	
	private int numeroPartida;
	private List<Jogador> jogadores;//lista de jogadores dentro da partida
	private List<Jogador> assassino;//lista com jogadores que mataram na partida
	private List<Jogador> vitima;//lista com jogadores que morreram na partida
	
	public int getNumeroPartida() {
		return numeroPartida;
	}
	public void setNumeroPartida(int numeroPartida) {
		this.numeroPartida = numeroPartida;
	}
	public List<Jogador> getJogadores() {
		return jogadores;
	}
	public void setJogadores(List<Jogador> jogadores) {
		this.jogadores = jogadores;
	}
	public List<Jogador> getAssassino() {
		return assassino;
	}
	public void setAssassino(List<Jogador> assassino) {
		this.assassino = assassino;
	}
	public List<Jogador> getVitima() {
		return vitima;
	}
	public void setVitima(List<Jogador> vitima) {
		this.vitima = vitima;
	}	

}
