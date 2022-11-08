public class Cliente implements Runnable {

    private GestorSillas gestorSillas;

    private int nCliente;

    public Cliente(GestorSillas gestorSillas, int num) {
        this.gestorSillas = gestorSillas;
        this.nCliente=num;
    }

    @Override
    public void run() {
        // Los clientes no esperan que haya sillas libres, no hay bucle infinito.
        // Si no hay sillas libres se van...
        entrarEnBarberia();
    }
    
    public void entrarEnBarberia() {
        int posSillaLibre = gestorSillas.getPosSillaLibre();
        if (posSillaLibre == -1) {
            System.out.println("Nuevo cliente - No habia sillas libres, me marcho");
            gestorSillas.contClientes++;
            return;
        }
        System.out.println("Nuevo cliente se sienta en la silla: " + (posSillaLibre +1));
    }
}
