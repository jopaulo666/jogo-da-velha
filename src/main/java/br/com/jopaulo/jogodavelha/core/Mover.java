package br.com.jopaulo.jogodavelha.core;

public class Mover {
	
	private int x;
	private int y;
	
	public Mover(String moverStr) throws MovimentoInvalidoException {
		try {
			String[] tokens = moverStr.split(",");
			
			this.x = Integer.parseInt(tokens[0]);
			this.y = Integer.parseInt(tokens[1]);
		
		} catch (Exception e) {
			throw new MovimentoInvalidoException("Jogada inválida");
		}
		
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}

}
