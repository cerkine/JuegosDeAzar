package com.company;

import java.util.Random;
import java.util.Scanner;

public class Ruleta {
    Scanner scanner = new Scanner(System.in);

    final int[] rojas = {1,3,5,7,9,12,14,16,18,19,21,23,25,27,30,32,34,36};

    int[] apuestas = new int[12];
    // indices de los TIPOS de apuestas

    final int ROJO = 0;
    final int NEGRO = 1;
    final int PAR = 2;
    final int IMPAR = 3;
    final int FALTA = 4;
    final int PASA = 5;
    final int DOCENA1 = 6;
    final int DOCENA2 = 7;
    final int DOCENA3 = 8;
    final int COLUMNA1 = 9;
    final int COLUMNA2 = 10;
    final int COLUMNA3 = 11;

    int apuestaj;

    int[] apuestasNumeros = new int[37];

    int totalApostado;

    int money = 100;  // balance de dinero

    int bola = 0;
    boolean esRoja = false;

    Random random= new Random();

    void start() {

        System.out.println("RULETA");

        do {

            mostrarRank();
            hacerApuestas();
            if (totalApostado > 0) {
                lanzarBola();
                calcularBalance();
                retirarApuestas();
            }
        } while (totalApostado > 0 && money > 0);


        if (money > 0){
            System.out.println("BYE! YOUR MONEY: " + money);
        } else {
            System.out.println("OUT OF MONEY!");
        }

        System.out.println("Presiona INTRO para volver...");
        scanner.nextLine();
    }

    void mostrarRank() {
        //System.out.println("Partidas ganadas crupier :" + ganadascru);
        //System.out.println("Partidas ganadas jugador :" + ganadasjug);
        totalApostado=0;
        System.out.println("Tu dinero: " + money);
    }
    void hacerApuestas() {

        do {
            System.out.println("Cuanto quieres apostar");

            apuestaj = scanner.nextInt();
            scanner.nextLine();
            if (apuestaj <= money && apuestaj >0) {
                System.out.println("Al numero o especial? (n/e)");
                char apuesta = scanner.nextLine().charAt(0);
                switch (apuesta) {
                    case 'n':

                        int numero;
                        do {
                            System.out.println("A que numero deseas apostar");
                            numero = scanner.nextInt();
                        } while(numero < 0 || numero > 36);

                        apuestasNumeros[numero] = apuestaj;
                        money-=apuestaj;
                        totalApostado+=apuestaj;
                        break;
                    case 'e':
                        System.out.println("Que zona desea? ");
                        String prueba = scanner.nextLine().toUpperCase();

                        switch (prueba) {
                            case "ROJO":
                                apuestas[ROJO] += apuestaj;
                                money-=apuestaj;
                                totalApostado+=apuestaj;
                                break;
                            case "NEGRO":
                                apuestas[NEGRO] += apuestaj;
                                money-=apuestaj;
                                totalApostado+=apuestaj;
                                break;
                            case "PAR":
                                apuestas[PAR] += apuestaj;
                                money-=apuestaj;
                                totalApostado+=apuestaj;
                                break;
                            case "IMPAR":
                                apuestas[IMPAR] += apuestaj;
                                money-=apuestaj;
                                totalApostado+=apuestaj;
                                break;
                            case "FALTA":
                                apuestas[FALTA] += apuestaj;
                                money-=apuestaj;
                                totalApostado+=apuestaj;
                                break;
                            case "PASA":
                                apuestas[PASA] += apuestaj;
                                money-=apuestaj;
                                totalApostado+=apuestaj;
                                break;
                            case "DOCENA1":
                                apuestas[DOCENA1] += apuestaj;
                                money-=apuestaj;
                                totalApostado+=apuestaj;
                                break;
                            case "DOCENA2":
                                apuestas[DOCENA2] += apuestaj;
                                money-=apuestaj;
                                totalApostado+=apuestaj;
                                break;
                            case "DOCENA3":
                                apuestas[DOCENA3] += apuestaj;
                                money-=apuestaj;
                                totalApostado+=apuestaj;
                                break;
                            case "COLUMNA1":
                                apuestas[COLUMNA1] += apuestaj;
                                money-=apuestaj;
                                totalApostado+=apuestaj;
                                break;
                            case "COLUMNA2":
                                apuestas[COLUMNA2] += apuestaj;
                                money-=apuestaj;
                                totalApostado+=apuestaj;
                                break;
                            case "COLUMNA3":
                                apuestas[COLUMNA3] += apuestaj;
                                money-=apuestaj;
                                totalApostado+=apuestaj;
                                break;
                            default:
                                System.out.println("No has introducido correctamene la apuesta");

                        }
                        break;
                    default:
                        System.out.println("No has introducido correctamene la apuesta");

                }
            }
            System.out.println();
            System.out.println();
            System.out.println("Tu dinero es: " + money);
        } while (apuestaj > 0 && money > 0);
    }

