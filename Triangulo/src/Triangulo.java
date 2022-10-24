import java.util.concurrent.Semaphore;

public class Triangulo implements Runnable{
    private int id;
    private double base;
    private double altura;
    private FabricaTriangulos fabTriangulos = new FabricaTriangulos(5);

    private static int cont = 1;

    public Triangulo(double base, double altura){
        this.id = cont++;
        this.base = base;
        this.altura = altura;
    }
    public void calculaArea() {
        double area = (base*altura)/2;
        System.out.printf ("El triángulo nº%d con base: %.1f y altura: %.1f tiene un área de: %.1f %n",id,base,altura,area);
    }
    @Override
    public void run(){
        try {
            fabTriangulos.semaforo.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            this.calculaArea();
            Thread.sleep (3000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            fabTriangulos.semaforo.release();
        }
    }


    public static class FabricaTriangulos {
        Semaphore semaforo;

        public FabricaTriangulos(int limite) {
            semaforo = new Semaphore(limite);
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