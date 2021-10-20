package Trabalho_tres;

import java.util.ArrayList;
import java.util.Scanner;


class Livro {
    private String titulo;
    private String nomeautor;

    public Livro(String t, String autor) {
        this.titulo = t;
        this.nomeautor = autor;
    }

    public Requesicao requesitaLivro(Leitor l, Data dtReq, Data dtDev) {
        return new Requesicao(l, this, dtReq, dtDev);
    }

    private String PRTLivro(){return "Livro: \""+titulo+"\", por: "+nomeautor;}

    public String toString(){return PRTLivro();}
}

class Leitor {
    private long nrutente;
    private String nome;

    public Leitor(String name, long nr) {
        this.nrutente = nr;
        this.nome = name;
    }


    private String PRINTLeitor(){return "Leitor: "+nome+", "+nrutente;}

    public String toString(){return PRINTLeitor();}
}


class Leitores {
    private ArrayList<Leitor> leitores;

    public void listaleitores() { //getLivros
        for (Leitor i : this.leitores) {
            System.out.println(i);
        }
    }

    public void adicionaleitor(Leitor l){
        this.leitores.add(l);
    }

    public Leitor getLeitor(int index) {
        return this.leitores.get(index);
    }

    public void removeleitor(Leitor l) {
        this.leitores.remove(l);
    }

    public boolean isEmpty() {
        return leitores.isEmpty();
    }

    public ArrayList<Leitor> getLeitores(){
        return this.leitores;
    }

}

class Livros {
    private ArrayList<Livro> livros;

    public void listaLivrosDisponiveis() { //getLivros
        for (Livro i : this.livros) {
            System.out.println(i);
        }
    }

    public void adicionalivro(Livro l){
        this.livros.add(l);
    }

    public Livro getlivro(int index) {
        return this.livros.get(index);
    }

    public void removelivro(Livro l) {
        this.livros.remove(l);
    }

    public boolean isEmpty() {
        return livros.isEmpty();
    }

    public ArrayList<Livro> getLivros(){
        return this.livros;
    }

}

class Data {
    private int dia, mes, ano;

    public Data(int d, int m, int a) { //construtor dá return a um objeto do tipo da classe
        this.dia = d % 32; //para nao ter dias maiores que 31
        this.mes = m % 13; //para nao ter meses maior que 13
        this.ano = a;
        //dá return por si mesmo
    }

    private String PRINTData() {
        return dia + "/" + mes + "/" + ano;
    }

    public String toString() {
        return PRINTData();
    }

    public boolean comparadatas(Data datagiven){
        //! NOTA: AS DATAS DE DEVOLUÇÃO SÃO UM MES DEPOIS.

        // verfica se é do mes a seguir do proximo ano || verficia se é do mesmo mes || verfica se no caso de ser o mes a seguir, tem dias menores.
        return ((this.ano == datagiven.ano-1) &&(this.mes == 12) && (datagiven.mes==1))
                || (this.mes == datagiven.mes) && (this.ano == datagiven.ano) ||
                (this.mes == (datagiven.mes+1)%13) && (this.dia <= datagiven.dia) && (this.ano == datagiven.ano);
    }

    private Data dataDevolucao() {
        //logica do calculo da dataDevolução:
        //adicionar 1 ao ano no caso do mes ser 12
        if(this.mes==12) return new Data(this.dia, (this.mes + 1) % 12, this.ano+1); //A data de devolucao é dada por o mes atual mais um.

        return new Data(this.dia, (this.mes + 1) % 12, this.ano); //A data de devolucao é dada por o mes atual mais um.
    }

    public Data getDataDev() {
        return dataDevolucao();
    }

    public static Data pedeData(Scanner stdin){
        int dias, mes ,ano;
        System.out.println("Seleciona a tua data: ");

        System.out.print("Dia: ");
        dias = stdin.nextInt();

        System.out.print("Mes: ");
        mes = stdin.nextInt();

        System.out.print("Ano:");
        ano = stdin.nextInt();
        return new Data(dias,mes,ano);
    }
}

class Requesicoes {
    private ArrayList<Requesicao> reqs;

    public Requesicoes() {
        reqs = new ArrayList<>();
    }

    public void imprimeReqs() {
        for (Requesicao i : reqs) {
            System.out.println("\nRequesição:\n" + i);
        }
    }

