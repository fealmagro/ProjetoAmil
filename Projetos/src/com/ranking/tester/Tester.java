package com.ranking.tester;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.ranking.bean.Arma;
import com.ranking.bean.Jogador;
import com.ranking.bean.Partida;
import com.ranking.business.AcaoPartida;
import com.ranking.view.Painel;

public class Tester {

	private static final Tester instance = new Tester();
	
	
	 
	private Tester() {}
	
	/**
	 * Obtém a instância da Business
	 * @return
	 */
	public static Tester getInstance() {
		return instance;
	}
	private static List<String> nomes = new ArrayList<String>();
	private static List<String> idades = new ArrayList<String>();
	private static List<Arma> armas = new ArrayList<Arma>();
	
	
	public static void main(String[] args) {
		
		try {
			nomes = getInstance().getNomes();
			idades = getInstance().getIdades();
			armas = getInstance().getArmas();
			List<Jogador>jogadores = new ArrayList<Jogador>();
			for(int i=0;i<nomes.size();i++){
				Jogador jogador = new Jogador();
				
				jogador.setNomeJogador(nomes.get(i));
				
				int indiceIdade = new Random().nextInt(idades.size());
				jogador.setIdadeJogador(Integer.parseInt(idades.get(indiceIdade)));
				
				if(jogador.getNomeJogador().equalsIgnoreCase("<WORLD>")){
				
				List<Arma>listaArma = new ArrayList<Arma>();
					for(int j=0;j<armas.size();j++){
						
						if(armas.get(j).getNomeArma().equalsIgnoreCase("DROWN")){
							listaArma.add(armas.get(j));
						}
						
					}
					jogador.setArmasJogador(listaArma);
				}else{
					List<Arma>listaArma = new ArrayList<Arma>();
					for(int j=0;j<armas.size();j++){
					
						if(!armas.get(j).getNomeArma().equalsIgnoreCase("DROWN")){
							listaArma.add(armas.get(j));
						}
					}
					jogador.setArmasJogador(listaArma);
				}
				
				jogadores.add(jogador);
				
				
			}
			Partida partida = new Partida();
			partida.setJogadores(jogadores);
			
			AcaoPartida.getInstance().iniciaPartida(partida);
			Painel.getInstance().geraRanking();
		} catch (IOException e) {
			
			System.out.println("Erro ao manipular arquivo. Causado por: "+e.getMessage());
		} catch (Exception e) {

			System.out.println("Erro ao manipular data de arquivo. Causado por: "+e.getMessage());
		}

	}
	public List<String> getNomes() {
		
		nomes.add("<WORLD>");
		nomes.add("Joao");
		nomes.add("Felipe");
		nomes.add("Guilherme");
		nomes.add("Ariel");
		nomes.add("Silvia");
		nomes.add("Silvio");
		nomes.add("Hugo");
				
		return nomes;
	}

	public List<String> getIdades() {
		
		idades.add("18");
		idades.add("20");
		idades.add("21");
		idades.add("30");
		idades.add("26");
		idades.add("45");
		idades.add("24");
		
		return idades;
	}


	public List<Arma> getArmas() {
	
		Arma arma = new Arma();
		arma.setNomeArma("DROWN");

		armas.add(arma);
		arma = new Arma();
		arma.setNomeArma("M16");

		armas.add(arma);
		arma = new Arma();
		arma.setNomeArma("KNIFE");

		armas.add(arma);
		return armas;

	}
	
	

}
