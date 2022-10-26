import java.util.concurrent.Semaphore;
public class Principal {

    public static void main(String[] args) {
        Semaphore sem = new Semaphore(1);
        Consumidor c = new Consumidor(sem);
        c.start();
        for (int i = 1; i <= 2; i++) {
            Productor p = new Productor(sem);
            p.start();
        }
    }
}
/*
Hilo productor 1: introduce el valor 12 en la posición 3,
        Hilo consumidor: saca el valor 8 de la posición 6,
        Hilo productor 2: el array está lleno
 */
