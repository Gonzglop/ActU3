import java.util.concurrent.Semaphore;

public class Productor extends Thread {

    private ArrayCompartido arrayCompartido;
    Semaphore semP;
    private int id;

    public Productor(ArrayCompartido b, int id, Semaphore semP) {
        this.arrayCompartido = b;
        this.id = id;
        this.semP = semP;
    }

    @Override
    public long getId() {
        return id;
    }

    public void run() {
        while (true) {
            try {
                int p = (int) (Math.random() * 10)+1;
                arrayCompartido.producir(p);

                sleep((int) (Math.random() * 3000));

            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
