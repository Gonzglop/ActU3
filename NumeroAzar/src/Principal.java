
public class Principal {
    public static void main(String[] args) {
        int numerAzar = (int)(Math.random() * 100);

        NumeroOculto numeroOculto = new NumeroOculto(numerAzar);

        for (int i=0; i < 10; i++) {
            new Hilo(numeroOculto).start();
        }

    }
}
