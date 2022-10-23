import java.util.concurrent.Semaphore;

public class Triangulo implements Runnable{
    private int id;
    private double base;
    private double altura;

    private static int cont = 1;
    Semaphore sem = new Semaphore(2);

    public Triangulo(double base, double altura){
        super();
        this.id = cont++;
        this.base = base;
        this.altura = altura;


    }
    public void calculaArea() throws InterruptedException {
        double area = (base*altura)/2;
        Thread.sleep (3000);

        System.out.printf ("El triángulo nº%d con base: %.1f y altura: %.1f tiene un área de: %.1f %n",id,base,altura,area);
    }
    @Override
    public void run(){
        try {
            sem.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {

            this.calculaArea();

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            sem.release();
        }
    }
    public static void main (String [] args){
        for (int i = 0; i < 10; i++) {
            int a = (int)(Math.random()*10+1);
            int b = (int)(Math.random()*10+1);
            new Thread(new Triangulo(b, a)).start();
        }
    }
}