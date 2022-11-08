
public class Principal {
    public static void main(String[] args) {

        int numerAzar = (int)(Math.random() * 100);
        System.out.println("(El número a adivinar es el "+ numerAzar + ")");

        NumeroOculto numeroOculto = new NumeroOculto(numerAzar);

        for (int i=0; i < 10; i++) {
            Hilo h = new Hilo(numeroOculto);
            h.setName("Hilo " + i);
            h.start();
        }
    }
}
