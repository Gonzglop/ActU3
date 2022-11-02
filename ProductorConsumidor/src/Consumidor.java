import java.util.concurrent.Semaphore;

public class Consumidor extends Thread{
    Semaphore semaphore;

    public Consumidor(Semaphore sem) {
        semaphore = sem;
    }
    public static int sumatorio = 0;

    public void run(){
        while (sumatorio<=100){
            try {
                for (int i = 0; i <Productor.productos.length; i++) {
                    System.out.println("hola");

                    consumeProductos2(i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(sumatorio);
    }

    private synchronized void consumeProductos2(int i) throws InterruptedException {
        if (Principal.cont==0){
            System.out.println("El array está vacío");
            this.wait();
        }else {
            this.notify();
        }
        if (Productor.productos[i]>=0 && sumatorio<=100){
            try {
                semaphore.acquire();
                System.out.println(this.getName()+"consume un producto en posición "+ i);

                sumatorio += Productor.productos[i];
                Productor.productos[i]=-1;
                Principal.cont--;

                System.out.println(Principal.cont);

                sleep(1000);


                semaphore.release();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }else{
            System.out.println("No puede consumir");
        }
    }
}
