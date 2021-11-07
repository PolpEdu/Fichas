
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); //vou precisar do scanner em duas funções

        int tamanho = pedirtamanho(sc);

        int[] Tabela = new int[tamanho];
        pedirelementos(Tabela, sc);

        sc.close(); //não vou precisar mais do scanner


        printTabela(Tabela, false);

        int[] SubTabela = subTabela(Tabela);

        printTabela(SubTabela, true);

    }

    public static int[] subTabela(int[] tabela) {
        int[] subtabela,tabelaord;
        int indexfinal = tabela.length-1, indexinicial = 0;


        //fazer uma tabela nova ordenada
        tabelaord = Arrays.copyOf(tabela, tabela.length);
        //ordena-la
        Arrays.sort(tabelaord);


        //System.out.println( Arrays.toString(tabelaord));


        //ver a primeira vez
        boolean p = true;
        for (int j = 0; j < tabela.length; j++) {
            if(tabela[j] != tabelaord[j]){

                if(p) { //é o primeiro index em que ela está desordenada
                    p = false;
                    indexinicial = j;
                }
                else { //se for o ultimo index em que ela está desordenada, este corresponde ao index final
                    indexfinal = j;
                }
            }
        }

        // ? System.out.printf("Nr de valores: %d, Começo(indexinicial): %d, Fim(indexfinal): %d\n", indexfinal - indexinicial + 1, indexinicial, indexfinal);

        //cria uma subtabela com o tamanho necessário e os dois indexes da tabela principal
        System.out.println(indexfinal);
        System.out.println(indexinicial);
        subtabela = new int[indexfinal - indexinicial + 1];
        System.arraycopy(tabela, indexinicial, subtabela, 0, indexfinal - indexinicial + 1); //copiar para a nova tabela os elementos com os indexes.

        return subtabela;
    }

    public static int pedirtamanho(Scanner sc) {
        System.out.print("Tamanho da Tabela: ");
        int tamanho = sc.nextInt();


        if (!(tamanho > 2)) {
            System.out.print("O tamanho da Tabela tem de ser maior que dois.");
            System.exit(0); //sair com o exit code 0
        }
        return tamanho;
    }

    public static int[] pedirelementos(int[] tabela, Scanner sc) {

        for (int i = 0; i < tabela.length; i++) {
            System.out.printf("Insira o elemento numero %d: ", i + 1);
            int lido = sc.nextInt();
            tabela[i] = lido;
        }

        return tabela;
    }

    public static void printTabela(int[] t, boolean sub) {
        System.out.println();
        if (sub)
            System.out.print("Sub-Tabela:\n"); //sub, variavel que controla se é a sub-tabela a ser passada como parametro.
        else System.out.print("Tabela = ");
        System.out.println(Arrays.toString(t));

    }

}
