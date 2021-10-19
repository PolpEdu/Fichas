package overridingClasses;

class Conhecimento {
    private String Portugues;
    private long Matematica;
    private byte informatica;
}


public class Pessoas {
    private String nome;
    private  Conhecimento c;

    public String comunica(){
        return this.nome + "a comunicar.";
    }

    public String missao(){
        return "Sou uma pessoa normal...";
    }
}


class Alunos extends Pessoas {
    protected long nraluno; //so consigo ver na subclass alunos

    public String aprende() {
        return "APRENDI1!!";
    }

    @Override
    public String missao(){
        return aprende();
    }

}

class Docentes extends Pessoas {
    protected long mecanografico;

    public String ensina(){
        return "ENSINEI!";
    }

    @Override
    public String missao(){
        return ensina();
    }

    private String textDOC() {
        return "O ";
    }
}