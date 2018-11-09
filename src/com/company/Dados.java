package com.company;

import java.util.Random;
import java.util.Scanner;

class Dados {
    Random random = new Random();
    Scanner scanner = new Scanner(System.in);
    final String[] dados = {"⚀","⚁","⚂","⚃","⚄","⚅"};

    int dinero = 100;  // balance de dinero
    int apuesta;      // cantidad apostada
    int numero;       // numero apostado
    int dado;         // numero que sale en el dado
    int ganadajugador;
    int ganadacpu;

    void start(){
        System.out.println("JUEGO DE DADOS");

        do {
            mostrarRank();
            System.out.println("Cuanto dinero quieres apostar?");
            pedirApuesta();
            if (apuesta > 0) {
                pedirNumero();
                sacarDado();
                calcularBalance();
            }
        } while (apuesta > 0 && dinero > 0);

        if (dinero == 0){
            System.out.println("TE HAS QUEDADO SIN DINERO! ADIOS");
        } else {
            System.out.println("ADIOS! TU DINERO: " + dinero);
        }
    }

    void pedirApuesta(){
        apuesta = scanner.nextInt();
        scanner.nextLine();
        if (apuesta <= dinero) {
            dinero = dinero - apuesta;
        } else {
            System.out.println("Intentas apostar mas de lo que tienes");
            pedirApuesta();
        } 
    }

    void pedirNumero(){
        System.out.println("A que numero deseas apostar? ");
        numero = scanner.nextInt();
         if (numero>0 && numero<7) {
             System.out.println("Tu dado es " + dados[numero-1]);
         }
         else {
             System.out.println("numero no valido ");
             pedirNumero();
         }
    }

    void sacarDado(){
        dado= 1+random.nextInt(6);
        System.out.println("El dado que ha salido es " + dados[dado-1]);
    }

    void calcularBalance(){
        if (numero==dado){
            ganadajugador = ganadajugador+1;
            dinero = dinero + apuesta*2;
            System.out.println("Has ganado, toma tu dinero :" + dinero);
        }
        else{
            ganadacpu=ganadacpu+1;
            System.out.println("Has perdido ");
        }
    }
    void mostrarRank(){
        System.out.println( " Ganadas Jugador : " + ganadajugador);
        System.out.println( " Ganadas Cpu     : " + ganadacpu);
        System.out.println( " Tu dinero actual: " + dinero);
    }
}
