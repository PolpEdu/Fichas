package Trabalho_dois;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in); 
		
		int tamanho = pedirtamanho(sc);
		
		int[] Tabela = new int[tamanho];
		pedirelementos(Tabela, sc);
		
		sc.close(); //não vou precisar mais do scanner
		
		
		printTabela(Tabela,false);
		
		int[] SubTabela = subTabela(Tabela);
		
		printTabela(SubTabela,true);
		
	}
	public static int[] subTabela(int[] tabela) {
		int[] subtabela;
		int termofinal=0, termoincial;
		boolean tabelaordenada = true;
		
		for(int i = 0; i<tabela.length; i++) {
			
			//ultimo termo
			if(i+1==tabela.length) break; 
			
			//o proximo termo não é maior que o anterior
			if(tabela[i] > tabela[i+1]) {
				tabelaordenada = false;
				termofinal = tabela[i+1];
				System.out.println("Termofinal: "+termofinal);
			}
		}
		
		if(tabelaordenada) {
			System.out.println("A tabela encontra-se ordenada. Não existe Sub-tabela.");
			return new int[]{};
		}
			
		for(int i = 0; i<tabela.length; i++) {
			
			//ultimo termo
			if(i+1==tabela.length) break; 
			
			if(termofinal > tabela[i+1]) {
				termoincial = tabela[i+1];
				System.out.println("TermoInicial: "+ termoincial);
			}
		}
		return new int[]{1,23,213,123,12,32};

		
	}
	
	public static int pedirtamanho(Scanner sc) {
		System.out.print("Tamanho da Tabela: ");
		int tamanho = sc.nextInt();
		
	
		if(!(tamanho>2)) {
			System.out.print("O tamanho tem de ser maior que dois.");
			System.exit(0); //sair com o exit code 0
		}
		return tamanho; 
	}
	
	public static int[] pedirelementos(int[] tabela, Scanner sc) {
		
		for(int i = 0; i<tabela.length; i++) {
				System.out.printf("Insire o elemento numero %d: ",i+1);
				int lido =sc.nextInt();
				tabela[i] = lido;
		}
		
		return tabela;
	}
	
	public static void printTabela(int[] t,boolean sub) {
		if(sub) System.out.print("Sub-Tabela:\n{");
		else System.out.print("Tabela = {");
		
		for(int i = 0; i<t.length; i++) {
			if(i==t.length-1) {
				System.out.printf("%d",t[i]);
				break;
			}
			System.out.printf("%d, ",t[i]);
			
		}
		System.out.println("}");
	}
	
}
