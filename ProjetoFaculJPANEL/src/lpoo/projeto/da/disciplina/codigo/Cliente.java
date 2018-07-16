package lpoo.projeto.da.disciplina.codigo;

import java.util.*;

public class Cliente extends Pontos {
	Scanner in = new Scanner(System.in);
	private String nome;
	private int idade;
	private String CPF;
	public Calendar dataNascimento = Calendar.getInstance();
	private boolean ativo;

	public Cliente() {

	}

	public void Cadastro(String cPF, String nome, int dia, int mes, int ano) {
		CPF = cPF;
		this.nome = nome;
		this.dataNascimento.set(Calendar.DAY_OF_MONTH, dia);
		this.dataNascimento.set(Calendar.MONTH, mes);
		this.dataNascimento.set(Calendar.YEAR, ano);
		this.ativo = true;
		this.setPontos(50);
		this.setIdade(dataNascimento.get(Calendar.YEAR));
	}

	// ====================================================================================================
	// Gets e Sets
	// ====================================================================================================
	public String getNome() {

		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int ano) {
		int idadeC = 2018 - ano;
		this.idade = idadeC;
	}

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String CPF) {
		this.CPF = CPF;
	}

	public boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public String getDataNascimento() {
		return dataNascimento.get(Calendar.DAY_OF_MONTH) + "/" + dataNascimento.get(Calendar.MONTH) + "/"
				+ dataNascimento.get(Calendar.YEAR);
	}

	public void setDataNascimento(int dia, int mes, int ano) {
		this.dataNascimento.set(ano, mes, dia);

	}

}
