
public class Productor extends Thread {

    private ArrayCompartido arrayCompartido;

    private int id;

    public Productor(ArrayCompartido b, int id) {
        this.arrayCompartido = b;
        this.id = id;
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
