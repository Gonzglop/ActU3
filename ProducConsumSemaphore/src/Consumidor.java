import java.util.concurrent.Semaphore;

public class Consumidor extends Thread {

    private ArrayCompartido arrayCompartido;
    Semaphore semC;
    private int sumatorio;
    private int limite;

    public Consumidor(ArrayCompartido b, int l,Semaphore semC) {
        this.arrayCompartido = b;
        this.sumatorio = 0;
        this.limite = l;
        this.semC = semC;
    }

    public void run() {
        while (sumatorio < limite) {
            try {
                int c = arrayCompartido.consumir();
                sumatorio += c;

                sleep((int) (Math.random() * 3000));

            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        System.out.println("TOTAL CONSUMIDO: " + sumatorio);
        String aux = arrayCompartido.getResultado() + ("\nTOTAL CONSUMIDO: " + sumatorio);
        EscribeFich.escribeDatos(aux);
        System.exit(0);
    }
}