public class Filosofo extends Thread {

    private Mesa mesa;
    private int comensal;
    private int indiceComensal;


    public Filosofo(Mesa m, int comensal){
        this.mesa = m;
        this.comensal = comensal;
        this.indiceComensal = comensal -1;
    }

    public void run(){
        while (true){
            this.pensando();

            this.mesa.cogerTenedores(this.indiceComensal);

            this.comiendo();

            System.out.println("Filósofo " + comensal + " deja de comer, tenedores libres: " +
                    (this.mesa.tenedorIzquierda(this.indiceComensal) +1 ) +
                    " y " + (this.mesa.tenedorDerecha(this.indiceComensal) +1 ));
            this.mesa.dejarTenedores(this.indiceComensal);
        }
    }

    public void pensando(){
        System.out.println("Filosofo " + comensal + " está pensando");
        try {
            sleep((long)(Math.random() * 5000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void comiendo(){
        System.out.println("Filosofo " + comensal + " está COMIENDO");
        try {
            sleep((long)(Math.random() * 5000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}