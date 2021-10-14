package Trabalho_tres;

import java.util.ArrayList;


class Livro {
    private String titulo;
    private String nomeautor;
    //private Requesicao requesicao;

    public Livro(String t, String autor) {
        this.titulo = t;
        this.nomeautor = autor;
    }

    public static void listaLivrosDisponiveis(ArrayList<Livro> livros) { //getLivros
        for (Livro i : livros) {
            System.out.println("Livro:\nTitulo: " + i.titulo + "\nPor: " + i.nomeautor + "\n");
        }
    }

    public void addRequesicao(Requesicao req) {

    }
}

class Leitor {
    private long nrutente;
    private String nome;

    public Leitor(String name, long nr) {
        this.nrutente = nr;
        this.nome = name;
    }

    public static void listaLeitores(ArrayList<Leitor> leitores) { //getLivros
        for (Leitor i : leitores) {
            System.out.println("Leitor:\nNome: " + i.nome + " - " + i.nrutente + "\n");
        }
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

    private String Data() {
        return dia + "/" + mes + "/" + ano;
    }

    public String toString() {
        return Data();
    }


    private Data dataDevolucao() {
        return new Data(this.dia, (this.mes + 1) % 12, this.ano); //A data de devolucao é dada por o mes atual mais um.
    }

    public Data getDataDev() {
        return dataDevolucao();
    }
}

class Requesições {
    private ArrayList<Requesicao> reqs;
    private int nr;

    public Requesições() {
        reqs = new ArrayList<>();
    }

    public void imprimeReqs() {
        for (Requesicao i : reqs) {
            System.out.println("\nRequesição:" + i);
        }
    }

    public void listaRequisitados(Data d) {
        for (Requesicao req : reqs) {
            if(req.comparadatas(d)) {
                System.out.println(req);
            }
        }
    }
}


class Requesicao {
    private Leitor leitor;
    private Livro livro;
    private Data datarequesicao;
    private Data datadevolucao;

    private Requesicao(Leitor l, Livro liv, Data dtreq, Data dtdev) {
        this.leitor = l;
        this.livro = liv;
        this.datadevolucao = dtdev;
        this.datarequesicao = dtreq;
    }

    public static Requesicao requesitalivro(Leitor l, Livro liv,Data dtReq, Data dtDev) {
        return  new Requesicao(l, liv, dtReq, dtDev);
    }

    public boolean comparadatas(Data datagiven){
        return this.datarequesicao == datagiven;
    }

    private String Requesicao(){return "Leitor:"+leitor+"\nLivro: "+livro+"\nData de Requesição: "+datarequesicao+"\nData de Devolução: "+datadevolucao;}

    public String toString(){
        return Requesicao();
    }
}

public class TrabalhoTres {

    private static void criaLeitores(ArrayList leitores) {
        Leitor l1 = new Leitor("Eduardo Nunes", 2020217675);
        Leitor l2 = new Leitor("Francisco Rosendo", 2020217697);
        Leitor l3 = new Leitor("Afonso Gouveia Melo", 2020217360);
        Leitor l4 = new Leitor("Lucas Anjo", 2020218028);
        leitores.add(l1);
        leitores.add(l2);
        leitores.add(l3);
        leitores.add(l4);
    }

    private static void criaLivros(ArrayList livros) {
        Livro l1 = new Livro("Os Maias", "Eça de Queiroz");
        Livro l2 = new Livro("O Ano Da Morte De Ricardo Reis", "José Saramago");
        Livro l3 = new Livro("Sherlock Holmes: A Study in Scarlet", "Arthur Conan Doyle");
        livros.add(l1);
        livros.add(l2);
        livros.add(l3);
    }

    public static void main(String[] args) {
        System.out.println("");
        Requesições reqs = new Requesições();
        ArrayList<Leitor> leitores = new ArrayList<>(); //crio uma lista de leitores.
        ArrayList<Livro> livros = new ArrayList<>();

        criaLeitores(leitores);
        criaLivros(livros);


        Livro.listaLivrosDisponiveis(livros);
        Leitor.listaLeitores(leitores);


        //exemplo
        Data d1 = new Data(23, 2, 2021);
        Data d1dev = d1.getDataDev();

        Data now = new Data(14, 12, 2021);
        Data nowdev = now.getDataDev();

        System.out.println("data: " + now + " - datadevolucao: " + nowdev);

        Livro livroareq = livros.get(2);


        Requesicao r = Requesicao.requesitalivro(leitores.get(3), livroareq,d1, d1dev);

    }
}
