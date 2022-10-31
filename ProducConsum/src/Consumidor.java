
public class Consumidor extends Thread {

    private ArrayCompartido arrayCompartido;
    private int sumatorio;
    private int limite;

    public Consumidor(ArrayCompartido b, int l) {
        this.arrayCompartido = b;
        this.sumatorio = 0;
        this.limite = l;
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
        System.exit(0);
    }
}