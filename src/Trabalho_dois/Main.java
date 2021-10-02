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
		int sep=0, sepindex= 0, sepindex2= 0;
		boolean tabelaordenada = true;
		
		for(int i = 0; i<tabela.length; i++) {
			
			//ultimo termo
			if(i+1==tabela.length) break; 
			
			//o proximo termo não é maior que o anterior
			if(tabela[i] > tabela[i+1]) {
				if(tabelaordenada == true) //é a primeira vez que estou a passar aqui porque estava true e vai passar a não estar.
					sepindex2= i;
				
				
				tabelaordenada = false;
				sepindex= i+1;
				sep = tabela[sepindex];
				System.out.printf("sep:%d\n",sep);
			}
			System.out.println("sepindex:"+sepindex+ ", sepindex2: "+sepindex2 + ", tabela[i]:"+tabela[i]);
		}
		
		
		//ver exceção no caso do utlimo numero desordenado seja antes da primeiro desordenado.
		for(int i = 0; i<tabela.length; i++) {
			
			if(sep<tabela[i] && i<sepindex2) { //tentar ordenar termo desordenado desde o inicio da fila.
				sepindex2 = i;
				System.out.println("sepindex:"+sepindex+ "  sepindex2: "+sepindex2 + "   tabela[i]:"+tabela[i]);
				break;
			}
		}
		
		
		if(tabelaordenada) {
			System.out.println("A tabela encontra-se ordenada. Não existe Sub-tabela.");
			return new int[]{};
		}
		
		System.out.printf("Nr de valores: %d, Começo(sepindex2): %d, Fim(sepindex): %d\n",sepindex-sepindex2+1,sepindex2,sepindex);
		subtabela = new int[sepindex-sepindex2+1]; 
		System.arraycopy(tabela, sepindex2, subtabela, 0, sepindex-sepindex2+1);
		
		return subtabela; 
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
