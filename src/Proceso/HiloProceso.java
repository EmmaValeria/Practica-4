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
        for (int i = 0; i < 5 ; i++) {
            System.out.println("Proceso "+nombre+"| tamanio:  "+tamanio+"| prioridad: "+prioridad);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(i == 2){
                Thread.yield();
            }
        }
        System.out.println("Proceso Terminado");
    }

    public static void main(String[] args) {
        Thread chrome = new Thread(new HiloProceso("Chrome",500,7));
        Thread word = new Thread(new HiloProceso("Word",200,5));
        Thread antivirus = new Thread(new HiloProceso("Antivirus",800,10));
        Thread spotify = new Thread(new HiloProceso("Spotify",300,4));
        Thread explorer = new Thread(new HiloProceso("Explorer",700,8));

        chrome.start();
        try {
            chrome.join();
        }catch (InterruptedException e){e.printStackTrace();}

        word.start();
        try {
            word.join();
        }catch (InterruptedException e){e.printStackTrace();}

        antivirus.setPriority(Thread.MAX_PRIORITY);
        antivirus.start();
        try {
            antivirus.join();
        }catch (InterruptedException e){e.printStackTrace();}


    }


}
