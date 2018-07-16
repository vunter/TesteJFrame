package lpoo.projeto.da.disciplina.codigo;

public class Pontos extends Produtos {

	private int pontos;
	private int pontosUsados;

	public Pontos() {

	}

	public Pontos(int pontos, int pontosUsados) {
		super();
		this.pontos = pontos;
		this.pontosUsados = pontosUsados;
	}

	public int getPontos() {
		return pontos;
	}

	public void setPontos(int pontos) {
		this.pontos = pontos;
	}

	public int getPontosUsados() {
		return pontosUsados;
	}

	public void setPontosUsados(int pontosUsados) {
		this.pontosUsados = pontosUsados;
	}

}
