public class NumeroOculto{
    private int nAzar;
    private int fin;

    public NumeroOculto(int nAzar) {
        this.nAzar = nAzar;
    }

    public int propuestaNumero(int num){
        if (nAzar==num){
            fin = -1;
            return 1;
        }else if (fin==-1){
            return fin;
        }else{
            return 0;

        }

    }
}
