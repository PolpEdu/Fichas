package Trabalho_tres;

import java.util.ArrayList;


class Livro {
    private String titulo;
    private String nomeautor;

    public Livro(String t, String autor) {
        this.titulo = t;
        this.nomeautor = autor;
    }

    public static void listaLivrosDisponiveis(ArrayList<Livro> livros) { //getLivros
        for (Livro i : livros) {
            System.out.println(i);
        }
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

    public static void listaLeitores(ArrayList<Leitor> leitores) { //getLivros
        for (Leitor i : leitores) {
            System.out.println(i);
        }
    }


    private String PRINTLeitor(){return "Leitor: "+nome+", "+nrutente;}

    public String toString(){return PRINTLeitor();}
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


    private Data dataDevolucao() {
        return new Data(this.dia, (this.mes + 1) % 12, this.ano); //A data de devolucao é dada por o mes atual mais um.
    }

    public Data getDataDev() {
        return dataDevolucao();
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

    public void adicionareq(Requesicao r) {
        reqs.add(r);
    }


    public void listaRequisitados(Data d) {
        System.out.print("\nLista de datas correspondidas a "+d);
        for (Requesicao req : reqs) {
            if(req.comparadatas(d)) {
                System.out.println("\n");
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


    public Requesicao(Leitor l, Livro liv, Data dtreq, Data dtdev) {
        this.leitor = l;
        this.livro = liv;
        this.datadevolucao = dtdev;
        this.datarequesicao = dtreq;
    }


    public boolean comparadatas(Data datagiven){
        return this.datarequesicao == datagiven;
    }

    private String PRINTRequesicao(){return leitor+"\n"+livro+"\n"+"Data de Requesição: "+ datarequesicao+"\n"+"Data de Devolução: "+datadevolucao;}

    public String toString(){return PRINTRequesicao();}
}

public class TrabalhoTres {

    private static void criaLeitores(ArrayList<Leitor> leitores) {
        Leitor l1 = new Leitor("Eduardo Nunes", 2020217675);
        Leitor l2 = new Leitor("Francisco Rosendo", 2020217697);
        Leitor l3 = new Leitor("Afonso Gouveia Melo", 2020217360);
        Leitor l4 = new Leitor("Lucas Anjo", 2020218028);
        leitores.add(l1);
        leitores.add(l2);
        leitores.add(l3);
        leitores.add(l4);
    }

    private static void criaLivros(ArrayList<Livro> livros) {
        Livro l1 = new Livro("Os Maias", "Eça de Queiroz");
        Livro l2 = new Livro("O Ano Da Morte De Ricardo Reis", "José Saramago");
        Livro l3 = new Livro("Sherlock Holmes: A Study in Scarlet", "Arthur Conan Doyle");
        livros.add(l1);
        livros.add(l2);
        livros.add(l3);
    }

    public static void main(String[] args) {
        Requesicoes listareqs = new Requesicoes();
        ArrayList<Leitor> leitores = new ArrayList<>(); //crio uma lista de leitores.
        ArrayList<Livro> livros = new ArrayList<>();

        //criação de dados
        criaLeitores(leitores);
        criaLivros(livros);


        //lista todos os dados disponiveis
        System.out.println("Livros disponiveis:");
        Livro.listaLivrosDisponiveis(livros);
        System.out.println("\nLeitores disponiveis:");
        Leitor.listaLeitores(leitores);


        //exemplo de datas e as suas devoluções
        Data d1 = new Data(23, 2, 2021);
        Data d1dev = d1.getDataDev();

        Data now = new Data(14, 12, 2021);
        Data nowdev = now.getDataDev();


        //cria requesições:
        Requesicao r = livros.get(2).requesitaLivro(leitores.get(2),d1, d1dev);
        Requesicao r1 = livros.get(1).requesitaLivro(leitores.get(0), now, nowdev);
        Requesicao r2 =  livros.get(0).requesitaLivro(leitores.get(1), now, nowdev);

        //adiciona às requesições
        listareqs.adicionareq(r);
        listareqs.adicionareq(r1);
        listareqs.adicionareq(r2);

        //lista todas as requisições:
        listareqs.imprimeReqs();

        //lista todas as requisições com base numa data dada.
        listareqs.listaRequisitados(now);
    }
}
