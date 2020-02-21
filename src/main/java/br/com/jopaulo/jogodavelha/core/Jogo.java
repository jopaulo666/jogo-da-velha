package br.com.jopaulo.jogodavelha.core;

import java.io.IOException;

import br.com.jopaulo.jogodavelha.Contants;
import br.com.jopaulo.jogodavelha.pontuacao.ArquivoDePontuacao;
import br.com.jopaulo.jogodavelha.pontuacao.Pontuacao;
import br.com.jopaulo.jogodavelha.ui.UI;

public class Jogo {
	
	private Tabuleiro tabuleiro = new Tabuleiro();
	private Jogador[] jogadores = new Jogador[Contants.SIMBOLO_JOGADORES.length];
	private int indexjogadorAtual = -1;
	private Pontuacao pontuacao;

	public void play() throws IOException {
		pontuacao = criarPontuacao();
		
		UI.imprimirTituloJogo();
		
		for (int i = 0; i < jogadores.length; i++) {
			jogadores[i] = criarJogador(i);
		}
		
		boolean fimJogo = false;
		Jogador jogadorAtual = proximoJogador();
		Jogador vencedor = null;
		
		while (!fimJogo) {
			tabuleiro.imprimir();
			
			boolean encontroSequencia;			
			try {
				encontroSequencia = jogadorAtual.jogar();
			}catch (MovimentoInvalidoException e) {
				UI.imprimirTexto("Erro: " + e.getMessage());
				continue;
			}
			
			if (encontroSequencia) {
				fimJogo = true;
				vencedor = jogadorAtual;
			} else if (tabuleiro.tabuleiroCheio()) {
				fimJogo = true;			
			} else {
				jogadorAtual = proximoJogador();				
			}
			
		}
		
		if (vencedor == null) {
			UI.imprimirTexto("Jogo empatado");
		} else {
			UI.imprimirTexto("O jogador '" + vencedor.getNome() + "' venceu a partida!");
		}
		
		tabuleiro.imprimir();
		UI.imprimirTexto("Fim do Jogo  ");
	}
	
	private Jogador criarJogador(int index) {
		String nome = UI.lerEntrada("Jogador " + (index + 1) + " =>");
		char simbolo = Contants.SIMBOLO_JOGADORES[index];
		Jogador jogador = new Jogador(nome, tabuleiro, simbolo);
		
		UI.imprimirTexto("O jogador '" + nome + "' vai usar o símbolo '" + simbolo + "'");
		
		return jogador;
	}
	
	private Jogador proximoJogador() {
//		jogadorAtual++;
//		if (jogadorAtual >= jogadores.length) {
//			jogadorAtual = 0;
//		}
//		return jogadores[jogadorAtual];
		
		indexjogadorAtual = (indexjogadorAtual +1) % jogadores.length;
		return jogadores[indexjogadorAtual];
	}
	
	private Pontuacao criarPontuacao() throws IOException {
		return new ArquivoDePontuacao();
	}
}
