
import static java.lang.Thread.currentThread;

public class ArrayCompartido {

    private int[] arrayProductos;
    private int cont;
    private boolean vacio;
    private boolean lleno;
    
    public ArrayCompartido(int tam){
        this.arrayProductos = new int[tam];
        this.cont = 0;
        this.vacio = true;
        this.lleno = false;
    }
    
    public synchronized int consumir(){
        while(vacio){
            try {
                System.err.println("Hilo consumidor: esperando...");
                wait();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }

        cont--;
        System.err.println("Hilo consumidor: Saca el valor " + arrayProductos[cont] + " en la posición " + cont);

        lleno = false;
        if(cont == 0){
            vacio = true;
            System.err.println("Hilo consumidor: El array está VACÍO.");
        }
        notifyAll();
        
        return arrayProductos[cont];
    }
    
    public synchronized void producir(int p){
        while(lleno){
            try {
                System.out.println("Hilo productor " + currentThread().getId() + ": esperando...");
                wait();

            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        
        arrayProductos[cont] = p;
        System.out.println("Hilo productor " + currentThread().getId() + ": Introduce el valor " + p + " en la posición " + cont);

        cont++;
        vacio = false;

        if(cont == arrayProductos.length){
            lleno = true;
            System.out.println("Hilo productor " + currentThread().getId() + ": El array está LLENO.");
        }
        notifyAll();
    }
}

