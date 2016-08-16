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


/*
 * Classe responsável por carregar dados das partidas.
 */
public class Tester {

	private static final Tester instance = new Tester();
	
	private Tester() {}

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
			for(int indiceJogadores=0;indiceJogadores<nomes.size();indiceJogadores++){
				Jogador jogador = new Jogador();
				
				jogador.setNomeJogador(nomes.get(indiceJogadores));
				
				int indiceIdade = new Random().nextInt(idades.size());
				jogador.setIdadeJogador(Integer.parseInt(idades.get(indiceIdade)));
				
				if(jogador.getNomeJogador().equalsIgnoreCase("<WORLD>")){
				
				List<Arma>listaArma = new ArrayList<Arma>();
					for(int indiceArma=0;indiceArma<armas.size();indiceArma++){
						
						if(armas.get(indiceArma).getNomeArma().equalsIgnoreCase("DROWN")){
							listaArma.add(armas.get(indiceArma));
						}
						
					}
					jogador.setArmasJogador(listaArma);
				}else{
					List<Arma>listaArma = new ArrayList<Arma>();
					for(int indiceArma=0;indiceArma<armas.size();indiceArma++){
					
						if(!armas.get(indiceArma).getNomeArma().equalsIgnoreCase("DROWN")){
							listaArma.add(armas.get(indiceArma));
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
	/*
	 * método responsável por carregar o nome dos jogadores para cada partida.
	 */
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
	/*
	 * método responsável por carregar a idade de cada jogador para cada partida.
	 */
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

	/*
	 * método responsável por carregar armas para cada jogador em cada uma das partidas
	 */
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
