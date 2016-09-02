package aed03;

import java.util.Scanner;

public class Menu {

	private static Scanner console = new Scanner(System.in);
	private static Arquivo<Colaborador> arquivo;

	public static void main(String[] args) {

		try {
			arquivo = new Arquivo<>("colaborador.db", Colaborador.class);

			// menu
			int opcao;
			do {
				System.out.println("\n\nCADASTRO DE COLABORADORES\n");
				System.out.println("1 - Listar colaborador");
				System.out.println("2 - Incluir colaborador");
				System.out.println("3 - Alterar colaborador");
				System.out.println("4 - Excluir colaborador");
				System.out.println("5 - Buscar colaborador");
				System.out.println("0 - Sair");
				System.out.print("\nOpcao: ");
				opcao = Integer.parseInt(console.nextLine());

				switch (opcao) {
				case 1:
					listarColaborador();
					break;
				case 2:
					incluirColaborador();
					break;
				case 3:
					alterarColaborador();
					break;
				case 4:
					excluirColaborador();
					break;
				case 5:
					buscarColaborador();
					break;
				case 0:
					break;
				default:
					System.out.println("Opção inválida");
				}

			} while (opcao != 0);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// colaborador

	public static void listarColaborador() throws Exception {

		Object[] colaborador = arquivo.listar();

		for (int i = 0; i < colaborador.length; i++) {
			System.out.println((Colaborador) colaborador[i]);
		}

	}

	public static void incluirColaborador() throws Exception {

		String nome, email;

		System.out.println("\nINCLUSÃO");
		System.out.print("Nome: ");
		nome = console.nextLine();
		System.out.print("Email: ");
		email = console.nextLine();

		System.out.print("\nConfirma inclusão? ");
		char confirma = console.nextLine().charAt(0);
		if (confirma == 's' || confirma == 'S') {
			Colaborador col = new Colaborador(-1, nome, email);
			int cod = arquivo.incluir(col);
			System.out.println("Colaborador incluído com código: " + cod);
		}
	}

	public static void alterarColaborador() throws Exception {

		System.out.println("\nALTERAÇÃO");

		int codigo;
		System.out.print("Código: ");
		codigo = Integer.valueOf(console.nextLine());
		if (codigo <= 0)
			return;

		Colaborador col;
		if ((col = arquivo.buscar(codigo)) != null) {
			System.out.println(col);

			String nome, email;

			System.out.print("\nNovo nome: ");
			nome = console.nextLine();
			System.out.print("Novo email: ");
			email = console.nextLine();

			System.out.print("\nConfirma alteração? ");
			char confirma = console.nextLine().charAt(0);
			if (confirma == 's' || confirma == 'S') {

				col.name = (nome.length() > 0 ? nome : col.name);
				col.email = (email.length() > 0 ? email : col.email);

				if (arquivo.alterar(col))
					System.out.println("Colaborador alterado.");
				else
					System.out.println("Colaborador não pode ser alterado.");
			}
		} else
			System.out.println("Colaborador não encontrado");

	}

	public static void excluirColaborador() throws Exception {

		System.out.println("\nEXCLUSÃO");

		int codigo;
		System.out.print("Código: ");
		codigo = Integer.valueOf(console.nextLine());
		if (codigo <= 0)
			return;

		Colaborador col;
		if ((col = arquivo.buscar(codigo)) != null) {
			System.out.println(col);
			System.out.print("\nConfirma exclusão? ");
			char confirma = console.nextLine().charAt(0);
			if (confirma == 's' || confirma == 'S') {
				if (arquivo.excluir()) {
					System.out.println("Colaborador excluído.");
				}
			}
		} else
			System.out.println("Colaborador não encontrado");

	}

	public static void buscarColaborador() throws Exception {

		System.out.println("\nBUSCA");

		int codigo;
		System.out.print("Código: ");
		codigo = Integer.valueOf(console.nextLine());
		if (codigo <= 0)
			return;

		Colaborador col;
		if ((col = arquivo.buscar(codigo)) != null)
			System.out.println(col);
		else
			System.out.println("Colaborador não encontrado");

	}

}
