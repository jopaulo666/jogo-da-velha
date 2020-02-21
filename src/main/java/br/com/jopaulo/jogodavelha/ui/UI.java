package br.com.jopaulo.jogodavelha.ui;

import br.com.softblue.commons.io.Console;

public class UI {

	public static void imprimirTexto(String texto) {
		System.out.println(texto);
	}
	
	public static void imprimirTextoSemQuebraLinha(String texto) {
		System.out.print(texto);
	}
	
	public static void imprimirNovaLinha() {
		System.out.println();
	}
	
	public static void imprimirTituloJogo() {
		imprimirTexto("==================");
		imprimirTexto("| JOGO DA VELHA	|");
		imprimirTexto("==================");
		imprimirNovaLinha();
	}
	
	public static String lerEntrada(String texto) {
		imprimirTextoSemQuebraLinha(texto + " ");
		return Console.readString();
	}
}
