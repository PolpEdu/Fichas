package Aula;

public class LuCorrido {


    public static void main(String[] args){
        Fracoes f1 = new Fracoes(10,5);
        Fracoes f2 = new Fracoes(90,3);


        System.out.println(f1.getmult(f2));
    }

}

class Fracoes {
    private long resultado;
    private long num, den;

    public Fracoes(long a, long b){
        this.num = a;
        this.den = b;
    }

    private Fracoes calcdiv(Fracoes f) {

        return f;
    }

    private Fracoes calcmult(Fracoes f) {
        long a = this.den*f.den;
        long b = this.num*f.num;

        Fracoes res = new Fracoes(a,b);
        return res;
    }

    private long calcsub(long a, long b) {
        return a-b;
    }

    private long calcsoma(long a, long b) {
        return a+b;
    }


    public long getsoma(long a, long b) {
        return calcsoma(a, b);
    }

    public long getsub(long a, long b ){
        return calcsub(a, b);
    }

    public Fracoes getmult(Fracoes f) {
        return calcmult(f);
    }

    public void getdiv(long a ,long b) {
        this.resultado = resultado;//calcdiv(a,b);
    }

    /*public String toString(){
        return resultado;
    }*/
}
