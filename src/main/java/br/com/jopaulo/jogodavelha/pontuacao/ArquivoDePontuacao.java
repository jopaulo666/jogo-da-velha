package br.com.jopaulo.jogodavelha.pontuacao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import br.com.jopaulo.jogodavelha.core.Jogador;

public class ArquivoDePontuacao implements Pontuacao {
	
	private static final Path ARQUIVO_PONTUACAO = Path.of("pontuacao.txt");
	private Map<String, Integer> pontuacaoMap = new HashMap<>();

	public ArquivoDePontuacao() throws IOException {
		configuracao();
	}
	
	private void configuracao() throws IOException {
		if (!Files.exists(ARQUIVO_PONTUACAO)) {
			Files.createFile(ARQUIVO_PONTUACAO);
		}
		
		try (BufferedReader reader = Files.newBufferedReader(ARQUIVO_PONTUACAO)){
			String linha;
			
			while ((linha = reader.readLine()) != null) {
				String[] tokes = linha.split("\\|");
				
				pontuacaoMap.put(tokes[0], Integer.parseInt(tokes[1]));
			}
		}
	}

	@Override
	public Integer getPontuacao(Jogador jogador) {
		return pontuacaoMap.get(jogador.getNome().toUpperCase());
	}

	@Override
	public void salvarPontuacao(Jogador jogador) throws IOException {
		Integer pontuacao = getPontuacao(jogador);
		
		if (pontuacao == null) {
			pontuacao = 0;
		}
		
		pontuacaoMap.put(jogador.getNome().toUpperCase(), pontuacao + 1);
		
		try (BufferedWriter writer = Files.newBufferedWriter(ARQUIVO_PONTUACAO)){
			Set<Map.Entry<String, Integer>> entries = pontuacaoMap.entrySet();
			
			for (Map.Entry<String, Integer> entry : entries) {
				String nome = entry.getKey().toUpperCase();
				Integer s = entry.getValue();
				writer.write(nome + "|" + s);
				writer.newLine();
			}
		}

	}

}