    void lanzarBola() {
        bola = random.nextInt(37);
        for (int n =0;n<rojas.length;n++) {

            if (bola == rojas[n]) {
                esRoja = true;
            }
        }
        if (esRoja){
            System.out.println(" La bola ha caido en " + bola + " y es roja ");
            } else if (bola != 0) {
                System.out.println(" La bola ha caido en " + bola + " y es negra ");
            } else {
                System.out.println(" La bola ha caido en 0" + " no hay color");
            }
        }
    // Se hace que la bola 'caiga' en un numero al azar entre 0 y 36
    // Se determina si esRoja
    // Y se muestra
    void calcularBalance(){
        for (int n=0;n<apuestasNumeros.length;n++) {
            if (bola == apuestasNumeros[n])
                switch (bola) {
                    case 0:
                        money += 36 * apuestasNumeros[0];
                        break;
                    default:
                        money += 35 * apuestasNumeros[bola];
                        break;
                }
        }

//        for (int y=1;y<13;y++){
//            if (bola == y) {
//                money += 3 * apuestas[DOCENA1];
//            }
//        }
//
//        for (int y=13;y<25;y++){
//            if (bola ==y){
//                money+= 3*apuestas[DOCENA2];
//            }
//        }
//
//        for (int y=25;y<37;y++){
//            if (bola==y){
//                money += 3*apuestas[DOCENA3];
//            }
//        }

        for (int y=1;y<35;y=y+2){
            if (bola==y){
                money+=3*apuestas[COLUMNA1];
            }
        }

        for (int y=2;y<36;y=y+2){
            if (bola==y){
                money+= 3*apuestas[COLUMNA2];
            }
        }

        for (int y=3;y<37;y=y+2){
            if (bola==y){
                money+= 3*apuestas[COLUMNA3];
            }
        }

        if (esRoja){
            money+=2*apuestas[ROJO];
        }else if(bola!=0){
            money+=apuestas[NEGRO];
        }

        for (int y=1;y<19;y++) {
            if (bola == y) {
                money += 2 * apuestas[FALTA];
            }
        }

        for (int y=19;y<37;y++){
            if (bola==y){
                money+= 2*apuestas[PASA];
            }
        }

        if(bola%2 == 0&& bola!=0){
            money += 2*apuestas[PAR];
        }else{
            money += 2*apuestas[IMPAR];
        }

        if(bola/13 == 0){
            money += 2*apuestas[DOCENA1];
        }else if(bola/13 == 1){
            money += 2*apuestas[DOCENA2];
        }else if(bola/13 == 2){
            money += 2*apuestas[DOCENA3];
        }


        System.out.println("Tu dinero es: " + money);
        // Si la bola es 0, se suma al dinero 36 veces la cantidad apostada al numero cero
        // si no es 0 hacemos las comprobaciones a los otros tipos de apuestas
    }

    void retirarApuestas(){
        for (int n =0;n<apuestas.length;n++){
            apuestas[n]=0;
        }
        for (int n=0; n<apuestasNumeros.length;n++){
            apuestasNumeros[n]=0;
        }
    }
}

