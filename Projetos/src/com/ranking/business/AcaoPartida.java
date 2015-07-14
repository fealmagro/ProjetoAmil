package com.ranking.business;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

import org.eclipse.jdt.internal.compiler.ast.ThisReference;

import com.ranking.bean.Jogador;
import com.ranking.bean.Partida;

public class AcaoPartida {
	
	private static final AcaoPartida instance = new AcaoPartida();
	
	
	 
	private AcaoPartida() {}
	
	/**
	 * Obtém a instância da Business
	 * @return
	 */
	public static AcaoPartida getInstance() {
		return instance;
	}
    
		private static final String path = "C:\\Projetos\\src\\com\\ranking\\txt\\";
		private static final String arquivoJogadores = "jogadores";
		private static final String arquivoPartidas = "partidas";
		private static final String arquivoRanking = "ranking";
		private static List<String> arquivos = null;
		
		public static void criaArquivo(String dataComposta) throws IOException {
			
			List<String>lista = getArquivos();
			for(int i=0;i<lista.size();i++){
				
				String caminho = path+"\\"+dataComposta+"\\";
				File file = new File(caminho);				
				file.mkdirs();
				String caminhoEarquivo = path+"\\"+dataComposta+"\\"+arquivos.get(i)+"_"+dataComposta+".txt";
				file = new File(caminhoEarquivo);
				BufferedWriter writer = new BufferedWriter(new FileWriter(file));
				
				writer.flush();
				writer.close();
				
				System.out.println("Arquivo gravado em: " + caminhoEarquivo);
			
			}
		}
		
		public static void criaArquivosPartida(String dataComposta)throws IOException{
			
				criaArquivo(dataComposta);
			
		}
		
		public String iniciaPartida(Partida partida) throws Exception{
			
			String retorno = "";
			
			String dataComposta = formataDataParaNomeArquivo(new Date());
			File file = new File(path+"\\"+dataComposta+"\\"+arquivoPartidas+"_"+dataComposta+".txt");
			
			if(!file.exists()){
				criaArquivosPartida(dataComposta);
			}
			if(partida!=null&&partida.getJogadores()!=null&&partida.getJogadores().size()>0){
				BufferedWriter writer = new BufferedWriter(new FileWriter(file));
				for(int j=0;j<partida.getJogadores().size();j++){
					
					writer.write(new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(new Date()) + " - New match "+j+" has started");  
			        writer.newLine();  
			        writer.flush(); 
			        
			        
					for(int i=0;i<partida.getJogadores().size();i++){
						int indice = retornaIndice(partida.getJogadores().size());
						int indiceVitima = retornaIndiceDiferente(partida.getJogadores().size(),indice);
						int indiceArmaKiller = retornaIndice(partida.getJogadores().get(indice).getArmasJogador().size());
						if(partida.getJogadores().get(indice).getNomeJogador().equals("<WORLD>")){
							writer.write(new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(new Date())+" - "+partida.getJogadores().get(indice).getNomeJogador()+" killed "+partida.getJogadores().get(indiceVitima).getNomeJogador()+" by "+partida.getJogadores().get(indice).getArmasJogador().get(indiceArmaKiller).getNomeArma());
						}else{
							writer.write(new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(new Date())+" - "+partida.getJogadores().get(indice).getNomeJogador()+" killed "+partida.getJogadores().get(indiceVitima).getNomeJogador()+" using "+partida.getJogadores().get(indice).getArmasJogador().get(indiceArmaKiller).getNomeArma());
						}
						  
				        writer.newLine();  
				        writer.flush(); 
				        
				        
					}
					writer.write(new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(new Date())+" - Match "+j+" has ended");  
			        writer.newLine();  
			        writer.flush(); 
			        
				}
				writer.close();
			}else{
				retorno = "semJogadores";
			}
			
			return retorno;
			
		}
		


		public static List<String> getArquivos() {
			arquivos = new ArrayList<String>();
			arquivos.add(arquivoPartidas);
			arquivos.add(arquivoJogadores);
			arquivos.add(arquivoRanking);
			return arquivos;
		}
		
		public String formataDataParaNomeArquivo(Date data) throws Exception{
			
			String retorno = "";
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			String formatado = format.format(data);
			formatado = formatado.replaceAll("/", " ");
			String[] quebra  = formatado.split(" ");
			String dia = quebra[0];
			String mes = quebra[1];
			String ano = quebra[2];
			retorno = dia+"_"+mes+"_"+ano;
						
			return retorno;
			
		}
		
		public int retornaIndiceDiferente(int indice1,int indice2){
			
			int retorno = new Random().nextInt(indice1);
			if(retorno>=indice1&&retorno==indice2){
				while(retorno==indice2&&retorno>=indice1){
					retorno = new Random().nextInt(indice1);
					if(retorno<indice1&&retorno!=indice2){
						return retorno;
					}
				}
			}
			return retorno;
		}
		
		public int retornaIndice(int indice1){
			
			int retorno = new Random().nextInt(indice1);			
			
			return retorno;
		}

}
