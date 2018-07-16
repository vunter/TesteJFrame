package lpoo.projeto.da.disciplina.codigo;


public class Produtos {	
	private int codigo;
	private double preco;
	
	
	public Produtos() {

	}

	public Produtos(int codigo, double preco) {
		super();
		this.codigo = codigo;
		this.preco = preco;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

}
