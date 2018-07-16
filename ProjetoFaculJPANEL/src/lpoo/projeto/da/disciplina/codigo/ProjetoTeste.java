package lpoo.projeto.da.disciplina.codigo;

import java.util.*;
import java.sql.*;

public class ProjetoTeste {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Conexao bd = new Conexao(); // Cria Objeto de conexão
		
		try {
			
			bd.conectar(); // tenta conectar ao banco de dados
			System.out.println(bd.estaConectado()); // Confirma e printa se está conectado

			if ("Conectado com sucesso!".equals(bd.estaConectado())) {
				Venda v = new Venda();
				boolean confirmacao = true;

				System.out.println(
						"============================================================================================\nPrograma Iniciado, para finalizar insira 9\n============================================================================================");
				while (confirmacao) {
					int cod = 0;

					System.out.println("Insira o código da operação: (utilize 0 para vizualizar todos os comandos)");
					cod = in.nextInt();
					if (cod != 9) {
						switch (cod) {
						case 0:
							System.out.println("Códigos de operação: " + "\n1: Iniciar venda de cliente cadastrado."
									+ "\n2: Iniciar venda de cliente não cadastrado." + "\n3: Cadastrar novo cliente. "
									+ "\n4: Status do usuário." + "\n5: Descadastrar cliente."
									+ "\n6: Pesquisar por nome." + "\n8: Listar Clientes."
									+ "\n9: Finalizar programa.");
							break;
						case 1: // Iniciar venda de cliente cadastrado
							System.out.println("Insira CPF: ");
							String cpf = in.next();
							try {
								String cpfConsultado = "";
								String buscaCPF = "SELECT * FROM Clientes WHERE cpf = '" + cpf + "'";
								bd.resultSet = bd.statement.executeQuery(buscaCPF);
								if (bd.resultSet != null && bd.resultSet.next()) {
									cpfConsultado = bd.resultSet.getString("cpf");
									v.setPontos(bd.resultSet.getInt("pontos"));
									v.setAtivo(bd.resultSet.getBoolean("ativo"));
								}
								if (cpfConsultado.equals(cpf)) {

									System.out.println(
											"Iniciando venda...\nUtilize o código de produto '0' para finalizar.");
									cod = 1;
									while (cod != 0) {
										System.out.println("Insira o código do produto: ");
										cod = in.nextInt();

										try {
											String buscaPontos = "SELECT pontos FROM Clientes WHERE cpf = '"
													+ cpfConsultado + "'";
											bd.resultSet = bd.statement.executeQuery(buscaPontos);
											if (bd.resultSet != null && bd.resultSet.next()) {
												v.setPontos(bd.resultSet.getInt("pontos"));
											}
										} catch (Exception e) {

										}
										try {

											String query = "SELECT preco FROM Produtos WHERE codigo = '" + cod + "';";
											bd.resultSet = bd.statement.executeQuery(query);
											if (bd.resultSet != null && bd.resultSet.next()) {
												double preco = bd.resultSet.getDouble("preco");
												v.setPreco(preco);
											}

										} catch (Exception e) {

										}
										if (cod != 0) {
											v.IniciarVenda(v.getPreco());
										}
									}

									if (v.getCodigo() == 0) {
										v.finalizarVenda();
										try {
											String setPontos = "UPDATE Clientes SET pontos = '" + v.getPontos()
													+ "' WHERE cpf = '" + cpfConsultado + "'";
											bd.statement.executeUpdate(setPontos);
										} catch (Exception e) {
											System.out.println("Erro ao atualizar pontos: " + e.getMessage());
										}
									}
								} else {
									System.out.println("Cliente não encontrado no banco de dados!");
								}
							} catch (Exception e) {
								System.out.println("Não foi possivel iniciar a venda: " + e);
							}
							break;
						case 2: // Iniciar venda de não cadastrado.
							System.out.println("Iniciando venda...\nUtilize o código de produto '0' para finalizar.");
							cod = 1;
							while (cod != 0) {
								System.out.println("Insira o código do produto: ");
								cod = in.nextInt();
								try {

									String query = "SELECT preco FROM Produtos WHERE codigo = '" + cod + "';";
									bd.resultSet = bd.statement.executeQuery(query);
									if (bd.resultSet != null && bd.resultSet.next()) {
										double preco = bd.resultSet.getDouble("preco");
										v.setPreco(preco);
									}

								} catch (Exception e) {

								}
								if (cod != 0) {
									v.IniciarVenda(v.getPreco());
								}
							}
							if (v.getCodigo() == 0) {
								v.finalizarVenda();
							}
							break;
						case 3:// Cadastrar

							System.out.println("Insira o CPF:");
							v.setCPF(in.next());
							System.out.printf("Insira o nome\n");
							v.setNome(in.next());
							System.out.println("Insira o sobrenome");
							v.setNome(v.getNome() + " " + in.next());
							if (v.getNome() != null) {
								System.out.println("Insira a data de nascimento (DD/MM/AAAA)");
								v.setDataNascimento(in.nextInt(), in.nextInt(), in.nextInt());
								v.setAtivo(true);
								v.setPontos(50);
								v.setIdade(v.dataNascimento.get(Calendar.YEAR));
							}
							bd.cadastrarCliente(v.getCPF(), v.getNome(), v.getIdade(), v.getDataNascimento(),
									v.getPontos());
							break;
						case 4: // Status
							System.out.println("Insira o CPF para consulta: ");
							String cpfStatus = in.next();
							bd.statusCliente(cpfStatus);
							break;
						case 5: // Descadastrar Cliente.
							System.out.println("Insira CPF do cliente:");
							String cpfDescadastrar = in.next();
							bd.descadastrarCliente(cpfDescadastrar);
							break;
						case 6: // Pesquisar por nome
							System.out.println("Insira nome para pesquisa:");
							bd.pesquisarClientePorNome(in.next());
							break;
						case 8:
							bd.listarNomes();
							break;
						case 9:
							confirmacao = false;
							break;
						default:
							break;
						}

					} else {
						confirmacao = false;
					}
				}
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		if ("Conectado com sucesso!".equals(bd.estaConectado())) {
			bd.desconectar();
		}
	}

}
