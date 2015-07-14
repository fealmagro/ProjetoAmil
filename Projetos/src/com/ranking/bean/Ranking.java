package com.ranking.bean;

public class Ranking {
	
	private int qtdJogadorPartida;//maior numero de jogadores por partida
	private int qtdJogadorDia;// maior numero de jogadores num dia
	private int qtdVitoriaConsecJogador;//maior numero de vitorias sem morte de um jogador
	private int qtdVitoriaConsecJogadorDia;//maior numero de vitorias sem morte de um jogador num dia
	private Jogador vencedorDia;//jogador com maior numero de vitorias no dia
	private Jogador vencedor;//jogador com maior numero de vitorias no jogo
	private Arma armaFavorita;//arma mais utilizada no jogo
	private Arma armaFavoritaDia;//arma mais utilizada no dia
	public int getQtdJogadorPartida() {
		return qtdJogadorPartida;
	}
	public void setQtdJogadorPartida(int qtdJogadorPartida) {
		this.qtdJogadorPartida = qtdJogadorPartida;
	}
	public int getQtdJogadorDia() {
		return qtdJogadorDia;
	}
	public void setQtdJogadorDia(int qtdJogadorDia) {
		this.qtdJogadorDia = qtdJogadorDia;
	}
	public int getQtdVitoriaConsecJogador() {
		return qtdVitoriaConsecJogador;
	}
	public void setQtdVitoriaConsecJogador(int qtdVitoriaConsecJogador) {
		this.qtdVitoriaConsecJogador = qtdVitoriaConsecJogador;
	}
	public int getQtdVitoriaConsecJogadorDia() {
		return qtdVitoriaConsecJogadorDia;
	}
	public void setQtdVitoriaConsecJogadorDia(int qtdVitoriaConsecJogadorDia) {
		this.qtdVitoriaConsecJogadorDia = qtdVitoriaConsecJogadorDia;
	}
	public Jogador getVencedorDia() {
		return vencedorDia;
	}
	public void setVencedorDia(Jogador vencedorDia) {
		this.vencedorDia = vencedorDia;
	}
	public Jogador getVencedor() {
		return vencedor;
	}
	public void setVencedor(Jogador vencedor) {
		this.vencedor = vencedor;
	}
	public Arma getArmaFavorita() {
		return armaFavorita;
	}
	public void setArmaFavorita(Arma armaFavorita) {
		this.armaFavorita = armaFavorita;
	}
	public Arma getArmaFavoritaDia() {
		return armaFavoritaDia;
	}
	public void setArmaFavoritaDia(Arma armaFavoritaDia) {
		this.armaFavoritaDia = armaFavoritaDia;
	}
	
	
	

}
