import java.util.concurrent.Semaphore;

public class Productor extends Thread {
    Semaphore semaphore;

    public Productor(Semaphore sem) {
        semaphore = sem;
    }

    static int productos[] = {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};

    public void run(){
        while (Consumidor.sumatorio<=2000)
        introduceProductos();
    }

    public void introduceProductos(){
        for (int i = 0; i <productos.length; i++) {
            if (productos[i]<0){
                try {
                    semaphore.acquire();

                    productos[i] = (int) Math.abs(Math.random()*1000);
                    System.out.println("Posición:" + i + " .Se genera valor : " + productos[i]);
                    sleep(1000);

                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }else{
                //System.out.println("Posición ocupada");
            }
        }
    }
}
