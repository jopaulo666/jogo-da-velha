package br.com.jopaulo.jogodavelha.core;

import br.com.jopaulo.jogodavelha.ui.UI;

public class Jogador {

	private String nome;
	private Tabuleiro tabuleiro;
	private char simbolo;	
	
	public Jogador(String nome, Tabuleiro tabuleiro, char simbolo) {
		this.nome = nome;
		this.tabuleiro = tabuleiro;
		this.simbolo = simbolo;
	}

	private Mover lerMovimento() throws MovimentoInvalidoException {
		String moverStr = UI.lerEntrada("Jogador '" + nome + "' =>");
		return new Mover(moverStr);
	}
	
	public boolean jogar() throws MovimentoInvalidoException {
		Mover mover = lerMovimento();
		return tabuleiro.jogar(this, mover);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Tabuleiro getTabuleiro() {
		return tabuleiro;
	}

	public void setTabuleiro(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
	}

	public char getSimbolo() {
		return simbolo;
	}

	public void setSimbolo(char simbolo) {
		this.simbolo = simbolo;
	}	
}
