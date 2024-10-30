package aa;

import java.util.Scanner;

public class Menu {

	MainFunction mainF = new MainFunction();
	FichaCriar fichaC = new FichaCriar();
	FichaVer fichaV = new FichaVer();
	FichaDeletar fichaD = new FichaDeletar();
	
	public void Menu() {

		int option = 0;
				
		System.out.print("Para navegar entre os menus, insira o número da opção que deseja selecionar.");
		
		while (true) {
			System.out.print("\n\nMENU DE NAVEGAÇÃO OPRPG FICHA GERENCIAMENT SYSTEM 32000\n\nOpções:\n\n"
					+ "(1) Criar Ficha (WIP)\n"
					+ "(2) Abrir Ficha (WIP)\n"
					+ "(3) Deletar Ficha (WIP)\n"
					+ "(0) Encerrar Programa\n\n"
					+ "Insira a opção desejada: ");
			option = mainF.ValidNums(0, 3);

			switch (option) {
				case 1:
					fichaC.IniciarCriacaoFicha();
					break;
				case 2:
					// fichaV.metodo();
					break;
				case 3:
					// fichaD.metodo();
					break;
			}

			if (option == 0) {
				System.out.println("\nEncerrando o Sistema...");
				break;
			}
		}
	}
}