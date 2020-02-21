package br.com.jopaulo.jogodavelha.core;

import br.com.jopaulo.jogodavelha.Contants;
import br.com.jopaulo.jogodavelha.ui.UI;

public class Tabuleiro {

	private char [][] matriz;
	
	public Tabuleiro() {
		matriz = new char [Contants.TAMANHO_TABULEIRO][Contants.TAMANHO_TABULEIRO];
		limpar();
	}
	
	public void limpar(){
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[i].length; j++) {
				matriz[i][j] = ' ';
			}
		}
	}
	
	public void imprimir() {
		UI.imprimirNovaLinha();
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[i].length; j++) {
				UI.imprimirTextoSemQuebraLinha(String.valueOf(matriz[i][j]));
				if (j < matriz[i].length -1) {
					UI.imprimirTextoSemQuebraLinha(" | ");
				}
			}
			UI.imprimirNovaLinha();
			if (i < matriz.length -1) {
				UI.imprimirTexto("---------");
			}
		}
	}
	
	public boolean tabuleiroCheio() {
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[i].length; j++) {
				if (matriz[i][j] == ' ') {
					return false;
				}
			}
		}	
		return true;
	}
	
	public boolean jogar(Jogador jogador, Mover mover) throws MovimentoInvalidoException {
		int i = mover.getY();
		int j = mover.getX();
		
		if (i < 0 || j < 0 || i > Contants.TAMANHO_TABULEIRO || j > Contants.TAMANHO_TABULEIRO) {
			throw new MovimentoInvalidoException("O intervalo da jogada é inválido");
		}
		
		if (matriz[i][j] != ' ') {
			throw new MovimentoInvalidoException("Jogada já realizada");
		}
		
		matriz[i][j] = jogador.getSimbolo();
		
		return checarLinhas(jogador) || checarColunas(jogador) || checarDiagonal1(jogador) || checarDiagonal2(jogador);
	}
	
	private boolean checarLinhas(Jogador jogador) {
		for (int i = 0; i < Contants.TAMANHO_TABULEIRO; i++) {
			if (checarLinha(i, jogador)) {
				return true;
			}
		}		
		return false;
	}
	
	private boolean checarLinha(int i, Jogador jogador) {
		char simbolo = jogador.getSimbolo();
		
		for (int j = 0; j < Contants.TAMANHO_TABULEIRO; j++) {
			if (matriz[i][j] != simbolo) {
				return false;
			}
		}		
		return true;
	}
	
	private boolean checarColunas(Jogador jogador) {
		for (int j = 0; j < Contants.TAMANHO_TABULEIRO; j++) {
			if (checarLinha(j, jogador)) {
				return true;
			}
		}		
		return false;
	}
	
	private boolean checarColuna(int j, Jogador jogador) {
		char simbolo = jogador.getSimbolo();
		
		for (int i = 0; i < Contants.TAMANHO_TABULEIRO; i++) {
			if (matriz[i][j] != simbolo) {
				return false;
			}
		}		
		return true;
	}
	
	private boolean checarDiagonal1(Jogador jogador) {
		char simbolo = jogador.getSimbolo();
		
		for (int i = 0; i < Contants.TAMANHO_TABULEIRO; i++) {
			if (matriz[i][i] != simbolo) {
				return false;
			}
		}
		return true;
	}
	
	private boolean checarDiagonal2(Jogador jogador) {
		char simbolo = jogador.getSimbolo();
		int ultimaLinha = Contants.TAMANHO_TABULEIRO - 1;
		
		for (int i = ultimaLinha, j = 0; i >= 0; i--, j++) {
			if (matriz[i][j] != simbolo) {
				return false;
			}
		}
		return true;
	}
}
