package view;

import controller.ThreadServidor;

import java.util.concurrent.Semaphore;

public class Executar {
    public static void main(String[] args) {
        Semaphore semaforo = new Semaphore(1);

        ThreadServidor[] threads = new ThreadServidor[21];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new ThreadServidor(i + 1, semaforo);

            threads[i].start();
        }
    }
}
