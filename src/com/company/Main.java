package com.company;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        char opcion;

        do {
            System.out.println("GAMES, GAMES, GAMES");
            System.out.println("a Dados\nb Piedra-Papel-Tijera\nc Cartas\nr Ruleta\ng Bomba \n\ns Salir");

            opcion = 't';//scanner.nextLine().charAt(0);
            switch (opcion) {
                case 'a':
                    new Dados().start();
                    break;
                case 'b':
                    new PiedraPapelTijera().start();
                    break;
                case 'c':
                    new Cartas().start();
                    break;
                case 'r' :
                    new Ruleta().start();
                    break;
                case 'g':
                    new Bomba().start();
                    break;
                case 't':
                    new Tragaperras().start();
                default:
                    System.out.println("OPCION INCORRECTA!");
                    break;
            }
        } while (opcion != 's');
    }
}


