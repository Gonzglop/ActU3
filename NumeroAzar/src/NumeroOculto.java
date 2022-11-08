public class NumeroOculto{
    private int nAzar;
    private int fin ;

    public NumeroOculto(int nAzar) {
        this.nAzar = nAzar;
        this.fin = 0;
    }

    public synchronized int propuestaNumero(int num){
        if (nAzar==num && fin==0){
            fin = -1;
            return 1;
        }else{
            return 0;
        }
    }

    public int getFin() {
        return fin;
    }

}
