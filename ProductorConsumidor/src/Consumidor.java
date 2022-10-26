import java.util.concurrent.Semaphore;

public class Consumidor extends Thread{
    Semaphore semaphore;

    public Consumidor(Semaphore sem) {
        semaphore = sem;
    }
    public static int sumatorio = 0;

    public void run(){
        while (sumatorio<=2000){
            consumeProductos();
        }
        System.out.println(sumatorio);
    }
    public void consumeProductos(){
        for (int i = 0; i <Productor.productos.length; i++) {
            if (Productor.productos[i]>=0 && sumatorio<=2000){
                try {
                    semaphore.acquire();

                    sumatorio += Productor.productos[i];
                    Productor.productos[i]=-1;
                    sleep(1000);

                    System.out.println("consume un producto");

                    semaphore.release();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
