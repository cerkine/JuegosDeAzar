package com.company;

import java.util.Random;
import java.util.Scanner;

public class Cartas {
    Random random = new Random();
    Scanner scanner = new Scanner(System.in);
    final String[] baraja = {"🂡","🂢","🂣","🂤","🂥","🂦","🂧","🂨","🂩","🂫","🂭","🂮"};

    int money = 100;  // balance de dinero
    int apuesta;      // cantidad apostada

    int[] puntos = new int[2];
    final int PLAYER = 0;
    final int CRUPIER = 1;

    int ganadascru;
    int ganadasjug;

    void start(){
        System.out.println("JUEGO DE DADOS");
        do {
            mostrarRank();
            System.out.println("Cuanto dinero quieres apostar?");
            pedirApuesta();
            if (apuesta > 0) {
                pedirCartas();
                sacarCartas();
                comprobarGanador();

            }
        } while (apuesta > 0 && money > 0);

        if (money== 0){
            System.out.println("TE HAS QUEDADO SIN DINERO! ADIOS");
        } else {
            System.out.println("ADIOS! TU DINERO: " + money);
        }
        // Pedir apuesta
        // Si apuesta es mayor que 0: pedir cartas y sacarCartas
        // Comprobar ganador
    }

    void mostrarRank() {
        System.out.println("Partidas ganadas crupier :"+ ganadascru);
        System.out.println("Partidas ganadas jugador :"+ ganadasjug);
        System.out.println("Tu dinero: "+money);
    }

    void pedirApuesta(){
        do {
            System.out.println("MONEY: " + money);
            System.out.println("APUESTA: ");
            apuesta = scanner.nextInt();
            scanner.nextLine();

            if (apuesta > money){
                System.out.println("NO TIENES TANTO DINERO");
            }
        } while (apuesta > money);

        money -= apuesta;
    }

    void sacarCartas() {
        if (puntos[PLAYER] > 21) return;

        System.out.println("TURNO DEL CRUPIER...");
        scanner.nextLine();
        do {
            int carta = random.nextInt(12);
            puntos[CRUPIER] += carta + 1;
            System.out.println("     " + (carta + 1) + " " + baraja[carta]);
            System.out.println("PUNTOS: " + puntos[CRUPIER]);
            scanner.nextLine();
        } while (puntos[CRUPIER] < 21 && puntos[CRUPIER] < puntos[PLAYER]);
    }

    void pedirCartas(){
        char sacar;
        do {
            System.out.println("SACAR CARTA?(s/n)");
            sacar = scanner.nextLine().charAt(0);

            if (sacar == 's') {
                int carta = random.nextInt(12);
                puntos[PLAYER] += carta + 1;
                System.out.println((carta + 1) + " " + baraja[carta]);
                System.out.println("PUNTOS: " + puntos[PLAYER]);
            }
        } while (sacar == 's' && puntos[PLAYER] < 21);
    }

    void comprobarGanador(){
        System.out.println();
        System.out.println("PLAYER: " + puntos[PLAYER] + "   CRUPIER: " + puntos[CRUPIER]);
        if (puntos[CRUPIER] > 21){
            System.out.println("PLAYER WINS!");
            money += apuesta*2;
            ganadasjug += 1;
        } else if (puntos[PLAYER] > 21){
            System.out.println("CRUPIER WINS");
            ganadascru += 1;
        } else if (puntos[CRUPIER] > puntos[PLAYER]) {
            ganadascru += 1;
            System.out.println("CRUPIER WINS");
        } else if (puntos[PLAYER] > puntos[CRUPIER]) {
            System.out.println("PLAYER WINS!");
            money += apuesta*2;
            ganadasjug += 1;
        } else {
            System.out.println("EMPATE");
            money += apuesta;
        }
    }

}
