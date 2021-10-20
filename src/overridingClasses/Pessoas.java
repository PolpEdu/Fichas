package overridingClasses;


public class Pessoas {
    private String nome;

    public String comunica(){
        return this.nome + "a comunicar.";
    }

    public String missao(){
        return "Sou uma pessoa normal...";
    }
    public String getnome(){
        return this.nome;
    }

    public String toString() {
        return "Sou uma pessoa normal com o nome: "+ nome ;
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

    @Override
    public String toString() {
        return "O "+ super.getnome() ;
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

    @Override
    public String toString() {
        return "O "+super.getnome()+ "";
    }
}