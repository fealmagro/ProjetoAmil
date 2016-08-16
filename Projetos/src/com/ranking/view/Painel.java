package com.ranking.view;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.ranking.bean.Arma;
import com.ranking.bean.Jogador;
import com.ranking.bean.Partida;
import com.ranking.business.AcaoPartida;

public class Painel{

	private static final Painel instance = new Painel();
	
	private Painel() {}
	
	public static Painel getInstance() {
		return instance;
	}
	private static final String path = "C:\\Projetos\\src\\com\\ranking\\txt\\";
	

	
	public void geraRanking()throws IOException,Exception{
				
		String dataComposta = AcaoPartida.getInstance().formataDataParaNomeArquivo(new Date());
		File file = new File(path+dataComposta+"\\"+"partidas"+"_"+dataComposta+".txt");
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(file).useDelimiter("\\ \\n");

		while(scanner.hasNext()){
			String texto = scanner.next();
			System.out.println(texto);
			String[] linhas = texto.split("\\n");
			System.out.println(linhas[0]);
			List<Partida>listaPartida = new ArrayList<Partida>();
			Partida partida = new Partida();
			List<Jogador> listaAssassino=new ArrayList<Jogador>();
			List <Jogador> listaVitimas=new ArrayList<Jogador>();
			for(int indiceLinhas=0;indiceLinhas<linhas.length;indiceLinhas++){
				

				String txtLinha = linhas[indiceLinhas];
				String[] linha = txtLinha.split(" ");

				if(!linha[3].equalsIgnoreCase("new")&&!linha[3].equalsIgnoreCase("Match")){

										
					Jogador assassino = new Jogador();
					Jogador vitima = new Jogador();
					Arma arma = new Arma();
					assassino.setNomeJogador(linha[3]);
					arma.setNomeArma(linha[7]);
					assassino.setArmaUsada(arma);
					vitima.setNomeJogador(linha[5]);					
					listaAssassino.add(assassino);
					listaVitimas.add(vitima);
					


				}else if(linha[3].equalsIgnoreCase("new")){
					partida = new Partida();
					partida.setNumeroPartida(Integer.parseInt(linha[5]));
					listaAssassino=new ArrayList<Jogador>();
				    listaVitimas=new ArrayList<Jogador>();
					
				}else if(linha[3].equalsIgnoreCase("Match")){
					partida.setAssassino(listaAssassino);
					partida.setVitima(listaVitimas);
					listaPartida.add(partida);
					
					partida = new Partida();
				}

			}

			geraDadosRanking(listaPartida);
			
		}
		
	}
	
