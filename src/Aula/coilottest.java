package Aula;

import java.util.Random;

class math {
    private int a;
    private int b;
    private int c;
    public static int soma(int a, int b) {
        return a + b;
    }
}

public class coilottest {
    //main method begins execution of Java application
    public static void main(String[] args) {
        //declare and initialize variables
        int num = 0;
        num = random();
        System.out.println("The random number is: " + num);

    }
    //generate a random number between 1-5
    public static int random(){
        Random r = new Random();
        int num = r.nextInt(5) + 1;
        return num;
    }

    //calculate de media
    public static double media(int a, int b){
        double media = (a + b) / 2.0;
        return media;
    }

    //calculate the sum
    public static int sum(int a, int b){
        int sum = a + b;
        return sum;
    }

}
