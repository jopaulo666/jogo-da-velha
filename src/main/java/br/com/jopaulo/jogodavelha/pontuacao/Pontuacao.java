package br.com.jopaulo.jogodavelha.pontuacao;

import java.io.IOException;

import br.com.jopaulo.jogodavelha.core.Jogador;

public interface Pontuacao {

	public Integer getPontuacao(Jogador jogador);
	
	public void salvarPontuacao(Jogador jogador) throws IOException;
}
