package filosofo;

import java.util.concurrent.Semaphore;

public class Mesa {

    private boolean[] tenedores;
    Semaphore semaforo;

    public Mesa(int numTenedores, int permisos){

        this.tenedores = new boolean[numTenedores];
        semaforo = new Semaphore(permisos);
    }

    public int tenedorIzquierda(int i){
        return i;
    }

    public int tenedorDerecha(int i){
        if (i==0){
            return this.tenedores.length -1;
        }else{
            return i - 1;
        }
    }

    public synchronized void cogerTenedores(int comensal){
        while (tenedores[tenedorIzquierda(comensal)] || tenedores[tenedorDerecha(comensal)]){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        tenedores[tenedorIzquierda(comensal)] = true;
        tenedores[tenedorDerecha(comensal)]= true;
    }

    public synchronized void dejarTenedores (int comensal){
        tenedores[tenedorIzquierda(comensal)] = false;
        tenedores[tenedorDerecha(comensal)]= false;
        notifyAll();

    }

}