    public void adicionareq(Requesicao r, Livros livrosdisponiveis) {
        //verificar se posso requesitar o livro pretendido (ver se ele já está requesitado)
        reqs.add(r);
        //ja nao é disponivel
        livrosdisponiveis.removelivro(r.getLivro());
    }

    public void listaRequisitados(Data d) {
        System.out.print("\n---------------------\nLista de datas de livros requesitados em "+d);
        for (Requesicao req : reqs) {
            if(req.getDatarequesicao().comparadatas(d)) {
                System.out.println("\n");
                System.out.println(req);
            }
        }
        System.out.println();
    }

    //esta funcao basicamente volta a por o livro nos livros disponiveis e remove a requesicao feita.
    public void levantaLivro(Requesicao r,Livros livrosdisponiveis) {
        if(reqs.contains(r))  {
            reqs.remove(r);
            System.out.println("Levantei o livro "+r.getLivro()+"\n");

        }
        livrosdisponiveis.adicionalivro(r.getLivro());
    }

    public Requesicao pedeRequesicao(Scanner stdin){
        int i = 1;
        for(Requesicao r: this.reqs){
            System.out.println(i+"- Requesicao:\n"+r);
            i++;
        }
        int escolher;
        do  {
            escolher = stdin.nextInt();
        } while (!(escolher>0 &&escolher<=i));

        // selecionei a requesicao vou dar-lhe return
        return this.reqs.get(escolher-1);
    }

    public void criaReq(Scanner stdin, Livros ls, Leitores leitors){
        int i = 1, escolherliv,j = 1, escolherleit;

        if(ls.isEmpty() || leitors.isEmpty()) {
            System.out.println("Não é possivel fazer requesições....");
            return;
        }
        System.out.println("Seleciona o número do Livro: ");
        for(Livro l: ls.getLivros()){
            System.out.println(i+" - "+l);
            i++;
        }
        do  {
            escolherliv = stdin.nextInt();
        } while (!(escolherliv>0 &&escolherliv<=i));


        System.out.println("Seleciona o número do Leitor: ");

        for(Leitor l: leitors.getLeitores()){
            System.out.println(j+" - "+l);
            j++;
        }

        do  {
            escolherleit = stdin.nextInt();
        } while (!(escolherleit>0 && escolherleit<=j));

        Data datreq = Data.pedeData(stdin);
        Data datadev = datreq.getDataDev();


        //selececionei os leitores/livros/datareq adiciono as requesicoes.
        Requesicao r = ls.getlivro(escolherliv-1).requesitaLivro(leitors.getLeitor(escolherleit-1),datreq,datadev);
        this.adicionareq(r,ls);
        System.out.println("Requesicao criada:\n"+r);
    }


}

class Requesicao {
    private Leitor leitor;
    private Livro livro;
    private Data datarequesicao;
    private Data datadevolucao;


    public Requesicao(Leitor l, Livro liv, Data dtreq, Data dtdev) {
        this.leitor = l;
        this.livro = liv;
        this.datadevolucao = dtdev;
        this.datarequesicao = dtreq;
    }

    public String toString(){return PRINTRequesicao();}

    public Livro getLivro(){return this.livro;}

    private String PRINTRequesicao(){return leitor+"\n"+livro+"\n"+"Data de Requesição: "+ datarequesicao+"\n"+"Data de Devolução: "+datadevolucao;}

    public Data getDatarequesicao(){return this.datarequesicao;}
}

public class TrabalhoTres {

    private static void criaLeitores(Leitores leitores) {
        Leitor l1 = new Leitor("Eduardo Nunes", 2020217675);
        Leitor l2 = new Leitor("Francisco Rosendo", 2020217697);
        Leitor l3 = new Leitor("Afonso Gouveia Melo", 2020217360);
        Leitor l4 = new Leitor("Lucas Anjo", 2020218028);
        leitores.adicionaleitor(l1);
        leitores.adicionaleitor(l2);
        leitores.adicionaleitor(l3);
        leitores.adicionaleitor(l4);
    }

    private static void criaLivros(Livros livros) {
        Livro l1 = new Livro("Os Maias", "Eça de Queiroz");
        Livro l2 = new Livro("O Ano Da Morte De Ricardo Reis", "José Saramago");
        Livro l3 = new Livro("Sherlock Holmes: A Study in Scarlet", "Arthur Conan Doyle");
        Livro l4 = new Livro("Engenharia de Janelas", "João Barata");
        Livro l5 = new Livro("Os Lusiadas", "Luis Vaz de Camões");

        livros.adicionalivro(l1);
        livros.adicionalivro(l2);
        livros.adicionalivro(l3);
        livros.adicionalivro(l4);
        livros.adicionalivro(l5);
    }

