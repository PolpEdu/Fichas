
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); //vou precisar do scanner em duas funções

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

            //o proximo termo não é maior que o anterior, logo está desordenada nestes indexes
            if(tabela[i] > tabela[i+1]) {
                if(tabelaordenada == true) //É a primeira vez que estou a passar aqui porque estava true e vai passar a n�o estar.
                    sepindex2= i;


                tabelaordenada = false;
                sepindex= i+1;
                sep = tabela[sepindex];
            }
        }

        if(tabelaordenada) { //não quero fazer mais operações. A tabela encontra-se ordernada.
            System.out.println("A tabela encontra-se ordenada. Não existe Sub-tabela.");
            return new int[]{};
        }



        //ver exceção no caso do utlimo numero desordenado seja antes da primeiro desordenado.
        for(int i = 0; i<tabela.length; i++) {
            //tentar ordenar termo
            if(sep<tabela[i] && i<sepindex2) { //se conseguir ordená-lo antes do index atual troca-o.
                sepindex2 = i;
                //System.out.println("sepindex:"+sepindex+ "  sepindex2: "+sepindex2 + "   tabela[i]:"+tabela[i]);
                break;
            }
        }




        //System.out.printf("Nr de valores: %d, Começo(sepindex2): %d, Fim(sepindex): %d\n",sepindex-sepindex2+1,sepindex2,sepindex);
        subtabela = new int[sepindex-sepindex2+1];
        System.arraycopy(tabela, sepindex2, subtabela, 0, sepindex-sepindex2+1); //copiar para a nova tabela os elementos com os indexes.

        return subtabela;
    }

    public static int pedirtamanho(Scanner sc) {
        System.out.print("Tamanho da Tabela: ");
        int tamanho = sc.nextInt();


        if(!(tamanho>2)) {
            System.out.print("O tamanho da Tabela tem de ser maior que dois.");
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
        System.out.println();
        if(sub) System.out.print("Sub-Tabela:\n"); //sub, variavel que controla se é a sub-tabela a ser passada como parametro.
        else System.out.print("Tabela = ");
        System.out.println(Arrays.toString(t));

    }

}
