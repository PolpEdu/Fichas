import java.util.ArrayList;
import java.util.Arrays;


class Livro {
    private String titulo;
    private String nomeautor;

    private Livro(String t, String autor) {
        this.titulo = t;
        this.nomeautor = autor;
    }

    public void listaLivrosDisponiveis(Livro){ //getLivros
        for(Livro i : livros) {
            System.out.println("Livro:\nTitulo:"+i.titulo+"\nPor:"+i.nomeautor);
        }
    }

    public void setLivro(String t, String a) {
        Livro l = new Livro(t,a);
    }
}

class Leitor {
    private long nrutente;
    private String nome;

    public Leitor(String name, long nr) {
        this.nrutente = nr;
        this.nome = name;
    }
}

class Data {
    private int dia, mes, ano;

    public  Data(int d, int m, int a){ //construtor dá return a um objeto do tipo da classe
        this.dia = d;
        this.mes = m;
        this.ano = a;
        //dá return por si mesmo
    }

    private String Data(){
        return dia +"/"+mes+"/"+ano;
    }


    public String toString() {
        return Data();
    }
}

class  Requesicao {
    /*private Leitor leitor = new Leitor();
    private Livro livro = new Livro();
    private Data datarequesicao = new Data();
    private Data datadevolucao = new Data();

    public  Requesicao(){


    }

    public Requesicao requesitalivro(Leitor l, Data dtReq, Data dtDev) {
        Requesicao req = new Requesicao();

        leitor = l;
        datarequesicao = dtReq;
        datadevolucao = dtDev;

        return req;
    }*/
}

public class TrabalhoTres {

    private static void criaLeitores(ArrayList leitores) {

        Leitor l1 = new Leitor("Eduardo Nunes", 2020217675);
        Leitor l2 = new Leitor("Francisco Rosendo", 2020217697);
        Leitor l3 = new Leitor("Afonso Gouveia Melo", 2020217360);
        Leitor l4 = new Leitor("Lucas Anjo", 2020218028);
    }

    private static void criaLivros(ArrayList livros,Livro l){
        ArrayList<Livro> livros = new ArrayList<Livro>();

        Livro l1 = new Livro("Os Maias", "Eça de Queiroz");
        Livro l2 = new Livro("O Ano Da Morte De Ricardo Reis", "José Saramago");
        Livro l3 = new Livro("Sherlock Holmes: A Study in Scarlet", "Arthur Conan Doyle");
        livros.add(l1);
        livros.add(l2);
        livros.add(l3);
    }

    public static void main(String[] args)  {
        System.out.println("");
        ArrayList<Leitor> leitores = new ArrayList<Leitor>(); //crio uma lista de leitores.
        ArrayList<Livro> livros = new ArrayList<Livro>();

        criaLeitores(leitores);
        criaLivros(livros);

        Livro l = new Livro();
        l.listaLivrosDisponiveis();

        //exemplo
        Data d1 = new Data(23,2,2021);
        System.out.println("data:"+d1);
    }
}