    private static Leitor criaLeitorInput(Leitores leitores, Scanner stdin) {
        String nome, nr;
        long nrLONG;

        System.out.print("Nome: ");
        nome = stdin.nextLine();

        System.out.print("Numero: ");
        nr = stdin.nextLine();

        Leitor leit = null;
        try {
            nrLONG = Long.parseLong(nr);
            leit = new Leitor(nome,nrLONG);
            leitores.adicionaleitor(leit);
            System.out.print(leit);


        }catch (Exception e) {
            System.out.println("Número de estudante invalido. Erro: "+e);
        }
        return leit;
    }

    private static Livro criaLivroInput(Livros livros,Scanner stdin) {
        String t, autor;
        t = stdin.nextLine(); //para fazer o paragrafo e esperar

        System.out.print("Título: ");
        t = stdin.nextLine();

        System.out.print("Autor: ");
        autor = stdin.nextLine();

        Livro l = new Livro(t,autor);
        livros.adicionalivro(l);
        System.out.print(l);
        return l;
    }


    public static void main(String[] args) {
        Requesicoes listareqs = new Requesicoes();
        Leitores listaleitores = new Leitores();
        Livros listalivros = new Livros();


        //criação de dados iniciais
        criaLeitores(listaleitores);
        criaLivros(listalivros);


        //exemplo de datas e as suas devoluções
        Data d1 = new Data(23, 2, 2021);
        Data d1dev = d1.getDataDev();

        Data now = new Data(14, 12, 2021);
        Data nowdev = now.getDataDev();

        /*Data antesnow = new Data(12, 1, 2021);
        Data antesnowdev = antesnow.getDataDev();*/


        //exemplos de criação de requesições:
        Requesicao r = listalivros.getlivro(2).requesitaLivro(listaleitores.getLeitor(2),d1, d1dev);
        Requesicao r1 = listalivros.getlivro(1).requesitaLivro(listaleitores.getLeitor(0), now, nowdev);
        // Requesicao r2 =  livrosdisponiveis.get(0).requesitaLivro(leitores.get(1), now, nowdev);
        // Requesicao r3 =  livrosdisponiveis.get(4).requesitaLivro(leitores.get(1), antesnow, antesnowdev);


        //adiciona às requesições algumas requesicoes posteriores.
        listareqs.adicionareq(r,listalivros);
        listareqs.adicionareq(r1,listalivros);


        int escolha;
        Scanner stdin = new Scanner(System.in);
        do {// Menu
            System.out.println("\n----------------------------\n1 - Adicionar livro");
            System.out.println("2 - Adicionar Leitor");
            System.out.println("3 - Lista livros disponiveis para requesicao");
            System.out.println("4 - Lista leitores");
            System.out.println("5 - Lista Reposicoes");
            System.out.println("6 - Cria uma reposição");
            System.out.println("7 - Lista livros requesitados com base numa data pedida");
            System.out.println("8 - Levanta Livro");

            System.out.println("0 - Sair\n----------------------------");
            escolha = stdin.nextInt();
            switch (escolha) {
                case 1:
                    criaLivroInput(listalivros,stdin);
                    break;
                case 2:
                    stdin.nextLine(); //para saltar a proxima linha
                    criaLeitorInput(listaleitores,stdin);
                    break;
                case 3:
                    listalivros.listaLivrosDisponiveis();
                    break;
                case 4:
                    listaleitores.listaleitores();
                    break;
                case 5:
                    listareqs.imprimeReqs();
                    break;
                case 6:
                    listareqs.criaReq(stdin,listalivros,listaleitores);
                    break;
                case 7:
                    Data ped = Data.pedeData(stdin);
                    //lista todas as requisições com base numa data dada.
                    listareqs.listaRequisitados(ped);
                    break;
                case 8:
                    Requesicao reqped = listareqs.pedeRequesicao(stdin);
                    listareqs.levantaLivro(reqped, listalivros);
                    break;
                default:
                    System.out.println("Input invalido.");
                    break;
                case 0:
                    System.exit(0);
            }
        } while (escolha != 0);
        stdin.close();
    }
}
