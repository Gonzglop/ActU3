import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) {
        Semaphore s = new Semaphore(1,true);
        ArrayCompartido b = new ArrayCompartido(10,s);
        Productor p1 = new Productor(b,1,s);
        Productor p2 = new Productor(b,2,s);
        Consumidor c = new Consumidor(b,100,s);

        c.start();
        p1.start();
        p2.start();
    }
}