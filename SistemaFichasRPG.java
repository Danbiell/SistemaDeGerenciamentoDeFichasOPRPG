package aa;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;


public class SistemaFichasRPG {
	
	static Scanner sc = new Scanner(System.in);
	static List<Object> personagensTotal = new ArrayList<>();
	static List<String> periciasMensagemRetorno = new ArrayList<>();

	public static int menu() {
		int menuOption = -1;
		try {
			System.out.print("\n\nMENU RPG SYSTEM 32:\n\n"
					+ "1- Criar nova ficha\n"
					+ "2- Mostrar fichas disponíveis\n"
					+ "3- Excluir ficha\n"
					+ "4- Modificar ficha\n"
					+ "5- Reinciar menu\n"
					+ "0- Encerrar sistema\n\n"
					+ "Opção: ");
			menuOption = sc.nextInt();
			menuOption = validNums(0, 5, menuOption);
		
			return menuOption;
		}
		
		catch(InputMismatchException ime) {
			System.out.println("\nErro. Tipo de inserção inválida para o menu (somente números inteiros)\n");
			sc.next();
			sc.nextLine();
			
			return 5;
		}
	}
	
	public static int validNums(int min, int max, int opcaoValid) {
		
		while (opcaoValid < min || opcaoValid > max) {
			try {
				System.out.print("\nInserção inválida... Insira novamente: ");
				opcaoValid = sc.nextInt();
								
			} catch(InputMismatchException ime) {
				System.out.print("\nInsira somente números. \n");
				sc.nextLine();
			}
		}
		return opcaoValid;
	}
	
	public static int validInput() {
		boolean inputCorreto = false;
		int valorInt = 0;
		
		while(inputCorreto == false) {
			try {
				valorInt = sc.nextInt();
				inputCorreto = true;
			} catch(InputMismatchException ime) {
				System.out.print("\nTipo de Inserção Inválida. Insira somente números: \n\n");
				sc.next();
				sc.nextLine();
			}
		}
		return valorInt;
	}
	
	public static List<Object> criarFicha() {
		
		List<Object> personagemAtual = new ArrayList<>();
		List<Integer> saude = new ArrayList<>();
		
		String nome, origem, criador, historico, hobbies;
		int nivel = 1, nex = 0, classe, pv = 0, pe = 0, san = 0, options, idade;
		sc.nextLine();
		
		System.out.print("\nInsira o nome do personagem: ");
		nome = sc.nextLine();
		personagemAtual.add(nome);
		
		System.out.print("\nInsira o nome de quem criou: ");
		criador = sc.nextLine();
		personagemAtual.add(criador);
		
		System.out.print("\nInsira o nível do personagem (1 a 20): ");
		nivel = validInput();
		nivel = validNums(1, 20, nivel);
		personagemAtual.add(nivel);
		
		System.out.print("\nInsira o NEX (Nível de Exposição Paranormal) do personagem (0 a 99%): ");
		nex = validInput();
		nex = validNums(0, 99, nex);
		personagemAtual.add(nex);
		

		personagemAtual.add(atributosDefinir(nivel));
		
		System.out.print("\nInsira a origem do personagem: ");
		origem = sc.nextLine();
		personagemAtual.add(origem);
		
		System.out.print("\n\nSelecione sua classe:\n\n"
				+ "1- Combatente (++ vida / -- pericias / -- PE)\n"
				+ "2- Especialista (+- vida / ++ pericias / +- PE)\n"
				+ "3- Ocultista (-- vida / +- pericias / ++ PE)\n\nOpção: ");
		classe = validInput();
		classe = validNums(1, 3, classe);
		if(classe == 1) {
			personagemAtual.add("Combatente");
		}
		if(classe == 2) {
			personagemAtual.add("Especialista");
		}
		if(classe == 3) {
			personagemAtual.add("Ocultista");
		}
		
		System.out.print("\nDefinir atributos de saúde automaticamente?\n\n1- Sim\n0- Não\n\nOpção: ");
		options = validInput();
		options = validNums(0, 1, options);
		
		if(options == 1) {
			List<Integer> atributosExtrair = (List<Integer>) personagemAtual.get(4);
			int vigor = (int) atributosExtrair.get(2);
			int presenca = (int) atributosExtrair.get(4);
			if(classe == 1) {
				pv = (20 + vigor) + ((4 + vigor) * (nivel - 1));
				pe = ((2 + presenca) * (nivel));
				san = (12 + (3 * (nivel - 1)));
				
				System.out.println("\nPV: -- " + pv + "/" + pv + " --\n"
						+ "PE: -- " + pe + "/" + pe + " --\n"
						+ "SAN: -- " + san + "/" + san + " --\n");		}
			if(classe == 2) {
				pv = (16 + vigor) + ((3 + vigor) * (nivel - 1));
				pe = ((3 + presenca) * (nivel));
				san = (16 + (4 * (nivel - 1)));
				
				System.out.println("\nPV: -- " + pv + "/" + pv + " --\n"
								+ "PE: -- " + pe + "/" + pe + " --\n"
								+ "SAN: -- " + san + "/" + san + " --\n");
			}
			if(classe == 3) {
				pv = (12 + vigor) + ((2 + vigor) * (nivel - 1));
				pe = ((4 + presenca) * (nivel));
				san = (20 + (5 * (nivel - 1)));
				
				System.out.println("\nPV: -- " + pv + "/" + pv + " --\n"
						+ "PE: -- " + pe + "/" + pe + " --\n"
						+ "SAN: -- " + san + "/" + san + " --\n");		
			}
		} else {
			options = 0;
			while(options == 0) {
				System.out.print("\n\nInsira a quantidade de PVs totais: ");
				pv = validInput();
				System.out.print("\nInsira a quantidade de PEs totais: ");
				pe = validInput();
				System.out.print("\nInsira a quantidade de SAN total: ");
				san = validInput();
			
				System.out.print("\n\nA saúde foi inserida corretamente?\n\n1- Sim\n0- Não\n\nOpção: ");
				options = validInput();
				options = validNums(0, 1, options);
			}
		}
		Collections.addAll(saude, pv, pe, san);
		personagemAtual.add(saude);
		
		personagemAtual.add(periciasDefinir(personagemAtual));
		
		personagemAtual.add(inventarioDefinir(personagemAtual));
		
		System.out.print("\nInsira a idade do seu personagem (17 a 125 anos. Insira '0' para desconhecido): ");
		idade = validInput();
		if(idade == 0) {
			personagemAtual.add(null);
		}
		else {
			idade = validNums(17, 125, idade);
			personagemAtual.add(idade);
		}		
		
		System.out.print("\n\nLista: " + personagemAtual);
		return personagemAtual;
	}
	
