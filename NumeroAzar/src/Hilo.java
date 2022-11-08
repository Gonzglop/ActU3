public class Hilo extends Thread{
    int numPrueba;
    NumeroOculto nOculto;

    public Hilo(NumeroOculto nOculto) {
        this.numPrueba = 0;
        this.nOculto = nOculto;
    }

    @Override
    public void run() {
        while (nOculto.propuestaNumero(numPrueba)!=1){
            numPrueba= (int)(Math.random() * 100);
            nOculto.propuestaNumero(numPrueba);
        }
        System.out.println("Se ha acertado el número");

    }

    /*
     @Override
    public void run() {
        while(!numOculto.gameOver) {
            try{
                if(numOculto.semaphore.tryAcquire()) {
                    Thread.sleep((long) (Math.random() * MAX_TIME_GUESSING));
                    double f = Math.random()/Math.nextDown(1.0);
                    double x = 0*(1.0 - f) + 100*f;
                    numPrueba = (int) Math.round(x);

                    if(numOculto.propuestaNumero(numPrueba) == -1) {
                        System.out.printf("El hilo %d propone el número %d... pero el juego ya había terminado.\n",
                                id, numPrueba);
                    }
                    else if (numOculto.propuestaNumero(numPrueba) == 0) {
                        System.out.printf("El hilo %d propone el número %d... y falla.\n",
                                id, numPrueba);
                    }
                    else {
                        System.out.printf("El hilo %d propone el número %d... y acierta!!.\n",
                                id, numPrueba);
                    }
                    numOculto.semaphore.release();
                }
            }
            catch (InterruptedException iex) {
                iex.printStackTrace();
            }
        }
    }
     */
}
