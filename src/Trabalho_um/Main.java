package Trabalho_um;

import java.util.Scanner;

public class Main {
	
	public static long pedir() {
		Scanner sc = new Scanner(System.in); 
		System.out.print("Valor de N: ");
		long lido = sc.nextLong();
		sc.close();
		return lido; 
	}
	
	public static int ocorrencias(int alg,long n) {
		int lastdigit = 0;
		int counter = 0;
		for(; 0<n; n /= 10) {
			lastdigit = (int) n%10; //resto do numero por 10, ou seja, o ultimo digito
			if(alg==lastdigit) counter++; //contar da presen�a do numero (1-9) 
		}
		return counter;
	}
	
	public static boolean repetidos(long n) {
		for(int al = 0; al<10;al++) {
			//System.out.println("al:"+al+"c:"+ocorrencias(al,n));
			if(ocorrencias(al,n)>=2) return true; //est� mais que duas vezes, logo repete-se
		}
		return false;
	}
	
	public static long reversenr(long nr) {
		long reverse = 0;
	    for (;nr > 0;nr /=10) reverse = reverse*10 + nr%10; //multiplicar por 10 e adicionar o resto do numero anterior
	    return reverse;
	}
	
	public static void desenha(long n) {
		boolean zeroattheend = n%10==0; //o zero no final vai desaparecer ao reverter, tenho de o tratar no final.

		long reversed = reversenr(n);
		System.out.println(" "); //new line
		for(int i = 9; i> 0;i--) {
			for(long j = reversed; j!=0; j /=10) {
				if(j%10>=i) {
					System.out.print(" *");
				}
				else {
					System.out.print("  ");
				}
			}
			System.out.println(); //new line
		}
		
		for(; reversed!=0; reversed /=10) System.out.print(" "+ reversed%10);
		if(zeroattheend) System.out.print(" 0");
		

	}
	
	public static void main(String[] args) {
		long n = pedir();
		if(n<0) {
			System.out.println("N�mero Inv�lido.");
			return;
		}
		
		if (repetidos(n)) {
			System.out.println("N�mero com algarismos repetidos.");
		}
		else {
			desenha(n);
		}
		System.out.println("");
	}

}
