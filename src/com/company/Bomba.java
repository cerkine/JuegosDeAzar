package com.company;


import java.util.Random;
import java.util.Scanner;

public class Bomba{
    Random random = new Random();
    Scanner scanner = new Scanner(System.in);

    final String[] symbols = {"\033[96m🏠\033[0m", "💣", "\033[35m🙅\033[0m", "\033[95m🙆\033[0m","\033[95m🙇\033[0m", "\033[91m\uD83D\uDD25\033[0m"};
    final int HOUSE = 0;
    final int BOMB = 1;
    final int WAITING = 2;
    final int SURVIVE = 3;
    final int DEAD = 4;
    final int FIRE = 5;
    int com=0;

    int[][] tablero = new int[5][10];

    int playerX, playerY;
    int money = 100;
    int apuesta;

    void start(){
        do {
            pedirApuesta();
            if (apuesta > 0) {
                inicializarTablero();
                mostrarTablero();
                elegirCasa();
                mostrarTablero();
                bombardear();
                mostrarTablero();
                comprobar();
                mostrarTablero();
                calcularBalance();
            }
        } while (apuesta > 0 && money > 0);

        System.out.println("Presiona INTRO para volver...");
        scanner.nextLine();
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

    void inicializarTablero() {
        for (int n = 0; n < tablero.length; n++) {
            for (int y=0;y<tablero[n].length;y++){
                tablero[n][y]=HOUSE;
            }

        }
    }


    void elegirCasa(){
        System.out.println("Elige fila");
        playerX=scanner.nextInt()-1;scanner.nextLine();
        System.out.println("Elige columna");
        playerY=scanner.nextInt()-1;scanner.nextLine();
        tablero[playerX][playerY]= WAITING;
        System.out.println("Casa elegida reza para que aguante");
        // Pedir al jugador que elija una fila: entre 1 y 5
        // Pedir también que elija una columna: entre 1 y 10

        // Asignar a la casilla elegida el valor WAITING
        // (se deberá restar 1 a los valores introducidos por el usuario)
    }

    void bombardear(){
        scanner.nextLine();
        tablero[playerX][playerY]=HOUSE;

                do{
                    com=0;
                    tablero[random.nextInt(5)][random.nextInt(10)]=BOMB;
                    for (int g = 0;g < tablero.length; g++) {
                        for (int h=0;h<10;h++){
                            com += tablero[g][h];
                        }
                    }
                }while (com<26 );
                // Poner 25 BOMB en 25 casillas diferentes del tablero
        System.out.println("¡¡¡Cayendo Bombas!!!    A CUBIERTO");
            }




    void comprobar(){
        if (tablero[playerX][playerY]==BOMB){
            tablero[playerX][playerY]=DEAD;
        }
        else {
            tablero[playerX][playerY]=SURVIVE;
        }
        // Si en la casilla elegida por el jugador hay una bomba, asignar el valor DEAD
        // si no, asignar a esa casilla el valor SURVIVE
        for (int n = 0;n < tablero.length; n++) {
            for (int y=0;y<10;y++) {
                if (tablero[n][y]==BOMB){
                    tablero[n][y]=FIRE;
                }
            }
        }
        // Recorrer el tablero y asignar el valor FIRE en las casillas que haya una BOMB
    }

    //tablero = { {0,0,0,0,0,0,0}, {0,0,0,0,0,0,0}, {0,0,0,0,0,0,0}}

    void mostrarTablero() {
        for (int n = 0; n < tablero.length; n++) {
            for (int y=0;y<tablero[n].length; y++) {
                System.out.print(symbols[tablero[n][y]]);
            }
            System.out.println();

        }

        System.out.println();
    }



    void calcularBalance(){
        if (tablero[playerX][playerY]==SURVIVE){
            System.out.println("Has ganado!!!");
            money+=2*apuesta;
        }
        else {
            System.out.println("Has Muerto....");
        }
        // Si la casilla elegida por el jugador tiene el valor SURVIVE,
        // mostramos el  mensaje "SOBREVIVISTE!" y le retornamos el doble de los apostado
        // Si no, mostramos el mensaje "HAS MUERTO"
    }
}


