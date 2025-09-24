public class HilosJoin extends Thread {

    // Definir atributos
    private String tipo;
    private int prioridad;

    // Constructor que recibe nombre del hilo, tipo y prioridad
    public HilosJoin(String nombre, String tipo, int prioridad) {
        super(nombre); // Pasa el nombre al constructor de Thread
        this.tipo = tipo;
        this.prioridad = prioridad;
    }

    // Sobreescribir el method run()
    public void run() {
        for (int i = 0; i < 4; i++) {
            // Imprimir en pantalla el estado del hilo en cada ciclo
            System.out.println("Proceso " + getName() +
                               " | Tipo: " + tipo +
                               " | Prioridad: " + prioridad +
                               " | Ciclo: " + i);
            try {
                // sleep(ms) pausa el hilo actual por 400 ms
                // Es de ayuda para simular espera
                Thread.sleep(400);
            } catch (InterruptedException e) {
                // Capturar si el hilo fue interrumpido mientras estaba en sleep
                e.printStackTrace();
            }
        }
        // Imprimir en pantalla el nombre del proceso que termina
        System.out.println("Termina proceso " + getName());
    }

    // Main
    public static void main(String[] args) {

        // Crear nuevos hilos (cada uno tiene nombre, tipo y prioridad asignada)
        HilosJoin chrome = new HilosJoin("Chrome", "Aplicación", 8);
        HilosJoin word = new HilosJoin("Word", "Aplicación", 7);
        HilosJoin antivirus = new HilosJoin("Antivirus", "Servicio", 10);
        HilosJoin notepad = new HilosJoin("Notepad", "Aplicación ligera", 5);
        HilosJoin explorer = new HilosJoin("Explorer", "Sistema", 9);

        // Inicio del proceso Chrome
        chrome.start();
        try {
            // Con join() obliga a main a esperar que termine "chrome" antes de continuar con los siguientes hilos
            chrome.join();
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        // Inicio del proceso Word
        word.start();
        try {
            // Hasta que termine Word, va a continuar con los siguientes hilos
            word.join();
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        // Inicio del proceso Antivirus
        antivirus.start();
        try {
            // Una vez que termine Antivirus comenzará con los siguientes procesos
            antivirus.join();
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        // Estos hilos se ejecutan en paralelo porque no tienen join()
        notepad.start();
        explorer.start();

        // Imprimir en pantalla el mensaje para el hilo principal (main)
        System.out.println("Termina thread main");
    }
}