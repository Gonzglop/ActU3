
import java.util.concurrent.Semaphore;

import static java.lang.Thread.currentThread;

public class ArrayCompartido {

    private int[] arrayProductos;
    Semaphore sem;
    private int cont;
    private boolean vacio;
    private boolean lleno;
    private static String resultado;

    public String getResultado() {
        return resultado;
    }


    public ArrayCompartido(int tam,Semaphore sem){
        this.arrayProductos = new int[tam];
        this.sem = sem;
        this.cont = 0;
        this.vacio = true;
        this.lleno = false;
        this.resultado = "";
    }
    
    public int consumir() {
        this.sem.acquireUninterruptibly();
        while(vacio){
            try {
                System.err.println("Hilo consumidor: esperando...");
                resultado += "\nHilo consumidor: esperando...";
                wait();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }

        cont--;
        System.err.println("Hilo consumidor: Saca el valor " + arrayProductos[cont] + " en la posición " + cont);
        resultado += "\nHilo consumidor: Saca el valor " + arrayProductos[cont] + " en la posición " + cont;

        lleno = false;
        if(cont == 0){
            vacio = true;
            System.err.println("Hilo consumidor: El array está VACÍO.");
            resultado += "\nHilo consumidor: El array está VACÍO.";
        }
        this.sem.release();
        
        return arrayProductos[cont];
    }
    
    public void producir(int p) {
        this.sem.acquireUninterruptibly();
        while(lleno){
            try {
                System.out.println("Hilo productor " + currentThread().getId() + ": esperando...");
                resultado += "\nHilo productor " + currentThread().getId() + ": esperando...";
                wait();

            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        
        arrayProductos[cont] = p;
        System.out.println("Hilo productor " + currentThread().getId() + ": Introduce el valor " + p + " en la posición " + cont);
        resultado += "\nHilo productor " + currentThread().getId() + ": Introduce el valor " + p + " en la posición " + cont;

        cont++;
        vacio = false;

        if(cont == arrayProductos.length){
            lleno = true;
            System.out.println("Hilo productor " + currentThread().getId() + ": El array está LLENO.");
            resultado += "\nHilo productor " + currentThread().getId() + ": El array está LLENO." ;
        }
        this.sem.release();
    }

}

