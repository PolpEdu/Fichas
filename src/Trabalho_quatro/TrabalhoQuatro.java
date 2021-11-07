package Trabalho_quatro;

import java.util.ArrayList;
import java.util.Random;


class Barco {
    protected String estrutura; //rigidos semirrigidos
    protected String dimensao; //pequenos medios grandes
    protected long matricula;


    protected Barco(String estrutura, String dimensao, int matricula) { //proctected para so as subclasses
        if(estrutura.equals("rigidos") || estrutura.equals("semirrigidos")) this.estrutura = estrutura;
        else throw new IllegalArgumentException("Estrutura invalida.");

        if(dimensao.equals("pequenos") || dimensao.equals("medios") || dimensao.equals("grandes")) this.dimensao = dimensao;
        else throw new IllegalArgumentException("Dimensao invalida.");

        this.matricula = matricula;
    }

    public String toString() {
        return "\nEstrutura: '" + this.estrutura + '\'' + ", Dimensao: '" + this.dimensao + '\'' + ", Matricula: " + this.matricula+"\n";
    }
}

class BarcosDeRecreio extends Barco{
    private long ocmax;

    public BarcosDeRecreio(String estrutura, String dimensao, int matricula, long ocmax){
        super(estrutura, dimensao, matricula);
        this.ocmax = ocmax;
    }

    @Override
    public String toString() {
        return "Barco de Recreio, " + "Ocupação máxima: " + this.ocmax + super.toString();
    }
}

class BarcosDePesca extends Barco {
    private String tipodepesca; //cana ou rede

    public BarcosDePesca(String estrutura, String dimensao, int matricula, String tipodepesca){
        super(estrutura, dimensao, matricula);

        if(tipodepesca.equals("cana") || tipodepesca.equals("rede")) this.tipodepesca = tipodepesca;
        else throw new IllegalArgumentException("Tipo de pesca invalida.");

    }

    @Override
    public String toString() {
        return "Barco de Pesca, " + "Tipo de pesca: " + this.tipodepesca + super.toString();
    }
}

class BarcosDaMarinha extends Barco{

    protected BarcosDaMarinha(String estrutura, String dimensao, int matricula) {
        super(estrutura, dimensao, matricula);
    }
}

class Torpedeiro extends BarcosDaMarinha {
    private int nrlancadores; // 1-6

    protected Torpedeiro(String estrutura, String dimensao, int matricula, int nrlancadores) {
        super(estrutura, dimensao, matricula);
        if(nrlancadores<7 || nrlancadores>0) this.nrlancadores = nrlancadores;
        else throw new IllegalArgumentException("Número de lançadores inválido.");

    }

    @Override
    public String toString() {
        return "Torpedeiro" + ", Numero de lancadores: " + this.nrlancadores +super.toString();
    }
}

class Fragata extends BarcosDaMarinha {
    private String tipoart; // antiaereas ou antissubmarinas

    protected Fragata(String estrutura, String dimensao, int matricula, String tipoart) {
        super(estrutura, dimensao, matricula);

        if(tipoart.equals("antiaereas") || tipoart.equals("antissubmarinas"))  this.tipoart = tipoart;
        else throw new IllegalArgumentException("Tipo de artilharia invalida.");


    }

    @Override
    public String toString() {
        return  "Fragata" + ", Tipo de artelharia: " + tipoart+super.toString();
    }

}


class Barcos {
    private ArrayList<Barco> marina;

    public Barcos(){
        marina = new ArrayList<>();
    }

    public void addBarco(Barco b){
        marina.add(b);
    }

    @Override
    public String toString() {
        String topprint = "Barcos da Marina:\n\n";
        for (int i = 0; i < marina.size(); i++) {
            topprint = topprint + marina.get(i).toString() + "\n";
        }
        return topprint;
    }
}

public class TrabalhoQuatro {
    public Barco criabarco(int s, Random r) {
        
        Barco b;
        String[] dimens = {"pequenos","medios","grandes"};
        
        
        // Generar dados principais de um barco:
        int estr = r.nextInt(2)+1;
        String estrS = estr == 1 ? "rigidos" : "semirrigidos";


        int dim = r.nextInt(3);
        String dimS = dimens[dim]; //seleciona ao acaso entre a lista de dimensoes

        int tam = 6; // tamanho da matricula
        String matri = "";
        for (int i = 0; i < tam; i++) { //generar matricula
            String sel = Integer.toString(r.nextInt(10)); // random a number to a 0 - 9
            matri = matri + sel;
        }
        int ma = Integer.parseInt(matri); //passa-la ara inteiro


        int lanc = r.nextInt(6)+1; //gera um nr de 1 a 6
        int fiftyfifty = lanc%2==1 ? 1: 2; //ver se e par ou impar para fazer 50%

        // System.out.println(s);
        switch (s) {
            case 1 -> {
                long ocomax = r.nextInt(100000);
                b = new BarcosDeRecreio(estrS, dimS, ma, ocomax);
            }
            case 2 -> {
                String tippesc = fiftyfifty == 1 ? "cana" : "rede";
                b = new BarcosDePesca(estrS, dimS, ma, tippesc);
            }
            case 3 -> b = new Torpedeiro(estrS, dimS, ma, lanc);
            case 4 -> {
                String tipoart = fiftyfifty == 1 ? "antiaereas" : "antissubmarinas";
                b = new Fragata(estrS, dimS, ma, tipoart);
            }
            default -> throw new IllegalStateException("Unexpected value: " + s);
        }
        return b;
    }


    public static void main(String[] args) {
        Random r = new Random();
        Barcos bs = new Barcos();
        TrabalhoQuatro m = new TrabalhoQuatro();

        int n = 10;   //nr de barcos a criar
        int selecao;  //nr ao acaso de 1 a 4


        for (int i = 0; i < n; i++) {
            selecao = r.nextInt(4)+1; //generar numeros de 1 a 4
            Barco b = m.criabarco(selecao,r);
            bs.addBarco(b);
        }
        System.out.println(bs);
    }
}