	public static List<Integer> atributosDefinir(int nivel) {

		List<Integer> atributos = new ArrayList<>();
		
		int ptsAtributo = 0, valFOR = 1, valAGI = 1, valVIG = 1, valINT = 1, valPRE = 1, optsAtributo = 0, fatorRepet = 0;
		boolean tem0 = false;
		
		atributos.clear();
		if(nivel < 4) {
			Collections.addAll(atributos, 1, 1, 1, 1, 1, 4);
			ptsAtributo = 4;
		} else if(nivel < 10) {
			Collections.addAll(atributos, 1, 1, 1, 1, 1, 5);
			ptsAtributo = 5;
		} else if(nivel < 16) {
			Collections.addAll(atributos, 1, 1, 1, 1, 1, 6);
			ptsAtributo = 6;
		} else if(nivel < 19) {
			Collections.addAll(atributos, 1, 1, 1, 1, 1, 7);
			ptsAtributo = 7;
		} else {
			Collections.addAll(atributos, 1, 1, 1, 1, 1, 8);
			ptsAtributo = 8;
		}
		
		System.out.print("\nVocê deseja usar a quantidade de pontos de atributo automática?\n\n1- Sim\n0- Não\n\nOpção: ");
		optsAtributo = validInput();
		optsAtributo = validNums(0, 1, optsAtributo);
		
		if(optsAtributo == 0) {
			System.out.print("\nInsira a quantidade de pontos de atributo para distribuir: ");
			ptsAtributo = validInput();
			atributos.set(5, ptsAtributo);
		}
		
		while(fatorRepet == 0) {
			while(ptsAtributo > 0 || optsAtributo == 0) {
				System.out.println("\n\nLista: " + atributos);
				System.out.print("Você possui " + ptsAtributo + " pontos de atributo para distribuir. Selecione:\n\n"
						+ "1- FORÇA - (" + valFOR + ")\n"
						+ "2- AGILIDADE - (" + valAGI + ")\n"
						+ "3- VIGOR - (" + valVIG + ")\n"
						+ "4- INTELECTO - (" + valINT + ")\n"
						+ "5- PRESENÇA - (" + valPRE + ")\n"
						+ "0- TIRAR UM PONTO\n\n"
						+ "Opção: ");
				optsAtributo = validInput();
				optsAtributo = validNums(0, 5, optsAtributo);
				switch(optsAtributo) {
					case 1:
						if(ptsAtributo == 0) {
							System.out.println("Sem pontos restantes! Retire de um atributo para adicionar em outro.\n");
						} else {
							valFOR ++;
							ptsAtributo --;
						}
						break;
					case 2:
						if(ptsAtributo == 0) {
							System.out.println("Sem pontos restantes! Retire de um atributo para adicionar em outro.\n");
						} else {
							valAGI ++;
							ptsAtributo --;
						}
						break;
					case 3:
						if(ptsAtributo == 0) {
							System.out.println("Sem pontos restantes! Retire de um atributo para adicionar em outro.\n");
						} else {
							valVIG ++;
							ptsAtributo --;
						}
						break;
					case 4:
						if(ptsAtributo == 0) {
							System.out.println("Sem pontos restantes! Retire de um atributo para adicionar em outro.\n");
						} else {
							valINT ++;
							ptsAtributo --;
						}
						break;
					case 5:
						if(ptsAtributo == 0) {
							System.out.println("Sem pontos restantes! Retire de um atributo para adicionar em outro.");
						} else {
							valPRE ++;
							ptsAtributo --;
						}
						break;
					case 0:
						System.out.print("Selecione o atributo para remover 1 ponto (0 retorna ao menu de atributos) : ");
						optsAtributo = validInput();
						optsAtributo = validNums(0, 5, optsAtributo);
						switch(optsAtributo) {
							case 1:
								if(valFOR == 0) {
									System.out.println("\nNão é possível reduzir um atributo abaixo de 0.");
								} else if(tem0 == true && valFOR == 1) {
									System.out.println("\nNão é possível reduzir mais de um atributo a 0.");
								} else {
									valFOR --;
									ptsAtributo ++;
								}
								break;
							case 2:
								if(valAGI == 0) {
									System.out.println("\nNão é possível reduzir um atributo abaixo de 0.");
								} else if(tem0 == true && valAGI == 1) {
									System.out.println("\nNão é possível reduzir mais de um atributo a 0.");
								} else {
									valAGI --;
									ptsAtributo ++;
								}
								break;
							case 3:
								if(valVIG == 0) {
									System.out.println("\nNão é possível reduzir um atributo abaixo de 0.");
								} else if(tem0 == true && valVIG == 1) {
									System.out.println("\nNão é possível reduzir mais de um atributo a 0.");
								} else {
									valVIG --;
									ptsAtributo ++;
								}
								break;
							case 4:
								if(valINT == 0) {
									System.out.println("\nNão é possível reduzir um atributo abaixo de 0.");
								} else if(tem0 == true && valINT == 1) {
									System.out.println("\nNão é possível reduzir mais de um atributo a 0.");
								} else {
									valINT --;
									ptsAtributo ++;
								}
								break;
							case 5:
								if(valPRE == 0) {
									System.out.println("\nNão é possível reduzir um atributo abaixo de 0.");
								} else if(tem0 == true && valPRE == 1) {
									System.out.println("\nNão é possível reduzir mais de um atributo a 0.");
								} else {
									valPRE --;
									ptsAtributo ++;
								}
								break;
							case 0:
								break;
							default:
								System.out.print("Erro.");								
						}
						if(valFOR == 0 || valAGI == 0 || valVIG == 0 || valINT == 0 || valPRE == 0) {
							tem0 = true;
						}
						break;
					default:
						System.out.print("\n\nALGO CORREU ERRADO\n\n");
						break;
						
				}
				atributos.set(0, valFOR);
				atributos.set(1, valAGI);
				atributos.set(2, valVIG);
				atributos.set(3, valINT);
				atributos.set(4, valPRE);
				atributos.set(5, ptsAtributo);
			}
		
			System.out.print("\nVocê possui 0 pontos atualmente. "
					+ "Seus atributos são:\n\n"
						+ "FOR - (" + valFOR + ")\n"
						+ "AGI - (" + valAGI + ")\n"
						+ "VIG - (" + valVIG + ")\n"
						+ "INT - (" + valINT + ")\n"
						+ "PRE - (" + valPRE + ")\n\n"
					+ "Está satisfeito com seus atributos?\n\n"
					+ "1- Sim\n"
					+ "0- Não\n\nOpção: ");
			fatorRepet = validInput();
			fatorRepet = validNums(0, 1, fatorRepet);
			switch(fatorRepet) {
				case 1:
					System.out.println("\nProsseguindo criação da ficha...\n");
					break;
				case 0:
					System.out.println("\nAlterando atributos... \n");
					optsAtributo = 0;
					break;
			}
		}
		
		return atributos;
	}
	
