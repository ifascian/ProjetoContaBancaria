package conta;

import java.io.IOException;
import java.util.Scanner;

import conta.controller.ContaController;
import conta.model.ContaCorrente;
import conta.model.ContaPoupanca;
import conta.util.Cores;

public class Menu {
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ContaController contas = new ContaController();
		Scanner input = new Scanner(System.in);
		
		int opcao, numero, agencia, tipo, aniversario, numeroDestino;
		String titular;
		float saldo, limite, valor;
		
		System.out.println("\nCriar Contas\n");
		
		ContaCorrente cc1 = new ContaCorrente(contas.gerarNumero(), 123, 1, "João Silva", 1000f, 100.0f);
		contas.cadastrar(cc1);
		ContaCorrente cc2 = new ContaCorrente(contas.gerarNumero(), 124, 1, "Maria da Silva", 2000f, 100.0f);
		contas.cadastrar(cc2);
		ContaPoupanca cp1 = new ContaPoupanca(contas.gerarNumero(), 125, 2, "Marina dos Santos", 4000f, 12);
		contas.cadastrar(cp1);
		ContaPoupanca cp2 = new ContaPoupanca(contas.gerarNumero(), 125, 2, "Juliana Ramos", 8000f, 15);
		contas.cadastrar(cp2);
		contas.listarTodas();
		
