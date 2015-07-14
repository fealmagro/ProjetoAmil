package com.ranking.view;

import java.io.*;
import java.text.SimpleDateFormat;
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
	
	/**
	 * Obtém a instância da Business
	 * @return
	 */
	public static Painel getInstance() {
		return instance;
	}
	private static final String path = "C:\\Projetos\\src\\com\\ranking\\txt\\";
	

	
	public void geraRanking()throws IOException,Exception{
				
		String dataComposta = AcaoPartida.getInstance().formataDataParaNomeArquivo(new Date());
		File file = new File(path+dataComposta+"\\"+"partidas"+"_"+dataComposta+".txt");
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
			for(int i=0;i<linhas.length;i++){
				

				String txtLinha = linhas[i];
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
		
		for(int m=0;m<listaPartida.size();m++){
			
			Partida partida = new Partida();
			partida = listaPartida.get(m);
			novaPartida = new Partida();
			novaPartida.setNumeroPartida(partida.getNumeroPartida());
			for(int i=0;i<partida.getAssassino().size();i++){
				
				List<String>nomes = new ArrayList<String>();
				Jogador jogador = new Jogador();
				jogador = partida.getAssassino().get(i);
				
				if(!nomes.contains(jogador.getNomeJogador())){
					
					nomes.add(jogador.getNomeJogador());
					if(!jogador.getNomeJogador().equals("<WORLD>")){
						int v = 0;
						for(int j=0;j<partida.getAssassino().size();j++){
							if(jogador.getNomeJogador().equals(partida.getAssassino().get(j).getNomeJogador())){
								
								v++;
								
							}				
						}
						jogador.setVitoriasJogador(v);
										
						
					}
					
					
				}
				listaAssassino.add(jogador);
				
			}
			
			novaPartida.setAssassino(listaAssassino);
			List<String>nomes = new ArrayList<String>();
			listaAssassino = new ArrayList<Jogador>();
			
			for(int i=0;i<partida.getVitima().size();i++){
				
				
				Jogador jogador = new Jogador();
				jogador = partida.getVitima().get(i);
				
				if(!nomes.contains(jogador.getNomeJogador())){
					
					nomes.add(jogador.getNomeJogador());
					
					int n = 0;
					for(int l=0;l<partida.getVitima().size();l++){
						
						if(jogador.getNomeJogador().equals(partida.getVitima().get(l).getNomeJogador())){
							
							n++;
						}
					}
					jogador.setDerrotasJogador(n);
									
						
					listaAssassino.add(jogador);
					
					
				}
				
				
			}
			
			novaPartida.setVitima(listaAssassino);
			
			novaListaPartida.add(novaPartida);
			
		}
		
		
		
		gravarArquivoRanking(novaListaPartida);
		
	}
	public void organizaJogadorByDerrota(List<Jogador>listaJogador)throws Exception{
		
		Collections.sort (listaJogador, new Comparator() {  
            public int compare(Object o1, Object o2) {  
                Jogador p1 = (Jogador) o1;  
                Jogador p2 = (Jogador) o2;  
                return p1.getDerrotasJogador() < p2.getDerrotasJogador() ? +1 : (p1.getDerrotasJogador() > p2.getDerrotasJogador() ? -1 : 0);  
            }  
        });  
		
	}
	public void organizaJogadorByVitoria(List<Jogador>listaJogador)throws Exception{
		
		Collections.sort (listaJogador, new Comparator() {  
            public int compare(Object o1, Object o2) {  
                Jogador p1 = (Jogador) o1;  
                Jogador p2 = (Jogador) o2;  
                return p1.getVitoriasJogador() < p2.getVitoriasJogador() ? +1 : (p1.getVitoriasJogador() > p2.getVitoriasJogador() ? -1 : 0);  
            }  
        });  
		
	}
	
	public void organizaPartida(List<Partida>listaPartida)throws Exception{
		
		Collections.sort (listaPartida, new Comparator() {  
            public int compare(Object o1, Object o2) {  
            	Partida p1 = (Partida) o1;  
            	Partida p2 = (Partida) o2;  
                return p1.getNumeroPartida() > p2.getNumeroPartida() ? -1 : (p1.getNumeroPartida() < p2.getNumeroPartida() ? +1 : 0);  
            }  
        });  
		
	}

	public void gravarArquivoRanking(List<Partida>listaPartida)throws IOException,Exception{
		
		String dataComposta = AcaoPartida.getInstance().formataDataParaNomeArquivo(new Date());
		File file = new File(path+dataComposta+"\\"+"ranking"+"_"+dataComposta+".txt");
		List<Jogador>listaJogador = new ArrayList<Jogador>();
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		
		for(int i=0;i<listaPartida.size();i++){
			listaJogador = new ArrayList<Jogador>();
			listaJogador = listaPartida.get(i).getAssassino();
			organizaJogadorByVitoria(listaJogador);
			List<String>nomes = new ArrayList<String>();
			if(listaJogador!=null&&listaJogador.size()>0){
				
				for(int h=0;h<listaJogador.size();h++){
					if(!nomes.contains(listaJogador.get(h).getNomeJogador())){
						nomes.add(listaJogador.get(h).getNomeJogador());
						if(h==0){
							writer.write("Partida número: "+listaPartida.get(i).getNumeroPartida());  
					        writer.newLine();
							writer.write("Jogador "+listaJogador.get(h).getNomeJogador()+" possui a maior quantidade de vitórias.");  
					        writer.newLine();  
					        writer.flush(); 
						}
						
						if(listaJogador.get(h).getVitoriasJogador()>0){
					        writer.write("Jogador "+listaJogador.get(h).getNomeJogador()+" Venceu "+listaJogador.get(h).getVitoriasJogador()+" vezes");  
					        writer.newLine();  
					        writer.flush(); 
						}
					}
					
				}
			}
			listaJogador = new ArrayList<Jogador>();
			listaJogador = listaPartida.get(i).getVitima();
			organizaJogadorByDerrota(listaJogador);
			nomes = new ArrayList<String>();
			if(listaJogador!=null&&listaJogador.size()>0){
				for(int h=0;h<listaJogador.size();h++){
					if(!nomes.contains(listaJogador.get(h).getNomeJogador())){
						nomes.add(listaJogador.get(h).getNomeJogador());
						if(h==0){
							writer.write("Partida número: "+listaPartida.get(i).getNumeroPartida());  
					        writer.newLine();
							writer.write("Jogador "+listaJogador.get(h).getNomeJogador()+" possui a maior quantidade de Derrotas.");  
					        writer.newLine();  
					        writer.flush(); 
						}
						
						if(listaJogador.get(h).getDerrotasJogador()>0){
					        writer.write("Jogador "+listaJogador.get(h).getNomeJogador()+" Foi morto "+listaJogador.get(h).getDerrotasJogador()+" vezes");  
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
