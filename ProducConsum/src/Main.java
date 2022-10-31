public class Main {

    public static void main(String[] args) {
        ArrayCompartido b = new ArrayCompartido(10);
        Productor p1 = new Productor(b,1);
        Productor p2 = new Productor(b,2);
        Consumidor c = new Consumidor(b,100);

        c.start();
        p1.start();
        p2.start();
    }
}