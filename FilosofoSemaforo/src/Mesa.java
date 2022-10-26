import java.util.concurrent.Semaphore;

public class Mesa {

    private Semaphore[] tenedores;

    public Mesa(int numTenedores){

        this.tenedores = new Semaphore[numTenedores];

        for (int cont = 0 ; cont < tenedores.length ; cont++){
            tenedores[cont] = new Semaphore(1);
        }
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

    public void cogerTenedores(int comensal){

        try {
            tenedores[tenedorIzquierda(comensal)].acquire();
            tenedores[tenedorDerecha(comensal)].acquire();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void dejarTenedores (int comensal){
        tenedores[tenedorIzquierda(comensal)].release();
        tenedores[tenedorDerecha(comensal)].release();

    }

}