	public static List<Object> periciasDefinir(List<Object> personagemAtual) {
		
		List<Integer> periciasMod = new ArrayList<>();
		List<Character> periciasTreinamento = new ArrayList<>();
		List<Object> periciasAtual = new ArrayList<>();
		
		int options = 0, qtdPericiasT = 0, qtdPericiasV = 0, qtdPericiasE = 0, grauT = 1, periciasEscolherVariavel = 0, periciasRemoverVariavel = 0;
		System.out.print("\n\nDefinir quantidade de perícias automaticamente (contando com origem)?\n\n1- Sim\n0- Não\n\nOpção: ");
		options = validInput();
		options = validNums(0, 1, options);
		
		periciasTreinamento.clear();
		periciasMod.clear();
		
		Collections.addAll(periciasTreinamento, 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'N');
		Collections.addAll(periciasMod, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
		
		System.out.print("Listas: " + periciasTreinamento + " " + periciasMod);
		
		int nivel = (int) personagemAtual.get(2);
		if(nivel < 7) {
			grauT = 1;
		} else if(nivel < 14) {
			grauT = 2;
		} else {
			grauT = 3;
		}
		
		if(options == 1) {
			
			List<Integer> atributosExtrair = (List<Integer>) personagemAtual.get(4);
			int intelecto = (int) atributosExtrair.get(3);
			
			if(personagemAtual.get(6) == "Combatente") {
				System.out.print("\nEscolha uma perícia entre Luta e Pontaria:\n\n1- Luta\n2- Pontaria\n\nOpção: ");
				options = validInput();
				options = validNums(1, 2, options);
				if(options == 1) {
					periciasTreinamento.set(14, 'T');
				} else {
					periciasTreinamento.set(18, 'T');
				}
				
				System.out.print("\n\nEscolha uma perícia entre Fortitude e Reflexos:\n\n1- Fortitude\n2- Reflexos\n\nOpção: ");
				options = validInput();
				options = validNums(1, 2, options);
				if(options == 1) {
					periciasTreinamento.set(9, 'T');
				} else {
					periciasTreinamento.set(20, 'T');
				}
				
				qtdPericiasT = 3 + intelecto; 
				if(grauT == 2) {
					qtdPericiasV = 2 + intelecto;
				}
				if(grauT == 3) {
					qtdPericiasV = 2 + intelecto;
					qtdPericiasE = 2 + intelecto;
				}
				
			}
			if(personagemAtual.get(6) == "Especialista") {
				qtdPericiasT = 9 + intelecto;
				if(grauT == 2) {
					qtdPericiasV = 5 + intelecto;
				}
				if(grauT == 3) {
					qtdPericiasV = 5 + intelecto;
					qtdPericiasE = 5 + intelecto;
				}
			}
			if(personagemAtual.get(6) == "Ocultista") {
				periciasTreinamento.set(16, 'T');
				periciasTreinamento.set(25, 'T');
				
				qtdPericiasT = 5 + intelecto;
				if(grauT == 2) {
					qtdPericiasV = 5 + intelecto;
				}
				if(grauT == 3) {
					qtdPericiasV = 3 + intelecto;
					qtdPericiasE = 3 + intelecto;
				}
			}
		} else {
			System.out.print("\n\nEscolha a quantidade de perícias do seu personagem (Treinado):");
			qtdPericiasT = validInput();
			System.out.print("\nEscolha a quantidade de perícias do seu personagem (Veterano):");
			qtdPericiasV = validInput();
			System.out.print("\nEscolha a quantidade de perícias do seu personagem (Expert):");
			qtdPericiasE = validInput();
		}
		System.out.print("\nExibir regras para a seleção de perícias?\n\n1- Sim\n0- Não\n\nOpção: ");
		options = validInput();
		options = validNums(0, 1, options);
		
		if(options == 1) {
			System.out.print("\n\nREGRAS PARA SELEÇÃO DE PERÍCIAS:\n\n"
					+ "1. BÔNUS POR TREINAMENTO:\n"
					+ " -> Se o treinamento for N, o bônus = 0\n"
					+ " -> Se o treinamento for T, o bônus = + 5\n"
					+ " -> Se o treinamento for V, o bônus = + 10\n"
					+ " -> Se o treinamento for E, o bônus = + 15\n\n"
					+ "2. SUBIR GRAU DE TREINAMENTO:\n"
					+ " -> Só é possível tornar uma perícia em Treinado [T] se ela for Nula [N]\n"
					+ " -> Só é possível tornar uma perícia em Veterano [V] se ela for Treinado [T]\n"
					+ " -> Só é possível tornar uma perícia em Expert [E] se ela for Veterano [V]" );
		} else {
			options = 1;
		}
		
		while((qtdPericiasT + qtdPericiasV + qtdPericiasE) > 0 || options == 0) {
			System.out.print( "\n\nRestam ainda:\n" 
							+ "  -->  Perícias para selecionar como (Treinado) [T]: " + qtdPericiasT
							+ "\n"
							+ "  -->  Perícias para selecionar como (Veterano) [V]: " + qtdPericiasV
							+ "\n"
							+ "  -->  Perícias para selecionar como (Expert)   [E]: " + qtdPericiasE
					+ " \n\n"
					+ "(0)   Desfazer uma perícia\n"
					+ "(1)   Acrobacia:     (" + periciasTreinamento.get(0) +  ")\n"
					+ "(2)   Adestramento:  (" + periciasTreinamento.get(1) +  ")\n"
					+ "(3)   Artes:         (" + periciasTreinamento.get(2) +  ")\n"
					+ "(4)   Atletismo:     (" + periciasTreinamento.get(3) +  ")\n"
					+ "(5)   Atualidades:   (" + periciasTreinamento.get(4) +  ")\n"
					+ "(6)   Ciências:      (" + periciasTreinamento.get(5) +  ")\n"
					+ "(7)   Crime:         (" + periciasTreinamento.get(6) +  ")\n"
					+ "(8)   Diplomacia:    (" + periciasTreinamento.get(7) +  ")\n"
					+ "(9)   Enganação:     (" + periciasTreinamento.get(8) +  ")\n"
					+ "(10)  Fortitude:     (" + periciasTreinamento.get(9) +  ")\n"
					+ "(11)  Furtividade:   (" + periciasTreinamento.get(10) + ")\n"
					+ "(12)  Intimidação:   (" + periciasTreinamento.get(11) + ")\n"
					+ "(13)  Intuição:      (" + periciasTreinamento.get(12) + ")\n"
					+ "(14)  Investigação:  (" + periciasTreinamento.get(13) + ")\n"
					+ "(15)  Luta:          (" + periciasTreinamento.get(14) + ")\n"
					+ "(16)  Medicina:      (" + periciasTreinamento.get(15) + ")\n"
					+ "(17)  Ocultismo:     (" + periciasTreinamento.get(16) + ")\n"
					+ "(18)  Percepção:     (" + periciasTreinamento.get(17) + ")\n"
					+ "(19)  Pontaria:      (" + periciasTreinamento.get(18) + ")\n"
					+ "(20)  Profissão:     (" + periciasTreinamento.get(19) + ")\n"
					+ "(21)  Reflexos:      (" + periciasTreinamento.get(20) + ")\n"
					+ "(22)  Religião:      (" + periciasTreinamento.get(21) + ")\n"
					+ "(23)  Sobrevivência: (" + periciasTreinamento.get(22) + ")\n"
					+ "(24)  Tática:        (" + periciasTreinamento.get(23) + ")\n"
					+ "(25)  Tecnologia:    (" + periciasTreinamento.get(24) + ")\n"
					+ "(26)  Vontade:       (" + periciasTreinamento.get(25) + ")\n"
					+ "\nOpção: ");
			options = validInput();
			options = validNums(0, 26, options);
			
			if(options > 0 && options < 27) {
				periciasEscolherVariavel = periciasEscolher(options, qtdPericiasT, qtdPericiasV, qtdPericiasE, periciasTreinamento);
					if (periciasEscolherVariavel == 1) {
						qtdPericiasT --;
					} else if(periciasEscolherVariavel == 2) {
						qtdPericiasV --;
					} else if (periciasEscolherVariavel == 3) {
						qtdPericiasE --;
					} else {
						
					}
			} else if(options == 0) {
					System.out.print("\nEscolha uma perícia para reduzir seu grau de treinamento: ");
					options = validInput();
					options = validNums(1, 26, options);
					
					periciasRemoverVariavel = periciasRemover(options, periciasTreinamento);
					
					if(periciasRemoverVariavel == 1) {
						qtdPericiasT ++;
					}
					if(periciasRemoverVariavel == 2) {
						qtdPericiasV ++;
					}
					if(periciasRemoverVariavel == 3) {
						qtdPericiasE ++;
					} else {
						System.out.println(periciasMensagemRetorno.get(0));
					}
			} else {
					System.out.print("\n\n\n----------------------- Erro ------------------------ \n\n\n");
			}
			
			if((qtdPericiasT + qtdPericiasV + qtdPericiasE) == 0) {
				System.out.print("\n\nSeus pontos de perícia acabaram. Você realizou todas as alterações desejadas corretamente?\n\n1- Sim\n0- Não\n\nOpção: ");
				options = validInput();
				options = validNums(0, 1, options);
				
				if(options == 1 && (qtdPericiasT + qtdPericiasV + qtdPericiasE) > 0) {
					System.out.print("Ainda há pontos de perícia restantes. Gaste todos os pontos para prosseguir...\n");
				}
			}
		}
		
		periciasAtual.add(periciasTreinamento);
		periciasAtual.add(periciasMod);
		
		return periciasAtual;
		
	}
	
	public static int periciasEscolher(int option, int qtdPericiasT, int qtdPericiasV, int qtdPericiasE, List<Character> periciasTreinamento) {
		
		option --;
		
		if(periciasTreinamento.get(option) == 'E') {
			periciasMensagemRetorno.set(0, "\nVocê já possui essa perícia no maior grau de treinamento. Selecione outra... \n");
		} 
		if(periciasTreinamento.get(option) == 'V' && qtdPericiasE > 0) {
			periciasTreinamento.set(option, 'E');
			return 3;
		} else if(qtdPericiasE == 0) {
			periciasMensagemRetorno.set(0, "\nVocê não possui pontos de Expert para melhorar seu treinamento nesta perícia. Selecione outra: \n");
		}
		if(periciasTreinamento.get(option) == 'T' && qtdPericiasV > 0) {
			periciasTreinamento.set(option, 'V');
			return 2;
		} else if(qtdPericiasV == 0) {
			periciasMensagemRetorno.set(0, "\nVocê não possui pontos de Veterano para melhorar seu treinamento nesta perícia. Selecione outra: \n");
		}
		if(periciasTreinamento.get(option) == 'N' && qtdPericiasT > 0) {
			periciasTreinamento.set(option, 'T');
			return 1;
		} else if(periciasTreinamento.get(0) == 0) {
			periciasMensagemRetorno.set(0, "\nVocê não possui pontos de Treinado para melhorar seu treinamento nesta perícia. Selecione outra: \n");
		}
		
		return 0;
	}
	
	public static int periciasRemover(int option, List<Character> periciasTreinamento) {
		
		option --;
		
		
		if(periciasTreinamento.get(option) == 'E') {
			periciasTreinamento.set(option, 'V');
			return 3;
		} 
		if(periciasTreinamento.get(option) == 'V') {
			periciasTreinamento.set(option, 'T');
			return 2;
		}
		if(periciasTreinamento.get(option) == 'T') {
			periciasTreinamento.set(option, 'N');
			return 1;
		}
		if(periciasTreinamento.get(option) == 'N') {
			periciasMensagemRetorno.set(0, "\nNão é possível reduzir o grau de treinamento da perícia, pois não há grau algum nela. \n");
		}
		
		return 0;
	}
	public static List<Object> inventarioItemUnitario(List<Object> personagemAtual){
		
		
		List<Object> inventarioItem = new ArrayList<>();
		List<Object> periciasAtual = (List<Object>) personagemAtual.get(8);
		List<Integer> periciasMod = (List<Integer>) periciasAtual.get(1);
		
		String nome, aprimoramentos, descricao;
		int tipo, peso, categoria, options, optionsPericias, modPericiasAntigo, modPericiasNovo;
		
		System.out.print("\nInsira o nome do item: ");
		sc.nextLine();
		nome = sc.nextLine();
		inventarioItem.add(nome);
		
		System.out.print("Insira o tipo do item:\n\n1- Arma\n2- Acessório\n3- Item Operacional\n4- Item Paranormal\n5- Item Amaldiçoado\n\nOpção: ");
		tipo = validInput();
		tipo = validNums(1, 5, tipo);
		
		switch(tipo) {
			case 1:
				inventarioItem.add("Arma");
				break;
			case 2:
				inventarioItem.add("Acessório");
				break;
			case 3:
				inventarioItem.add("Item Operacional");
				break;
			case 4:
				inventarioItem.add("Item Paranormal");
				break;
			case 5:
				inventarioItem.add("Item Amaldiçoado");
				break;
		}
		
		System.out.print("\nInsira os aprimoramentos do item (insira '0' para 'nenhum'): ");
		sc.nextLine();
		aprimoramentos = sc.nextLine();
		inventarioItem.add(aprimoramentos);
		
		if(inventarioItem.get(inventarioItem.size() - 1).equals("0")) {
			inventarioItem.set(inventarioItem.size() - 1, null);
			
		}
			
		System.out.print("\nInsira o peso do item (de 0 a 10): ");
		peso = validInput();
		peso = validNums(0, 10, peso);
		inventarioItem.add(peso);
		
		System.out.print("\nInsira o custo em categoria do item: ");
		categoria = validInput();
		categoria = validNums(0, 4, categoria);
		switch(categoria) {
		case 1:
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
		}
		inventarioItem.add(categoria);
		
		if(tipo == 2) {
			System.out.print("\nO acessório fornecerá um modificador em testes de perícia?\n\n1- Sim\n0- Não\n\nOpção: ");
			options = validInput();
			options = validNums(0, 1, options);
			
			int contador = 1;
			switch(options) {
			case 1:
				while(options != 0 && contador <= 2) {
					System.out.print("\nSelecione a perícia para adicionar o modificador:\n\n"
							+ "(0)   Cancelar adição de bônus\n"
							+ "(1)   Acrobacia:     ( " + periciasMod.get(0) +  " )\n"
							+ "(2)   Adestramento:  ( " + periciasMod.get(1) +  " )\n"
							+ "(3)   Artes:         ( " + periciasMod.get(2) +  " )\n"
							+ "(4)   Atletismo:     ( " + periciasMod.get(3) +  " )\n"
							+ "(5)   Atualidades:   ( " + periciasMod.get(4) +  " )\n"
							+ "(6)   Ciências:      ( " + periciasMod.get(5) +  " )\n"
							+ "(7)   Crime:         ( " + periciasMod.get(6) +  " )\n"
							+ "(8)   Diplomacia:    ( " + periciasMod.get(7) +  " )\n"
							+ "(9)   Enganação:     ( " + periciasMod.get(8) +  " )\n"
							+ "(10)  Fortitude:     ( " + periciasMod.get(9) +  " )\n"
							+ "(11)  Furtividade:   ( " + periciasMod.get(10) + " )\n"
							+ "(12)  Intimidação:   ( " + periciasMod.get(11) + " )\n"
							+ "(13)  Intuição:      ( " + periciasMod.get(12) + " )\n"
							+ "(14)  Investigação:  ( " + periciasMod.get(13) + " )\n"
							+ "(15)  Luta:          ( " + periciasMod.get(14) + " )\n"
							+ "(16)  Medicina:      ( " + periciasMod.get(15) + " )\n"
							+ "(17)  Ocultismo:     ( " + periciasMod.get(16) + " )\n"
							+ "(18)  Percepção:     ( " + periciasMod.get(17) + " )\n"
							+ "(19)  Pontaria:      ( " + periciasMod.get(18) + " )\n"
							+ "(20)  Profissão:     ( " + periciasMod.get(19) + " )\n"
							+ "(21)  Reflexos:      ( " + periciasMod.get(20) + " )\n"
							+ "(22)  Religião:      ( " + periciasMod.get(21) + " )\n"
							+ "(23)  Sobrevivência: ( " + periciasMod.get(22) + " )\n"
							+ "(24)  Tática:        ( " + periciasMod.get(23) + " )\n"
							+ "(25)  Tecnologia:    ( " + periciasMod.get(24) + " )\n"
							+ "(26)  Vontade:       ( " + periciasMod.get(25) + " )\n"
							+ "\nOpção: ");
					optionsPericias = validInput();
					optionsPericias = validNums(0, 26, optionsPericias);
					
					if(optionsPericias != 0) {
						modPericiasAntigo = periciasMod.get(optionsPericias - 1);
						
						System.out.printf("Digite o modificador para a perícia (atual modificador = %d): ", modPericiasAntigo);
						modPericiasNovo = validInput();
						modPericiasNovo = validNums(-15, 99, modPericiasNovo);
						
						inventarioItem.add(modPericiasNovo);
						
						modPericiasNovo += modPericiasAntigo;
						
						periciasMod.set((optionsPericias - 1), modPericiasNovo);						

						System.out.print(modPericiasAntigo + " -- " + modPericiasNovo);
					}
					if(contador == 1) {
						System.out.print("\n" + periciasMod + "\n\nDeseja adicionar mais um modificador?\n\n1- Sim\n0- Não\n\nOpções: ");
						options = validInput();
						options = validNums(0, 1, options);
					}
						
					if(options == 0 && contador == 1) {
						inventarioItem.add(null);
					}

					contador ++;
				}
				break;
			case 0:
				inventarioItem.add(null);
				inventarioItem.add(null);
				break;
			}
		}
			if(tipo == 5) {
				System.out.print("\nO item amaldiçoado fornecerá algum modificador em testes de perícia?\n\n1- Sim\n0- Não\n\nOpção: ");
				options = validInput();
				options = validNums(0, 1, options);
				
				int contador = 1;
				switch(options) {
				case 1:
					while(options != 0 && contador <= 2) {
						System.out.print("\nSelecione a perícia para adicionar o modificador:\n\n"
								+ "(0)   Cancelar adição de bônus\n"
								+ "(1)   Acrobacia:     ( " + periciasMod.get(0) +  " )\n"
								+ "(2)   Adestramento:  ( " + periciasMod.get(1) +  " )\n"
								+ "(3)   Artes:         ( " + periciasMod.get(2) +  " )\n"
								+ "(4)   Atletismo:     ( " + periciasMod.get(3) +  " )\n"
								+ "(5)   Atualidades:   ( " + periciasMod.get(4) +  " )\n"
								+ "(6)   Ciências:      ( " + periciasMod.get(5) +  " )\n"
								+ "(7)   Crime:         ( " + periciasMod.get(6) +  " )\n"
								+ "(8)   Diplomacia:    ( " + periciasMod.get(7) +  " )\n"
								+ "(9)   Enganação:     ( " + periciasMod.get(8) +  " )\n"
								+ "(10)  Fortitude:     ( " + periciasMod.get(9) +  " )\n"
								+ "(11)  Furtividade:   ( " + periciasMod.get(10) + " )\n"
								+ "(12)  Intimidação:   ( " + periciasMod.get(11) + " )\n"
								+ "(13)  Intuição:      ( " + periciasMod.get(12) + " )\n"
								+ "(14)  Investigação:  ( " + periciasMod.get(13) + " )\n"
								+ "(15)  Luta:          ( " + periciasMod.get(14) + " )\n"
								+ "(16)  Medicina:      ( " + periciasMod.get(15) + " )\n"
								+ "(17)  Ocultismo:     ( " + periciasMod.get(16) + " )\n"
								+ "(18)  Percepção:     ( " + periciasMod.get(17) + " )\n"
								+ "(19)  Pontaria:      ( " + periciasMod.get(18) + " )\n"
								+ "(20)  Profissão:     ( " + periciasMod.get(19) + " )\n"
								+ "(21)  Reflexos:      ( " + periciasMod.get(20) + " )\n"
								+ "(22)  Religião:      ( " + periciasMod.get(21) + " )\n"
								+ "(23)  Sobrevivência: ( " + periciasMod.get(22) + " )\n"
								+ "(24)  Tática:        ( " + periciasMod.get(23) + " )\n"
								+ "(25)  Tecnologia:    ( " + periciasMod.get(24) + " )\n"
								+ "(26)  Vontade:       ( " + periciasMod.get(25) + " )\n"
								+ "\nOpção: ");
						optionsPericias = validInput();
						optionsPericias = validNums(0, 26, optionsPericias);
						
						if(optionsPericias != 0) {
							modPericiasAntigo = periciasMod.get(optionsPericias - 1);
							
							System.out.printf("Digite o modificador para a perícia (atual modificador = %d): ", modPericiasAntigo);
							modPericiasNovo = validInput();
							modPericiasNovo = validNums(-15, 99, modPericiasNovo);
							
							inventarioItem.add(modPericiasNovo);
							
							modPericiasNovo += modPericiasAntigo;
							
							periciasMod.set((optionsPericias - 1), modPericiasNovo);						

							System.out.print(modPericiasAntigo + " -- " + modPericiasNovo);
						}
						if(contador == 1) {
							System.out.print("\n" + periciasMod + "\n\nDeseja adicionar mais um modificador?\n\n1- Sim\n0- Não\n\nOpções: ");
							options = validInput();
							options = validNums(0, 1, options);
						}
							
						if(options == 0 && contador == 1) {
							inventarioItem.add(null);
						}

						contador ++;
					}
					break;
				case 0:
					inventarioItem.add(null);
					inventarioItem.add(null);
					break;
				}
		}
			
		if(tipo != 2 && tipo != 5) {
			inventarioItem.add(null);
			inventarioItem.add(null);
		}
		
		System.out.print("\nInsira uma descrição para o item: ");
		sc.nextLine();
		descricao = sc.nextLine();
		inventarioItem.add(descricao);
		
		return inventarioItem;
	}
	
	public static List<Object> inventarioDefinir(List<Object> personagemAtual) {
		int limiteCategoria1 = 0, limiteCategoria2 = 0, limiteCategoria3 = 0, limiteCategoria4 = 0, pesoCargaAtual = 0, patente, options, pesoCargaTotal;
		int qtdCategoria1 = 0, qtdCategoria2 = 0, qtdCategoria3 = 0, qtdCategoria4 = 0;
		boolean adicionarItem = true;
	
		List<Object> inventarioItens = new ArrayList<>();
		
		System.out.print("Selecione o atributo para cálculo de carga:\n\n1- FORÇA\n2- INTELECTO\n\nOpção:  ");
		options = validInput();
		options = validNums(1, 2, options);
		
		List<Integer> atributoCarga = (List<Integer>) personagemAtual.get(4);
		int intelecto = atributoCarga.get(3);
		int forca = atributoCarga.get(0);
		
		if(options == 1) {
			if(forca > 0) {
				pesoCargaTotal = forca *  5;
			} else {
				pesoCargaTotal = 2;
			}
		} else {
			if(intelecto > 0) {
				pesoCargaTotal = intelecto * 5;
			} else {
				pesoCargaTotal = 2;
			}
		}
		
		System.out.print("\nEscolha a patente do agente:\n\n1- Recruta\n2- Operador\n3- Agente Especial\n4- Oficial de Operações\n5- Agente de Elite\n\nOpções: ");
		patente = validInput();
		patente = validNums(1, 5, patente);
		
		switch(patente) {
			case 1:
				limiteCategoria1 = 2;
				limiteCategoria2 = 0;
				limiteCategoria3 = 0;
				limiteCategoria4 = 0;
				break;
			case 2:
				limiteCategoria1 = 3;
				limiteCategoria2 = 1;
				limiteCategoria3 = 0;
				limiteCategoria4 = 0;
				break;
			case 3:
				limiteCategoria1 = 3;
				limiteCategoria2 = 2;
				limiteCategoria3 = 1;
				limiteCategoria4 = 0;
				break;
			case 4:
				limiteCategoria1 = 3;
				limiteCategoria2 = 3;
				limiteCategoria3 = 2;
				limiteCategoria4 = 1;
				break;
			case 5:
				limiteCategoria1 = 3;
				limiteCategoria2 = 3;
				limiteCategoria3 = 3;
				limiteCategoria4 = 2;
				break;			
		}
		
		while(adicionarItem == true) {
			
			
			System.out.print("\n"
							+ "Carga: " + pesoCargaAtual + "/" + pesoCargaTotal + "\n"
							+ "Categoria I   : " + qtdCategoria1 + "/" + limiteCategoria1 + "\n"
							+ "Categoria II  : " + qtdCategoria2 + "/" + limiteCategoria2 + "\n"
							+ "Categoria III : " + qtdCategoria3 + "/" + limiteCategoria3 + "\n"
							+ "Categoria IV  : " + qtdCategoria4 + "/" + limiteCategoria4 + "\n\n");
			
			for(int contadorItens = 1; contadorItens <= inventarioItens.size(); contadorItens ++) {
				
				List <Object> listaAtual = (List<Object>) inventarioItens.get(0 + (contadorItens - 1));
				System.out.print("\n\n"
						+ "ITEM NÚMERO (" + contadorItens + ")\n"
								+ "Nome do item: " + listaAtual.get(0) + "\n"
								+ "Peso:         " + listaAtual.get(3) + "\n"
								+ "Categoria     " + listaAtual.get(4) + "\n"
								+ "Tipo:         " + listaAtual.get(1) + "\n"
								+ "Descrição:    " + listaAtual.get(7) + "\n");
				if(listaAtual.get(5) != null) {
					System.out.println("Modificador de Perícia 1: " + listaAtual.get(5));
				}
				if(listaAtual.get(6) != null) {
					System.out.println("Modificador de Perícia 2: " + listaAtual.get(6));
				}
			}
			
			System.out.print("\nEscolha uma opção:\n\n1- Criar novo item\n2- Deletar um item\n0- Fechar sistema de inventário\n\nOpção: ");
			options = validInput();
			options = validNums(0, 2, options);
			
			
			switch(options) {
			
				case 1:
					inventarioItens.add(inventarioItemUnitario(personagemAtual));
					List<Integer> listaAtualPesosCategorias = (List<Integer>) inventarioItens.get(inventarioItens.size() - 1);
					
					pesoCargaAtual += listaAtualPesosCategorias.get(3);
					switch(listaAtualPesosCategorias.get(4)) {
					case 1:
						qtdCategoria1 ++;
						break;
					case 2:
						qtdCategoria2 ++;
						break;
					case 3:
						qtdCategoria3 ++;
						break;
					case 4:
						qtdCategoria4 ++;
					}
					
					break;
				case 2:
					try {
						if(inventarioItens.size() < 1) {
							System.out.print("\nNão há itens para serem apagados.\n");
						} else {
						System.out.print("\nSelecione o número do item para apagar (Selecione 0 para cancelar) : ");
						options = validInput();
						options = validNums(0, inventarioItens.size(), options);
						List<Integer> listaAtualPesosCategorias1 = (List<Integer>) inventarioItens.get(options - 1);
						
						if(options == 0) {
							
						} else {
							
							pesoCargaAtual -= listaAtualPesosCategorias1.get(3);
							switch(listaAtualPesosCategorias1.get(4)) {
							case 1:
								qtdCategoria1 --;
								break;
							case 2:
								qtdCategoria2 --;
								break;
							case 3:
								qtdCategoria3 --;
								break;
							case 4:
								qtdCategoria4 --;
								break;
							}
								
							inventarioItens.remove(options - 1);
							}
						}
						
					} catch(java.lang.IndexOutOfBoundsException ioobe) {
						System.out.print("\nNão há itens para serem apagados.\n");
						
					}
					
					break;
				case 0:
					adicionarItem = false;
			}

			System.out.print("\n\nLista total: " + inventarioItens);
		}
		
		System.out.print("\n\nEncerrando sistema de gerenciamento de inventário... ");
		
		return inventarioItens;
	}
	
	public static void main(String[] args) {
		int menuOpcao = 5, confirmacaoCriar = 0;
		periciasMensagemRetorno.add(null);
		
		while(menuOpcao != 0) {
			menuOpcao = menu();
			
			switch(menuOpcao) {
				case 1:
					System.out.print("\nDeseja iniciar o processo de criação de ficha?\n"
							+ "1- Sim\n"
							+ "0- Não\n\n"
							+ "Opção: ");
					
					confirmacaoCriar = validInput();
					confirmacaoCriar = validNums(0, 1, confirmacaoCriar);
					
					if(confirmacaoCriar == 1) {
						personagensTotal.add(criarFicha());
						System.out.print("\nPersonagensTotal: " + personagensTotal);
					}
					else {
						menuOpcao = 5;
					}
					break;
				case 2:
					System.out.println("\nOpção escolhida: 2\n");
					break;
				case 3:
					System.out.println("\nOpção escolhida: 3\n");
					break;
				case 4:
					System.out.println("\nOpção escolhida: 4\n");
					break;
				case 5:
					break;			
				case 0:
					break;
				default:
					System.out.println("\n\n\nAlgo deu errado. \n-----------------------------------------------------------\n-----------------------------------\n\n\n\n");
					break;
			}
		}
		
		System.out.println("\nFinalizando o programa...\n");
	}
}

/* Sistema de fichas de RPG. Deve: 
 * 
 * 1. Ter um menu para acessar as opções V
 * 2. Permitir criação de personagens com informações X
 * 3. Permitir rolagens de dados automaticamente X
 * 
 * A fazer:
 * 
 * a. implementar poderes, com (custo), (descrição), (nome) e (bônus perícia) se houver. Alterar bônus perícia para inventario também
 * alt. Estudar getters e setters / uso de classes diversas
 */