package Aula;


import java.util.*;

class Estudante {
    //Atributos
    private String nome;
    private int[] notas;

    public Estudante() {
        System.out.print("Nome do estudante: ");
        Scanner sc = new Scanner(System.in);
        nome = sc.nextLine();
        System.out.print("Quantas notas? ");
        int numNotas = sc.nextInt();

        //Cria o vetor notas com a dimensão necessária
        notas = new int[numNotas];
        for (int i = 0; i < numNotas; i++) {
            System.out.print("Nota " + (i + 1) + " deste aluno: ");
            notas[i] = sc.nextInt();
        }
    }

    //Método para cálculo da média
    private float calculaMedia() {
        float soma = 0;
        if (notas.length > 0) {
            for (int i = 0; i < notas.length; i++) {
                soma += notas[i];
            }
            return soma / notas.length;
        } else return -1;
    }

    //Método de acesso externo à média
    public float getMedia() {
        return calculaMedia();
    }

    //Escreve os dados de um estudante
    public void imprimeEstudante() {
        System.out.print("As notas de " + nome + " são: ");
        for (int i = 0; i < notas.length; i++) {
            System.out.print(notas[i] + " ");
        }
        System.out.println();
        System.out.println("A média é " + calculaMedia());
    }

    private String ES(){
        return "Nome:"+this.nome+" - Notas: "+ Arrays.toString(this.notas);
    }

    public String toString() {
        return ES();
    }

}


class GereTurma {
    public static void main(String[] args) {
        Turma t = new Turma(); // Cria uma turma
        int escolha;
        Scanner stdin = new Scanner(System.in);
        do {// Menu
            System.out.println("1 - Adicionar estudante");
            System.out.println("2 - Ordenar estudantes");
            System.out.println("3 - Lista de estudantes");
            System.out.println("0 - Sair");
            escolha = stdin.nextInt();
            switch (escolha) {
                case 1:
                    t.juntaEstudante();
                    break;
                case 2:
                    t.ordenaTurma();
                    break;
                case 3:
                    t.imprimeTurma();
                    break;
                case 0:
                    System.exit(0);
            }
        } while (escolha != 0);
        stdin.close();
    }
}



class Turma {
    private List<Estudante> lista;//Construtor –cria ArrayListpara guardar dados

    public Turma() {
        lista = new ArrayList<Estudante>();
    }//Ordena estudantes por ordem decresc. da média

    public void ordenaTurma() {
        int numEst = lista.size();
        for (int i = 0; i < numEst - 1; ++i) {
            int indiceDoMaior = localizaMaior(i);
            Estudante temp1 = lista.get(i);
            Estudante temp2 = lista.get(indiceDoMaior);
            lista.set(i, temp2);
            lista.set(indiceDoMaior, temp1);
        }
    }

    List<String> inverte(List<String> aList) {
        ArrayList<String> bList = new ArrayList<>(aList);
        ListIterator<String> forwardI = bList.listIterator();
        ListIterator<String> reverseI =
                bList.listIterator(bList.size());
        for (int curr = 0, meio = aList.size() / 2; curr < meio;
             ++curr) {
            String left = forwardI.next();
            String right = reverseI.previous();
            reverseI.set(left);
            forwardI.set(right);
        }
        return bList;
    }

    //Adiciona um novo estudante
    public void juntaEstudante() {
        Estudante e = new Estudante();
        lista.add(e);
    }

    //Imprime os dados de todos os estudantes
    public void imprimeTurma() {
        for (Estudante e : lista)
            System.out.println(e);
    }

    //Localiza elem. com maior média numa parte do ArrayList
    private int localizaMaior(int inicio) {
        int posMaior = inicio;
        float medMaior = lista.get(inicio).getMedia();
        for (int i = inicio + 1; i < lista.size(); ++i) {
            Estudante temp = lista.get(i);
            if (temp.getMedia() > medMaior) {
                posMaior = i;
                medMaior = temp.getMedia();
            }
        }
        return posMaior;
    }


}