package aa;

import java.util.Scanner;
import java.util.InputMismatchException;

public class MainFunction {
	
	public static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		Menu menu = new Menu();
		
		menu.Menu();
	}
	
	public static int ValidNums(int minimo, int maximo) {

		boolean deuErro = false;
		int valor;

		while (true) {
			try {
				valor = sc.nextInt();
				while (valor < minimo || valor > maximo) {
					System.out.print("\nO valor inserido está fora do escopo de opções válidas. Reinsira: ");
					valor = sc.nextInt();
				}
				break;
			} catch (InputMismatchException e) {
				System.out.print("\nInserção inválida. O valor não é um número inteiro.");
				sc.next();
				System.out.print("\n\nReinsira o número: ");
			}
		}

		return valor;
	}
}

/*

A fazer:

	--- a. Verificar se pode unir ValidPeriod e ValidInput (Seria útil para otimizar código demaaaaaai) VV certinho
	b.

*/