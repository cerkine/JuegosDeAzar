package com.company;

import java.util.Random;
import java.util.Scanner;

public class PiedraPapelTijera {
        Scanner scanner2 = new Scanner(System.in);
        int dinero = 100;
        int numjugada;
        int ganadacpu;
        int ganadajugador;
        int cantiApostada;



        void mostrarRank(){
            System.out.println( " Ganadas Jugador : " + ganadajugador);
            System.out.println( " Ganadas Cpu     : " + ganadacpu);
            System.out.println( " Tu dinero actual: " + dinero);
        }

        void pedirApuesta() {
            cantiApostada = scanner2.nextInt();
            scanner2.nextLine();
            if (cantiApostada <= dinero) {
                dinero = dinero - cantiApostada;
                System.out.println( " Tu dinero actual: " + dinero);
            } else {
                System.out.println("Intentas apostar mas de lo que tienes");
                pedirApuesta();
            }
        }
        void start() {
            System.out.println("PIEDRA-PAPEL-TIJERA");

            do {
                mostrarRank();
                System.out.println("Cantidad que deseas apostar");
                pedirApuesta();
                if (cantiApostada > 0) {
                    jugar();
                }

            } while (cantiApostada> 0 && dinero > 0);

            if (dinero == 0) {
                System.out.println("TE HAS QUEDADO SIN DINERO! ADIOS");
            } else {
                System.out.println("ADIOS! TU DINERO: " + dinero);
            }
        }

        void jugar(){

            System.out.println("Escoge ? (R=Piedra/P=Papel/T=Tijera?");

            char jugada = scanner2.nextLine().charAt(0);

            switch (jugada) {
                case 'R':
                    numjugada = 0;
                    System.out.println("Has escogido Piedra ✊");
                    break;
                case 'P':
                    numjugada = 1;
                    System.out.println("Has escogido Papel ✋");
                    break;
                case 'T':
                    numjugada = 2;
                    System.out.println("Has escogido Tijera ✌");
                    break;
            }

            Random random = new Random();
            int maquina =random.nextInt(3);

            if (numjugada==maquina){
                dinero = dinero +cantiApostada;
                System.out.println("Empate");
                System.out.println();
                System.out.println();
                System.out.println("Se devuelve el dinero "+ dinero);
            }
            else if (numjugada== 0 && maquina ==1){
                ganadacpu = ganadacpu +1;
                System.out.println();
                System.out.println("Maquina Papel");
                System.out.println();
                System.out.println();
                System.out.println("Has perdido");
                System.out.println();
                System.out.println();

            }
            else if (numjugada == 0 && maquina==2){
                ganadajugador = ganadajugador +1;
                dinero = dinero + cantiApostada*2;
                System.out.println();
                System.out.println("Maquina Tijera");
                System.out.println();
                System.out.println();
                System.out.println("Has ganado, se suma tu ganancia " +dinero);
                System.out.println();
                System.out.println();

            }
            else if (numjugada== 1 && maquina==0){
                dinero = dinero + cantiApostada*2;
                ganadajugador = ganadajugador +1;
                System.out.println();
                System.out.println("Maquina Piedra");
                System.out.println();
                System.out.println();
                System.out.println("Has Ganado");
                System.out.println();
                System.out.println();
            }
            else if (numjugada == 1 && maquina==2){
                ganadacpu = ganadacpu +1;
                System.out.println();
                System.out.println("Maquina Tijera");
                System.out.println();
                System.out.println();
                System.out.println("Has perdido");
                System.out.println();
                System.out.println();
            }
            else if (numjugada== 2 && maquina==0){
                ganadacpu = ganadacpu +1;
                System.out.println();
                System.out.println("Maquina Piedra");
                System.out.println();
                System.out.println();
                System.out.println("Has perdido");
                System.out.println();
                System.out.println();
            }
            else if (numjugada == 2 && maquina==1){
                dinero = dinero + cantiApostada*2;
                ganadajugador = ganadajugador +1;
                System.out.println();
                System.out.println("Maquina Papel");
                System.out.println();
                System.out.println();
                System.out.println("Has ganado, se suma tu ganancia " +dinero);                System.out.println();
                System.out.println();
                System.out.println();
            }
        }
}



