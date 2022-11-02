import java.util.concurrent.Semaphore;

public class Productor extends Thread {
    Semaphore semaphore;

    public Productor(Semaphore sem) {
        semaphore = sem;
    }

    static int productos[] = {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};

    public void run(){
        while (Consumidor.sumatorio<=100) {
            try {
                introduceProductos();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public  void introduceProductos() throws InterruptedException {
        for (int i = 0; i <productos.length; i++) {
            introduceProductos2(i);
        }
    }

    private synchronized void introduceProductos2(int i) throws InterruptedException {
        if (Principal.cont==10){
            System.out.println(this.getName() + "El array está lleno.");
            this.wait();
        }else {
            this.notify();
        }
        if (productos[i]<0){
            try {
                semaphore.acquire();

                productos[i] = (int) Math.abs(Math.random()*10)+1;
                System.out.println(this.getName()+"Posición " + i + " .Se genera valor : " + productos[i]);

                Principal.cont++;
                System.out.println(Principal.cont);
                sleep(1000);

                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }else{
            System.out.println("Posición ocupada");
        }
    }
}
