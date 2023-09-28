package controller;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

public class ThreadServidor extends Thread {
    private int id;
    private Semaphore semaforo;
    int tempoCalculo;
    int tempoTransacao;

    public ThreadServidor(int id, Semaphore semaforo) {
        this.id = id;
        this.semaforo = semaforo;
    }

    @Override
    public void run() {
        if (id % 3 == 1) {
            threadTipo1();
        } else if (id % 3 == 2) {
            threadTipo2();
        } else if (id % 3 == 0) {
            threadTipo3();
        }
    }

    private void threadTipo1() {
        tempoTransacao = 1000;

        for (int i = 0; i < 2; i++) {
            tempoCalculo = gerarTempoAleatorio(200, 1000);

            fazerCalculo(tempoCalculo);
            fazerTransacaoDeBd(tempoTransacao);
        }
    }

    private void threadTipo2() {
        tempoTransacao = 1500;

        for (int i = 0; i < 3; i++) {
            tempoCalculo = gerarTempoAleatorio(500, 1500);

            fazerCalculo(tempoCalculo);
            fazerTransacaoDeBd(tempoTransacao);
        }
    }

    private void threadTipo3() {
        tempoTransacao = 1500;

        for (int i = 0; i < 3; i++) {
            tempoCalculo = gerarTempoAleatorio(1000, 2000);

            fazerCalculo(tempoCalculo);
            fazerTransacaoDeBd(tempoTransacao);
        }
    }

    private void fazerCalculo(int tempoDeEspera) {
        exibirAcao(tempoDeEspera, "calculos");
        fazerEsperar(tempoDeEspera);
    }

    private void fazerTransacaoDeBd(int tempoDeEspera) {
        try {
            semaforo.acquire();
            exibirAcao(tempoDeEspera, "transacao de BD");
            fazerEsperar(tempoDeEspera);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            semaforo.release();
        }
    }

    private int gerarTempoAleatorio(int minimo, int maximo) {
        maximo++; //O máximo não é inclusivo
        return ThreadLocalRandom.current().nextInt(minimo, maximo);
    }

    private void fazerEsperar(int tempoDeEspera) {
        try {
            sleep(tempoDeEspera);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void exibirAcao(int tempoDeEspera, String acao) {
        System.out.println("#" + id + " >>> fazendo: " +  acao + " |  levara: " + tempoDeEspera + "ms");
    }
}