		while(true) {
			
				System.out.println(Cores.TEXT_YELLOW + Cores.ANSI_BLACK_BACKGROUND
								+  "");
				System.out.println("                                      ");
				System.out.println("        Banco do Brazil com Z         ");
				System.out.println("                                      ");
				System.out.println("**************************************");
				System.out.println("                                      ");
				System.out.println("   1 - Criar conta                    ");
				System.out.println("   2 - Listar todas as contas         ");
				System.out.println("   3 - Buscar conta por número        ");
				System.out.println("   4 - Atualizar dados da conta       ");
				System.out.println("   5 - Apagar conta                   ");
				System.out.println("   6 - Sacar                          ");
				System.out.println("   7 - Depositar                      ");
				System.out.println("   8 - Transferir valores entre contas");
				System.out.println("   9 - Sair                           ");
				System.out.println("                                      ");
				System.out.println("**************************************");
				System.out.println("Entre com a opção desejada:           ");
				System.out.println("                                      ");
			
			opcao = input.nextInt();

			
			if (opcao == 9) {
				System.out.println(Cores.TEXT_WHITE_BOLD + "\nBanco do Brazil com Z - O seu futuro começa aqui!");
				sobre();
				input.close();
				System.exit(0);
			}
			
			switch(opcao){
				
			case 1:
				
				System.out.println(Cores.TEXT_WHITE + "Criar conta\n\n");
				System.out.println("Digite o número da agência: ");
				agencia = input.nextInt();
				
				System.out.println("Digite o número do titular: ");
				input.skip("\\R?");
				titular = input.nextLine();
				
				do {
					System.out.println("Digite o tipo da sua conta (1 - CC ou 2 - CP)");
					tipo  = input.nextInt();
				}while(tipo < 1 && tipo > 2);
				
				System.out.println("Digite saldo da conta (R$): ");
				saldo = input.nextFloat();
				
				switch(tipo) {
				
				case 1 -> {
					System.out.println("Digite o limite do crédito (R$): ");
					limite = input.nextFloat();
					contas.cadastrar(new ContaCorrente(contas.gerarNumero(), agencia, tipo, titular, saldo, limite));
				}
				
				case 2 -> {
					System.out.println("Digite o dia do aniversário da conta: ");
					aniversario = input.nextInt();
					contas.cadastrar(new ContaPoupanca(contas.gerarNumero(), agencia, tipo, titular, saldo, aniversario));				}
			}
				keyPress();
					break;
					
			case 2:
				System.out.println(Cores.TEXT_WHITE + "Listar todas as contas\n\n");
				contas.listarTodas();
				keyPress();
					break;
					
			case 3:
				
				System.out.println(Cores.TEXT_WHITE +"Consultar dados da conta\n\n");
				
				System.out.println("Digite o número da conta: ");
				numero = input.nextInt();
				
				contas.procurarPorNumero(numero);
				
				keyPress();
					break;
				
			case 4:	
				
				System.out.println(Cores.TEXT_WHITE + "Atualizar dados da conta\n\n");
				
				System.out.println("Digite o número da conta: ");
				numero = input.nextInt();
				
				var buscaConta = contas.buscarNaCollection(numero);
				
				if(buscaConta != null) {
					tipo = buscaConta.getTipo();
					
				System.out.println("Digite o número da Agência: ");
					agencia = input.nextInt();
					
				System.out.println("Digite o nome do titular: ");
				input.skip("\\R?");	
				titular = input.nextLine();
				
				System.out.println("Digite saldo da conta (R$): ");
				saldo = input.nextFloat();
				
				switch(tipo) {
				
					case 1 -> {
						System.out.println("Digite o limite do crédito (R$): ");
						limite = input.nextFloat();
						
						contas.atualizar(new ContaCorrente (numero, agencia, tipo, titular,saldo, limite));
					}
					case 2 -> {
						System.out.println("Digite o dia do aniversário da conta: ");
						aniversario = input.nextInt();
						
						contas.atualizar(new ContaPoupanca(numero, agencia, tipo, titular, saldo, aniversario) );
					}
					default -> {
						System.out.println("Tipo de conta inválido!");
					}
						
				}
			}else {
				System.out.println("A conta não foi encontrada!");
			}
				keyPress();
					break;
					
			case 5:
					
				System.out.println(Cores.TEXT_WHITE + "Apagar conta\n\n");
				
				System.out.println("Digite o número da conta: ");
				numero = input.nextInt();
				
				contas.deletar(numero);
				
				keyPress();
					break;
					
			case 6:
					
				System.out.println(Cores.TEXT_WHITE + "Saque\n\n");
				
				System.out.println("Digite o número da conta: ");
				numero = input.nextInt();
				
				do {
					System.out.println("Digite o valor do saque (R$): ");
					valor = input.nextFloat();
				} while(valor <= 0);
				
				contas.sacar(numero, valor);
				
				keyPress();
					break;
				
			case 7:	
					
				System.out.println(Cores.TEXT_WHITE + "Depósito\n\n");
				
				System.out.println("Digite o número da conta: ");
				numero = input.nextInt();
				
				do {
					System.out.println("Digite o valor do depósito (R$): ");
					valor = input.nextFloat();
				}while(valor <= 0);
				
				contas.depositar(numero, valor);
				
				keyPress();
					break;
					
			case 8:	
					
				System.out.println(Cores.TEXT_WHITE + "transferência entre contas\n\n");
				
				System.out.println("Digite o número da conta de origem: ");
				numero = input.nextInt();
				
				System.out.println("Digite o número da conta de destino: ");
				numeroDestino = input.nextInt();
				
				do {
					System.out.println("Digite o valor da transferência (R$): ");
					valor = input.nextFloat();
				}while(valor <= 0);
				
				contas.transferir(numero, numeroDestino, valor);
				
				keyPress();
					break;
					
			default:
				
				System.out.println(Cores.TEXT_RED_BOLD + "\nOpção inválida!\n");
					break;
			}
			
		}
	}

public static void sobre() {
	
	System.out.println("\n**");
	System.out.println("Projeto desenvolvido por:");
	System.out.println("Isabelly Fasciana - isabellyfasciana12@gmail.com");
	System.out.println("github.com/ifascian");
	System.out.println("");
	}

public static void keyPress() {
	
	try {
		
		System.out.println(Cores.TEXT_RESET + "\n\nPressione Enter para continuar...");
		System.in.read();
		
	}catch  (IOException e){
		
		System.out.println("Você pressionou uma tecla diferente de enter!");
		
		}
	}
}