package Proceso;

public class HiloProceso extends Thread {
    String nombre;
    int tamanio;
    int prioridad;
    public HiloProceso(String nombre, int tamanio, int prioridad) {
        this.nombre = nombre;
        this.tamanio = tamanio;
        this.prioridad = prioridad;
    }
    @Override
    public void run() {

    }
}
