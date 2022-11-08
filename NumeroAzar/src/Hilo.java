public class Hilo extends Thread{
    int numPrueba;
    NumeroOculto nOculto;

    public Hilo(NumeroOculto nOculto) {
        this.nOculto = nOculto;
    }

    private void generaNum() throws InterruptedException {
        numPrueba = (int)(Math.random() * 100);
        sleep((long)(Math.random() * 5000));
    }

    @Override
    public void run() {
        while (nOculto.getFin()!=-1){
            try{
                generaNum();

                if(nOculto.propuestaNumero(numPrueba)==1) {

                    System.out.println(currentThread().getName() + " genera el número "+numPrueba+" : ¡ACIERTA!");

                }else if (nOculto.getFin()!=-1){

                    System.err.println(currentThread().getName() + " genera el número "+numPrueba+" : FALLA");
                }
            }catch (InterruptedException iex) {
                iex.printStackTrace();
            }
        }
    }
}