	public void geraDadosRanking(List<Partida> listaPartida)throws IOException,Exception{
		
		
		
		List<Jogador> listaAssassino = new ArrayList<Jogador>();
		List<Partida> novaListaPartida = new ArrayList<Partida>();
		Partida novaPartida = new Partida();
		
		for(int indicePartidas=0;indicePartidas<listaPartida.size();indicePartidas++){
			
			Partida partida = new Partida();
			partida = listaPartida.get(indicePartidas);
			novaPartida = new Partida();
			novaPartida.setNumeroPartida(partida.getNumeroPartida());
			for(int indiceAssassinos=0;indiceAssassinos<partida.getAssassino().size();indiceAssassinos++){
				
				List<String>nomes = new ArrayList<String>();
				Jogador jogador = new Jogador();
				jogador = partida.getAssassino().get(indiceAssassinos);
				
				if(!nomes.contains(jogador.getNomeJogador())){
					
					nomes.add(jogador.getNomeJogador());
					if(!jogador.getNomeJogador().equals("<WORLD>")){
						int indiceVitorias = 0;
						for(int indiceAssassinoPartida=0;indiceAssassinoPartida<partida.getAssassino().size();indiceAssassinoPartida++){
							if(jogador.getNomeJogador().equals(partida.getAssassino().get(indiceAssassinoPartida).getNomeJogador())){
								
								indiceVitorias++;
								
							}				
						}
						jogador.setVitoriasJogador(indiceVitorias);
										
						
					}
					
					
				}
				listaAssassino.add(jogador);
				
			}
			
			novaPartida.setAssassino(listaAssassino);
			List<String>nomes = new ArrayList<String>();
			listaAssassino = new ArrayList<Jogador>();
			
			for(int indiceVitimas=0;indiceVitimas<partida.getVitima().size();indiceVitimas++){
				
				
				Jogador jogador = new Jogador();
				jogador = partida.getVitima().get(indiceVitimas);
				
				if(!nomes.contains(jogador.getNomeJogador())){
					
					nomes.add(jogador.getNomeJogador());
					
					int indiceDerrotasJogador = 0;
					for(int indiceVitimasPartida=0;indiceVitimasPartida<partida.getVitima().size();indiceVitimasPartida++){
						
						if(jogador.getNomeJogador().equals(partida.getVitima().get(indiceVitimasPartida).getNomeJogador())){
							
							indiceDerrotasJogador++;
						}
					}
					jogador.setDerrotasJogador(indiceDerrotasJogador);
									
						
					listaAssassino.add(jogador);
					
					
				}
				
				
			}
			
			novaPartida.setVitima(listaAssassino);
			
			novaListaPartida.add(novaPartida);
			
		}
		
		
		
		gravarArquivoRanking(novaListaPartida);
		
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void organizaJogadorByDerrota(List<Jogador>listaJogador)throws Exception{
		
		Collections.sort (listaJogador, new Comparator() {  
            public int compare(Object objeto1, Object objeto2) {  
                Jogador jogador1 = (Jogador) objeto1;  
                Jogador jogador2 = (Jogador) objeto2;  
                return jogador1.getDerrotasJogador() < jogador2.getDerrotasJogador() ? +1 : (jogador1.getDerrotasJogador() > jogador2.getDerrotasJogador() ? -1 : 0);  
            }  
        });  
		
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void organizaJogadorByVitoria(List<Jogador>listaJogador)throws Exception{
		
		Collections.sort (listaJogador, new Comparator() {  
            public int compare(Object objeto1, Object objeto2) {  
                Jogador jogador1 = (Jogador) objeto1;  
                Jogador jogador2 = (Jogador) objeto2;  
                return jogador1.getVitoriasJogador() < jogador2.getVitoriasJogador() ? +1 : (jogador1.getVitoriasJogador() > jogador2.getVitoriasJogador() ? -1 : 0);  
            }  
        });  
		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void organizaPartida(List<Partida>listaPartida)throws Exception{
		
		Collections.sort (listaPartida, new Comparator() {  
            public int compare(Object objeto1, Object objeto2) {  
            	Partida partida1 = (Partida) objeto1;  
            	Partida partida2 = (Partida) objeto2;  
                return partida1.getNumeroPartida() > partida2.getNumeroPartida() ? -1 : (partida1.getNumeroPartida() < partida2.getNumeroPartida() ? +1 : 0);  
            }  
        });  
		
	}

	public void gravarArquivoRanking(List<Partida>listaPartida)throws IOException,Exception{
		
		String dataComposta = AcaoPartida.getInstance().formataDataParaNomeArquivo(new Date());
		File file = new File(path+dataComposta+"\\"+"ranking"+"_"+dataComposta+".txt");
		List<Jogador>listaJogador = new ArrayList<Jogador>();
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		
		for(int indicePartida=0;indicePartida<listaPartida.size();indicePartida++){
			listaJogador = new ArrayList<Jogador>();
			listaJogador = listaPartida.get(indicePartida).getAssassino();
			organizaJogadorByVitoria(listaJogador);
			List<String>nomes = new ArrayList<String>();
			if(listaJogador!=null&&listaJogador.size()>0){
				
				for(int indiceJogador=0;indiceJogador<listaJogador.size();indiceJogador++){
					if(!nomes.contains(listaJogador.get(indiceJogador).getNomeJogador())){
						nomes.add(listaJogador.get(indiceJogador).getNomeJogador());
						if(indiceJogador==0){
							writer.write("Partida número: "+listaPartida.get(indicePartida).getNumeroPartida());  
					        writer.newLine();
							writer.write("Jogador "+listaJogador.get(indiceJogador).getNomeJogador()+" possui a maior quantidade de vitórias.");  
					        writer.newLine();  
					        writer.flush(); 
						}
						
						if(listaJogador.get(indiceJogador).getVitoriasJogador()>0){
					        writer.write("Jogador "+listaJogador.get(indiceJogador).getNomeJogador()+" Venceu "+listaJogador.get(indiceJogador).getVitoriasJogador()+" vezes");  
					        writer.newLine();  
					        writer.flush(); 
						}
					}
					
				}
			}
			listaJogador = new ArrayList<Jogador>();
			listaJogador = listaPartida.get(indicePartida).getVitima();
			organizaJogadorByDerrota(listaJogador);
			nomes = new ArrayList<String>();
			if(listaJogador!=null&&listaJogador.size()>0){
				for(int indiceJogador=0;indiceJogador<listaJogador.size();indiceJogador++){
					if(!nomes.contains(listaJogador.get(indiceJogador).getNomeJogador())){
						nomes.add(listaJogador.get(indiceJogador).getNomeJogador());
						if(indiceJogador==0){
							writer.write("Partida número: "+listaPartida.get(indicePartida).getNumeroPartida());  
					        writer.newLine();
							writer.write("Jogador "+listaJogador.get(indiceJogador).getNomeJogador()+" possui a maior quantidade de Derrotas.");  
					        writer.newLine();  
					        writer.flush(); 
						}
						
						if(listaJogador.get(indiceJogador).getDerrotasJogador()>0){
					        writer.write("Jogador "+listaJogador.get(indiceJogador).getNomeJogador()+" Foi morto "+listaJogador.get(indiceJogador).getDerrotasJogador()+" vezes");  
					        writer.newLine();  
					        writer.flush(); 
						}
					}
				}
			}
			
		}
		
		
		writer.close();
	}
	

}
