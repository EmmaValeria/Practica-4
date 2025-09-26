public class ProcesoHilo extends Thread{

    // Atributos
    private int tamano;
    private int prioridad;

    // Constructor
    public ProcesoHilo(String nombre, int tamano, int prioridad){
        super(nombre);
        this.tamano = tamano;
        this.prioridad = prioridad;
    }

    // Setter para modificar el tamaño en ejecución
    public void setTamano(int nuevoTamano) {
        this.tamano = nuevoTamano;
        System.out.println("El proceso " + getName() + " ha cambiado su tamaño a: " + nuevoTamano + "MB");
    }

    // Setter para modificar la prioridad
    public void setPrioridad(int nuevaPrioridad) {
        this.prioridad = nuevaPrioridad; // Actualizar atributo
        super.setPriority(nuevaPrioridad); // Actualizar prioridad del Thread
        System.out.println("El proceso " + getName() + " ha cambiado su prioridad a: " + nuevaPrioridad);
    }

    // Getters para la tabla de procesos
    public int getTamano() {
        return tamano;
    }

    public int getPrioridad() {
        return prioridad;
    }

    // Sobreescribir el method run()
    public void run() {
        for (int i = 1; i <= 5; i++) {
            // Imprimir en pantalla el estado del hilo en cada ciclo
            System.out.println("Proceso: " + getName() +
                    " | Tamaño: " + tamano + "MB" +
                    " | Prioridad: " + prioridad +
                    " | Ciclo: " + i);

            // Si el ciclo es igual a 2, el proceso ejecuta un yield()
            if (i == 2) {
                System.out.println("El proceso " + getName() + " ejecuta yield() en la iteración " + i);
                Thread.yield();
            }

            // Spotify: yield() extra en la tercera iteración
            if (getName().equals("Spotify") && i == 3) {
                System.out.println("El proceso " + getName() + " ejecuta yield() en la iteración 3");
                Thread.yield();
            }

            // Explorer: sleep de 800 ms en la segunda iteración
            if (getName().equals("Explorer") && i == 2) {
                try {
                    System.out.println("El proceso " + getName() + " duerme 800ms en la iteración 2");
                    Thread.sleep(800);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            try {
                // sleep(ms) pausa el hilo actual por 500 ms para simular que el proceso está trabajando
                Thread.sleep(500);
            } catch (InterruptedException e) {
                // Capturar si el hilo fue interrumpido mientras estaba en sleep
                e.printStackTrace();
            }
        }
        // Imprimir en pantalla el nombre del proceso que termina
        System.out.println("El proceso " + getName() + " ha terminado su ejecución. \n");
    }

    // Method para imprimir la tabla de procesos
    public static void imprimirTabla(ProcesoHilo... procesos) {
        System.out.println("\nTabla de procesos:");
        System.out.println("-----------------------------------------------");
        System.out.printf("%-15s %-15s %-15s%n", "Nombre", "Tamaño(MB)", "Prioridad");
        System.out.println("-----------------------------------------------");
        for (ProcesoHilo p : procesos) {
            System.out.printf("%-15s %-15s %-15s%n", p.getName(), p.getTamano(), p.getPrioridad());
        }
        System.out.println("-----------------------------------------------\n");
    }

    // Main
    public static void main(String[] args) {

        // Crear nuevos hilos (cada uno tiene nombre, tamaño y prioridad asignada)
        ProcesoHilo chrome = new ProcesoHilo("Chrome", 500, 7);
        ProcesoHilo word = new ProcesoHilo("Word", 200, 5);
        ProcesoHilo antivirus = new ProcesoHilo("Antivirus", 800, 10);
        ProcesoHilo spotify = new ProcesoHilo("Spotify", 300, 4);
        ProcesoHilo explorer = new ProcesoHilo("Explorer", 700, 8);
        ProcesoHilo backup = new ProcesoHilo("Backup", 1000, 6);

        // Imprimir tabla inicial
        imprimirTabla(chrome, word, antivirus, spotify, explorer, backup);

        try {
            // Proceso Chrome
            chrome.start();
            chrome.join();

            // Proceso Word
            word.start();
            word.join();

            // Proceso Antivirus
            antivirus.start();
            antivirus.join();

            // Cambiar la prioridad de Spotify antes de lanzarlo
            spotify.setPrioridad(9);

            // Ejecutar Spotify, Explorer y Backup en paralelo
            spotify.start();
            explorer.start();
            backup.start();

            // Modificar el tamaño de Spotify Y Explorer durante su ejecución
            spotify.setTamano(350);
            explorer.setTamano(750);

            // Imprimir la tabla después de las modificaciones
            imprimirTabla(chrome, word, antivirus, spotify, explorer, backup);

            // join() para Spotify, Explorer, Backup
            spotify.join();
            explorer.join();
            backup.join();

            // Imprimir en pantalla que todos los hilos han terminado
            System.out.println("Todos los hilos han finalizado su ejecución.\n");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Imprimir mensaje final
        System.out.println("Todos los hilos han sido atendidos correctamente.\n");
    }
}
