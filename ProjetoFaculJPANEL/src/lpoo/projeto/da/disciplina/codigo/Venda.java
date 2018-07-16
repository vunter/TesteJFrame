package lpoo.projeto.da.disciplina.codigo;

public class Venda extends Cliente implements Comandos {

	private double somaPrecos = 0;
	private double desconto;
	private double valorFinal;

	public Venda() {

	}

	public Venda(double somaPrecos, double desconto, double valorFinal) {
		super();
		this.somaPrecos = somaPrecos;
		this.desconto = desconto;
		this.valorFinal = valorFinal;
	}

	@Override
	public void IniciarVenda(double preco) {
		this.somarPrecos(preco);
	}

	@Override
	public void Status() {
		System.out.println("Cliente " + getNome() + "\nIdade: " + getIdade() + "\nCPF: " + getCPF() + "\nAtivo: "
				+ getAtivo() + "\nData de Nascimento/Idade: " + getDataNascimento() + "--" + this.getIdade()
				+ " Anos \nPontos: " + getPontos() + "\nPontos Usados: " + getPontosUsados());

	}

	public void somarPrecos(double preco) {
		this.setSomaPrecos(preco + this.getSomaPrecos());
		System.out.println("Preço do produto: R$" + this.getPreco() + "\nSubtotal: R$" + this.getSomaPrecos());

	}

	public void finalizarVenda() {

		if (this.getAtivo() && this.getPontos() > 0) {
			this.setDesconto(this.getPontos() * 0.20);
			System.out.println("Você possui " + this.getPontos() + " pontos, que equivalem a R$" + this.getDesconto()
					+ "\nDeseja utilizar o desconto?");
			if ("S".equals(in.next()) || "s".equals(in.next())) {
				this.setValorFinal(this.getSomaPrecos() - this.getDesconto());
				this.setPontosUsados(this.getPontos() + this.getPontosUsados());
				this.setPontos(0);

			} else {
				this.setValorFinal(this.getSomaPrecos());
			}
			this.setPontos(((int) this.getSomaPrecos() / 4) + this.getPontos());
		} else {
			this.setValorFinal(this.getSomaPrecos());
		}
		System.out.println("Valor total: R$" + this.getValorFinal());
		this.setSomaPrecos(0);

	}

	// =====================================================================
	// GETS E SETS
	// =====================================================================
	public double getSomaPrecos() {
		return somaPrecos;
	}

	public void setSomaPrecos(double somaPrecos) {
		this.somaPrecos = somaPrecos;
	}

	public double getDesconto() {
		return desconto;
	}

	public void setDesconto(double desconto) {
		this.desconto = desconto;
	}

	public double getValorFinal() {
		return valorFinal;
	}

	public void setValorFinal(double valorFinal) {
		this.valorFinal = valorFinal;
	}

}
